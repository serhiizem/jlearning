package telecom.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import telecom.dao.PersistentEntity;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.ResultSet;

@Data
@MappedSuperclass
@NoArgsConstructor
public class DomainEntity implements PersistentEntity {
    @Id
    private Long id;
}
