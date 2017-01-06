package hadooprelative.mahout.one;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.Map;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/1/2
 * Time: 19:57
 * To change this template use File | Settings | File Templates.
 */

public class HandlerData {

    public static void main(String [] args){

        try{
            BufferedWriter bufferWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("src/main/java/hadooprelative/mahout/data/wiki.dat"))));
            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/main/java/hadooprelative/mahout/data/data.txt"))));

            String tmp = null;
            Map<Long,List<Long>> userList = Maps.newHashMap();
            while ((tmp = bufferReader.readLine()) != null){
                String [] data = tmp.split(",");
                if (data.length != 3){
                    continue;
                }

                Long userId = Long.valueOf(data[0].trim());
                Long itemId = Long.valueOf(data[1].trim());

                if (userList.get(userId) == null){
                    List<Long> list = Lists.newArrayList();
                    list.add(itemId);
                    userList.put(userId,list);
                }else{
                    List<Long> list = userList.get(userId);
                    list.add(itemId);
                }
            }

            //now start to store to file

            for(Map.Entry<Long,List<Long>> entry : userList.entrySet()){

                StringBuilder builder = new StringBuilder("");
                builder.append(entry.getKey());
                builder.append(": ");

                for(Long value : entry.getValue()){
                    builder.append(value);
                    builder.append(" ");
                }
                builder.append("\n");

                bufferWriter.write(builder.toString());
                bufferWriter.flush();

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
