package com.curtain.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ：Curtain
 * @date ：Created in 2021/4/7 19:35
 * @description：TODO
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        
        //所有中间件技术都是基于TCP/IP协议基础上构建新型的协议规范，只不过rabbitmq遵循的是amqp
        //ip port
        //1：创建连接工程
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.99.73.234");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");
        //2：创建连接Connection
        Connection connection = connectionFactory.newConnection("生成者");
        //3：创建连接获取通道Channel 
        Channel channel = connection.createChannel();
        //4：创建通道创建交换机、声明队列、绑定关系、路由Key、发送消息和接收消息
        //5：准备消息内容
        //6：发送消息给队列queue
        //7：关闭连接
        //8：关闭通道
    }
}
