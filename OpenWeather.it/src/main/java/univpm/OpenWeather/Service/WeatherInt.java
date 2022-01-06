package univpm.OpenWeather.Service;

import java.net.MalformedURLException;


import org.json.simple.JSONObject;

import univpm.OpenWeather.Model.City;
import univpm.OpenWeather.Model.Weather;

public interface WeatherInt {
	
	public City getCity(String name) throws MalformedURLException;
	public JSONObject getInfo(String url) throws MalformedURLException;
	public Weather getWeather(String name) throws MalformedURLException;
	public String toString();
	public String UrlBuilder(boolean current, String cityName);
	JSONObject printInfo(Weather city);

}
