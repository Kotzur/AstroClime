package astroclime.backend;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;

public class WeatherData {
	
	public static String CITY_NAME = "Cambridge";
	public static String COUNTRY_CODE = "GB";
	public static Unit UNIT = Unit.C;
	public static List<String> PREVIOUS_LOCATIONS = new ArrayList<>();
	public static String LANGUAGE = "English";
	public static List<String> LANGUAGES = new ArrayList<>(Arrays.asList("English", "Polish"));
	
	private static OpenWeatherMap OWM;
	
	private static OpenWeatherMap getOWM() {
		if (OWM == null) {
			OWM = new OpenWeatherMap("");
			OWM.setApiKey("5b9c6f952fad43199fd6b949f7f9bbb0");
		}
		
		return OWM;
	}
	
	
	
	public static CurrentWeather getCurrentWeather(String cityName, String countryCode) throws JSONException, IOException{
		CurrentWeather cwd = getOWM().currentWeatherByCityName(cityName, countryCode);
		return cwd;
	}
	
	public static float getCloudCover(CurrentWeather c) {
		if (c.hasCloudsInstance()) {
			return c.getCloudsInstance().getPercentageOfClouds();
		}else{
			return -1;
		}
	}
	
	public static int getTemperature(CurrentWeather c) {
		
		float t = -1;
		
		if (c.hasMainInstance()) {
			t = c.getMainInstance().getTemperature();
		}
		
		if (UNIT != Unit.F) {
			t = (t-32) * (0.5556f);
		}
		if (UNIT == Unit.K) {
			t += 273;
		}
		
		return Math.round(t);
	}
	
	public static float getVisibility(CurrentWeather c) {
		
		float v = 0;
		
		if (c.hasRawResponse()) {
			String[] responses = c.getRawResponse().split(",");
			
			for (int i = 0; i < responses.length; i++) {
				if (responses[i].contains("visibility")) {
					String[] split = responses[i].split(":");
					v = Float.parseFloat(split[1]);
					i = responses.length;
				}
			}
		}
		
		return v / 1000f;
		
	}
	
	public static float getHumidity(CurrentWeather c) {
		float h = -1;
		
		if (c.hasMainInstance() && c.getMainInstance().hasHumidity()) {
			h = c.getMainInstance().getHumidity();
		}
		
		return h;
	}
	
	public static float getRainfall(CurrentWeather c) {
		float r = 0;
		
		if (c.hasRainInstance() && c.getRainInstance().hasRain3h()) {
			r = c.getRainInstance().getRain3h();
			
		}
		
		return r;
	}
	
	public static String getWeather(CurrentWeather c) {

		
		String w = "NOTHING";
		
		w = c.getWeatherInstance(0).getWeatherName();
		
		return w;
	}

	public static String getSunset(CurrentWeather c) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		String time = format.format(c.getSysInstance().getSunsetTime());
		
		return time;
	}
	
	public static String getSunrise(CurrentWeather c) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		String time = format.format(c.getSysInstance().getSunriseTime());
		
		return time;
	}

	//TODO check if valid city
	public static Boolean checkValidCity(String cityName){
		return true;
	}

	public static void main(String args[]) {
		try {
			CurrentWeather cwd = getCurrentWeather("Cambridge","GB");
			System.out.println(getSunrise(cwd));
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	

}
