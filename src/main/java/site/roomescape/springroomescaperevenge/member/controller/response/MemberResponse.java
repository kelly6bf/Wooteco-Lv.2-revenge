package site.roomescape.springroomescaperevenge.member.controller.response;

import lombok.Getter;
import site.roomescape.springroomescaperevenge.member.domain.Member;
import site.roomescape.springroomescaperevenge.member.domain.MemberRole;

@Getter
public class MemberResponse {

    private final Long id;
    private final String email;
    private final String name;
    private final MemberRole role;

    public MemberResponse(final Member member) {
        this(
                member.getId(),
                member.getEmail().value(),
                member.getName().value(),
                member.getRole()
        );
    }

    public MemberResponse(
            final Long id,
            final String email,
            final String name,
            final MemberRole role
    ) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
    }
}
