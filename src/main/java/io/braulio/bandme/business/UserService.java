package io.braulio.bandme.business;

import io.braulio.bandme.domain.User;
import io.braulio.bandme.dto.AuthenticationRequestDto;
import io.braulio.bandme.dto.AuthenticationResponseDto;
import io.braulio.bandme.dto.UserRegistrationRequestDto;

public interface UserService {
    User registerUser(UserRegistrationRequestDto userRegistrationRequestDto);
    AuthenticationResponseDto signInUser(AuthenticationRequestDto authenticationRequestDto);
    AuthenticationResponseDto signInUserGoogle(String token);
}
