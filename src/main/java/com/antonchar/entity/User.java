package com.antonchar.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    private boolean isAdmin;

    private Date createDate;

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public User setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
        return this;
    }
}
