package com.example.day1.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PostController {

    @Autowired
    private PostGateway postGateway;

    @GetMapping("post/{id}")
    public PostResponse getById(@PathVariable int id) {
        Optional<PostResponse> result = postGateway.getById(id);

        PostResponse response = new PostResponse();
        response.setId(result.get().getId());
        response.setUserId(result.get().getUserId());
        response.setTitle(result.get().getTitle());
        response.setBody(result.get().getBody());
        return response;
    }
}
