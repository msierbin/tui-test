package com.example.demo.adapters.api;

import com.example.demo.domain.model.NotFoundException;
import com.example.demo.domain.model.Repository;
import com.example.demo.domain.ports.RepoClient;
import com.example.springreactiveopenapicodegen.dto.RepoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class ReposControllerTest {

    @Mock
    private RepoClient repoClient;

    @Mock
    private ServerWebExchange exchange;

    private ReposController reposController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        reposController = new ReposController(repoClient);
    }

    @Test
    void getUserRepos_ReturnsFluxOfRepoDTO() {
        // given
        Repository repository = Repository.builder().name("mockRepoName").build();
        RepoDTO repoDTO = RepoMapper.of(repository);
        // when
        when(repoClient.getUserRepos(anyString())).thenAnswer((Answer<Flux<Repository>>) invocation -> Flux.just(repository));
        StepVerifier.create(reposController.getUserRepos("mockUsername", exchange))
        // then
                .expectNextMatches(responseEntity ->
                        responseEntity.getStatusCode().is2xxSuccessful() &&
                                responseEntity.getBody() != null &&
                                responseEntity.getBody().blockFirst().equals(repoDTO)
                )
                .verifyComplete();
    }

    @Test
    void getUserRepos_ReturnsNotFoundUser() {
        // when
        doThrow(new NotFoundException("not-found")).when(repoClient).getUserRepos(anyString());
        // then
        assertThrows(NotFoundException.class, () -> {
            reposController.getUserRepos("mockUsername", exchange);
        });
    }
}