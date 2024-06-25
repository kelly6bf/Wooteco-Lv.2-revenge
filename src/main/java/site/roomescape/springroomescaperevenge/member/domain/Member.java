package site.roomescape.springroomescaperevenge.member.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {

    private final Long id;
    private final MemberEmail email;
    private final MemberPassword password;
    private final MemberName name;

    @Builder
    public Member(
            final Long id,
            final String email,
            final String password,
            final String name
    ) {
        this.id = id;
        this.email = new MemberEmail(email);
        this.password = new MemberPassword(password);
        this.name = new MemberName(name);
    }
}
