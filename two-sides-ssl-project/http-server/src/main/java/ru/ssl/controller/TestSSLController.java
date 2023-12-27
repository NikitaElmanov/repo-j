package ru.ssl.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ssl")
public class TestSSLController {

    @GetMapping
    public ResponseEntity<String> getData() {
        return ResponseEntity.ok("Hello from ssl connection !!!");
    }

}
