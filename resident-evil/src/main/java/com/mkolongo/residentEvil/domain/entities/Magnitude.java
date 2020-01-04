package com.mkolongo.residentEvil.domain.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Magnitude {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    private final String name;
}
