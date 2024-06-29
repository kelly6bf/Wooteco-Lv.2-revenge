package site.roomescape.springroomescaperevenge.member.domain;

import lombok.Builder;
import lombok.Getter;

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
}
