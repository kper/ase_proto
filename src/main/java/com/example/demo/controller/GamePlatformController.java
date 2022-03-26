package com.example.demo.controller;

import com.example.demo.service.GameplatformService;
import io.kubernetes.client.openapi.ApiException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class GamePlatformController {
    private final GameplatformService gameplatformService;

    public GamePlatformController(GameplatformService gameplatformService) {
        this.gameplatformService = gameplatformService;
    }

    @PostMapping(value = "/gameplatform")
    public String create() throws IOException, ApiException {
        return gameplatformService.run();
    }
}
