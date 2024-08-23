package hrms.dbinfo;

import java.sql.*;

public class DBConnection {
	
	private static Connection con;
	
	public static Connection openConnection()
	{
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver"); // factory method -> to create the object of the class
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrms_db", "root","root"); // connection String
			
			
			// jdbc:mysql -> sub protocol
			// ://localhost: -> name or IP address of the machine where database is being installed
			//  3306 ->> port no. -> logical number where database will be listen
			//  hrms_db -> database name
			// root -> database user name
			// root -> database user password
			
		}
		
		catch ( SQLException | ClassNotFoundException cse)
		{
			
			cse.printStackTrace();
			
		}
		
		return con;
		
	}
	
//	public static void main(String[] args) {
//		
//		Connection con = openConnection();
//		System.out.println(con);
//		
//	}

}
