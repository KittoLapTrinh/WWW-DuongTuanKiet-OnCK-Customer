package com.example.wwwduongtuankietonck2cus.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="customer")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cus_id")
    private long id;
    @Column(name = "cust_name")
    private String name;
    @Column(name = "cust_address")
    private String address;
    @Column(name = "cust_email")
    private String email;
    @Column(name = "cust_dob")
    private LocalDate dob;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Account> accounts;


    public Customer(String name, String address, String email, LocalDate dob) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", accounts=" + accounts +
                '}';
    }
}
