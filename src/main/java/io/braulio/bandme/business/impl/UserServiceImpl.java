package io.braulio.bandme.business.impl;

import io.braulio.bandme.business.UserService;
import io.braulio.bandme.domain.User;
import io.braulio.bandme.domain.enums.Role;
import io.braulio.bandme.dto.UserRegistrationRequestDto;
import io.braulio.bandme.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        User user = User.builder()
                .id(null)
                .name(userRegistrationRequestDto.getName())
                .email(userRegistrationRequestDto.getEmail())
                .password(userRegistrationRequestDto.getPassword())
                .roles(List.of(Role.ROLE_USER))
                .build();

        return userRepository.save(user);
    }
}
