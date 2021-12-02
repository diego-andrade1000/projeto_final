package com.example.backend.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;


/**
 * A user.
 */
@Entity
@Table(name = "user")
@Getter
@Setter
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @NotNull
    @Column(name = "name")
    private String name;

    @JsonIgnore
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60)
    private String password;

    @Email
    @Size(min = 5, max = 254)
    @Column(length = 254, unique = true, name = "email")
    private String email;



    @NotNull
    @Column(name = "authority")
    private String authoritie;
}

