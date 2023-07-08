package gcu.sp.visioinnecktitude.domain.member.repository;

import gcu.sp.visioinnecktitude.domain.member.entity.Member;
import gcu.sp.visioinnecktitude.domain.member.vo.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByMemberId(String id);

    boolean existsByNickname(String nickname);

    Optional<Member> findByMemberIdAndState(String id, State state);
}
