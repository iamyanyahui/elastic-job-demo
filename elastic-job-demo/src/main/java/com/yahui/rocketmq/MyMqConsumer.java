package com.yahui.rocketmq;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2021-06-20
 */
public class MyMqConsumer {
    private static final String TOPIC = "test-topic-1";
    private static final String CONSUMER_GROUP_1 = "test-topic-consumer-group-1";
    private static final String CONSUMER_GROUP_2 = "test-topic-consumer-group-2";
    private static final String NAMESRV_ADDR = "livestream-yanyahui-01.dev.kwaidc.com:9876";
    private static final ExecutorService EXECUTORS =
            Executors.newFixedThreadPool(10, new ThreadFactoryBuilder().setNameFormat("my-consumer-%d").build());


    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQPushConsumer consumer1 = buildConsumer(CONSUMER_GROUP_1, ConsumeConcurrentlyStatus.CONSUME_SUCCESS);
        DefaultMQPushConsumer consumer2 = buildConsumer(CONSUMER_GROUP_1, ConsumeConcurrentlyStatus.RECONSUME_LATER);

        DefaultMQPushConsumer consumer3 = buildConsumer(CONSUMER_GROUP_2, ConsumeConcurrentlyStatus.CONSUME_SUCCESS);

        EXECUTORS.submit(() -> {
            try {
                consumer1.start();
            } catch (MQClientException e) {
                e.printStackTrace();
            }
        });
        EXECUTORS.submit(() -> {
            try {
                consumer2.start();
            } catch (MQClientException e) {
                e.printStackTrace();
            }
        });
        EXECUTORS.submit(() -> {
            try {
                consumer3.start();
            } catch (MQClientException e) {
                e.printStackTrace();
            }
        });
        TimeUnit.DAYS.sleep(1);
    }

    private static DefaultMQPushConsumer buildConsumer(String group, ConsumeConcurrentlyStatus status) throws MQClientException {
        //定义消费者名称，MQ往消费者推送
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(group);
        //连接rocketMQ的namesrv地址（此次为集群）
        consumer.setNamesrvAddr(NAMESRV_ADDR);
        //从上次的消费进度继续消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);//订阅的主题和标签（*代表所有标签）
        consumer.subscribe(TOPIC, "*");
        //消费者监听
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            for (MessageExt msg : msgs) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    String topic = msg.getTopic();
                    String msgbody = new String(msg.getBody(), "UTF-8");
                    String tag = msg.getTags();
                    System.out.println("topic:" + topic + ", msgbody:" + msgbody + ", tag:" + tag + ", group:" + consumer.getConsumerGroup() + ", "
                            + "instanceName:" + consumer.getInstanceName());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    //MQ发送失败重试机制，1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
                    return status;
                }
            }
            //消息处理成功
            return status;
        });
        return consumer;
    }
}
