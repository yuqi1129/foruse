package file;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

/**
 * Author yuqi
 * Time 3/4/18 16:32
 **/
public class ReadChangeFile {
    public static void main(String[] args) {
        try {
            boolean running = true;
            BufferedInputStream reader = new BufferedInputStream(new FileInputStream("/Users/yuqi/project/foruse/src/main/resources/out.txt"));

            while (running) {
                if (reader.available() > 0) {
                    System.out.print((char) reader.read());
                } else {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        running = false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
