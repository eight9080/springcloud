package ro.dg.cloudex.weatherapp;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "unknown")
    public String getWeather(){
        final String body = restTemplate.getForEntity("http://weather-service/weather", String.class).getBody();
        return body;
    }

    public String unknown(){
        return "unknown";
    }
}
