package telecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import telecom.domain.SysRole;

public interface SysRoleRepository extends JpaRepository<SysRole,Long> {
}
