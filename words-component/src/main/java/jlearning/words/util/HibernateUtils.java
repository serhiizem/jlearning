package jlearning.words.util;

import org.hibernate.query.Query;

import java.util.List;

public class HibernateUtils {

    @SuppressWarnings("unchecked")
    public static <T> List<T> listAndCast(Query q) {
        return q.list();
    }
}
