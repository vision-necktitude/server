package gcu.sp.visioinnecktitude.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberRequest {
    @Schema(description = "닉네임", example = "dongseok")
    String nickname;
    @Schema(description = "아이디", example = "dsk0820")
    String id;
    @Schema(description = "아이디", example = "dsk0820")
    String password;
}
