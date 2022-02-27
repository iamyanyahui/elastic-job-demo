package com.yahui;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2021-12-13
 */
public interface RocketMqConstant {
    String NAME_SRV_ADDR = "livestream-yanyahui-01.dev.kwaidc.com:9876";
    String TOPIC = "test-topic-3";
    String PRODUCER_GROUP = "p-" + TOPIC;
    String CONSUMER_GROUP = "c-" + TOPIC;

}
