package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@Getter
public class UserRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    @Email
    private String mail;
    @Pattern(regexp = "^\\+?\\d{10,13}$", message = "El número de teléfono no es válido")
    private String phone;
    private String address;
    @NotBlank
    private String idDniType;
    @Pattern(regexp = "\\d{6,15}$")
    private String dniNumber;
    @NotBlank
    private String idPersonType;
    @NotBlank
    @Size(min = 8)
    private String password;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private Date birthdate;
}
