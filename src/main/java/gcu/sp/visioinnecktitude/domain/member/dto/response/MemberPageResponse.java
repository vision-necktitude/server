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
public class MemberPageResponse {
    @Schema(description = "name", example = "동석")
    String name;
    @Schema(description = "id", example = "dsk0820")
    String id;
}
