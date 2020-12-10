package org.example.Fanout;

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
 * @Package: org.example.Fanout
 * @ClassName: Provider2
 * @Description: java类作用描述
 * @Author: zhangq
 * @CreateDate: 2020-12-10 18:33
 * @UpdateUser: zhangq
 * @UpdateDate: 2020-12-10 18:33
 * @UpdateRemark: The modified content
 * @Version: 1.0 *
 */
public class Provider2 {

  @Test
  public void sendmessage() throws IOException {
    //获取连接对象
    Connection connection = RabbitMQUtils.getConnection();
    //获取连接中通道
    Channel channel = connection.createChannel();
    //参数1 交换机名称  参数2 类型
    channel.exchangeDeclare("logs", "fanout");
    //发布消息
    channel.basicPublish("logs", "", MessageProperties.PERSISTENT_TEXT_PLAIN,
        ("张强测试fanout:").getBytes());

    RabbitMQUtils.closeConnectionFactory(channel, connection);


  }

}

