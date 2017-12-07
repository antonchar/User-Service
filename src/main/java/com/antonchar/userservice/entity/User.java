package com.antonchar.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
@SequenceGenerator(name = "PK_SEQ", sequenceName = "USERSERVICE_PK_SEQUENCE", allocationSize = 1)
public class User {

    public enum Role {
        USER,
        ADMIN,
        SUPERADMIN
    }

    @Id
    @GeneratedValue(generator = "PK_SEQ")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "pwd_hash", nullable = false)
    private String pwdHash;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "blocked", nullable = false)
    private Boolean blocked;

    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creationDate;
}
