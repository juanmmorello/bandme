package tech.juanmamorello.stock.business;

import tech.juanmamorello.stock.domain.User;
import tech.juanmamorello.stock.dto.UserRegistrationRequestDto;

public interface UserService {
    User findByEmail(String email);
    User registerUser(UserRegistrationRequestDto userRegistrationRequestDto);
}
