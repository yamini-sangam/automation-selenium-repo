package demoautomation1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_Test {
public static void main(String[] args) throws Exception {


	try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/automationtesting", "root", "root")) {
		 String sql = "select * from userregistration";
		 try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			    ResultSet res = stmt.executeQuery(sql);
			    while (res.next())
			    {
			    	System.out.print(res.getString(1));
			    	System.out.print(" " + res.getString(2));
			    	System.out.print(" " + res.getString(3));
			    	System.out.print(" " + res.getString(4));
			    	System.out.print(" " + res.getString(5));
			    	System.out.println(" " + res.getString(6));
			    }
		 }
	}
	catch (SQLException e) {
        e.printStackTrace();
    }
}
}
   

  



