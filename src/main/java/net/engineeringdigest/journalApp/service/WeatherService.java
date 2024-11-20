package net.engineeringdigest.journalApp.service;
import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    @Value("${apis.weather.key}")
    private String weatherAPIKey = "";
    private String weatherAPI = "https://api.openweathermap.org/data/2.5/weather?q=CITY&appid=" + weatherAPIKey;

    @Autowired
    private RestTemplate restTemplate;

    public String getWeather(String city){
        String finalAPI = weatherAPI.replace("CITY", city);
        // converting JSON to POJO is called deserialization
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        double tempInCelsius = response.getBody().getMain().getTemp() - 273.15;
        double humid = response.getBody().getMain().getHumidity();
        return "Today's Temperature in" + city + " is: " + (int) Math.round(tempInCelsius) + "°C & Humidity feels like: " + humid + "%";
    }
}
