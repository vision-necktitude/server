package gcu.sp.visioinnecktitude.domain.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogInResponse {
    @Schema(description = "JWT", example = "oqu3hotifnaebgfioaebgfoib")
    private String jwt;
}