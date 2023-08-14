package com.doomole.stockproject.util;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class WebClientService {

    public Mono<Map> post(String url, String uri, Map<String, Object> map) {
        WebClient webClient = WebClient.create(url);

        return webClient.post()
                .uri(uri)
                .bodyValue(map)
                .retrieve()
                .bodyToMono(Map.class);
    }
}
