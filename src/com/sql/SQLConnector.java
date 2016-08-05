package com.sql;

import java.sql.*;
import javax.swing.*;

public class SQLConnector {

	public SQLConnector() {
		// TODO Auto-generated constructor stub
		Connection conn=null;
			conn=null;
	}
	
	public static Connection dbConnector(){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:/home/vetron/workspace/Just_After_GATE/yourGATE.sqlite");
			//JOptionPane.showMessageDialog(null, "Succesfull Connection");
			return conn;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
	}

}
//------------------------------------------------------- YO VIMAL --------------------------------------------------------------