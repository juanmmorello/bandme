package tech.juanmamorello.stock.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tech.juanmamorello.stock.business.UserService;
import tech.juanmamorello.stock.domain.User;
import tech.juanmamorello.stock.dto.AuthenticationRequestDto;
import tech.juanmamorello.stock.dto.UserRegistrationRequestDto;
import tech.juanmamorello.stock.utils.JwtTokenProvider;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/auth/register")
    public User register(@RequestBody UserRegistrationRequestDto registrationRequest){
        registrationRequest.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        return userService.registerUser(registrationRequest);
    }

    @GetMapping("/auth/signin")
    public ResponseEntity signIn(@RequestBody AuthenticationRequestDto authenticationRequest){
        try {
            String username = authenticationRequest.getEmail();
            String password = authenticationRequest.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User user = this.userService.findByEmail(username);
            String token = jwtTokenProvider.createToken(username, user.getRolesAsString());

            Map<Object, Object> model = new HashMap<>();
            model.put("email", user.getEmail());
            model.put("username", user.getName());
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
