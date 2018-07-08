package telecom.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import telecom.model.Region;
import telecom.service.RegionService;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @GetMapping("/regions")
    @PreAuthorize("hasAuthority('query-demo')")
    public List<Region> getAllRegions() {
        return regionService.findAll()
                .stream().sorted(comparing(Region::getName))
                .collect(toList());
    }
}