package telecom.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import telecom.controller.view_model.TariffViewModel;
import telecom.model.Tariff;

import static java.util.Optional.ofNullable;
import static telecom.util.Constants.COMMON_DATETIME_FORMAT;

@Component
public class TariffToTariffViewModelConverter implements Converter<Tariff, TariffViewModel> {

    @Override
    public TariffViewModel convert(Tariff source) {
        TariffViewModel tariffViewModel = new TariffViewModel();
        tariffViewModel.setName(source.getName());
        tariffViewModel.setCorporate(source.isCorporate());

        String creationDateAsString = ofNullable(source.getCreationDate())
                .map(date -> date.format(COMMON_DATETIME_FORMAT.get()))
                .orElse(null);
        tariffViewModel.setCreationDate(creationDateAsString);

        tariffViewModel.setStatus(source.getStatus());

        return tariffViewModel;
    }
}
