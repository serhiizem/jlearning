package telecom.service;

import java.util.List;

public interface ExtendedConversionService {
    <T, R> List<R> convert(List<T> target, Class<R> resultClass);
}
