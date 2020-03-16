package io.braulio.bandme.business;

import io.braulio.bandme.domain.User;
import io.braulio.bandme.dto.UserRegistrationRequestDto;

public interface UserService {
    User findByEmail(String email);
    User registerUser(UserRegistrationRequestDto userRegistrationRequestDto);
}
