package ru.x5.keycloaktest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('admin_client')")
    public ResponseEntity<String> testAdmin() {
        return ResponseEntity.ok("hello, \"ADMIN\"!");
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('user_client', 'admin_client')")
    public ResponseEntity<String> testUser() {
        return ResponseEntity.ok("hello, \"USER\"!");
    }

}
