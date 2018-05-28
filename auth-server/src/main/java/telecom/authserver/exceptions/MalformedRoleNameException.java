package telecom.authserver.exceptions;

import static java.lang.String.format;

public class MalformedRoleNameException extends RuntimeException {

    private static final String EXAMPLE_ROLE_STRING_FORMAT = "ROLE_USER";

    public MalformedRoleNameException(String roleName) {
        super(format("Role name should start with ROLE_ prefix." +
                        "Received role string: %s. Expected format: %s",
                roleName, EXAMPLE_ROLE_STRING_FORMAT));
    }
}
