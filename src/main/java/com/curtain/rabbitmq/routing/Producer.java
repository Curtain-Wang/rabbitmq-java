package com.curtain.rabbitmq.routing;

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
    public static void main(String[] args){
        
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
        Connection connection = null;
        try {
            connection = connectionFactory.newConnection("生成者");
            //3：创建连接获取通道Channel
            Channel channel = connection.createChannel();
            //5：准备消息内容
            String message = "Hello Curtain!!20210413";
            //6.准备交换机
            String exchangeName = "fanout-exchange";
            //7.定义路由Key
            String routeKey = "";
            //8.指定交换机类型
            String type = "fanout";
            //9：发送消息给队列queue
            channel.basicPublish(exchangeName, routeKey, null, message.getBytes());
            //10：关闭连接通道
            if (channel != null && channel.isOpen()){
                channel.close();
            }
            //11：关闭连接
            if (connection != null && connection.isOpen()){
                connection.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
