package org.example.Direct;

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
public class consum11 {

  public static void main(String[] args) throws IOException {
    Connection connection = RabbitMQUtils.getConnection();
    Channel channel = connection.createChannel();
    //参数1 交换机名称  参数2 类型
    channel.exchangeDeclare("logs_direct", "direct");
  //临时队列名称
    String queneName=channel.queueDeclare().getQueue();
    //基于route 绑定交换机和队列
    channel.queueBind(queneName,"logs_direct","error");

    //autoAck 置位 false
    channel.basicConsume(queneName, true, new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
          byte[] body) throws IOException {

        System.out.println("消费者-1：" + new String(body));

      }
    });
  }
}
