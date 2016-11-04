package httpclienttest.testone;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.KeyStore;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/3
 * Time: 20:19
 * To change this template use File | Settings | File Templates.
 */

public class TestOne {

    private static final Logger logger = LoggerFactory.getLogger(TestOne.class);

    public static void main(String [] args){
        try{
            HttpClient client = HttpClients.createDefault();

            HttpGet get = new HttpGet("http://datastream2.server.163.org:8488/mgr/datastream/tags/listActive");
            //HttpGet get = new HttpGet("http://www.baidu.com");

            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                System.out.println(entity.getContentEncoding());
                System.out.println(entity.getContentType());
                System.out.println(entity.getContentEncoding());

                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String message = null ;
                while((message = reader.readLine()) != null){
                    System.out.println(message);
                }

            }


            URI uri = new URIBuilder().setScheme("http").setHost("www.baidu.com").build();
            HttpGet httpGet = new HttpGet(uri);
            System.out.println(httpGet.getURI());
        }catch (Exception e){
            logger.error("get error: {}",e);
        }
    }
}
