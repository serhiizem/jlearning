package telecom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import telecom.controller.view_model.TariffViewModel;
import telecom.model.Tariff;
import telecom.service.ExtendedConversionService;
import telecom.service.TariffService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TariffController {

    private final ExtendedConversionService conversionService;
    private final TariffService tariffService;

    @GetMapping("/available-tariffs")
    public List<TariffViewModel> getTariffs() {
        List<Tariff> allTariffs = tariffService.findAll();
        return conversionService.convert(allTariffs, TariffViewModel.class);
    }
}
