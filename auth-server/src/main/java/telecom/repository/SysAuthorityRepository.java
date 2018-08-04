package telecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import telecom.domain.SysAuthority;

public interface SysAuthorityRepository extends JpaRepository<SysAuthority, Long> {
}
