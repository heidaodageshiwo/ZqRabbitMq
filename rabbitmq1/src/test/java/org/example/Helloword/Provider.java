package org.example.Helloword;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.MessageProperties;
import java.io.IOException;
import org.example.RabbitMQUtils;
import org.junit.Test;

/**
 * java类简单作用描述
 *
 * @ProjectName: rabbitmq
 * @Package: org.example.Helloword
 * @ClassName: Provider
 * @Description: java类作用描述
 * @Author: zhangq
 * @CreateDate: 2020-12-10 17:21
 * @UpdateUser: zhangq
 * @UpdateDate: 2020-12-10 17:21
 * @UpdateRemark: The modified content
 * @Version: 1.0 *
 */
public class Provider {

  @Test
  public void sendmessage() throws IOException {
    //获取连接对象
    Connection connection= RabbitMQUtils.getConnection();
    //获取连接中通道
    Channel channel = connection.createChannel();
    //通道绑定对应消息队列
    //参数1 队列名称如果队列不存在则自动创建
    //参数2 用来定义队列特性是否要持久化 true持久化
    //参数3 是否是独占队列 true 独占
    //参数4 消费者消费完消息是否自动删除队列 true自动删除
    //参数5 额外参数
    channel.queueDeclare("hello",false,false,false,null);
    // channel.queueDeclare("hello",true,false,false,null);
    //发布消息
    //参数1 交换机名称 参数2队列名称 参数3参数 参数4 消息的具体内容
    channel.basicPublish("","hello",null,"张强测试".getBytes());
    //   channel.basicPublish("","hello", MessageProperties.PERSISTENT_TEXT_PLAIN,"张强测试".getBytes());

    RabbitMQUtils.closeConnectionFactory(channel, connection);


  }


  public static void main(String[] args) throws IOException {
    Connection connection = RabbitMQUtils.getConnection();
    Channel channel = connection.createChannel();
    channel.queueDeclare("hello",false,false,false,null);
    channel.basicConsume("hello",true, new DefaultConsumer(channel){
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
          byte[] body) throws IOException {
//        super.handleDelivery(consumerTag, envelope, properties, body);
        System.out.println("=========="+new String(body));
      }
    });




  }


}
