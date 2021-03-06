package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "ContainerRuntime", url = "http://localhost:8082/")
public interface ContainerRuntimeClient {
    @PostMapping(value = "/run")
    String run();
}
