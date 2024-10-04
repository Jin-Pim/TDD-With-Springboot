package com.example.day1.post;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class PostGateway {

    private final RestTemplate restTemplate;
    private final String postApiUrl;

    public PostGateway(RestTemplate restTemplate, @Value("${post.api.url}") String postApiUrl) {
        this.restTemplate = restTemplate;
        this.postApiUrl = postApiUrl;
    }

    public Optional<PostResponse> getById(int postId) {
        String url = this.postApiUrl + "/posts/" + postId;
        try {
            PostResponse result = restTemplate.getForObject(url, PostResponse.class);
            return Optional.of(result);
        } catch (Exception e) {
            return Optional.empty();
//            throw new RuntimeException("PostGateway error!!");
        }

    }

}
