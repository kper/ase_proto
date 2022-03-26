package com.example.demo.controller;

import com.example.demo.service.GameplatformService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GamePlatformController {
    private final GameplatformService gameplatformService;

    public GamePlatformController(GameplatformService gameplatformService) {
        this.gameplatformService = gameplatformService;
    }

    @PostMapping(value = "/gameplatform")
    public String create() {
        return gameplatformService.run();
    }
}
