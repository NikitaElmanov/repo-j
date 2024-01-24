package ru.themleaf.pdf.service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Slf4j
@Service
public class DocumentGenerator {

    public StreamingResponseBody html2Pdf(String html) {

        try (var byteArrayOutputStream = new ByteArrayOutputStream()) {

            var pdfWriter = new PdfWriter(byteArrayOutputStream);

            var defaultFontProvider = new DefaultFontProvider(
                    false,
                    true,
                    false
            );

            var converterProperties = new ConverterProperties();
            converterProperties.setFontProvider(defaultFontProvider);

            HtmlConverter.convertToPdf(html, pdfWriter, converterProperties);

            return byteArrayOutputStream::writeTo;

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }

        return null;
    }
}
