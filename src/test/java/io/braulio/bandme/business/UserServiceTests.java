package io.braulio.bandme.business;

import io.braulio.bandme.ApplicationTests;
import io.braulio.bandme.domain.User;
import io.braulio.bandme.dto.UserRegistrationRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class UserServiceTests extends ApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void registerUser_withValidUserDetails_returnPersistedEntity() {
        // Test case setup
        UserRegistrationRequestDto requestDto = userRegistrationWithRoleUser();

        // Test execution
        User newUser = userService.registerUser(requestDto);

        // Test case assertions
        assertThat(requestDto.getName()).isEqualTo(newUser.getName());
    }

    private UserRegistrationRequestDto userRegistrationWithRoleUser() {
        return UserRegistrationRequestDto.builder()
                .name("testeros")
                .email("teseros01@testerin.io")
                .password("123456")
                .build();
    }
}
