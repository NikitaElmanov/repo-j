package ru.themleaf.pdf.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.thymeleaf.spring6.SpringTemplateEngine;
import ru.themleaf.pdf.model.Person;
import ru.themleaf.pdf.service.DataMapper;
import ru.themleaf.pdf.service.DocumentGenerator;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PdfController {

    DataMapper dataMapper;
    DocumentGenerator documentGenerator;
    SpringTemplateEngine springTemplateEngine;

    @PostMapping(value = "/get/pdf")
    public ResponseEntity<StreamingResponseBody> getPdf(@RequestBody List<Person> persons) {

        var context = dataMapper.setData(persons);

        var template = springTemplateEngine.process("template", context);

        var streamingResponseBody = documentGenerator.html2Pdf(template);

        return ResponseEntity.ok(streamingResponseBody);
    }

}
