package com.yahui;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.common.RemotingHelper;


public class Producer {

    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer(RocketMqConstant.PRODUCER_GROUP);
        producer.setNamesrvAddr(RocketMqConstant.NAME_SRV_ADDR);

        /*
         * Launch the instance.
         */
        producer.start();

        for (int i = 0; ; i++) {
            try {
                Message msg = new Message(RocketMqConstant.TOPIC, ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = producer.send(msg);
                System.out.printf("%s%n", sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
    }
}