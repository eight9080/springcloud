package ro.dg.cloudex.datetimeapp;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DatetimeService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod="unknown")
    public String getCurrentDatetime() {
        return restTemplate
                .getForEntity("http://datetime-service/datetime", String.class)
                .getBody();
    }

    public String unknown() {
        return "unknown";
    }
}
