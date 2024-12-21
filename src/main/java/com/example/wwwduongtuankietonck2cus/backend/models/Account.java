package com.example.wwwduongtuankietonck2cus.backend.models;

import com.example.wwwduongtuankietonck2cus.backend.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_id")
    private long id;
    @Column(name = "acc_number")
    private double balance;

    @ManyToOne
    @JoinColumn(name = "cus_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;



}
