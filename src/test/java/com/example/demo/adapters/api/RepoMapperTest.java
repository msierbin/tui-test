package com.example.demo.adapters.api;

import com.example.demo.domain.model.Branch;
import com.example.demo.domain.model.Repository;
import com.example.springreactiveopenapicodegen.dto.BranchDTO;
import com.example.springreactiveopenapicodegen.dto.RepoDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepoMapperTest {

    @Test
    public void testRepoDtoMapping() {
        // Given
        Repository repository = Repository.builder()
                .name("Test Repo")
                .ownerLogin("TestOwner")
                .branches(
                    List.of(
                        Branch.builder().name("branch1").lastCommitSha("commit1").build(),
                        Branch.builder().name("branch2").lastCommitSha("commit2").build()))
                .build();

        // When
        RepoDTO repoDTO = RepoMapper.of(repository);

        // Then
        assertEquals("Test Repo", repoDTO.getName());
        assertEquals("TestOwner", repoDTO.getOwnerLogin());
        assertEquals(2, repoDTO.getBranches().size());
        assertEquals("branch1", repoDTO.getBranches().get(0).getName());
        assertEquals("branch2", repoDTO.getBranches().get(1).getName());
    }

    @Test
    public void testBranchDtoMapping() {
        // Given
        Branch branch = Branch.builder().name("Test Branch").lastCommitSha("123abc").build();

        // When
        BranchDTO branchDTO = RepoMapper.of(branch);

        // Then
        assertEquals("Test Branch", branchDTO.getName());
        assertEquals("123abc", branchDTO.getLastCommitSha());
    }

}