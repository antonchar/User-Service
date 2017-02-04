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
    @Id
    @GeneratedValue(generator = "PK_SEQ")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "admin", nullable = false)
    private Boolean admin;

    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creationDate;
}
