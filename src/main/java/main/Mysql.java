package main;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/10/23
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */

public class Mysql {
    public static void main(String [] args){
        try{
            Connection con = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/it","root","123456");
            System.out.println("yes");
        }catch (Exception e){
            System.out.println("Mysql error: " + e.getMessage());
        }
    }

}
