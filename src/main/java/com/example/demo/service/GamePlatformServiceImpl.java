package com.example.demo.service;

import com.example.demo.feign.ContainerRuntimeClient;
import com.google.gson.Gson;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.BatchV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Job;
import io.kubernetes.client.openapi.models.V1ObjectMeta;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class GamePlatformServiceImpl implements GameplatformService {
    private final ContainerRuntimeClient runtimeClient;
    private final CoreV1Api api;
    private final BatchV1Api batch;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private final Yaml yaml;
    private final ResourceLoader resourceLoader;
    private final Gson gson;

    public GamePlatformServiceImpl(ContainerRuntimeClient runtimeClient, CoreV1Api api, BatchV1Api batchV1Api, Yaml yaml, ResourceLoader resourceLoader, Gson gson) {
        this.runtimeClient = runtimeClient;
        this.api = api;
        this.batch = batchV1Api;
        this.yaml = yaml;
        this.resourceLoader = resourceLoader;
        this.gson = gson;
    }

    @Override
    public String run() throws ApiException, IOException {
        V1Job job = loadJobFromYaml("job.yml");

        V1ObjectMeta metaData = job.getMetadata();
        metaData.setName(String.format("%s-%s", metaData.getName(), dateTimeFormatter.format(LocalDateTime.now())));

        batch.createNamespacedJob("default", job, "true", null, null);

        return "ok";
    }

    private V1Job loadJobFromYaml(String fileName) throws IOException {
        Map jobManifest = (Map) yaml.load(
                resourceLoader.getResource(ResourceLoader.CLASSPATH_URL_PREFIX + fileName).getInputStream());

        return gson.fromJson(gson.toJson(jobManifest), V1Job.class);
    }
}
