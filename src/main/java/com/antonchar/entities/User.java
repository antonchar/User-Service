package com.antonchar.entities;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Accessors(chain = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer age;
    private boolean admin;

    @Column(name = "creation_date")
    private Date creationDate;
}
