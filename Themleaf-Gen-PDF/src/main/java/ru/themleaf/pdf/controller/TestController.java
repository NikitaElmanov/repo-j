package ru.themleaf.pdf.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TestController {

    @PostMapping(value = "/cert")
    public ResponseEntity<String> getCert(@RequestPart("cert") MultipartFile cert) {

        System.out.println(cert);
        return ResponseEntity.ok(cert.getOriginalFilename());
    }

}
