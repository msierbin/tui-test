package com.example.demo.adapters.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class RepoControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testGetRepo() {
        webTestClient.get()
            .uri("/users/msierbin/repos")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$[0].ownerLogin").isEqualTo("msierbin")
        ;
    }
}