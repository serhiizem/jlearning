package telecom.tariffs.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import telecom.tariffs.model.Region;
import telecom.tariffs.service.RegionService;

import java.util.List;

@Slf4j
@ResponseBody
@RequestMapping(value = "/regions")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @GetMapping
    public List<Region> getAllRegions() {
        List<Region> regions = regionService.findAll();
        log.info("Retrieving information regarding {} regions", regions.size());
        return regions;
    }
}