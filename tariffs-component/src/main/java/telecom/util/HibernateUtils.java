package telecom.util;

import org.hibernate.Query;

import java.util.List;

public class HibernateUtils {

    @SuppressWarnings("unchecked")
    public static <T> List<T> listAndCast(Query q) {
        return q.list();
    }
}
