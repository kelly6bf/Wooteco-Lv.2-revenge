package site.roomescape.springroomescaperevenge.common.service.port;

public interface PasswordEncoder {

    String encode(String password);

    boolean matches(String password, String encodedPassword);
}
