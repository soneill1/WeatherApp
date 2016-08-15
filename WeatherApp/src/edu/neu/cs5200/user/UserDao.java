package edu.neu.cs5200.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	
public Connection getConnection() {
		
		String connectionUrl = "jdbc:mysql://localhost:3306/weather app2";
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionUrl, "root", "admin");
;		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User selectByUsernameAndPassword(String username, String password) {
		String sql = "select * from user where username=? and password=?";
		Connection connection = getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet results = statement.executeQuery();
			if(results.next()){
				int id = results.getInt("id");
				User user = new User(id, username, password);
				return user;
			}
			
		} catch (SQLException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		
		return null; 
	}

	public static void main(String[] args) {
		UserDao dao = new UserDao();
		User user = dao.selectByUsernameAndPassword("alice", "alice");
		System.out.println(user);
	}

}
