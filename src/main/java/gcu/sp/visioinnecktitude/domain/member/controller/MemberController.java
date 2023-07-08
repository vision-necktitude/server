package gcu.sp.visioinnecktitude.domain.member.controller;

import gcu.sp.visioinnecktitude.common.response.BaseResponse;
import gcu.sp.visioinnecktitude.common.response.BaseResponseStatus;
import gcu.sp.visioinnecktitude.domain.member.dto.request.CreateMemberRequest;
import gcu.sp.visioinnecktitude.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "유저")
@RequestMapping(value = "/member")
public class MemberController {
    private final MemberService memberService;
    @PostMapping(value = "/signup")
    @Operation(summary = "회원가입 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "1000", description = "성공"),
            @ApiResponse(responseCode = "2021", description = "이미 존재하는 닉네임 입니다.", content = @Content),
            @ApiResponse(responseCode = "2022", description = "이미 존재하는 유저 아이디 입니다.", content = @Content),
            @ApiResponse(responseCode = "4001", description = "서버 오류입니다.", content = @Content)
    })
    public ResponseEntity<BaseResponse<String>> signUp(@RequestBody CreateMemberRequest createMemberRequest) {
            memberService.createMember(createMemberRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse<>(BaseResponseStatus.SUCCESS));
    }
}