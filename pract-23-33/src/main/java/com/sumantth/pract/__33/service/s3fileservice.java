package com.sumantth.pract.__33.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectAclRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.File;
import java.io.IOException;

@Service
public class s3fileservice {
/*
Steps to upload to aws s3

1) get aws package s3and authentication package
2) get crendentials of aws
3) aws authenticate
4) use s3 file upload
 */


    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    private S3Client s3Client;

    public s3fileservice(
            @Value("${aws.accesskeyId}") String accessKeyId,
            @Value("${aws.secretkey}") String secretKey,
            @Value("${aws.region}") String region) {

        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(accessKeyId, secretKey);
        s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
                .build();
    }

    public void uploadFileToS3(MultipartFile inputFile, String filename) throws S3Exception, SdkClientException, IOException {
        // Create put request
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .build();

        // Upload the file
        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputFile.getInputStream(), inputFile.getSize()));
    }

    // log file upload to s3
    public void logFileToS3(File inputFile, String filename) throws S3Exception, SdkClientException, IOException {
        // Create put request
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .build();

        // Upload the file
        s3Client.putObject(putObjectRequest, inputFile.toPath());
    }
}
