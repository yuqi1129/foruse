package mqtest.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;



/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/2
 * Time: 21:30
 * To change this template use File | Settings | File Templates.
 */

public class EndPoint {

    private static Logger logger = LoggerFactory.getLogger(EndPoint.class);

    protected  Channel channel;
    protected  Connection connection;
    protected  String endPointName;


    public EndPoint(String endPointName){
        this.endPointName = endPointName;

        try {
            ConnectionFactory factory = new ConnectionFactory();
            System.out.println(factory);
            factory.setHost("app-68.photo.163.org");
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(endPointName, false, false, false, null);

        }catch (Exception e){
            logger.error("get error:" + e);
        }
    }

    public void close() throws IOException{
        this.channel.close();
        this.connection.close();
    }

}
