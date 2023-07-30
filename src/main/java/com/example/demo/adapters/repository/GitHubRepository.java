package com.example.demo.adapters.repository;

import com.example.demo.domain.model.Repository;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
class GitHubRepository {
    private boolean fork;
    private String name;
    private GitHubOwner owner;
    private List<GitHubBranch> branches;

    Repository toDomain() {
        return Repository.builder()
                .name(name)
                .ownerLogin(owner.getLogin())
                .branches(branches.stream().map(GitHubBranch::toDomain).toList())
                .build();
    }
}
