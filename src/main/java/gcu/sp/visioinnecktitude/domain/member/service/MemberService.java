package gcu.sp.visioinnecktitude.domain.member.service;

import gcu.sp.visioinnecktitude.domain.member.dto.request.CreateMemberRequest;
import gcu.sp.visioinnecktitude.domain.member.dto.request.DuplicateNameRequest;
import gcu.sp.visioinnecktitude.domain.member.dto.request.LoginRequest;
import gcu.sp.visioinnecktitude.domain.member.dto.request.ModifyNameRequest;

public interface MemberService {
    void createMember(CreateMemberRequest createMemberRequest);

    Long loginMember(LoginRequest loginRequest);

    boolean isValidName(DuplicateNameRequest duplicateNameRequest);
    boolean checkDuplicateName(String name);

    void modifyName(Long memberId, ModifyNameRequest modifyNameRequest);
}
