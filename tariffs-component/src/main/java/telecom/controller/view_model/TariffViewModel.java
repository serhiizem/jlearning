package telecom.controller.view_model;

import lombok.Data;

@Data
public class TariffViewModel {
    private String name;
    private String status;
    private String creationDate;
    private boolean isCorporate;
}
