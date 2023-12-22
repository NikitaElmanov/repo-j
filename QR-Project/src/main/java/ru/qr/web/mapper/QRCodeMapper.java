package ru.qr.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.qr.model.QRCode;
import ru.qr.model.QRCodeContent;
import ru.qr.web.api.CommonInfoDto;
import ru.qr.web.api.QRCodeContentDto;

@Mapper
public interface QRCodeMapper extends CommonMapper<QRCodeContent, QRCodeContentDto> {

    @Mapping(target = "id", source = "qrCode.id")
    @Mapping(target = "size", source = "qrCode.size")
    @Mapping(target = "content", source = "content")
    CommonInfoDto mapConstruct(QRCode qrCode, String content);

}
