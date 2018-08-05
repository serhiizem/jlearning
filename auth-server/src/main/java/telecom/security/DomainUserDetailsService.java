package telecom.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import telecom.model.SecurityUser;
import telecom.repository.SysUserRepository;

import static java.lang.String.format;

@Slf4j
@Service("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String lowcaseUsername = username.toLowerCase();

        return sysUserRepository.findOneWithRolesByUsername(lowcaseUsername)
                .map(u -> new SecurityUser(u.getUsername(), u.getPassword(), u.getRoles()))
                .orElseThrow(() -> new UsernameNotFoundException(
                        format("No user was found by the given username: %s", lowcaseUsername)));
    }
}
