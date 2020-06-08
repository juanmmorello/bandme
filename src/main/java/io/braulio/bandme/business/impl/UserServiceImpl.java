package io.braulio.bandme.business.impl;

import io.braulio.bandme.business.UserService;
import io.braulio.bandme.domain.User;
import io.braulio.bandme.domain.enums.Role;
import io.braulio.bandme.dto.AuthenticationRequestDto;
import io.braulio.bandme.dto.AuthenticationResponseDto;
import io.braulio.bandme.dto.UserRegistrationRequestDto;
import io.braulio.bandme.repository.UserRepository;
import io.braulio.bandme.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;


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

    @Override
    public AuthenticationResponseDto signInUser(AuthenticationRequestDto req) {
        try {
            String username = req.getEmail();
            String password = req.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User user = this.findByEmail(username);
            String token = jwtTokenProvider.createToken(username, user.getRolesAsString());

            Map<Object, Object> model = new HashMap<>();
            model.put("email", user.getEmail());
            model.put("username", user.getName());
            model.put("token", token);

            return AuthenticationResponseDto.builder()
                    .email(user.getEmail())
                    .token(token)
                    .build();
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @Override
    public AuthenticationResponseDto signInUserGoogle(String token) {



        return null;
    }

    private User findByEmail(String email) {

        return userRepository
                .findByEmail(email)
                .orElseThrow(NoSuchElementException::new);
    }

    private Boolean isEmailAvailableForUsage(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }
}
