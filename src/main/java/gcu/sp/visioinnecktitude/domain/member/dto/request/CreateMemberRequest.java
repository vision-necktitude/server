package gcu.sp.visioinnecktitude.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberRequest {
    @Schema(description = "이름", example = "dongseok")
    private String name;
    @Schema(description = "성별", example = "female or male")
    @Pattern(regexp = "female|male", message = "유효한 성별이 아닙니다")
    private String sex;
    @Schema(description = "생일", example = "1999-08-20")
    @Pattern(regexp = "^(19[0-9][0-9]|20[0-9][0-9])-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$", message = "년월일 형식(yyyy-MM-DD)에 맞지 않습니다")
    private String birthday;
    @Schema(description = "아이디", example = "dsk0820")
    private String id;
    @Schema(description = "비밀번호", example = "1234")
    private String password;
}