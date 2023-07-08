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
public class LoginRequest {
    @Schema(description = "아이디", example = "dsk0820")
    private String id;
    @Schema(description = "비밀번호", example = "1234")
    private String password;
}
