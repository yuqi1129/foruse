package other;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/1/5
 * Time: 16:34
 * To change this template use File | Settings | File Templates.
 */

public class FileReaderTest {


    public static void main(String [] args){

        long position = 0L ;

        try{
            File file = new File("re.txt");
            if(!file.exists()){
                file.createNewFile();
            }

            RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
            randomAccessFile.seek(position);
            String tmp = null ;
            while (true){
                if ((tmp = randomAccessFile.readLine()) != null){
                    System.out.println(tmp);
                    randomAccessFile.seek(position + tmp.length());
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
