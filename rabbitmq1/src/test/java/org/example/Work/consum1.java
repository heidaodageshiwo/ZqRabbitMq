package org.example.Work;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import org.example.RabbitMQUtils;

/**
 * java类简单作用描述
 *
 * @ProjectName: rabbitmq
 * @Package: org.example.Work
 * @ClassName: consum1
 * @Description: java类作用描述
 * @Author: zhangq
 * @CreateDate: 2020-12-10 18:08
 * @UpdateUser: zhangq
 * @UpdateDate: 2020-12-10 18:08
 * @UpdateRemark: The modified content
 * @Version: 1.0 *
 */
public class consum1 {

  public static void main(String[] args) throws IOException {
    Connection connection = RabbitMQUtils.getConnection();
    Channel channel = connection.createChannel();
    channel.basicQos(1);//每一次只能消费一个消息
    channel.queueDeclare("work", true, false, false, null);
    //autoAck 置位 false
    channel.basicConsume("work", false, new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
          byte[] body) throws IOException {
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("消费者-1：" + new String(body));
        //手动确认  false每次确认一个
        channel.basicAck(envelope.getDeliveryTag(),false);
      }
    });
  }
}
