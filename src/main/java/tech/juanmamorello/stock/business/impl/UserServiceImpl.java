package tech.juanmamorello.stock.business.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.juanmamorello.stock.business.UserService;
import tech.juanmamorello.stock.domain.User;
import tech.juanmamorello.stock.domain.enums.Role;
import tech.juanmamorello.stock.dto.UserRegistrationRequestDto;
import tech.juanmamorello.stock.repository.UserRepository;

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
