package site.roomescape.springroomescaperevenge.mock;

import site.roomescape.springroomescaperevenge.common.service.port.PasswordEncoder;

public class TestPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(final String password) {
        return "encoded-header " + password + "encoded-footer";
    }

    @Override
    public boolean matches(final String password, final String encodedPassword) {
        return false;
    }
}
