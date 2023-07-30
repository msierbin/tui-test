package com.example.demo.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BranchTest {

    @Test
    public void testBranchBuilder() {
        // Given
        String expectedName = "branch1";
        String expectedLastCommitSha = "commitSha1";

        // When
        Branch branch = Branch.builder()
                .name(expectedName)
                .lastCommitSha(expectedLastCommitSha)
                .build();

        // Then
        assertEquals(expectedName, branch.getName());
        assertEquals(expectedLastCommitSha, branch.getLastCommitSha());
    }

    @Test
    public void testBranchSettersAndGetters() {
        // Given
        String expectedName = "branch1";
        String expectedLastCommitSha = "commitSha1";

        // When
        Branch branch = Branch.builder().name(expectedName).lastCommitSha(expectedLastCommitSha).build();

        // Then
        assertEquals(expectedName, branch.getName());
        assertEquals(expectedLastCommitSha, branch.getLastCommitSha());
    }
}