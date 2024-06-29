package site.roomescape.springroomescaperevenge.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayName("회원 수정 도메인 테스트")
class MemberUpdateTest {

    @DisplayName("회원 수정 정보로 이름, 이메일이 입력되면 변경된 Member 객체를 반환한다.")
    @Test
    void updateEmailAndNameTest() {
        // Given
        final Member member = Member.builder()
                .email(new MemberEmail("kelly6bf@gmail.com"))
                .password(new MemberPassword("kellyPw1234!"))
                .name(new MemberName("kelly"))
                .role(MemberRole.USER)
                .build();
        final String newEmail = "elmo@email.com";
        final String newName = "elmo";
        final MemberUpdate memberUpdate = new MemberUpdate(newEmail, newName);

        // When
        final Member updatedMember = memberUpdate.update(member);

        // Then
        assertSoftly(softly -> {
            softly.assertThat(updatedMember.getEmail().value()).isEqualTo(newEmail);
            softly.assertThat(updatedMember.getName().value()).isEqualTo(newName);
        });
    }

    @DisplayName("회원 수정 정보로 이메일이 입력되면 변경된 Member 객체를 반환한다.")
    @Test
    void updateEmailTest() {
        // Given
        final String oldName = "kelly";
        final Member member = Member.builder()
                .email(new MemberEmail("kelly6bf@gmail.com"))
                .password(new MemberPassword("kellyPw1234!"))
                .name(new MemberName(oldName))
                .role(MemberRole.USER)
                .build();
        final String newEmail = "elmo@email.com";
        final MemberUpdate memberUpdate = new MemberUpdate(newEmail, null);

        // When
        final Member updatedMember = memberUpdate.update(member);

        // Then
        assertSoftly(softly -> {
            softly.assertThat(updatedMember.getEmail().value()).isEqualTo(newEmail);
            softly.assertThat(updatedMember.getName().value()).isEqualTo(oldName);
        });
    }

    @DisplayName("회원 수정 정보로 이름이 입력되면 변경된 Member 객체를 반환한다.")
    @Test
    void updateNameTest() {
        // Given
        final String oldEmail = "kelly@email.com";
        final Member member = Member.builder()
                .email(new MemberEmail(oldEmail))
                .password(new MemberPassword("kellyPw1234!"))
                .name(new MemberName("kelly"))
                .role(MemberRole.USER)
                .build();
        final String newName = "elmo";
        final MemberUpdate memberUpdate = new MemberUpdate(null, newName);

        // When
        final Member updatedMember = memberUpdate.update(member);

        // Then
        assertSoftly(softly -> {
            softly.assertThat(updatedMember.getEmail().value()).isEqualTo(oldEmail);
            softly.assertThat(updatedMember.getName().value()).isEqualTo(newName);
        });
    }

    @DisplayName("수정 정보로 아무것도 입력되지 않으면 아무 값도 변경하지 않는다.")
    @Test
    void notUpdateTest() {
        // Given
        final String oldEmail = "kelly@email.com";
        final String oldName = "kelly";
        final Member member = Member.builder()
                .email(new MemberEmail(oldEmail))
                .password(new MemberPassword("kellyPw1234!"))
                .name(new MemberName(oldName))
                .role(MemberRole.USER)
                .build();
        final MemberUpdate memberUpdate = new MemberUpdate(null, null);

        // When
        final Member updatedMember = memberUpdate.update(member);

        // Then
        assertSoftly(softly -> {
            softly.assertThat(updatedMember.getEmail().value()).isEqualTo(oldEmail);
            softly.assertThat(updatedMember.getName().value()).isEqualTo(oldName);
        });
    }
}
