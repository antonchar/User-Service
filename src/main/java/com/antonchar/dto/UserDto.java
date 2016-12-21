package com.antonchar.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@Accessors(chain = true)
public class UserDto {

    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @Min(16)
    @Max(90)
    private Integer age;

    private boolean admin;

    @Column(name = "creation_date")
    private Date creationDate;
}
