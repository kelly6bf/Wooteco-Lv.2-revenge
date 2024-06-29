package site.roomescape.springroomescaperevenge.member.domain;

public class MemberUpdate {

    private final String newEmail;
    private final String newName;

    public MemberUpdate(final String email, final String name) {
        this.newEmail = email;
        this.newName = name;
    }

    public Member update(final Member member) {
        if (newEmailIsEmpty() && newNameIsEmpty()) {
            return member.updateEmail(new MemberEmail(newEmail))
                    .updateName(new MemberName(newName));
        }

        if (newEmailIsEmpty()) {
            return member.updateEmail(new MemberEmail(newEmail));
        }

        if (newNameIsEmpty()) {
            return member.updateName(new MemberName(newName));
        }

        return member;
    }

    private boolean newEmailIsEmpty() {
        return this.newEmail != null && !this.newEmail.isBlank();
    }

    private boolean newNameIsEmpty() {
        return this.newName != null && !this.newName.isBlank();
    }
}
