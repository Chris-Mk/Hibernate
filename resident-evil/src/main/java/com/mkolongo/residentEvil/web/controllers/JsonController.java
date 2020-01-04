package com.mkolongo.residentEvil.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonController {

    @GetMapping(value = "/json", produces = "application/json")
    public String json() {
        return """
               {
                    "name":"John",
                    "age":31,
                    "city":"New York"
               }
               """;
    }
}
