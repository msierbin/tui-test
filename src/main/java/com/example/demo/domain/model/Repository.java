package com.example.demo.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class Repository {
    private String name;
    private String ownerLogin;
    @Builder.Default
    private List<Branch> branches = new ArrayList<>();
}
