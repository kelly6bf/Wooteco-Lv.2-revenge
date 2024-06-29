package site.roomescape.springroomescaperevenge.member.infrastructure.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.roomescape.springroomescaperevenge.member.domain.Member;
import site.roomescape.springroomescaperevenge.member.domain.MemberEmail;
import site.roomescape.springroomescaperevenge.member.domain.MemberName;
import site.roomescape.springroomescaperevenge.member.domain.MemberPassword;
import site.roomescape.springroomescaperevenge.member.domain.MemberRole;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
@Entity
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "password", length = 500, nullable = false)
    private String password;

    @Column(name = "name", length = 10, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 10, nullable = false)
    private MemberRole role;

    @Builder
    public MemberEntity(
            final Long id,
            final String email,
            final String password,
            final String name,
            final MemberRole role
    ) {
        this.email = email;
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public MemberEntity(final Member member) {
        this(
                member.getId(),
                member.getEmail().value(),
                member.getPassword().value(),
                member.getName().value(),
                member.getRole()
        );
    }

    public Member toModel() {
        return Member.builder()
                .id(this.id)
                .email(new MemberEmail(this.email))
                .password(new MemberPassword(this.password))
                .name(new MemberName(this.name))
                .role(this.role)
                .build();
    }
}
