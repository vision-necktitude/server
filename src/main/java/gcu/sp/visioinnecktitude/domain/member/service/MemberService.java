package gcu.sp.visioinnecktitude.domain.member.service;

import gcu.sp.visioinnecktitude.domain.member.dto.request.*;

public interface MemberService {
    void createMember(CreateMemberRequest createMemberRequest);

    Long loginMember(LoginRequest loginRequest);

    boolean isValidName(DuplicateNameRequest duplicateNameRequest);
    boolean checkDuplicateName(String name);

    void modifyName(Long memberId, ModifyNameRequest modifyNameRequest);

    void modifyPassword(Long memberId, ModifyPasswordRequest modifyPasswordRequest);
}
