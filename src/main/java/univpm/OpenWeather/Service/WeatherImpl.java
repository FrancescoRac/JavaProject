package univpm.OpenWeather.Service;

<<<<<<< HEAD
import java.io.InputStreamReader;

import java.io.Reader;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import univpm.OpenWeather.Model.Position;
import univpm.OpenWeather.Model.Weather;
import univpm.OpenWeather.Utils.Stats;


	@Service
	public class WeatherImpl implements WeatherInt {
		
		private String apiKey = "15b8b402dfd9f2d93b1bfa8245d0edc6";
		private String url ="https://api.openweathermap.org/data/2.5/weather?q=";
		
		@Override 
		public String UrlBuilder(String name) {
			if (name.isEmpty()) {
				return null;
			}
			else {
				this.url= url + name + "&appid=" + apiKey;
			}
			return this.url;						
		}
		
		
		@Override
		public JSONObject getInfo(String u) throws MalformedURLException {
			// TODO Auto-generated method stub
			JSONObject obj = null;
			URL url = new URL(u);
				try {
					
					HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.connect();
				
					int responseCode = conn.getResponseCode();
					if (responseCode !=200) {
						throw new Exception("HttpResponseCode: " + responseCode);
					}else {
						Reader scan=new InputStreamReader(url.openStream());
					
						JSONParser parser = new JSONParser();
						
						obj = (JSONObject) parser.parse(scan);
										
						scan.close();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				return obj;
			
	}
		
		
		@Override
		public Weather getDailyWeather(String cityName) throws MalformedURLException {
			// TODO Auto-generated method stub
				String u = UrlBuilder(cityName);
				System.out.println(u);
				JSONObject object = getInfo(u);
				
				Weather meteo = new Weather();
				Position coordinates = new Position();
				System.out.println(object);
				
				try {
					
					JSONObject meteoObj = (JSONObject) object.get("main");
					double temp = (double) meteoObj.get("temp"); 
					double temp_max= (double) meteoObj.get("temp_max");
					double temp_min= (double) meteoObj.get("temp_min");
					long pressure=(long) meteoObj.get("pressure");
					double feels_like = (double) meteoObj.get("feels_like");
					String data = (String) meteoObj.get("data");
					meteo.setTemp(temp);
					meteo.setTemp_min(temp_min);
					meteo.setTemp_max(temp_max);
					meteo.setPressure(pressure);
					meteo.setFeels_like(feels_like);
					meteo.setData(data);
					
					
					String name = (String) object.get("name");
					long id = (long) object.get("id");
					meteo.setName(name);
					meteo.setId(id);
					
					JSONObject coordinatesObj = (JSONObject) object.get("coord");
					double lat = (double) coordinatesObj.get("lat");
					double lon = (double) coordinatesObj.get("lon");
					coordinates.setLat(lat);
					coordinates.setLon(lon);
					meteo.setCoordinates(coordinates);
				
					
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(meteo.getName() + " " + meteo.getId()) ;
				
				return meteo;
			
		}


		public Weather getWeather(String cityName) throws MalformedURLException {
			// TODO Auto-generated method stub
			Weather meteo = new Weather();
			
			String u = UrlBuilder(cityName); //Crea URL
			
			JSONObject object = getInfo(u); //JSONObject contentente il JSON 
			
			Stats s = new Stats();
			s.getDailyWeather(object, meteo); //Passo il JSON per farlo elaborare
					
			return meteo;
		}

		/**
		 *Metodo che usa getCity per prendere previsioni 
		 *meteo della città richiesta e
		 *restituisce il JSONArray
		 * 
		 *@return restituisce il JSONArray con la città e le relative informazioni
		 * 
		 */
		
		public String searchArray(JSONObject obj,String arrayName, String valueName) {
			JSONArray array=(JSONArray) obj.get(arrayName);
			Iterator<?> i=array.iterator();
			String value="";
			
			while (i.hasNext()) {
				JSONObject info=(JSONObject) i.next();
				value=(String) info.get(valueName);
			}
			return value;
			
		}
		
		
		public void ResetUrl() {
			this.url = "https://api.openweathermap.org/data/2.5/weather?q=";
		}


		
=======
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class WeatherImpl implements WeatherInt {
	
	//private String apiKey = "15b8b402dfd9f2d93b1bfa8245d0edc6";
	
	@Override
	public JSONObject getCity(String cityName) {
		// TODO Auto-generated method stub
		JSONObject name = new JSONObject();
	/*	String url = "https://api.openweathermap.org/data/2.5/forecast?q="+ cityName + "&appid" + apiKey ;
		
		RestTemplate prova = new RestTemplate();
		
		name = new JSONObject(prova.getForObject(url, String.class));
	*/
		
		return name;
		
	}

	/**
	 *Metodo che usa getCity per prendere previsioni 
	 *meteo della città richiesta e
	 *restituisce il JSONArray
	 * 
	 *@return restituisce il JSONArray con la città e le relative informazioni
	 * 
	 */
	
	
	
	@Override
	public JSONObject getAPI(String name) throws MalformedURLException {
		// TODO Auto-generated method stub
		StringBuilder informationString =new StringBuilder();
		JSONArray ris = new JSONArray();
		JSONObject countryData = null;
		
			try {
				URL openweather= new URL("https://api.openweathermap.org/data/2.5/forecast?q=Milano&appid=15b8b402dfd9f2d93b1bfa8245d0edc6&mode=JSON&units=metric&lang=it");
				HttpsURLConnection conn=(HttpsURLConnection) openweather.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
			
				int responseCode = conn.getResponseCode();
				if (responseCode !=200) {
					throw new Exception("HttpResponseCode: " + responseCode);
				}else {
					Scanner scan=new Scanner(openweather.openStream());
					while(scan.hasNext()) {
						informationString.append(scan.nextLine());
					}
					scan.close();
					System.out.println(informationString);
				
					JSONParser parse = new JSONParser();
					ris = (JSONArray) parse.parse(String.valueOf(informationString));
				
					System.out.println(ris.get(0));
				
					countryData = (JSONObject) ris.get(0);
				
					System.out.println(countryData.get("location type"));
				
				}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return countryData;
		
}
	
	@Override 
	public String toString() {
		return null;
	}


	

>>>>>>> refs/remotes/Francesco/FrancescoUpdate
}
