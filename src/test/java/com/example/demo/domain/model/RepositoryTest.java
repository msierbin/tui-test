package com.example.demo.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class RepositoryTest {

    @Test
    public void testBuilder() {
        // Given
        Branch branch1 = Branch.builder()
                .name("branch1")
                .lastCommitSha("commitSha1")
                .build();

        Branch branch2 = Branch.builder()
                .name("branch2")
                .lastCommitSha("commitSha2")
                .build();

        List<Branch> branches = new ArrayList<>();
        branches.add(branch1);
        branches.add(branch2);

        // When
        Repository repository = Repository.builder()
                .name("repository")
                .ownerLogin("ownerLogin")
                .branches(branches)
                .build();

        // Then
        assertEquals("repository", repository.getName());
        assertEquals("ownerLogin", repository.getOwnerLogin());
        assertEquals(2, repository.getBranches().size());
        assertEquals("branch1", repository.getBranches().get(0).getName());
        assertEquals("branch2", repository.getBranches().get(1).getName());
    }

    @Test
    public void testSettersAndGetters() {
        // Given
        Branch branch = Branch.builder()
                .name("branch1")
                .lastCommitSha("commitSha1")
                .build();
        List<Branch> branches = new ArrayList<>();
        branches.add(branch);

        // When
        Repository repository = Repository.builder()
                .name("repository")
                .ownerLogin("ownerLogin")
                .branches(branches)
                .build();

        // Then
        assertEquals("repository", repository.getName());
        assertEquals("ownerLogin", repository.getOwnerLogin());
        assertEquals(1, repository.getBranches().size());
        assertEquals("branch1", repository.getBranches().get(0).getName());
    }
}