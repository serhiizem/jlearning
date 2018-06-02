package telecom.tariffs.util;

public final class Queries {
    private Queries() {
    }

    /** ************************** Region management queries *********************************** **/
    public static final String CREATE_REGION_QUERY = "INSERT INTO region(name_region) VALUES (?) RETURNING id";
    public static final String GET_REGION_QUERY = "SELECT id, name_region FROM region WHERE id = ?";
    public static final String GET_ALL_REGIONS_QUERY = "SELECT id, name_region FROM region";
}
