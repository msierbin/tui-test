package com.example.demo.adapters.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.domain.model.Repository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class GitHubRepositoryTest {

    @Test
    public void testToDomain() {
        // Given
        GitHubOwner owner = new GitHubOwner();
        owner.setLogin("ownerLogin");

        GitHubBranch branch1 = new GitHubBranch();
        branch1.setName("branch1");
        branch1.setLastCommitSha("commitSha1");

        GitHubBranch branch2 = new GitHubBranch();
        branch2.setName("branch2");
        branch2.setLastCommitSha("commitSha2");

        List<GitHubBranch> branches = new ArrayList<>();
        branches.add(branch1);
        branches.add(branch2);

        GitHubRepository gitHubRepository = new GitHubRepository();
        gitHubRepository.setName("repository");
        gitHubRepository.setOwner(owner);
        gitHubRepository.setBranches(branches);

        // When
        Repository repository = gitHubRepository.toDomain();

        // Then
        assertEquals("repository", repository.getName());
        assertEquals("ownerLogin", repository.getOwnerLogin());
        assertEquals(2, repository.getBranches().size());
        assertEquals("branch1", repository.getBranches().get(0).getName());
        assertEquals("branch2", repository.getBranches().get(1).getName());
    }
}