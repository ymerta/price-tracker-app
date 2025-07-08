package com.ymerta.pricetracker.service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriceAnalyticsService {

    private final WebClient webClient;

    public PriceAnalyticsService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://price-analytics.p.rapidapi.com")
                .defaultHeader("x-rapidapi-key", "your-api-key")
                .defaultHeader("x-rapidapi-host", "price-analytics.p.rapidapi.com")
                .defaultHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
    }

    public String createJobForSource(String term, String source) {
        return webClient.post()
                .uri("/search-by-term")
                .body(BodyInserters.fromFormData("values", term)
                        .with("source", source)
                        .with("country", "de"))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
    public List<String> createJobsForAllSources(String term) {
        List<String> sources = List.of("amazon", "ebay");
        List<String> jobIds = new ArrayList<>();

        for (String source : sources) {
            String response = createJobForSource(term, source);
            String jobId = extractJobIdFromResponse(response);
            if (jobId != null) {
                jobIds.add(jobId);
            }
        }

        return jobIds;
    }

    private String extractJobIdFromResponse(String response) {
        try {
            int start = response.indexOf("\"job_id\":\"") + 10;
            int end = response.indexOf("\"", start);
            return response.substring(start, end);
        } catch (Exception e) {
            return null;
        }
    }
    public String pollJob(String jobId) {
        return webClient.get()
                .uri("/poll-job/" + jobId)
                .header("x-rapidapi-key", "your-api-key")
                .header("x-rapidapi-host", "price-analytics.p.rapidapi.com")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}