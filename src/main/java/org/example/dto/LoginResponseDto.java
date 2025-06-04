package org.example.dto;

/*import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor*/
public class LoginResponseDto {
    private String token;

    public LoginResponseDto(String token) {
        this.token = token;
    }
}
