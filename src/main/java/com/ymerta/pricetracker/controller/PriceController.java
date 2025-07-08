package com.ymerta.pricetracker.controller;

import com.ymerta.pricetracker.service.PriceAnalyticsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/price")
public class PriceController {

    private final PriceAnalyticsService service;

    public PriceController(PriceAnalyticsService service) {
        this.service = service;
    }

    @GetMapping("/create-job")
    public Map<String, Object> createJobs(@RequestParam String term) {
        List<String> jobIds = service.createJobsForAllSources(term);
        Map<String, Object> response = new HashMap<>();
        response.put("error", false);
        response.put("job_ids", jobIds);
        return response;
    }

    @GetMapping("/poll-job")
    public String pollJob(@RequestParam String jobId) {
        return service.pollJob(jobId);
    }
}