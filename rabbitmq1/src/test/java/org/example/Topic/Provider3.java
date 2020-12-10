package org.example.Topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import java.io.IOException;
import org.example.RabbitMQUtils;
import org.junit.Test;

/**
 * java类简单作用描述
 *
 * @ProjectName: rabbitmq
 * @Package: org.example.Direct
 * @ClassName: Provider3
 * @Description: java类作用描述
 * @Author: zhangq
 * @CreateDate: 2020-12-10 18:52
 * @UpdateUser: zhangq
 * @UpdateDate: 2020-12-10 18:52
 * @UpdateRemark: The modified content
 * @Version: 1.0 *
 */
public class Provider3 {

  @Test
  public void sendmessage() throws IOException {
    //获取连接对象
    Connection connection = RabbitMQUtils.getConnection();
    //获取连接中通道
    Channel channel = connection.createChannel();
    //参数1 交换机名称  参数2 类型
    channel.exchangeDeclare("topics", "topic");
    String routingkey="user.save";
//    String routingkey="user.save.delete";
//    String routingkey="user.savesa";
    //发布消息
    channel.basicPublish("topics", routingkey, MessageProperties.PERSISTENT_TEXT_PLAIN,
        ("张强测试topics:").getBytes());

    RabbitMQUtils.closeConnectionFactory(channel, connection);


  }

}
