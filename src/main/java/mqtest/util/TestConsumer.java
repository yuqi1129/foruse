package mqtest.util;

import com.rabbitmq.client.AMQP;

import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

import org.apache.commons.lang.SerializationUtils;
import org.apache.kafka.common.network.SslChannelBuilder;
import org.apache.zookeeper.server.util.SerializeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/2
 * Time: 21:39
 * To change this template use File | Settings | File Templates.
 */

public class TestConsumer extends EndPoint implements Runnable,Consumer{

    private static Logger logger = LoggerFactory.getLogger(TestConsumer.class);

    public TestConsumer(String endPointName)throws IOException{
        super(endPointName);
    }


    public void run(){
        try{
            channel.basicConsume(endPointName,true,this);
        }catch (IOException e){
            logger.error("get error: {}", e);
        }
    }

    public void handleConsumeOk(String consumerTag){
        System.out.println("Consumer " + consumerTag + " registered");
    }

    public void handleDelivery(String consumer, Envelope env, AMQP.BasicProperties properties, byte [] body) throws IOException{
        try {
            //Map map = (HashMap) SerializationUtils.deserialize(body);
            //System.out.println("Message Number " + map.get("message number") + "received");
            System.out.println("Message number " + new String(body,"UTF-8") + " received");
        }catch (Exception e){
            logger.error("catch error: {}",e);
        }
    }

    public void handleCancel(String consumeTag){}
    public void handleCancelOk(String consumeTag){}
    public void handleRecoverOk(String consumerTag){}
    public void handleShutdownSignal(String consumeTag,ShutdownSignalException e){}
}
