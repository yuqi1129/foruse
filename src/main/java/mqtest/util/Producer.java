package mqtest.util;


import org.apache.commons.lang.SerializationUtils;
import org.slf4j.Logger;


import java.io.IOException;
import java.io.Serializable;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/2
 * Time: 21:35
 * To change this template use File | Settings | File Templates.
 */

public class Producer extends EndPoint {

    private static Logger logger = org.slf4j.LoggerFactory.getLogger(Producer.class);

    public Producer(String endPointName) throws IOException{
        super(endPointName);
    }

    public void sendMessage(byte [] object) throws IOException {
        channel.basicPublish("",endPointName,null, SerializationUtils.serialize(object));
    }
}
