package com.election.electionbackend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password; // Store hashed password

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String province;

    @Column(nullable = false)
    private String role; // New role field

    @Column
    private String voorstander;

    @Column
    private String firstname;

    @Column
    private String lastname;

    //Columns for social media platforms
    @Column
    private String social1;

    @Column
    private String social2;

    @Column
    private String social3;

    @Column
    private String social4;

    @Column
    private String profielOmschrijving;

    public User(long l, String alice, String mail) {
    }

    public User() {

    }


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getVoorstander() { return voorstander; }

    public void setVoorstander(String voorstander) { this.voorstander = voorstander; }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSocial1() { return social1; }

    public void setSocial1(String social1) {this.social1 = social1;}

    public String getSocial2() { return social2;}

    public void setSocial2(String social2) {this.social2 = social2;}

    public String getSocial3() { return social3; }

    public void setSocial3(String social3) {this.social3 = social3;}

    public String getSocial4() { return social4;}

    public void setSocial4(String social4) {this.social4 = social4;}

    public String getProfielOmschrijving()  { return profielOmschrijving; }

    public void setProfielOmschrijving(String profielOmschrijving) {this.profielOmschrijving = profielOmschrijving;}
}

