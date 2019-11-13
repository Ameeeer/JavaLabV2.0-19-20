package com.company.servlets.models;

public class User {
    private Integer id;
    private String email;
    private String login;
    private String password;
    private String role;
    private String country;
    private String info;

    public User(String email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public User(String email) {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public User(String email, String login, String password, String country, String info) {
        this.email = email;
        this.login = login;
        this.password = password;
        this.country = country;
        this.info = info;
    }

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId( Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail( String email) {
        this.email = email;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin( String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword( String password) {
        this.password = password;
    }
}
