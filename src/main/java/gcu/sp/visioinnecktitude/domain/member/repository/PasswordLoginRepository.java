package gcu.sp.visioinnecktitude.domain.member.repository;

import gcu.sp.visioinnecktitude.domain.member.entity.PasswordLogin;
import gcu.sp.visioinnecktitude.domain.member.vo.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordLoginRepository extends JpaRepository<PasswordLogin, Long> {
    Optional<PasswordLogin> findByLoginIdAndState(String id, State state);

    boolean existsByLoginId(String id);
}
