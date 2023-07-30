package com.example.demo.adapters.repository;

import com.example.demo.domain.model.NotFoundException;
import com.example.demo.domain.model.Repository;
import com.example.demo.domain.ports.RepoClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.util.function.Predicate.not;

@Service
public class GitHubClient implements RepoClient {

    private static final String API_URL = "https://api.github.com";
    private final WebClient webClient = WebClient.create(API_URL);

    public Flux<Repository> getUserRepos(String username) {
        return webClient.get()
                .uri("/users/{username}/repos", username)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(GitHubRepository.class)
                .onErrorResume(WebClientResponseException.class,
                        ex -> ex.getStatusCode() == HttpStatus.NOT_FOUND
                                ? Mono.error(new NotFoundException("Username '" + username +"' cannot be found"))
                                : Mono.error(ex))
                .filter(not(GitHubRepository::isFork))
                .flatMap(repo -> getBranchesWithLastCommit(username, repo))
                .map(GitHubRepository::toDomain);
    }

    private Mono<GitHubRepository> getBranchesWithLastCommit(String username, GitHubRepository repo) {
        return this.webClient.get().uri("/repos/{username}/{repo}/branches", username, repo.getName())
                .retrieve()
                .bodyToFlux(GitHubBranch.class)
                .flatMap(branch -> getLastCommit(username, repo, branch))
                .collectList()
                .map(branches -> {
                    repo.setBranches(branches);
                    return repo;
                });
    }

    private Mono<GitHubBranch> getLastCommit(String username, GitHubRepository repo, GitHubBranch branch) {
        return this.webClient.get().uri("/repos/{username}/{repo}/branches/{branch}", username, repo.getName(), branch.getName())
                .retrieve()
                .bodyToMono(GitHubBranch.class)
                .map(branchWithCommit -> {
                    branch.setLastCommitSha(branchWithCommit.getCommit().getSha());
                    return branch;
                });
    }
}
