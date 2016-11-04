package mqtest.util.testone;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/3
 * Time: 15:25
 * To change this template use File | Settings | File Templates.
 */

public class ConsumerTest extends MqBase {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerTest.class);
    public void consume(){
        try {
            channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName,EXCHANGE_NAME,"");

            Consumer consumer = new DefaultConsumer(channel){

                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,byte [] body){
                    String message = null ;
                    try {
                       message = new String(body, "UTF-8");
                   }catch (Exception e){
                       logger.error("get error:{}",e);
                   }
                    System.out.println("get Message:" +message);
                    //channel.basicAck();

                }
            };

            channel.basicConsume(queueName,true,consumer);



        }catch (Exception e){
            logger.error("get error: {}",e);
        }
    }

    public static void main(String [] args){
        ConsumerTest consumerTest = new ConsumerTest();

        consumerTest.consume();
    }

}
