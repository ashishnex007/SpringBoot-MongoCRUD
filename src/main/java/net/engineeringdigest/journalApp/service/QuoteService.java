package net.engineeringdigest.journalApp.service;
import net.engineeringdigest.journalApp.api.response.QuoteResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class QuoteService {

    private String quoteAPI = "https://api.api-ninjas.com/v1/quotes?category=happiness";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public String getQuote(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", appCache.APP_CACHE.get("quote_api"));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<QuoteResponse>> response = restTemplate.exchange(
                quoteAPI,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<QuoteResponse>>() {}
        );

        List<QuoteResponse> quotes = response.getBody();

        if (quotes != null && !quotes.isEmpty()) {
            QuoteResponse firstQuote = quotes.get(0);
            return firstQuote.getQuote(); // Return the quote text
        }

        return "No quote available";
    }

}
