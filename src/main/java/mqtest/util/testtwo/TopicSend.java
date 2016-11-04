package mqtest.util.testtwo;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/3
 * Time: 19:33
 * To change this template use File | Settings | File Templates.
 */

public class TopicSend {

    private static final Logger logger = LoggerFactory.getLogger(TopicSend.class);
    private static final String EXCHANGE_NAME = "topic_logs" ;

    public static void main(String [] args){

        Connection connection = null;
        Channel channel = null;

        try{
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("app-68.photo.163.org");

            connection = connectionFactory.newConnection();
            channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME,"topic") ;

            String [] routingKeys = new String [] { "quick.orange.rabbit",
                                                    "lazy.orange.elephant",
                                                    "quick.orange.fox",
                                                    "quick.brown.fox",
                                                    "quick.orange.male.rabbit",
                                                    "lazy.orange.male.rabbit"};


            for (String string : routingKeys){
                String messgae = "From " + string + " routingKeys' message!";
                channel.basicPublish(EXCHANGE_NAME,string,null,messgae.getBytes());
                System.out.println("send message:" + messgae);
            }
        }catch (Exception e){
            logger.error("get error: {}",e);

        }finally {
            if (connection != null){
                try{
                    connection.close();
                }catch (Exception e1){
                    logger.error("get error: {}" ,e1);
                }
            }
        }
    }
}
