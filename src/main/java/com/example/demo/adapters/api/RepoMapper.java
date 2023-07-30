package com.example.demo.adapters.api;

import com.example.demo.domain.model.Branch;
import com.example.demo.domain.model.Repository;
import com.example.springreactiveopenapicodegen.dto.BranchDTO;
import com.example.springreactiveopenapicodegen.dto.RepoDTO;

class RepoMapper {
    static RepoDTO of(Repository repository) {
        return RepoDTO.builder()
                .name(repository.getName())
                .ownerLogin(repository.getOwnerLogin())
                .branches(repository.getBranches().stream().map(RepoMapper::of).toList())
                .build();
    }

    static BranchDTO of(Branch branch) {
        return BranchDTO.builder()
                .name(branch.getName())
                .lastCommitSha(branch.getLastCommitSha())
                .build();
    }

}
