package telecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import telecom.domain.SysUser;

import java.util.Optional;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    Optional<SysUser> findOneWithRolesByUsername(String username);
}
