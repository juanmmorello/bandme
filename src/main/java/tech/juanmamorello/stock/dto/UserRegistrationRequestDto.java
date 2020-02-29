package tech.juanmamorello.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserRegistrationRequestDto {

    private String name;
    private String email;
    private String password;
}
