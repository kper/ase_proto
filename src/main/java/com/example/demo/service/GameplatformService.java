package com.example.demo.service;

import io.kubernetes.client.openapi.ApiException;

import java.io.IOException;

public interface GameplatformService {
    String run() throws ApiException, IOException;
}
