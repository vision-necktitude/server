package gcu.sp.visioinnecktitude.domain.member.repository;

import gcu.sp.visioinnecktitude.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByMemberId(String id);

    boolean existsByNickname(String nickname);
}
