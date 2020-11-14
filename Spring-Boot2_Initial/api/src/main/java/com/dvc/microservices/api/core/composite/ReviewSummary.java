package com.dvc.microservices.api.core.composite;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewSummary {

    private final int reviewId;
    private final String author;
    private final String subject;
}
