package com.mkolongo.residentEvil.domain.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Creator {
    CORP("Corp"),
    MORT("Mort");

    private final String name;
}
