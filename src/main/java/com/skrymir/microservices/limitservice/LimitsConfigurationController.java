package com.skrymir.microservices.limitservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.skrymir.microservices.limitservice.bean.LimitsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    @HystrixCommand(fallbackMethod = "fallbackRetrieveLimitsFromConfigurations")
    public LimitsConfiguration retrieveLimitsFromConfigurations() {
        return new LimitsConfiguration(configuration.getMaximum(), configuration.getMinimum());
    }

    public LimitsConfiguration fallbackRetrieveLimitsFromConfigurations() {
        return new LimitsConfiguration(100, 1);
    }
}
