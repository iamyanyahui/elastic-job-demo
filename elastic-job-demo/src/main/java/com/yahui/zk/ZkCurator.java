package com.yahui.zk;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.transaction.TransactionOp;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import com.dangdang.ddframe.job.reg.exception.RegExceptionHandler;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2021-06-19
 */
public class ZkCurator {
    private static final String CONNECT_STR = "livestream-yanyahui-01.dev.kwaidc.com:2181";
    private static final String NAMESPACE = "curator-demo";
    private CuratorFramework client;
    private final Map<String, CuratorCache> caches = new ConcurrentHashMap<>();

    public ZkCurator() {
        client = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_STR)
                .connectionTimeoutMs(5000)
                .sessionTimeoutMs(5000)
                .namespace(NAMESPACE)
                .retryPolicy(new ExponentialBackoffRetry(500, 5, 1000))
                .build();
        client.start();
    }

    public String get(final String key) {
        CuratorCache cache = findCuratorCache(key);
        if (null == cache) {
            String value = getDirectly(key);
            addCacheData(key);
            return value;
        }
        Optional<ChildData> resultInCache = cache.get(key);
        if (resultInCache.isPresent()) {
            return null == resultInCache.get().getData() ? null : new String(resultInCache.get().getData(), StandardCharsets.UTF_8);
        }
        return getDirectly(key);
    }

    private CuratorCache findCuratorCache(final String key) {
        for (Entry<String, CuratorCache> entry : caches.entrySet()) {
            if (key.startsWith(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public String getDirectly(final String key) {
        try {
            return new String(client.getData().forPath(key), StandardCharsets.UTF_8);
            //CHECKSTYLE:OFF
        } catch (final Exception ex) {
            //CHECKSTYLE:ON
            RegExceptionHandler.handleException(ex);
            return null;
        }
    }

    public void addCacheData(final String cachePath) {
        CuratorCache cache = CuratorCache.build(client, cachePath);
        try {
            cache.start();
            //CHECKSTYLE:OFF
        } catch (final Exception ex) {
            //CHECKSTYLE:ON
            RegExceptionHandler.handleException(ex);
        }
        caches.put(cachePath + "/", cache);
    }

    public void persist(final String key, final String value) {
        try {
            if (!isExisted(key)) {
                client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(key, value.getBytes(StandardCharsets.UTF_8));
            } else {
                update(key, value);
            }
            //CHECKSTYLE:OFF
        } catch (final Exception ex) {
            //CHECKSTYLE:ON
            System.out.println("exception: " + ex);
        }
    }

    public boolean isExisted(final String key) {
        try {
            return null != client.checkExists().forPath(key);
            //CHECKSTYLE:OFF
        } catch (final Exception ex) {
            //CHECKSTYLE:ON
            RegExceptionHandler.handleException(ex);
            return false;
        }
    }

    public void update(final String key, final String value) {
        try {
            TransactionOp transactionOp = client.transactionOp();
            client.transaction().forOperations(transactionOp.check().forPath(key), transactionOp.setData().forPath(key, value.getBytes(StandardCharsets.UTF_8)));
            //CHECKSTYLE:OFF
        } catch (final Exception ex) {
            //CHECKSTYLE:ON
            RegExceptionHandler.handleException(ex);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ZkCurator curator = new ZkCurator();
        String key = "/first";
        curator.persist(key, "firstValue");

        while (true) {
            String value = curator.get(key);
            System.out.println("getValue:" + value);
            System.out.println("cache:" + curator.caches);

            value = curator.get(key);
            System.out.println("getValueFromCache: " + value);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
