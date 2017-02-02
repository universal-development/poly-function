package com.unidev.service;

import com.unidev.polygateway.domain.ServiceRequest;
import com.unidev.polygateway.domain.ServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service {

    private static Logger LOG = LoggerFactory.getLogger(Service.class);

    @PostMapping
    public ServiceResponse handle(@RequestBody ServiceRequest request) {
        LOG.info("Processing request {}", request);
        ServiceResponse serviceResponse = new ServiceResponse();
        return serviceResponse;
    }

}
