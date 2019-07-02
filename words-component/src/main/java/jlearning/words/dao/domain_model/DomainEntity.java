package jlearning.words.dao.domain_model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
@NoArgsConstructor
public class DomainEntity {
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;
}
