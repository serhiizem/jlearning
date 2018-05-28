package telecom.authserver.exceptions;

import static java.lang.String.format;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(String roleName) {
        super(format("Failed to resolve any role by the given string role representation: " +
                "%s", roleName));
    }
}
