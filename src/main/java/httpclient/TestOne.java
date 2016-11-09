package httpclient;

import com.google.common.collect.Lists;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EncodingUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/8
 * Time: 18:50
 * To change this template use File | Settings | File Templates.
 */

public class TestOne {

    private static final Logger logger = LoggerFactory.getLogger(TestOne.class);
    public static void main(String [] args){

        try{

            CloseableHttpClient httpClient = HttpClients.createDefault();

            HttpGet get = new HttpGet("http://www.beautylegmm.com");

            CloseableHttpResponse response = httpClient.execute(get);

            /*
            HttpResponse response1 = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK,"OK");
            System.out.println(response.getProtocolVersion());
            System.out.println(response.getStatusLine().getStatusCode());
            System.out.println(response.getStatusLine().getReasonPhrase());
            System.out.println(response.getStatusLine().toString());
            */

            HttpEntity entity = response.getEntity();
            if (entity != null){
                /**
                 *  如果不用这一个条件的话，会直接报错
                 */
                entity = new BufferedHttpEntity(entity);
                long len = entity.getContentLength();
                //System.out.println(EncodingUtils.toString(entity));

                System.out.println(len);
                byte [] b = null ;
                if (len > 0) {
                    b = new byte[(int) len];
                }
                entity.getContent().read(b,0,(int)len);
                System.out.println(new String(b,"UTF-8"));

            }

            List<NameValuePair> list = Lists.newArrayList();
            list.add(new BasicNameValuePair("tagId","2225"));
            list.add(new BasicNameValuePair("rateLimit","100000"));

            UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(list, Consts.UTF_8);

            HttpPost httpPost = new HttpPost("http://localhost:8080/mgr/datastream/tags/fc");
            httpPost.setEntity(encodedFormEntity);
            response = httpClient.execute(httpPost);

            entity = response.getEntity() ;

            if (entity != null){
                entity = new BufferedHttpEntity(entity);
                long len = entity.getContentLength();
                //System.out.println(EncodingUtils.toString(entity));
                byte [] b = null ;
                if (len > 0) {
                  b =  new byte[(int) len];
                }
                entity.getContent().read(b,0,(int)len);
                System.out.println(new String(b,"UTF-8"));

            }



        }catch(Exception e){
            logger.error("get errror: {}",e);
        }
    }
}
