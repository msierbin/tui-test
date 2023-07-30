package com.example.demo.adapters.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GitHubCommitTest {

    @Test
    public void testSettersAndGetters() {
        // Given
        GitHubCommit commit = new GitHubCommit();

        // When
        commit.setSha("commitSha1");

        // Then
        assertEquals("commitSha1", commit.getSha());
    }

}