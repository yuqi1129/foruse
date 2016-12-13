package protostuff;

import com.google.common.collect.Lists;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.Pipe;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import org.apache.commons.lang.time.StopWatch;
import org.apache.hadoop.hdfs.web.resources.PermissionParam;
import org.apache.hadoop.yarn.webapp.hamlet.HamletSpec;

import java.util.List;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/12/6
 * Time: 21:20
 * To change this template use File | Settings | File Templates.
 */

public class TestOne {

    private static class People{
        private String name ;
        private int number;

        public People(String name, int number) {
            this.name = name;
            this.number = number;
        }

        public People() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }

    public static List<byte []> serrializePeople(List<People> peoples){
        if (peoples == null  || peoples.size() == 0)
            return null ;


        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<byte []> bytes = Lists.newArrayList() ;

        Schema<People> schema = RuntimeSchema.getSchema(People.class);
        LinkedBuffer buffer = LinkedBuffer.allocate(4096);

        byte [] protobuff = null ;
        for(People p :peoples){
            try{
                protobuff = ProtostuffIOUtil.toByteArray(p,schema,buffer);
                bytes.add(protobuff);
            }finally {
                buffer.clear();
            }
        }

        System.out.println(stopWatch.getTime());

        return bytes;
    }


    public static List<People> deserializePeople(List<byte[]> plist){

        if (plist == null || plist.size() == 0)
            return null ;

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Schema<People> schema = RuntimeSchema.getSchema(People.class);
        List<People> peoples = Lists.newArrayList();

        for(byte[] b: plist){
            People p = new People();
            ProtobufIOUtil.mergeFrom(b,p,schema);
            peoples.add(p);
        }

        System.out.println(stopWatch.getTime());

        return peoples;

    }

    public static void main(String [] args){

        List<People> peoples = Lists.newArrayList();
        for (int i = 0 ; i < 1000000 ;i ++){
            peoples.add(new People(new Integer(i).toString(),i+1));
        }

        List<byte [] > bytes = serrializePeople(peoples);
        System.out.println(bytes.get(0).length);

        List<People> result = deserializePeople(bytes);

    }

}
