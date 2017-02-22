package com.antonchar.userservice.services.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import com.antonchar.userservice.entities.User;

@Data
@Accessors(chain = true)
public class UserDto {

    private Long id;
    private String email;
    private String pwdHash;

    @NotNull(message = "{error.null}")
    @Size(min = 3, max = 50, message = "{error.name.length}")
    private String name;

    private String surname;

    @NotNull(message = "{error.null}")
    @Min(value = 16, message = "{error.age.min}")
    @Max(value = 90, message = "{error.age.max}")
    private Integer age;

    private User.Role role;

    private Boolean isBlocked;

    private LocalDateTime creationDate;

    public boolean isAdmin() {
        return role == User.Role.ADMIN || role == User.Role.SUPERADMIN;
    }
}
