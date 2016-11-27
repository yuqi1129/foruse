package mqtest.util.testtwo;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/3
 * Time: 19:17
 * To change this template use File | Settings | File Templates.
 */

public class ReceiveOne {

    private static final Logger logger = LoggerFactory.getLogger(ReceiveOne.class);
    private static final String EXCHANGE_NAME = "topic_logs";


    public static void main(String [] args){


        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("app-68.photo.163.org");

            Connection connection = connectionFactory.newConnection();

            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            String queueName = channel.queueDeclare().getQueue();

            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File("final.txt"))));

            while((queueName = bf.readLine()) != null){
                channel.queueDelete(queueName);
                logger.info("delete:" + queueName);
            }

            /*

            String[] routingKeys = new String[]{"*.orange.*"};

            for (String string : routingKeys) {
                channel.queueBind(queueName, EXCHANGE_NAME, string);
            }

            Consumer consumer = new DefaultConsumer(channel) {

                public void handleDelivery(String consumerTag, Envelope e, AMQP.BasicProperties properties, byte[] body) {
                    try {
                        String message = new String(body, "UTF-8");
                        System.out.println("we received meesage:" + message);
                    }catch (Exception e2){
                        logger.error("get error: {}",e2);
                    }
                }

            };

            channel.basicConsume(queueName, true, consumer);
            */
        }catch (Exception e1){
            logger.error("get error: {}",e1);
        }

    }
}
