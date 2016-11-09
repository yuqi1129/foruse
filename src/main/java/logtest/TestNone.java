package logtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/9
 * Time: 10:09
 * To change this template use File | Settings | File Templates.
 */

public class TestNone {
    private static final Logger errorLog = LoggerFactory.getLogger("myerror");
    private static final Logger infoLog = LoggerFactory.getLogger("myinfo");
    private static final Logger LOGGER = LoggerFactory.getLogger(TestNone.class);

    public static void main(String [] args){
        //
        errorLog.error("we get error");
        infoLog.info("we get a info,haha ");
        LOGGER.info("get info");
        LOGGER.error("get error");
        LOGGER.info("{}","haha");

    }


}
