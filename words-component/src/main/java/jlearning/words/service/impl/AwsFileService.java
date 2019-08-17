package jlearning.words.service.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import jlearning.words.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.File;

import static com.amazonaws.services.s3.model.CannedAccessControlList.PublicRead;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Slf4j
@Service
@PropertySource("s3.properties")
public class AwsFileService implements FileService {

    private static final String AWS_USER_ACCESS_KEY_ID = "ACCESS_KEY_ID";
    private static final String AWS_USER_SECRET_KEY = "SECRET_ACCESS_KEY";
    private static final String AWS_OBJECT_CONTENT_TYPE = "plain/text";

    @Value("${bucket.name}")
    private String bucketName;
    @Value("${folder.name}")
    private String folderName;
    @Value("${region}")
    private String region;

    @Override
    public String upload(File file) {
        AmazonS3 s3Client = createClient();

        String destination = folderName + "/file-" + randomAlphabetic(10);
        PutObjectRequest request = createPutRequest(file, destination);

        s3Client.putObject(request);

        return destination;
    }

    private AmazonS3 createClient() {
        String accessKeyId = System.getenv().get(AWS_USER_ACCESS_KEY_ID);
        String secretAccessKey = System.getenv().get(AWS_USER_SECRET_KEY);
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKeyId, secretAccessKey);

        return AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }

    private PutObjectRequest createPutRequest(File file, String fileKey) {
        PutObjectRequest request = new PutObjectRequest(bucketName, fileKey, file);
        request.withCannedAcl(PublicRead);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(AWS_OBJECT_CONTENT_TYPE);
        request.setMetadata(metadata);

        return request;
    }
}
