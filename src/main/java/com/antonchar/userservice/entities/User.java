package com.antonchar.userservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;


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
