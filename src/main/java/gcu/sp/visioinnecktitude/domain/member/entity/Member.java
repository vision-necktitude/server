package gcu.sp.visioinnecktitude.domain.member.entity;

import gcu.sp.visioinnecktitude.common.entitry.BaseEntity;
import gcu.sp.visioinnecktitude.domain.member.vo.State;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@EqualsAndHashCode(callSuper = true)
public class Member extends BaseEntity {
    private String memberId;
    private String password;
    private String nickname;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private State state = State.A;
}