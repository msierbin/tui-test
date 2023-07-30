package com.example.demo.adapters.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GitHubOwnerTest {

    @Test
    public void testSettersAndGetters() {
        // Given
        GitHubOwner owner = new GitHubOwner();

        // When
        owner.setLogin("ownerLogin");

        // Then
        assertEquals("ownerLogin", owner.getLogin());
    }

}