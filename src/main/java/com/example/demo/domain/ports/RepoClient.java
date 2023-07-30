package com.example.demo.domain.ports;

import com.example.demo.domain.model.Repository;
import reactor.core.publisher.Flux;

public interface RepoClient {
    Flux<Repository> getUserRepos(String username);
}