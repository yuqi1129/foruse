package mqtest.util.testone;

import com.google.common.collect.Lists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
            //channel.exchangeDeclare(super.EXCHANGE_NAME, "fanout");
            channel.queueDeclare("test7", true, false, false, null);
            //start to publist message
            for (int i = 0; i < 1; i++) {
                //channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
                channel.basicPublish("", "test7", null, message.getBytes());
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
            //String message = "message-" + i + " was to send";
            String message = i + "," + "hello" + "," + i * i;
            System.out.println(message);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            producer.sendMessage(message);
        }

    }
}
