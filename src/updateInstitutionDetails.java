import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class updateInstitutionDetails {
	 Connection connection;
	 User u;
	 //sql is insert statement to be excecuted and passing db object to select specific database
	public void connect(JPanel contentPane,Database db,String sql){
		
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
	  Statement stmt = connection.createStatement();
	  
	  //excecuting sql update
	  stmt.executeUpdate(sql);

		  //String param1 = rows.getString("record_date");
		  //System.out.println(param1);
	  
	  System.out.println("Successfully Connected to the database!");
	  

	    } catch (ClassNotFoundException e) {

	  System.out.println("Could not find the database driver " + e.getMessage());
	    } catch (SQLException e) {
	    	//JOptionPane.showMessageDialog(contentPane,"No such UserId found!");
	    	JOptionPane.showMessageDialog(contentPane, "ERROR for "+ db.sid +": "+ e.getMessage());
	  System.out.println("Could not connect to the database " + e.getMessage());
	    }
		
	    
	}
}

