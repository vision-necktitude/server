package gcu.sp.visioinnecktitude.domain.member.service;

import gcu.sp.visioinnecktitude.common.exceptions.BaseException;
import gcu.sp.visioinnecktitude.domain.member.dto.request.CreateMemberRequest;
import gcu.sp.visioinnecktitude.domain.member.dto.request.LoginRequest;
import gcu.sp.visioinnecktitude.domain.member.entity.Member;
import gcu.sp.visioinnecktitude.domain.member.repository.MemberRepository;
import gcu.sp.visioinnecktitude.domain.member.vo.State;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static gcu.sp.visioinnecktitude.common.response.BaseResponseStatus.*;

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

    @Override
    public Long loginMember(LoginRequest loginRequest) {
        Member member = memberRepository.findByMemberIdAndState(loginRequest.getId(), State.A).orElseThrow(()->new BaseException(NOT_EXIST_ID_OR_PASSWORD));
        //비밀번호가 다를 경우 throw
        if(!checkPassword(loginRequest.getPassword(),member.getPassword()))
            throw new BaseException(NOT_EXIST_ID_OR_PASSWORD);
        return member.getId();
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
    public boolean checkPassword(String password1, String password2){
        return passwordEncoder.matches(password1,password2);
    }
}