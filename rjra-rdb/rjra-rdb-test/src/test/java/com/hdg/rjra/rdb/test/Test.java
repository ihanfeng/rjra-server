package com.hdg.rjra.rdb.test;

import com.oracle.jrockit.jfr.Producer;
import sun.plugin2.message.Message;

import java.util.Properties;

/**
 * Created by Rock on 2015/1/29 0029.
 */
public class Test {

    @org.junit.Test
    private void test(){
            Properties properties = new Properties();
            properties.put(PropertyKeyConst.ProducerId, "PID_001");
            Producer producer = ONSFactory.createProducer(properties);
            // 在发送消息前，必须调用start方法来启动Producer，只需调用一次即可。
            producer.start();
            Message msg = new Message( //
                    "TopicTestONS", // Message Topic
                    "TagA", // Message Tag
                    "Hello ONS".getBytes()); // Message Body
            // 发送消息，只要不抛异常就是成功
            SendResult sendResult = producer.send(msg);
            System.out.println(sendResult);

            // 在应用退出前，销毁Producer对象<br>
            // 注意：如果不销毁也没有问题
            producer.shutdown();
    }
}
