package io.braulio.bandme.web.rest;

import io.braulio.bandme.business.UserService;
import io.braulio.bandme.domain.User;
import io.braulio.bandme.dto.AuthenticationRequestDto;
import io.braulio.bandme.dto.AuthenticationResponseDto;
import io.braulio.bandme.dto.UserRegistrationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/auth/register")
    public User register(@RequestBody UserRegistrationRequestDto registrationRequest){
        registrationRequest.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        return userService.registerUser(registrationRequest);
    }

    @GetMapping("/auth/signin")
    public AuthenticationResponseDto signIn(@RequestBody AuthenticationRequestDto authenticationRequest){
        return userService.signInUser(authenticationRequest);
    }

    @GetMapping("/auth/signin/google")
    public ResponseEntity googleSignIn(String token){
        throw new UnsupportedOperationException();
    }
}
