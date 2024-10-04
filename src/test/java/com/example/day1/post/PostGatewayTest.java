package com.example.day1.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9999)
class PostGatewayTest {

    @Autowired
    private PostGateway postGateway;

    @Test
    @DisplayName("Get post by id with external api Success by WireMock Stub")
    void getById() throws IOException {
        // Arrange
        stubFor(get(urlPathEqualTo("/posts/1")).willReturn(aResponse()
                        .withBody(read("classpath:postApiResponse.json"))
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)));

        // Act
        Optional<PostResponse> result = postGateway.getById(1);

        // Assert
        assertEquals(11, result.get().getId());
        assertEquals(11, result.get().getUserId());
        assertEquals("Test Title", result.get().getTitle());
        assertEquals("Test Body", result.get().getBody());
    }

    public static String read(String filePath) throws IOException {
        File file = ResourceUtils.getFile(filePath);
        return new String(Files.readAllBytes(file.toPath()));
    }
}