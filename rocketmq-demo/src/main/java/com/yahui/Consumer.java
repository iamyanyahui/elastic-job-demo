package com.yahui;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2021-12-13
 */
public class Consumer {


    public static void startConsumer() {
        try {
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(RocketMqConstant.CONSUMER_GROUP);
            consumer.setNamesrvAddr(RocketMqConstant.NAME_SRV_ADDR);
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
            consumer.subscribe(RocketMqConstant.TOPIC, "*");
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
