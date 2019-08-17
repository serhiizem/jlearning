package jlearning.words.service;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;

import static javax.xml.bind.DatatypeConverter.parseBase64Binary;

public interface FileService {

    String TEMP_FILE_NAME = "tmp.file";

    void upload(File file);

    @SneakyThrows
    default File createFileFromBase64Content(String content) {
        if (content == null) {
            throw new IllegalArgumentException("Content should not be null");
        }
        if (!content.substring(0, 10).equals("data:image")) {
            throw new IllegalArgumentException("Argument is not a valid base64 image representation." +
                    "Input string should start with \"data:image\"");
        }

        String base64Image = content.split(",")[1];
        byte[] imageBytes = parseBase64Binary(base64Image);

        File tempFile = new File(TEMP_FILE_NAME);
        FileUtils.writeByteArrayToFile(tempFile, imageBytes);

        return tempFile;
    }
}
