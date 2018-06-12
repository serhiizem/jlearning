package telecom.model;

import lombok.Data;
import lombok.SneakyThrows;
import telecom.dao.PersistentEntity;

import java.sql.ResultSet;

@Data
public class DomainEntity implements PersistentEntity {
    private Long id;

    @SneakyThrows
    public DomainEntity(ResultSet resultSet) {
        this.id = resultSet.getLong("id");
    }
}
