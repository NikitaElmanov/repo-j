package ru.test.crypto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSSLController {

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test test aaaa!!!");
    }
}
