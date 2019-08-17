package jlearning.words.conversion;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import jlearning.words.conversion.ExtendedConversionService;

import java.util.Collection;
import java.util.List;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.empty;

@Service
@RequiredArgsConstructor
public class ExtendedConversionServiceImpl implements ExtendedConversionService {

    private final ConversionService conversionService;

    @Override
    public <T, R> List<R> convert(List<T> target, Class<R> resultClass) {
        return ofNullable(target)
                .map(Collection::stream)
                .orElse(empty())
                .map(item -> conversionService.convert(item, resultClass))
                .collect(toList());
    }
}
