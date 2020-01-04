package com.mkolongo.residentEvil.domain.models.view;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserViewModel {

    private Long id;
    private String username;
    private Set<String> authorities;
}
