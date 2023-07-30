package com.example.demo.domain.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Branch {
    private String name;
    private String lastCommitSha;
}
