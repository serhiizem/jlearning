package telecom.tariffs.model;

import lombok.Data;
import lombok.SneakyThrows;
import telecom.tariffs.dao.PersistentEntity;

import java.sql.ResultSet;

@Data
public class DomainEntity implements PersistentEntity {
    private Long id;

    @SneakyThrows
    public DomainEntity(ResultSet resultSet) {
        this.id = resultSet.getLong("id");
    }
}
