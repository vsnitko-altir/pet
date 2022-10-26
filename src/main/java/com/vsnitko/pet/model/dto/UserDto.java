package com.vsnitko.pet.model.dto;

import com.vsnitko.pet.model.entity.jpa.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author v.snitko
 * @since 2022.10.15
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @Size(min = 2, message = "First name should contain at least 2 characters")
    private String firstName;

    @Size(min = 2, message = "Last name should contain at least 2 characters")
    private String lastName;

    @Email(message = "Please, provide a valid email")
    private String email;

    @Size(min = 4, message = "Password should contain at least 4 characters")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    private Boolean active = true;
}
