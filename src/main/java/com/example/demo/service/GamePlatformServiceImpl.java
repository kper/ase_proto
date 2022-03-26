package com.example.demo.service;

import com.example.demo.feign.ContainerRuntimeClient;
import org.springframework.stereotype.Service;

@Service
public class GamePlatformServiceImpl implements GameplatformService {
    private final ContainerRuntimeClient runtimeClient;

    public GamePlatformServiceImpl(ContainerRuntimeClient runtimeClient) {
        this.runtimeClient = runtimeClient;
    }

    @Override
    public String run() {
        return runtimeClient.run();
    }
}
