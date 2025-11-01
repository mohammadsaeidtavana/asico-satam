package com.asico.hr.miniIo;

import io.minio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MiniIoService {

    private final MinioClient minioClient;

    @Value("${minio.url}")
    private String baseUrl;

    /**
     * آپلود فایل به MinIO با bucket برابر کدملی
     */
    public String uploadFile(MultipartFile file, String codemeli,String fullFileName) {
        if (file == null || file.isEmpty())
            throw new IllegalArgumentException("فایل نباید خالی باشد");
        if (codemeli == null || codemeli.isBlank())
            throw new IllegalArgumentException("کدملی نباید خالی باشد");

        try {
            String bucketName = codemeli.trim();

            // بررسی وجود باکت و ایجاد در صورت نیاز
            boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!exists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            String policyJson = buildPublicReadPolicy(bucketName);
            minioClient.setBucketPolicy(
                    SetBucketPolicyArgs.builder()
                            .bucket(bucketName)
                            .config(policyJson)
                            .build()
            );

            String originalName = file.getOriginalFilename();
            String extension = "";

            if (originalName != null && originalName.contains(".")) {
                extension = originalName.substring(originalName.lastIndexOf(".") + 1);
            }
            // تولید نام یکتا برای فایل
            String fileName = fullFileName+"."+extension;

            try (InputStream inputStream = file.getInputStream()) {
                minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket(bucketName)
                                .object(fileName)
                                .stream(inputStream, file.getSize(), -1)
                                .contentType(file.getContentType())
                                .build()
                );
            }

            // آدرس نهایی فایل
            return baseUrl + "/" + bucketName + "/" + fileName;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("خطا در آپلود فایل به MinIO", e);
        }
    }

    private String buildPublicReadPolicy(String bucketName) {
        // قالب policy سازگار با S3/MinIO
        return "{\n" +
                "  \"Version\": \"2012-10-17\",\n" +
                "  \"Statement\": [\n" +
                "    {\n" +
                "      \"Effect\": \"Allow\",\n" +
                "      \"Principal\": {\"AWS\": [\"*\"]},\n" +
                "      \"Action\": [\"s3:GetObject\"],\n" +
                "      \"Resource\": [\"arn:aws:s3:::" + bucketName + "/*\"]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }
}