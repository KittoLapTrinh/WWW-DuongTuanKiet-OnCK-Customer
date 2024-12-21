package com.example.wwwduongtuankietonck2cus;

import com.example.wwwduongtuankietonck2cus.backend.enums.AccountStatus;
import com.example.wwwduongtuankietonck2cus.backend.models.Account;
import com.example.wwwduongtuankietonck2cus.backend.models.Customer;
import com.example.wwwduongtuankietonck2cus.backend.repositories.AccountRepository;
import com.example.wwwduongtuankietonck2cus.backend.repositories.CustomerRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.ZoneId;

@SpringBootApplication
public class WwwDuongTuanKietOnCk2CusApplication {

    public static void main(String[] args) {
        SpringApplication.run(WwwDuongTuanKietOnCk2CusApplication.class, args);
    }

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AccountRepository accountRepository;

//    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            Faker faker = new Faker();
            for (int i = 1; i <= 20; i++) { // Tạo 20 khách hàng
                Customer customer = Customer.builder()
                        .name(faker.name().fullName())
                        .address(faker.address().fullAddress())
                        .email(faker.internet().emailAddress())
                        .dob(faker.date().birthday(18, 60).toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate())
                        .build();

                customerRepository.save(customer);

                for (int j = 1; j <= 3; j++) { // Mỗi khách hàng có 3 tài khoản
                    Account account = Account.builder()
                            .balance(faker.number().randomDouble(2, 100, 1000))
                            .customer(customer)
                            .status(AccountStatus.CHECKING)
                            .build();

                    accountRepository.save(account);
                }
            }
        };
    }
}
