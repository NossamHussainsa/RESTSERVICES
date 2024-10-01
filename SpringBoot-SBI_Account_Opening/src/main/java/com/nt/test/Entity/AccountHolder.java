package com.nt.test.Entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "AccountHolder")
public class AccountHolder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="Account_seq" )
    @SequenceGenerator(name = "Account_seq", allocationSize = 1, initialValue = 1001)
    @Column(name = "AccountHolder_id")
    private Integer id;

    @NotBlank(message = "Name should not be empty")
    @Size(min = 5, max = 25, message = "Name length must be between 5 and 25 characters")
    @Column(name = "AccountHolder_Name")
    private String name;

    @NotBlank(message = "Bank name should not be empty")
    @Size(min = 3, max = 35, message = "Bank name length must be between 3 and 35 characters")
    @Column(name = "AccountHolder_BankName")
    private String bankname;

    @NotBlank(message = "Account type should not be empty")
    @Size(min = 5, max = 15, message = "Account type length must be between 5 and 15 characters")
    @Column(name = "AccountHolder_AccountType")
    private String accountType;

    @NotBlank(message = "Branch should not be empty")
    @Size(min = 5, max = 15, message = "Branch length must be between 5 and 15 characters")
    @Column(name = "AccountHolder_Branch")
    private String branch;

    @NotBlank(message = "IFSC code should not be empty")
    @Size(min = 5, max = 15, message = "IFSC code length must be between 5 and 15 characters")
    @Column(name = "AccountHolder_ifsccode")
    private String ifsccode;

    @DecimalMin(value = "0.0", inclusive = true, message = "Balance must be greater than or equal to 0.0")
    @Column(name = "AccountHolder_Amount")
    private Double amount=0.0;

    @Column(name ="AccountHolder_isActive")
    private String isActive; 

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getIfsccode() {
        return ifsccode;
    }

    public void setIfsccode(String ifsccode) {
        this.ifsccode = ifsccode;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

	
}
