package gcu.sp.visioinnecktitude.domain.member.service;

import gcu.sp.visioinnecktitude.common.exceptions.BaseException;
import gcu.sp.visioinnecktitude.domain.member.dto.request.CreateMemberRequest;
import gcu.sp.visioinnecktitude.domain.member.dto.request.LoginRequest;
import gcu.sp.visioinnecktitude.domain.member.entity.Member;
import gcu.sp.visioinnecktitude.domain.member.entity.PasswordLogin;
import gcu.sp.visioinnecktitude.domain.member.repository.MemberRepository;
import gcu.sp.visioinnecktitude.domain.member.repository.PasswordLoginRepository;
import gcu.sp.visioinnecktitude.domain.member.vo.State;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static gcu.sp.visioinnecktitude.common.response.BaseResponseStatus.*;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final PasswordLoginRepository passwordLoginRepository;

    @Transactional
    @Override
    public void createMember(CreateMemberRequest createMemberRequest) {
        Member member = Member.builder()
                .name(createMemberRequest.getName())
                .sex(createMemberRequest.getSex())
                .birthday(LocalDate.parse(createMemberRequest.getBirthday()))
                .build();
        PasswordLogin passwordLogin = PasswordLogin.builder()
                .password(passwordEncode(createMemberRequest.getPassword()))
                .loginId(createMemberRequest.getId())
                .build();
        member.setPasswordLogin(passwordLogin);
        if (checkDuplicateName(createMemberRequest.getName()))
            throw new BaseException(EXIST_NICKNAME);
        if (checkDuplicateId(createMemberRequest.getId()))
            throw new BaseException(EXIST_MEMBER_ID);
        memberRepository.save(member);
    }

    @Override
    public Long loginMember(LoginRequest loginRequest) {
        PasswordLogin passwordLogin = passwordLoginRepository.findByLoginIdAndState(loginRequest.getId(), State.A).orElseThrow(() -> new BaseException(NOT_EXIST_ID_OR_PASSWORD));
        //비밀번호가 다를 경우 throw
        if (!checkPassword(loginRequest.getPassword(), passwordLogin.getPassword()))
            throw new BaseException(NOT_EXIST_ID_OR_PASSWORD);
        return passwordLogin.getMember().getId();
    }

    public boolean checkDuplicateName(String name) {
        return memberRepository.existsByName(name);
    }

    public boolean checkDuplicateId(String id) {
        return passwordLoginRepository.existsByLoginId(id);
    }

    public boolean checkPassword(String password1, String password2) {
        return passwordEncoder.matches(password1, password2);
    }
    public String passwordEncode(String password) {
        return passwordEncoder.encode(password);
    }
}