package univpm.OpenWeather.Controller;


import java.net.MalformedURLException;



import java.net.URL;
import java.util.Scanner;
import java.util.Vector;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import univpm.OpenWeather.Model.City;
import univpm.OpenWeather.Model.Weather;
import univpm.OpenWeather.Service.WeatherImpl;

@RestController
public class OpenWeatherController {
	//private boolean current=true;
	
	@Autowired
	WeatherImpl service;
	
	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value="name",defaultValue="Mario")String name) {
		return "Salve "+name+", questo è OpenWeather";		
	}
	
	/**
	 * cambio solo queste prime due rotte
	 * @param name nome della città
	 * @return JSONObject con la rappresentazione della città
	 * @throws MalformedURLException avvisa se la chiamata non è andata a buon fine
	 * 
	 * @author lucas
	 */
	
	@RequestMapping("/current")
	public ResponseEntity<JSONObject> current(@RequestParam(name = "name", defaultValue = "Milano")String name) throws MalformedURLException{
		/**
		 * service.ResetUrl();
		 * il reset di url lo metterei direttamente dentro al service
		 */
		Weather meteo=new Weather();
		//meteo=(Weather) service.getCity(name);
		meteo=service.getWeather(name);
		return new ResponseEntity<>(service.printInfo(meteo), HttpStatus.OK);
	}
	
	@GetMapping(value= "/forecast/{cityName}")//non ancora finita, questa rotta dovrebbe fare la chiamata per il forecast 5 giorni e stampare tutto
	public ResponseEntity<Vector<Weather>> forecast(@RequestParam(name = "name", defaultValue = "Milano")String name) throws MalformedURLException{
		Vector<Weather> forecast=new Vector<Weather>();
		/**
		 * in questa parte devo trovare un modo per riempire il vettore forecast con un for each
		 * ogni giorno le info della city devono essere uguali( percheè non cambia) e devono cambiare solo le previsioni che
		 * si valorizzano con @method service.getWeather()
		 */
		
		return new ResponseEntity<>(forecast, HttpStatus.OK);
	}
	
	
	
	
	/*
	 * da qui in giù non ho modificato niente
	 
	
	
	
	@GetMapping(value = "/ApiCall")	
	public ResponseEntity<Object> ApiCall(@RequestParam (name="name", defaultValue = "Milano") String name) throws MalformedURLException {
		return new ResponseEntity<>(service.getInfo(service.UrlBuilder(name)).toString(), HttpStatus.OK);
	}
	
	/*
	 * Metodo che permette di vedere le informazioni 
	 * relative alla città (non il meteo)
	 *
	
	@GetMapping (value = "/CityInfo")
	public ResponseEntity<City> CityInfo(@RequestParam(name = "name", defaultValue = "Milano") String name) throws MalformedURLException {
		service.ResetUrl();
		return new ResponseEntity<>(service.getCity(name), HttpStatus.OK);
	}
	
	/*
	 * Metodo che consente di vedere le informazioni 
	 * riguardanti il meteo di un giorno di una città specifica
	 * @Author Francesco Rachiglia
	 *
	
	
	@RequestMapping("/weather")
	public StringBuilder Meteo() throws MalformedURLException {
		
		StringBuilder informationString = new StringBuilder();
		URL url= new URL("https://api.openweathermap.org/data/2.5/forecast?q=Milano&appid=15b8b402dfd9f2d93b1bfa8245d0edc6&mode=JSON&units=metric&lang=it");
		
		
		try {
			HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
		
			int responseCode = conn.getResponseCode();
			if (responseCode !=200) {
				throw new Exception("HttpResponseCode: " + responseCode);
			}else {
				Scanner scan=new Scanner(url.openStream());
				informationString = new StringBuilder();
				while(scan.hasNext()) {
					informationString.append(scan.nextLine());
				}
				scan.close();
				System.out.println(informationString);
			
				JSONParser parse = new JSONParser();
				JSONArray ris = (JSONArray) parse.parse(String.valueOf(informationString));
			
				System.out.println(ris.get(0));
			
				JSONObject countryData = (JSONObject) ris.get(0);
			
				System.out.println(countryData.get("location_type"));
			
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return informationString;
	}
	*/
	
	
}

