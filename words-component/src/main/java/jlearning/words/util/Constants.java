package jlearning.words.util;

import org.springframework.http.ResponseEntity;

import java.time.format.DateTimeFormatter;

import static java.lang.ThreadLocal.withInitial;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.springframework.http.ResponseEntity.ok;

public final class Constants {

    private Constants() {
    }

    public static ResponseEntity OK = ok().build();

    public static final ThreadLocal<DateTimeFormatter> COMMON_DATETIME_FORMAT
            = withInitial(() -> ofPattern("dd/MM/yyyy"));

}
