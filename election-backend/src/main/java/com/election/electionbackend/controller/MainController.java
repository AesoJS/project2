package com.election.electionbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;


@RestController
public class MainController {

    @GetMapping(path = "/test", produces = "application/json")
    public Map<String, String> getTestRoot() {
        // Create a JSON-like response
        Map<String, String> response = new HashMap<>();
        response.put("message", "");
        return response;
    }
}
