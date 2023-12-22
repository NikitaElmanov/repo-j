package ru.qr.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
@Tag(name = "QR code Controller", description = "This controller serves all requests concerning QR code")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/qr")
public class QRCodeController {

    QRCodeService qrCodeService;
    QRCodeMapper qrCodeMapper;

    @GetMapping("/{id}")
    @Operation(summary = "Gives QR code data by id")
    public ResponseEntity<CommonInfoDto> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(qrCodeService.getOne(id));
    }

    @GetMapping
    @Operation(summary = "Gives all QR codes data")
    public ResponseEntity<List<CommonInfoDto>> getAll() {
        return ResponseEntity.ok(qrCodeService.getAll());
    }

    @PostMapping
    @Operation(summary = "Take string content to convert into QR code")
    public void save(@RequestBody QRCodeContentDto dto) {
        QRCodeContent qrCodeContent = qrCodeMapper.toEntity(dto);
        qrCodeService.save(qrCodeContent);
    }

    @GetMapping("/stream/{id}")
    @Operation(summary = "Gives picture of QR code by id")
    public ResponseEntity<StreamingResponseBody> getPicture(@PathVariable UUID id) {
        InputStream inputStream = qrCodeService.getPicture(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(inputStream::transferTo);
    }

    @PostMapping(value = "/content",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Gives content from QR code")
    public ResponseEntity<QRCodeContentDto> getContentByImage(@RequestParam(value = "picture")
                                                              MultipartFile picture) {
        String content = qrCodeService.getContentByPicture(picture);

        QRCodeContentDto contentDto = new QRCodeContentDto();
        contentDto.setContent(content);

        return ResponseEntity.ok(contentDto);
    }

}
