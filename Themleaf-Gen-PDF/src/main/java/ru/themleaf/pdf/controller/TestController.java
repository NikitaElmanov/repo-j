package ru.themleaf.pdf.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TestController {

    private static final BouncyCastleProvider PROVIDER = new BouncyCastleProvider();

    static {
        Security.addProvider(PROVIDER);
    }

    @PostMapping(value = "/cert")
    public ResponseEntity<?> getCert(@RequestPart("cert") MultipartFile cert) throws IOException, CertificateException {

        X509Certificate certX509 =
                (X509Certificate) CertificateFactory.getInstance("X.509")
                        .generateCertificate(cert.getInputStream());

        X509CertificateHolder holder = new JcaX509CertificateHolder(certX509);
        X509Certificate certificate = new JcaX509CertificateConverter()
                .setProvider(PROVIDER)
                .getCertificate(holder);

        return ResponseEntity.ok(certX509.toString());
    }

    public static void main(String[] args) throws TransformerException, ParserConfigurationException, IOException,
                                                  SAXException {
        String unformattedXml = """
                <tns:booking>
                    <tns:bookingID>115</tns:bookingID>
                    <tns:type>double</tns:type>
                    <tns:amount>1</tns:amount>
                    <tns:stayPeriod>
                        <tns:checkin>
                            <tns:year>2013</tns:year>
                            <tns:month>11</tns:month>
                            <tns:date>14</tns:date>
                        </tns:checkin>
                        <tns:checkout>
                            <tns:year>2013</tns:year>
                            <tns:month>11</tns:month>
                            <tns:date>16</tns:date>
                        </tns:checkout>
                    </tns:stayPeriod>
                </tns:booking>""";

        final var xml = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(new ByteArrayInputStream(unformattedXml.getBytes()))
                .getDocumentElement();

        final var transformerFactory = TransformerFactory.newInstance();
        var transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty("indent", "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        var source = new DOMSource(xml);
        var result = new StreamResult(System.out);
        transformer.transform(source, result);
    }

}
