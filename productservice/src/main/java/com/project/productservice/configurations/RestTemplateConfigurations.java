package com.project.productservice.configurations;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration

public class RestTemplateConfigurations {

    @Bean("LoadBalancingRestTemplate")
    @LoadBalanced
    @Primary
    public RestTemplate getRestTemplate() {
        return new RestTemplate(new CustomClientHttpRequestFactory());
    }

    @Bean("FakeStoreRestTemplate")
    public RestTemplate getFakeStoreRestTemplate() {
        return new RestTemplate(new CustomClientHttpRequestFactory());
    }
}
