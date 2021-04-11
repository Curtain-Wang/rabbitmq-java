package com.curtain.rabbitmq.simple;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ：Curtain
 * @date ：Created in 2021/4/7 19:36
 * @description：TODO
 */
public class Consumer {
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
        Connection connection = null;
        Channel channel = null;
        try {
            connection = connectionFactory.newConnection("消费者");
            //3：创建连接获取通道Channel
            channel = connection.createChannel();
            channel.basicConsume("queue1", true, new DeliverCallback() {
                        public void handle(String consumerTag, Delivery message) throws IOException {
                            System.out.println("收到的消息是：" + new String(message.getBody(), "UTF-8"));
                        }
                    }, new CancelCallback() {
                        public void handle(String consumerTag) throws IOException {
                            System.out.println("接收消息失败了");
                        }
                    }
            );
            System.out.println("开始接收消息");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            if (channel != null && channel.isOpen()){
                channel.close();
            }
            //8：关闭连接
            if (connection != null && connection.isOpen()){
                connection.close();
            }
        }

    }
}
