package com.example.demo.adapters.api;

import com.example.demo.domain.ports.RepoClient;
import com.example.springreactiveopenapicodegen.api.v1.ReposApi;
import com.example.springreactiveopenapicodegen.dto.RepoDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class ReposController implements ReposApi {

    private final RepoClient repoClient;

    @Override
    public Mono<ResponseEntity<Flux<RepoDTO>>> getUserRepos(String username, ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok(repoClient.getUserRepos(username).map(RepoMapper::of)));
    }

}