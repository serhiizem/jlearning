package jlearning.words.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Word {
    private String value;
    private List<String> translations;
    private String base64Image;
    private String fileLocation;
}
