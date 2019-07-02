package telecom.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Word {
    private String value;
    private List<String> translations;
}
