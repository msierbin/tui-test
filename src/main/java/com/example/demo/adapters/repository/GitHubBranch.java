package com.example.demo.adapters.repository;

import com.example.demo.domain.model.Branch;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class GitHubBranch {
    private String name;
    private String lastCommitSha;
    private GitHubCommit commit;

    Branch toDomain() {
        return Branch.builder().name(name).lastCommitSha(lastCommitSha).build();
    }
}
