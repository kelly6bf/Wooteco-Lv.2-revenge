package site.roomescape.springroomescaperevenge.member.domain;

import lombok.Builder;
import lombok.Getter;
import site.roomescape.springroomescaperevenge.common.service.port.PasswordEncoder;
import site.roomescape.springroomescaperevenge.member.domain.exception.MemberPasswordMissMatchException;

@Getter
public class Member {

    private final Long id;
    private final MemberEmail email;
    private final MemberPassword password;
    private final MemberName name;
    private final MemberRole role;

    @Builder
    public Member(
            final Long id,
            final MemberEmail email,
            final MemberPassword password,
            final MemberName name,
            final MemberRole role
    ) {
        validateMemberRoleNotNull(role);

        this.email = email;
        this.id = id;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    private void validateMemberRoleNotNull(final MemberRole role) {
        if (role == null) {
            throw new IllegalArgumentException("회원 권한 값은 NULL이 입력될 수 없습니다.");
        }
    }

    public Member updateEmail(final MemberEmail email) {
        return Member.builder()
                .id(this.id)
                .email(email)
                .password(this.password)
                .name(this.name)
                .role(this.role)
                .build();
    }

    public Member updateName(final MemberName name) {
        return Member.builder()
                .id(this.id)
                .email(this.email)
                .password(this.password)
                .name(name)
                .role(this.role)
                .build();
    }

    public Member changePassword(
            final MemberPassword oldPassword,
            final MemberPassword newPassword,
            final PasswordEncoder passwordEncoder
    ) {
        if (!passwordEncoder.matches(oldPassword.value(), this.password.value())) {
            throw new MemberPasswordMissMatchException("회원 비밀번호가 일치하지 않습니다.");
        }

        final MemberPassword newEncodedPassword = new MemberPassword(passwordEncoder.encode(newPassword.value()));
        return Member.builder()
                .id(this.id)
                .email(this.email)
                .password(newEncodedPassword)
                .name(this.name)
                .role(this.role)
                .build();
    }
}
