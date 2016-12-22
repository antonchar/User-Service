package com.antonchar.entities;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Data
@Accessors(chain = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{error.null}")
    @Size(min = 3, max = 50, message = "{error.name.length}")
    private String name;

    @NotNull(message = "{error.null}")
    @Min(value = 16, message = "{error.age.min}")
    @Max(value = 90, message = "{error.age.max}")
    private Integer age;

    private boolean admin;

    @Column(name = "creation_date")
    private Date creationDate;
}
