package telecom.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Tariff extends DomainEntity {
    @Column(name = "tariff_name")
    private String name;
    @Column(name = "product_status")
    private String status;
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @Column(name = "is_corporate")
    private boolean isCorporate;
}
