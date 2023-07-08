package gcu.sp.visioinnecktitude.domain.member.service;

import gcu.sp.visioinnecktitude.domain.member.dto.request.CreateMemberRequest;

public interface MemberService {
    void createMember(CreateMemberRequest createMemberRequest);
}
