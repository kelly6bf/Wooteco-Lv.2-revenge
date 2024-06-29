package site.roomescape.springroomescaperevenge.docs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import site.roomescape.springroomescaperevenge.member.controller.MemberController;
import site.roomescape.springroomescaperevenge.member.controller.request.CreateMemberRequest;
import site.roomescape.springroomescaperevenge.member.service.MemberService;
import site.roomescape.springroomescaperevenge.mock.FakeMemberRepository;
import site.roomescape.springroomescaperevenge.mock.TestPasswordEncoder;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberControllerDocsTest extends RestDocsSupport {

    private final MemberService memberService = new MemberService(
            new FakeMemberRepository(),
            new TestPasswordEncoder());

    @Override
    protected Object initController() {
        return new MemberController(this.memberService);
    }

    @DisplayName("회원 생성 API")
    @Test
    void createMemberApi() throws Exception {
        // Given
        final String email = "kelly@email.com";
        final String password = "kellyPw1234!";
        final String name = "kelly";
        final CreateMemberRequest createMemberRequest = new CreateMemberRequest(email, password, name);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/members")
                        .content(objectMapper.writeValueAsString(createMemberRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("member-create",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("email")
                                        .type(JsonFieldType.STRING)
                                        .description("회원 이메일"),
                                fieldWithPath("password")
                                        .type(JsonFieldType.STRING)
                                        .description("회원 비밀번호"),
                                fieldWithPath("name")
                                        .type(JsonFieldType.STRING)
                                        .description("회원 이름")
                        ),
                        responseFields(
                                fieldWithPath("id")
                                        .type(JsonFieldType.NUMBER)
                                        .description("생성 회원 인덱스 번호"),
                                fieldWithPath("email")
                                        .type(JsonFieldType.STRING)
                                        .description("생성 회원 이메일"),
                                fieldWithPath("name")
                                        .type(JsonFieldType.STRING)
                                        .description("생성 회원 이름"),
                                fieldWithPath("role")
                                        .type(JsonFieldType.STRING)
                                        .description("생성 회원 권한")
                        )
                ));
    }
}
