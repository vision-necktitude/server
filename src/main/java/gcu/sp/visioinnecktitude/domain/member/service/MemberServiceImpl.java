package gcu.sp.visioinnecktitude.domain.member.service;

import gcu.sp.visioinnecktitude.common.exceptions.BaseException;
import gcu.sp.visioinnecktitude.domain.member.dto.request.CreateMemberRequest;
import gcu.sp.visioinnecktitude.domain.member.entity.Member;
import gcu.sp.visioinnecktitude.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static gcu.sp.visioinnecktitude.common.response.BaseResponseStatus.EXIST_MEMBER_ID;
import static gcu.sp.visioinnecktitude.common.response.BaseResponseStatus.EXIST_NICKNAME;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public void createMember(CreateMemberRequest createMemberRequest) {
        Member member = Member.builder()
                .memberId(createMemberRequest.getId())
                .nickname(createMemberRequest.getNickname())
                .password(passwordEncode(createMemberRequest.getPassword()))
                .build();
        if (checkDuplicateNickname(createMemberRequest.getNickname()))
            throw new BaseException(EXIST_NICKNAME);
        if (checkDuplicateId(createMemberRequest.getId()))
            throw new BaseException(EXIST_MEMBER_ID);
        memberRepository.save(member);
    }

    public boolean checkDuplicateNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    public boolean checkDuplicateId(String id) {
        return memberRepository.existsByMemberId(id);
    }

    public String passwordEncode(String password) {
        return passwordEncoder.encode(password);
    }
}