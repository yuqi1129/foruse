package mqtest.util.testone;

import com.google.common.base.Preconditions;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/3
 * Time: 15:14
 * To change this template use File | Settings | File Templates.
 */

public class MqBase {

    private static final Logger logger = LoggerFactory.getLogger(MqBase.class);
    protected static final String EXCHANGE_NAME = "logs" ;
    protected Channel channel ;
    protected Connection connection;
    ConnectionFactory connectionFactory;

    public MqBase(){
        try {
            connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("app-68.photo.163.org");
            connectionFactory.setVirtualHost("/");
            this.connection = connectionFactory.newConnection();
            this.channel = connection.createChannel();
        }catch (Exception e){
            logger.error("get error:{}",e);
        }
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
