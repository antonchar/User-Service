package com.antonchar.userservice.services.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Email;

import com.antonchar.userservice.entities.User;

@Data
@Accessors(chain = true)
public class UserDto {

    private Long id;

    @Email(message = "{error.email}")
    private String email;

    private String pwdHash;

    @NotNull(message = "{error.null}")
    @Size(min = 3, max = 30, message = "{error.name.length}")
    private String name;

    @NotNull(message = "{error.null}")
    @Size(min = 4, max = 50, message = "{error.surname.length}")
    private String surname;

    @NotNull(message = "{error.null}")
    @Min(value = 16, message = "{error.age.min}")
    @Max(value = 90, message = "{error.age.max}")
    private Integer age;

    private User.Role role;

    private Boolean blocked;

    private LocalDateTime creationDate;

    public boolean isAdmin() {
        return role == User.Role.ADMIN || role == User.Role.SUPERADMIN;
    }
}
