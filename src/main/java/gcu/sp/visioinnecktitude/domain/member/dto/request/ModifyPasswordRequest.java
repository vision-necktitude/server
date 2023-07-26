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
public class ModifyPasswordRequest {
    @Schema(description = "비밀번호", example = "dongseok")
    private String password;
}
