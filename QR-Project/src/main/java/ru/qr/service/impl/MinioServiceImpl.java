package ru.qr.service.impl;

import io.minio.*;
import liquibase.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.qr.props.MinioProperties;
import ru.qr.service.MinioService;
import ru.qr.web.exception.FileUploadException;

import java.io.InputStream;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class MinioServiceImpl implements MinioService {

    MinioClient minioClient;
    MinioProperties minioProperties;

    @Override
    public void saveFile(InputStream inputStream, String fileName) {
        try {
            createBucket();
        } catch (Exception e) {
            throw new FileUploadException("Can not create bucket in minio " + e.getMessage());
        }

        if (StringUtil.isEmpty(fileName)) {
            throw new FileUploadException("File name for Minio is null or empty");
        }

        save(inputStream, fileName);
    }

    @SneakyThrows
    private void createBucket() {
        boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(minioProperties.getBucket())
                .build());

        if (!bucketExists) {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(minioProperties.getBucket())
                    .build());
        }
    }

    @SneakyThrows
    private void save(InputStream inputStream, String fileName) {
        minioClient.putObject(PutObjectArgs.builder()
                .stream(inputStream, inputStream.available(), -1)
                .bucket(minioProperties.getBucket())
                .object(fileName)
                .build());
    }

    @Override
    @SneakyThrows
    public InputStream getFile(String fileName) {
        return minioClient.getObject(GetObjectArgs.builder()
                .bucket(minioProperties.getBucket())
                .object(fileName)
                .build());
    }

}
