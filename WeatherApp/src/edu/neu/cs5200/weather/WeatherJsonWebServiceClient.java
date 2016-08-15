package edu.neu.cs5200.weather;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WeatherJsonWebServiceClient {
	
	private String urlApi = "http://api.worldweatheronline.com/premium/v1/weather.ashx?key=68e4c6c766054d0bb98193204161208&q={{ZIP}}&format=json&num_of_days=2";
	
	public Weather getWeatherForZip(String zip){
		
		String urlStr = urlApi.replace("{{ZIP}}", zip);

		try {
			URL url = new URL(urlStr);
			HttpURLConnection connection =  (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			// now stream results, and read line by line with a buffer
			InputStream stream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);
			String line; 
			String json = "";
			while((line = buffer.readLine()) != null) {
				json += line;
			}
			
			
			JSONParser parser = new JSONParser();
			try {
				JSONObject root = (JSONObject) parser.parse(json);
				JSONObject data = (JSONObject) root.get("data");
				JSONArray current_condition = (JSONArray) data.get("current_condition");
				JSONObject firstCondition = (JSONObject) current_condition.get(0);
				JSONArray weatherDescArray = (JSONArray) firstCondition.get("weatherDesc");
				JSONObject weatherDesc = (JSONObject) weatherDescArray.get(0);
				JSONArray weatherIconUrlArray = (JSONArray) firstCondition.get("weatherIconUrl");
				JSONObject weatherIconUrl = (JSONObject) weatherIconUrlArray.get(0);
				
				Condition currentCondition = new Condition();
				currentCondition.cloudCover = Float.parseFloat(firstCondition.get("cloudcover").toString());
				currentCondition.pressure = Float.parseFloat(firstCondition.get("pressure").toString());		
				currentCondition.humidity = Float.parseFloat(firstCondition.get("humidity").toString());	
				currentCondition.weatherDesc = weatherDesc.get("value").toString();
				currentCondition.weatherIconUrl = weatherIconUrl.get("value").toString();
				
				JSONArray weather = (JSONArray) data.get("weather");
				
				Weather weatherObject = new Weather("Cape May Court House", "08210", currentCondition, null);
				
				return weatherObject;
				
				//System.out.println(currentCondition.weatherIconUrl);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		WeatherJsonWebServiceClient client = new WeatherJsonWebServiceClient();
		client.getWeatherForZip("08210");
	}

}
