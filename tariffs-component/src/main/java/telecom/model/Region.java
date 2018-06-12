package telecom.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;

import java.sql.ResultSet;

@Data
@EqualsAndHashCode(callSuper = true)
public class Region extends DomainEntity {
    private String name;

    @SneakyThrows
    public Region(ResultSet resultSet) {
        super(resultSet);
        this.name = resultSet.getString("name");
    }
}
