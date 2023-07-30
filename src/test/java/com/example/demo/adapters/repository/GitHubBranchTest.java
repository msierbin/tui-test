package com.example.demo.adapters.repository;

import com.example.demo.domain.model.Branch;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GitHubBranchTest {

    @Test
    public void testToDomainMapping() {
        // Given
        GitHubCommit commit = new GitHubCommit();
        commit.setSha("commitSha1");

        GitHubBranch gitHubBranch = new GitHubBranch();
        gitHubBranch.setName("branch1");
        gitHubBranch.setLastCommitSha("commitSha1");
        gitHubBranch.setCommit(commit);

        // When
        Branch branch = gitHubBranch.toDomain();

        // Then
        assertEquals("branch1", branch.getName());
        assertEquals("commitSha1", branch.getLastCommitSha());
    }

    @Test
    public void testCommitSetAndGet() {
        // Given
        GitHubCommit commit = new GitHubCommit();
        commit.setSha("commitSha1");

        // When
        String sha = commit.getSha();

        // Then
        assertEquals("commitSha1", sha);
    }
}