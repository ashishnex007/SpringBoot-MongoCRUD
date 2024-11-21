package net.engineeringdigest.journalApp.service;
import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;

    public String getWeather(String city){
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if(weatherResponse != null){
            System.out.println("Data from Redis");
            double tempInCelsius = weatherResponse.getMain().getTemp() - 273.15;
//            System.out.println("Temp in Celsius: " + tempInCelsius);
            double humid = weatherResponse.getMain().getHumidity();
//            System.out.println("Humidity: " + humid);
            return "Today's Temperature in" + city + " is: " + (int) Math.round(tempInCelsius) + "°C & Humidity feels like: " + humid + "%";
        }else{
            System.out.println("Data from API");
            String finalAPI = appCache.APP_CACHE.get("weather_api").replace("CITY", city);
            // converting JSON to POJO is called deserialization
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            double tempInCelsius = response.getBody().getMain().getTemp() - 273.15;
            double humid = response.getBody().getMain().getHumidity();
            String body = "Today's Temperature in" + city + " is: " + (int) Math.round(tempInCelsius) + "°C & Humidity feels like: " + humid + "%";
            if(body != null){
                redisService.set("weather_of_" + city, response.getBody(), 300L);
                System.out.println("Data saved to Redis");
            }
            return body;
        }
    }
}
