package jlearning.words.service.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import jlearning.words.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

@Slf4j
@Service
@PropertySource("s3.properties")
public class AwsFileService implements FileService {

    private static final String AWS_USER_ACCESS_KEY_ID = "ACCESS_KEY_ID";
    private static final String AWS_USER_SECRET_KEY = "SECRET_ACCESS_KEY";

    @Value("${bucket.name}")
    private String bucketName;
    @Value("${s3.url}")
    private String s3Url;
    @Value("${region}")
    private String region;

    @Override
    public void upload(String content, String path) {
        if (content != null && content.substring(0, 10).equals("data:image")) {
            String base64Image = content.split(",")[1];
            byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);
            BufferedImage img;
            try {
                img = ImageIO.read(new ByteArrayInputStream(imageBytes));
                File file = new File("picture." + content.split(";")[0].split("/")[1]);
                ImageIO.write(img, content.split(";")[0].split("/")[1], file);
                uploadFileToAmazon(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadFileToAmazon(String pathToFile) {
        AmazonS3 s3Client = createClient();

        File file = new File(pathToFile);
        PutObjectRequest request = createPutRequest(file);

        s3Client.putObject(request);
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

    private PutObjectRequest createPutRequest(File file) {
        String fileKey = RandomStringUtils.randomAlphabetic(10);
        PutObjectRequest request = new PutObjectRequest(bucketName, "word-hints/file-" + fileKey, file);
        request.withCannedAcl(CannedAccessControlList.PublicRead);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("plain/text");
        request.setMetadata(metadata);

        return request;
    }
}
