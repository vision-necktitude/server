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
public class DuplicateNameRequest {
    @Schema(description = "이름", example = "dongseok")
    private String name;
}
