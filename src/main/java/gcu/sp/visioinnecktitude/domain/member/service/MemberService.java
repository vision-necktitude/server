package gcu.sp.visioinnecktitude.domain.member.service;

import gcu.sp.visioinnecktitude.domain.member.dto.request.CreateMemberRequest;
import gcu.sp.visioinnecktitude.domain.member.dto.request.LoginRequest;

public interface MemberService {
    void createMember(CreateMemberRequest createMemberRequest);

    Long loginMember(LoginRequest loginRequest);
}
