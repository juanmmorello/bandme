package io.braulio.bandme.business.impl;

import io.braulio.bandme.business.UserService;
import io.braulio.bandme.domain.User;
import io.braulio.bandme.domain.enums.Role;
import io.braulio.bandme.dto.UserRegistrationRequestDto;
import io.braulio.bandme.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findByEmail(String email) {

        return userRepository
                .findByEmail(email)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User registerUser(UserRegistrationRequestDto userRegistrationRequestDto) {

        Assert.isTrue(isEmailAvailableForUsage(userRegistrationRequestDto.getEmail()), "The email is being used by another user");

        User user = User.builder()
                .id(null)
                .name(userRegistrationRequestDto.getName())
                .email(userRegistrationRequestDto.getEmail())
                .password(userRegistrationRequestDto.getPassword())
                .roles(List.of(Role.ROLE_USER))
                .build();

        return userRepository.save(user);
    }

    private Boolean isEmailAvailableForUsage(String email){
        return userRepository.findByEmail(email).isEmpty();
    }
}
