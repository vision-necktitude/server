package gcu.sp.visioinnecktitude.domain.member.entity;

import gcu.sp.visioinnecktitude.common.entitry.BaseEntity;
import gcu.sp.visioinnecktitude.domain.member.vo.State;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@EqualsAndHashCode(callSuper = true)
public class Member extends BaseEntity {
    private String sex;

    private LocalDate birthday;

    private String name;

    @CreatedDate
    @Builder.Default
    private LocalDateTime privacyAgreementAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private State state = State.A;
    @OneToOne(mappedBy = "member", cascade = ALL)
    private PasswordLogin passwordLogin;

    public void setPasswordLogin(PasswordLogin passwordLogin) {
        this.passwordLogin = passwordLogin;
        passwordLogin.setMember(this);
    }
    public void setName(String name){
        this.name = name;
    }
}