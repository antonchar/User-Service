package com.antonchar.entity;

import lombok.Data;
import lombok.Getter;
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

    // Standard getter
    public boolean isAdmin() {
        return this.admin;
    }

    // Getter for thymeleaf
    public boolean getAdmin() {
        return this.admin;
    }
}
