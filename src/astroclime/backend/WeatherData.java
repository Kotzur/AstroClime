package astroclime.backend;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.DailyForecast;
import net.aksingh.owmjapis.HourlyForecast;
import net.aksingh.owmjapis.OpenWeatherMap;

public class WeatherData {
	
	public static String CITY_NAME = "Cambridge";
	public static String COUNTRY_CODE = "GB";
	public static Unit UNIT = Unit.C;
	public static String LANGUAGE = "English";
	public static List<String> LANGUAGES = new ArrayList<>(Arrays.asList("English", "Polish"));
	
	private static OpenWeatherMap OWM;
	
	private static OpenWeatherMap getOWM() {
		
		//get a singleton instance of the open weather API
		
		if (OWM == null) {
			OWM = new OpenWeatherMap("");
			OWM.setApiKey("5b9c6f952fad43199fd6b949f7f9bbb0");
		}
		
		return OWM;
	}
	
	
	
	public static CurrentWeather getCurrentWeather(String cityName, String countryCode) throws JSONException, IOException{
		
		//get current weather data from a given cityname and country code
		
		CurrentWeather cwd = getOWM().currentWeatherByCityName(cityName, countryCode);
		return cwd;
	}
	
	public static float getCloudCover(CurrentWeather c) {
		//get the cloud cover
		if (c.hasCloudsInstance()) {
			return c.getCloudsInstance().getPercentageOfClouds();
		}else{
			return 0;
		}
	}
	
	public static int getTemperature(CurrentWeather c) {
		//get the temperature depending on the current unit
		float t = 0;
		
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
		//get the current visibility.
		
		//API does not provide this so this scrapes the raw response
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
		
		//return in km
		return v / 1000f;
		
	}
	
	public static float getHumidity(CurrentWeather c) {
		//get current humidity
		float h = 0;
		
		if (c.hasMainInstance() && c.getMainInstance().hasHumidity()) {
			h = c.getMainInstance().getHumidity();
		}
		
		return h;
	}
	
	public static float getRainfall(CurrentWeather c) {
		//get rainfall
		float r = 0;
		
		if (c.hasRainInstance() && c.getRainInstance().hasRain3h()) {
			r = c.getRainInstance().getRain3h();
			
		}
		
		return r;
	}
	
	public static String getSunset(CurrentWeather c) {
		//get the sunset time returned in the HH:mm format
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		String time = format.format(c.getSysInstance().getSunsetTime());
		
		return time;
	}
	
	public static String getSunrise(CurrentWeather c) {
		//get the sunrise time returned in the HH:mm format
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		String time = format.format(c.getSysInstance().getSunriseTime());
		
		return time;
	}
	
	public static DailyForecast getDailyForecast(String cityName, String countryCode) throws JSONException, IOException {
		//get a daily forecast for the next 8 days. This means we get today plus the next 7 days
		DailyForecast df = getOWM().dailyForecastByCityName(cityName, countryCode, (byte) 8);
		
		return df;
	}
	
	

}
