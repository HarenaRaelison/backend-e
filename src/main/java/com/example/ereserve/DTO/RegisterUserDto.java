package com.example.ereserve.DTO;

import com.example.ereserve.Entity.Contract;
import com.example.ereserve.Entity.RoleType;

public class RegisterUserDto {
    private String email;

    private String password;

    private String fullName;
    private RoleType role;
    private Contract contract;
    // getters and setters here...

    public RoleType getRole() {
        return role;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public void setRole(RoleType role) {
        this.role = role;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


}