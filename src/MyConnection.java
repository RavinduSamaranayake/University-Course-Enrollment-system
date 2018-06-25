
import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
    public static Connection getconnection(){
      Connection con = null;
      try{
      Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mysql://localhost/crsdb","root","");
      System.out.print("Connected......................");
      }
      catch(Exception ex){
          System.out.println(ex.getMessage());
      }
        return con;
    }
}

