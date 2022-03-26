package com.example.demo.kubernetes;

import com.google.gson.Gson;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.apis.BatchV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import java.io.IOException;

@Configuration
public class KubernetesConfiguration {
    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public Yaml yaml() {
        return new Yaml(new SafeConstructor());
    }

    @Bean
    ApiClient apiClient() throws IOException {
        ApiClient client = Config.defaultClient();
        return client;
    }

    @Bean
    CoreV1Api api(ApiClient client) {
        CoreV1Api api = new CoreV1Api(client);
        return api;
    }

    @Bean
    BatchV1Api batch(ApiClient client) {
        BatchV1Api batchV1Api = new BatchV1Api(client);
        return batchV1Api;
    }
}