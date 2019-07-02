package jlearning.words.dao.domain_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "WORDS")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WordDto extends DomainEntity {
    private String value;
    private int numberOfErrors;
    private String userRef;

    @ElementCollection
    @CollectionTable(
            name = "TRANSLATIONS",
            joinColumns = {
                    @JoinColumn(name = "word_id", referencedColumnName = "id")
            })
    @Column(name = "translation")
    private List<String> translations;

    public WordDto(String value, String userRef, List<String> translations) {
        this.value = value;
        this.userRef = userRef;
        this.translations = translations;
    }
}
