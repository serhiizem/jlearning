package telecom.authserver;

import com.google.common.collect.ImmutableMap;
import telecom.authserver.exceptions.MalformedRoleNameException;
import telecom.authserver.exceptions.RoleNotFoundException;

public enum UserRole {

    CSR,
    SERVICE_MANAGER,
    USER;

    private static final String SPRING_SECURITY_ROLE_PREFIX = "ROLE_";

    private static final ImmutableMap<String, UserRole> userRolesMapper =
            ImmutableMap.<String, UserRole>builder()
                    .put("CSR", CSR)
                    .put("SERVICE_MANAGER", SERVICE_MANAGER)
                    .put("USER", USER)
                    .build();

    public static UserRole fromRoleName(String roleName) {
        roleName = extractMeaningfulRoleName(roleName);
        UserRole userRole = userRolesMapper.get(roleName);
        if (userRole == null) {
            throw new RoleNotFoundException(roleName);
        }
        return userRole;
    }

    private static String extractMeaningfulRoleName(String roleName) {
        if (!roleName.startsWith(SPRING_SECURITY_ROLE_PREFIX))
            throw new MalformedRoleNameException(roleName);
        return roleName.split(SPRING_SECURITY_ROLE_PREFIX)[1];
    }
}
