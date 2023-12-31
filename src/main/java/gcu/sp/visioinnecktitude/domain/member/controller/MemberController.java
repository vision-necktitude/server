package gcu.sp.visioinnecktitude.domain.member.controller;

import gcu.sp.visioinnecktitude.common.config.security.jwt.JwtTokenProvider;
import gcu.sp.visioinnecktitude.common.response.BaseResponse;
import gcu.sp.visioinnecktitude.common.response.BaseResponseStatus;
import gcu.sp.visioinnecktitude.domain.member.dto.request.*;
import gcu.sp.visioinnecktitude.domain.member.dto.response.LogInResponse;
import gcu.sp.visioinnecktitude.domain.member.dto.response.MemberPageResponse;
import gcu.sp.visioinnecktitude.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "유저")
@RequestMapping(value = "/member")
@CrossOrigin
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping(value = "/signup")
    @Operation(summary = "회원가입 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "409", description = "이미 존재하는 닉네임 입니다. \t\n \t\n이미 존재하는 유저 아이디 입니다.", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버 오류입니다.", content = @Content)
    })
    public ResponseEntity<BaseResponse<String>> signup(@Valid @RequestBody CreateMemberRequest createMemberRequest) {
        memberService.createMember(createMemberRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse<>(BaseResponseStatus.SUCCESS));
    }

    @PostMapping(value = "/login")
    @Operation(summary = "로그인 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "409", description = "아이디 또는 비밀번호를 잘못 입력했습니다.", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버 오류입니다.", content = @Content)
    })
    public ResponseEntity<BaseResponse<LogInResponse>> login(@RequestBody LoginRequest loginRequest) {

        Long memberId = memberService.loginMember(loginRequest);
        LogInResponse logInResponse = new LogInResponse(jwtTokenProvider.createAccessToken(Long.toString(memberId)));
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(logInResponse));
    }

    @PostMapping(value = "/duplicate/name")
    @Operation(summary = "이름 중복체크 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "409", description = "중복된 이름입니다.", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버 오류입니다.", content = @Content)
    })
    public ResponseEntity<BaseResponse<LogInResponse>> isValidName(@RequestBody DuplicateNameRequest duplicateNameRequest) {

        if(memberService.isValidName(duplicateNameRequest))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new BaseResponse<>(BaseResponseStatus.EXIST_NICKNAME));
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(BaseResponseStatus.SUCCESS));
    }

    @PostMapping(value = "/modify/name")
    @Operation(summary = "이름 변경 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "409", description = "중복된 이름입니다.", content = @Content),
            @ApiResponse(responseCode = "409", description = "존재하지 않는 유저입니다.", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버 오류입니다.", content = @Content)
    })
    public ResponseEntity<BaseResponse<LogInResponse>> modifyName(@RequestBody ModifyNameRequest modifyNameRequest) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(loggedInUser.getName());
        if(memberService.checkDuplicateName(modifyNameRequest.getName()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new BaseResponse<>(BaseResponseStatus.EXIST_NICKNAME));
        memberService.modifyName(memberId,modifyNameRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(BaseResponseStatus.SUCCESS));
    }
    @PostMapping(value = "/modify/password")
    @Operation(summary = "비밀번호 변경 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "409", description = "존재하지 않는 유저입니다.", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버 오류입니다.", content = @Content)
    })
    public ResponseEntity<BaseResponse<LogInResponse>> modifyPassword(@RequestBody ModifyPasswordRequest modifyPasswordRequest) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(loggedInUser.getName());
        memberService.modifyPassword(memberId,modifyPasswordRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(BaseResponseStatus.SUCCESS));
    }
    @GetMapping(value = "/member-page")
    @Operation(summary = "유저 페이지 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "409", description = "존재하지 않는 유저입니다.", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버 오류입니다.", content = @Content)
    })
    public ResponseEntity<BaseResponse<MemberPageResponse>> getMemberPage() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(loggedInUser.getName());
        MemberPageResponse memberPageResponse = memberService.getMemberPage(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(memberPageResponse));
    }
}