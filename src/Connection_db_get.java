import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Connection_db_get {
	

	 Connection connection;
	 User u;
	 //Passing userId to add to SQL query, db - passing db object for code to understand where to connect
	public User connect(int userId, JPanel contentPane,Database db){
		
	    try {
	    	 connection = null;
	  // Load the Oracle JDBC driver

	  String driverName = "oracle.jdbc.driver.OracleDriver";

	  Class.forName(driverName);

	  // Create a connection to the database

	  String serverName = db.ip;

	  String serverPort = "1521";

	  String sid = db.sid;

	  String url = "jdbc:oracle:thin:@" + serverName + ":" + serverPort + ":" + sid;

	  String username = "simp_dstrods";

	  String password = "diamond1987";

	  connection = DriverManager.getConnection(url, username, password);
	  //SYS_USER_INFORMATION
	  String sql = "SELECT * FROM bw3.SYS_USER_INFORMATION WHERE USERID = '"+userId+"'";
	  Statement stmt = connection.createStatement();
	  ResultSet rows = stmt.executeQuery(sql);
	  rows.next();
	  System.out.println(rows.getString("USERID"));
	  
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
	  
	  
//	  Date pass = rows.getDate("PASSWORDDATE");
//	  Date logon = rows.getDate("LASTLOGON"); 
//	  Date logoff = rows.getDate("LASTLOGOFF"); 
	  
	  //manipulating date, this one could cause some issues in the future
	  String pass = rows.getString("PASSWORDDATE");
	  String logon = rows.getString("LASTLOGON"); 
	  String logoff = rows.getString("LASTLOGOFF"); 
	  
	  pass = pass.substring(0,pass.length()-2);
	  logon = logon.substring(0,logon.length()-2);
	  logoff = logoff.substring(0,logoff.length()-2);
	  
	  //System.out.println(sdf.format(pass));
	  //creating user object from query result
	  u = new User(rows.getString("USERID"),rows.getString("USERNAME"),rows.getString("USERSHORTNAME"),rows.getString("BANKCODE"),rows.getString("BRANCHCODE"),rows.getString("DEPARTMENTCODE"),
			  rows.getString("LANGUAGE"),rows.getString("ADMINISTRATOR"),rows.getString("DEVELOPER"),rows.getString("INTERNALRS2"),rows.getString("STATUS"),
			  pass,logon,logoff,rows.getString("ACCESSGROUPS"),rows.getString("VALIDINSTITUTIONS"),rows.getString("DEFAULTINSTITUTION"),
			  rows.getString("SHOWTIP"),rows.getString("AUDITTRAIL"),rows.getString("PASS_WORD"),rows.getString("MODULEACCESS"),rows.getString("WRONGPASSWORDCOUNT")
			  );
	  
		  //String param1 = rows.getString("record_date");
		  //System.out.println(param1);
	  
	  System.out.println("Successfully Connected to the database!");
	  

	    } catch (ClassNotFoundException e) {

	  System.out.println("Could not find the database driver " + e.getMessage());
	    } catch (SQLException e) {
	    	JOptionPane.showMessageDialog(contentPane,"No such UserId found!");
	  //System.out.println("Could not connect to the database " + e.getMessage());
	    }
		return u;
	    
	}
	
	// Returning Database List here
	public void databaseList(ArrayList<Database> dbList){
	    try {
	    	 connection = null;
	  // Load the Oracle JDBC driver

	  String driverName = "oracle.jdbc.driver.OracleDriver";

	  Class.forName(driverName);

	  // Create a connection to the database

	  String serverName = "172.21.64.72";
	  String serverPort = "1521";
	  String sid = "SYSIMP";
	  String url = "jdbc:oracle:thin:@" + serverName + ":" + serverPort + ":" + sid;
	  String username = "sysimp_util";
	  String password = "sysimp";

	  connection = DriverManager.getConnection(url, username, password);
	  //SYS_USER_INFORMATION
	  String sql = "select * from CHT_INDEXING_environments";
	  Statement stmt = connection.createStatement();
	  
	  //System.out.println(rows.getString("USERID"));
	  
	  try {
		  ResultSet rows = stmt.executeQuery(sql);

		    while (rows.next()) {
		    	dbList.add(new Database(rows.getString("NAME"),rows.getString("IP_ADDRESS")));
		    }
		} finally {
		    stmt.close();
		}
	  
	  
	    } catch (ClassNotFoundException e) {

	  System.out.println("Could not find the database driver " + e.getMessage());
	    } catch (SQLException e) {
	    	
	    	JOptionPane.showMessageDialog(null,"No such UserId found!");
	  //System.out.println("Could not connect to the database " + e.getMessage());
	    }

		
	}
	
}
