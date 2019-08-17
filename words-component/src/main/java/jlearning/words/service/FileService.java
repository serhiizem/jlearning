package jlearning.words.service;

public interface FileService {
    void upload(String content, String path);
    void uploadFileToAmazon(String pathToFile);
}
