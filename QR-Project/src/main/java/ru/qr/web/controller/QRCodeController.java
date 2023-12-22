package ru.qr.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import ru.qr.model.QRCodeContent;
import ru.qr.service.QRCodeService;
import ru.qr.web.api.CommonInfoDto;
import ru.qr.web.api.QRCodeContentDto;
import ru.qr.web.mapper.QRCodeMapper;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/qr")
public class QRCodeController {

    QRCodeService qrCodeService;
    QRCodeMapper qrCodeMapper;

    @GetMapping("/{id}")
    public ResponseEntity<CommonInfoDto> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(qrCodeService.getOne(id));
    }

    @GetMapping
    public ResponseEntity<List<CommonInfoDto>> getAll() {
        return ResponseEntity.ok(qrCodeService.getAll());
    }

    @PostMapping
    public void save(@RequestBody QRCodeContentDto dto) {
        QRCodeContent qrCodeContent = qrCodeMapper.toEntity(dto);
        qrCodeService.save(qrCodeContent);
    }

    @GetMapping("/stream/{id}")
    public ResponseEntity<StreamingResponseBody> handleRequest(@PathVariable UUID id) {
        InputStream inputStream = qrCodeService.getPicture(id);
        return ResponseEntity.ok(inputStream::transferTo);
    }

}
