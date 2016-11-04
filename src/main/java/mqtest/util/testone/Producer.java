package mqtest.util.testone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/3
 * Time: 15:19
 * To change this template use File | Settings | File Templates.
 */

public class Producer extends MqBase {

    //now we get the message

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    public void  sendMessage(String message){

        try {
            channel.exchangeDeclare(super.EXCHANGE_NAME, "fanout");
            //start to publist message
            for (int i = 0; i < 10000; i++) {
                channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
            }
           // channel.close();
            //connection.close();
        }catch (Exception e){
            logger.error("get error:{}",e);
        }
    }

    public static void main(String [] args){
        Producer producer = new Producer();
        for (int i = 1; i <= 1000 ; i++){
            String message = "message-" + i + " was to send";
            System.out.println(message);
            producer.sendMessage(message);
        }
    }
}
