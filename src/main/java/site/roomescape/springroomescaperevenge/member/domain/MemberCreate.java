package site.roomescape.springroomescaperevenge.member.domain;

import lombok.Builder;
import lombok.Getter;
import site.roomescape.springroomescaperevenge.common.service.port.PasswordEncoder;
import site.roomescape.springroomescaperevenge.member.domain.exception.InvalidMemberPasswordLengthException;

@Getter
public class MemberCreate {

    private static final int MINIMUM_ENABLE_PASSWORD_LENGTH = 8;
    private static final int MAXIMUM_ENABLE_PASSWORD_LENGTH = 13;

    private final MemberEmail email;
    private final MemberPassword password;
    private final MemberName name;

    @Builder
    public MemberCreate(
            final String email,
            final String password,
            final String name
    ) {
        validatePlainPasswordLength(password);

        this.email = new MemberEmail(email);
        this.password = new MemberPassword(password);
        this.name = new MemberName(name);
    }

    private void validatePlainPasswordLength(final String password) {
        if (password == null
                || password.length() < MINIMUM_ENABLE_PASSWORD_LENGTH
                || password.length() > MAXIMUM_ENABLE_PASSWORD_LENGTH) {
            throw new InvalidMemberPasswordLengthException("유효하지 않은 회원 비밀번호 길이입니다. - " + password);
        }
    }

    public Member create(final PasswordEncoder passwordEncoder) {
        final MemberPassword encodedPassword = password.encode(passwordEncoder);

        return Member.builder()
                .email(this.email)
                .password(encodedPassword)
                .name(this.name)
                .role(MemberRole.USER)
                .build();
    }
}
