package com.example.wwwduongtuankietonck2cus.frontend.controllers;

import com.example.wwwduongtuankietonck2cus.backend.enums.AccountStatus;
import com.example.wwwduongtuankietonck2cus.backend.models.Account;
import com.example.wwwduongtuankietonck2cus.backend.models.Customer;
import com.example.wwwduongtuankietonck2cus.backend.services.AccountService;
import com.example.wwwduongtuankietonck2cus.backend.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CustomerController {
//    private static Logger log = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;
    private final AccountService accountService;

    @GetMapping({"","/","/index"})
    public String index(){
        return "index";
    }

    @GetMapping("/customers")
    public String getAllCustomers(Model model) {
        Collection<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/customers/detail/{id}")
    public String getCustomer(Model model, @PathVariable long id) {
        Customer customer = customerService.getCustomerById(id);
        if(customer == null){
            model.addAttribute("errorMessage", "Customer not found");
            return "error";
        }
        model.addAttribute("customer", customer);
        model.addAttribute("status", AccountStatus.values());
        model.addAttribute("accounts", customer.getAccounts());
        return "customers-details";
    }

    @GetMapping("/report1")
    public String getAccountsBetweenBalance(Model model,
                                            @RequestParam(value = "min", required = false, defaultValue = "0") double min,
                                            @RequestParam(value = "max", required = false, defaultValue = "1000000") double max) {
        model.addAttribute("accounts", accountService.getAccountsBetweenBalance(min, max));
        return "report1";
    }


    @GetMapping("/report2")
    public String getReport2(Model model){
        model.addAttribute("customers", customerService.getCustomersByYearDob());
        return "report2";
    }

    @PostMapping("/customers/insert")
    public String insertCustomer(Model model,
                                @RequestParam("address") String address,
                                @RequestParam("dob") LocalDate dob,
                                @RequestParam("email") String email,
                                @RequestParam("name") String name){
        customerService.addCustomer(new Customer(address, dob,email,name));
        model.addAttribute("customers", customerService.getAllCustomers());
        return "redirect:/customers";
    }

    @PostMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }

    @GetMapping("/customers/edit/{id}")
    public String editCustomer(Model model, @PathVariable Long id){
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "customer-edit";
    }

    @PostMapping("/customers/edit/{id}")
    public String updateCustomer(Model model, @PathVariable long id,
                                  @RequestParam("address") String address,
                                  @RequestParam("dob") LocalDate dob,
                                  @RequestParam("email") String email,
                                  @RequestParam("name") String name){
        Customer customer = customerService.getCustomerById(id);
        customer.setAddress(address);
        customer.setDob(dob);
        customer.setEmail(email);
        customer.setName(name);

        customerService.addCustomer(customer);
        return "redirect:/customers";
    }

    @PostMapping("/customers/update")
    public String updateCustomer(@ModelAttribute Customer customer){
        customerService.updateCustomer(customer);
        return "redirect:/customers";
    }

//    @GetMapping("/customers/{id}/filter")
//    public String filterCustomerByStatus(@PathVariable long id,
//                                         @RequestParam(name = "status", required = false) String statusString,
//                                         Model model) {
//        // Lấy thông tin customer từ dịch vụ
//        Customer customer = customerService.getCustomerById(id);
//        if (customer == null) {
//            model.addAttribute("errorMessage", "Customer not found");
//            return "error";
//        }
//
//        // Chuyển đổi từ String sang Enum AccountStatus nếu có giá trị
//        final AccountStatus accountStatus; // Đảm bảo là final
//        if (statusString != null && !statusString.isEmpty()) {
//            try {
//                accountStatus = AccountStatus.valueOf(statusString); // Chuyển đổi String thành enum
//            } catch (IllegalArgumentException e) {
//                model.addAttribute("errorMessage", "Invalid status value: " + statusString);
//                return "error";
//            }
//        } else {
//            accountStatus = null;
//        }
//
//        // Lọc tài khoản theo status nếu có giá trị status
//        List<Account> filteredAccounts = customer.getAccounts().stream()
//                .filter(acc -> accountStatus == null || acc.getStatus().equals(accountStatus)) // Sử dụng .equals() thay vì ==
//                .collect(Collectors.toList());
//
//        // Truyền dữ liệu vào model
//        model.addAttribute("status", AccountStatus.values()); // Tất cả các giá trị enum
//        model.addAttribute("customer", customer);
//        model.addAttribute("account", filteredAccounts); // Các tài khoản đã lọc
//        model.addAttribute("statusfilter", accountStatus); // Status đã chọn trong form
//        return "customers"; // Trả về view tương ứng
//    }







}
