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

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.util.ObjectUtils.isEmpty;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9999)
class PostGatewayFailureTest {

    @Autowired
    private PostGateway postGateway;

    @Test
    @DisplayName("Try to fail with 404, should return empty")
    void getById() throws IOException {
        // Arrange
        stubFor(get(urlPathEqualTo("/posts/1")).willReturn(aResponse()
                        .withBody(read("classpath:postApiResponse.json"))
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(404)));

        // Act
        Optional<PostResponse> result = postGateway.getById(1);

        // Assert
        assertTrue(isEmpty(result));
    }

    public static String read(String filePath) throws IOException {
        File file = ResourceUtils.getFile(filePath);
        return new String(Files.readAllBytes(file.toPath()));
    }
}