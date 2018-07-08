package telecom.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class SysAuthority extends AbstractAuditingEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String value;
}
