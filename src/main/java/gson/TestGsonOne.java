package gson;

import com.google.gson.Gson;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/9/27
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 */

public class TestGsonOne {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Type stringObjectMap = new TypeToken<Map<String, Object>>() {
        }.getType();

        //String jsonString = "{'a':1,{'a':1,'b':2}:2}";
        //String jsonString = "{'a':1}";
        //String jsonString = "{{a:1,b:2}:xxx}";
        String jsonString = "{\"hehe\":0,\"data\":[{\"id\":8,\"clusterName\":\"HBase ETL 0.4中\",\"product\":\"datastream\",\"akkaConfig\":\"\",\"akkaPath\":\"akka.tcp://SlothServer@localhost:2553/user/JobActor\",\"clusterUrl\":\"sloth13.hz.163.org\",\"clusterPort\":\"48592\",\"clusterWebUi\":\"http://sloth3.hz.163.org:8088/proxy/application_1496999663723_0202/\"},{\"id\":10,\"clusterName\":\"yuqi_test\",\"product\":\"datastream\",\"akkaConfig\":\" \",\"akkaPath\":\"akka.tcp://SlothServer@localhost:2553/user/JobActor\",\"clusterUrl\":\"sloth12.hz.163.org\",\"clusterPort\":\"43284\",\"clusterWebUi\":\"http://sloth3.hz.163.org:8088/proxy/application_1496999663723_0201/\"},{\"id\":12,\"clusterName\":\"failover\",\"product\":\"datastream\",\"akkaConfig\":\"\",\"akkaPath\":\"akka.tcp://SlothServer@sloth1.hz.163.org:2553/user/JobActor\",\"clusterUrl\":\"sloth13.hz.163.org\",\"clusterPort\":\"50979\",\"clusterWebUi\":\"http://sloth1.hz.163.org:8088/proxy/application_1505703415185_0002/\"}],\"msg\":\"success\"}";
        Map<String, Object> json = gson.fromJson(jsonString, stringObjectMap);
        for (Map.Entry<String, Object> entry : json.entrySet()) {
            System.out.println(entry.getKey() + " --> " + entry.getValue());
        }

    }
}
