package telecom.util;

import java.time.format.DateTimeFormatter;

import static java.lang.ThreadLocal.withInitial;
import static java.time.format.DateTimeFormatter.ofPattern;

public final class Constants {

    private Constants() {
    }

    public static final ThreadLocal<DateTimeFormatter> COMMON_DATETIME_FORMAT
            = withInitial(() -> ofPattern("dd/MM/yyyy"));

}
