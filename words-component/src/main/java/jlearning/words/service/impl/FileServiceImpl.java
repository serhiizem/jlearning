package telecom.service.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import telecom.service.FileService;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

@Slf4j
@Service
@PropertySource("s3.properties")
public class FileServiceImpl implements FileService {

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

    @SneakyThrows(InterruptedException.class)
    private void uploadFileToAmazon(String pathToFile) {
        String accessKeyId = System.getenv().get("ACCESS_KEY_ID");
        String secretAccessKey = System.getenv().get("SECRET_ACCESS_KEY");
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKeyId, secretAccessKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();

        TransferManager tm = TransferManagerBuilder.standard()
                .withS3Client(s3Client)
                .build();

        Upload upload = tm.upload(bucketName, "", new File(pathToFile));
        upload.waitForCompletion();
    }
}
