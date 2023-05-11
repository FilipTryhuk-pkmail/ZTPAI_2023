package com.ZTPAI2023.GoldenOaks.userAccount;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class UserAccount {
    private @Id @GeneratedValue Long id;
    private String email;
    private String pass;
    private String name;
    private String surname;
    private String phoneNumber;
    private String city;
    private String streetAddress;
    private String postalCode;
    private boolean userAdmin = false;

    public UserAccount() {}

    public UserAccount(String email) {
        this.email = email;
    }

    public UserAccount(String email, String pass, String name, String surname, String phoneNumber, String city, String streetAddress, String postalCode) {
        this.email = email;
        this.pass = pass;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phone_number) {
        this.phoneNumber = phone_number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String street_address) {
        this.streetAddress = street_address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postal_code) {
        this.postalCode = postal_code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String password) {
        this.pass = password;
    }

    public boolean isUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(boolean userAdmin) {
        this.userAdmin = userAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount)) return false;
        UserAccount userAccount = (UserAccount) o;
        return Objects.equals(this.id, userAccount.id) && Objects.equals(this.email, userAccount.email) && Objects.equals(this.name, userAccount.name)
                && Objects.equals(this.surname, userAccount.surname) && Objects.equals(this.pass, userAccount.pass) && Objects.equals(this.phoneNumber, userAccount.phoneNumber)
                && Objects.equals(this.city, userAccount.city) && Objects.equals(this.streetAddress, userAccount.streetAddress)
                && Objects.equals(this.postalCode, userAccount.postalCode) && Objects.equals(this.userAdmin, userAccount.userAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.email, this.pass, this.name, this.surname, this.phoneNumber,
                this.city, this.streetAddress, this.postalCode, this.userAdmin);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", email=" + this.email + ", password=" + this.pass + ", name=" + this.name
                + ", surname=" + this.surname + ", phone number=" + this.phoneNumber + ", city=" + this.city + ", street address="
                + this.streetAddress + ", postal code=" + this.postalCode + ", user admin=" + this.userAdmin + "}";
    }
}
