package ru.qr.web.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@Schema(name = "QR code content DTO")
@FieldDefaults(level = PRIVATE)
public class QRCodeContentDto {

    @Schema(description = "QR code content", example = "Hello world !")
    String content;

}
