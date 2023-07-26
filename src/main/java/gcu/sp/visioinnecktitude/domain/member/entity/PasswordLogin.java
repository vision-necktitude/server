package gcu.sp.visioinnecktitude.domain.member.entity;

import gcu.sp.visioinnecktitude.common.entitry.BaseEntity;
import gcu.sp.visioinnecktitude.domain.member.vo.State;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@EqualsAndHashCode(callSuper = true)
public class PasswordLogin extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
    private String loginId;
    private String password;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private State state = State.A;

    public void setMember(Member member) {
        this.member = member;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
