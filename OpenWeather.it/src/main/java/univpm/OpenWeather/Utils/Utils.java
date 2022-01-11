package univpm.OpenWeather.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import univpm.OpenWeather.Model.Weather;

public class Utils {
	/**
	 * Questa classe contiene diversi metodi utili e li raggruppa per cercare di
	 *  rendere più chiaro il resto del programma
	 *  @author lucas
	 */

	public Utils() {}
	
	public Vector<Weather> selectDay(){
		
		return null;
		
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
	
	/**
	 * Questo metodo converte la data da epoch time ad un formato più leggibile
	 * @param epochDate data in epoch time
	 * @return date in formato leggibile
	 * @author lucas
	 */
	public String dateConverter(long epochDate) {
		String date;
		DateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//da sistemare l'anno perchè stampa 1970
		date=format.format(epochDate);
		return date;
	}
	
	public Date toDate(long epoch) {
		//String date;//2022-01-09T09:14:36.201+00:00
		//DateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//date=format.format(epoch);
		Date jDate = new Date(epoch * 1000);
		return jDate;
	}
	
	
	/**
	 * converte la temperatura da Kelvin a celsius e la arrotonda
	 * @param temp temperatura in Kelvin
	 * @return temperatura in Celsius
	 */
	public double tempConverter(double temp) {
		double t=temp-273.15;
		double rounded=Math.round(t*100.0)/100.0;
		return  rounded;
	}
}
