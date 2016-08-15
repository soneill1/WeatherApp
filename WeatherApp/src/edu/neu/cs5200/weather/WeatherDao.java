package edu.neu.cs5200.weather;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// DAO Design Pattern
public class WeatherDao {
	
	// Singleton Design Pattern
	private static WeatherDao instance = null;
	protected WeatherDao() {}
	public static WeatherDao getInstance() {
		if(instance == null)
			instance = new WeatherDao();
		return instance;
	}
	
	
	
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
	
	private void create(Weather weather) {
		String sql = "insert into weather (zipcode, locationName) values (?,?)";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, weather.getZip());
			statement.setString(2, weather.getLocationname());
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}
	
	public List<Weather> selectFromUserId(int id) {
		List<Weather> ws = new ArrayList<Weather>();
		
		String sql = "select zipcode, locationName, weather.id from weather, user2weather, user where user.id = ? AND user.id = user2weather.userId AND weather.id = user2weather.id";
		
		Connection connection = getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				id = results.getInt("id");
				String zip = results.getString("zipcode");
				String locationName = results.getString("locationName");
				
				Weather weather = new Weather(id, locationName, zip);
				ws.add(weather);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return ws;
	}

	public static void main(String[] args) {
		// not mean't to be used many times, should be only one
		WeatherDao dao = WeatherDao.getInstance();
		
		//Weather weather = new Weather("02115","Boston");
		
		dao.create(weather);
	}
	

}
