package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * java类简单作用描述
 *
 * @ProjectName: rabbitmq
 * @Package: org.example
 * @ClassName: RabbitMQUtils
 * @Description: java类作用描述
 * @Author: zhangq
 * @CreateDate: 2020-12-10 17:12
 * @UpdateUser: zhangq
 * @UpdateDate: 2020-12-10 17:12
 * @UpdateRemark: The modified content
 * @Version: 1.0 *
 */
public class RabbitMQUtils {

  private static ConnectionFactory connectionFactory;

  static {
    connectionFactory = new ConnectionFactory();
    connectionFactory.setHost("192.168.56.200");
    connectionFactory.setPort(5672);
    connectionFactory.setVirtualHost("/ems");
    connectionFactory.setUsername("ems");
    connectionFactory.setPassword("123");
  }

  public static Connection getConnection() {
    try {
      return connectionFactory.newConnection();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TimeoutException e) {
      e.printStackTrace();
    }
    return null;
  }
  //关闭连接
  public static void closeConnectionFactory(Channel channel,Connection connection){
    if(channel!=null){
      try {
        channel.close();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (TimeoutException e) {
        e.printStackTrace();
      }
    }
    if(connection!=null){
      try {
        connection.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
