
package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionDB {
    public static Connection ConexionMySql(){
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_agencia_viajes","root","");
            System.out.println("Coexion a DB");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return con;
    }
    
    /*public static void main(String[] args) {
        ConexionMySql();
    }*/
}
 