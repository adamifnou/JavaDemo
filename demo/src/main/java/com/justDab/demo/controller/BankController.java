package com.justDab.demo.controller;

import com.justDab.demo.dto.DepositRequest;
import com.justDab.demo.model.Account;
import com.justDab.demo.model.Client;
import com.justDab.demo.model.Employee;
import com.justDab.demo.service.AccountService;


import com.justDab.demo.service.AdvisorService;
import com.justDab.demo.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.join;

@RestController
public class BankController {
    private final AccountService accountService;
    private final ClientService clientService;
    private final AdvisorService advisorService;

    // Constructor
    public BankController(AccountService accountService, ClientService clientService, AdvisorService advisorService) {
        this.accountService = accountService;
        this.clientService = clientService;
        this.advisorService = advisorService;
    }

    // as a client I want to be able to do the following:
    // Get my account balance
    @GetMapping("/account/{accountId}/balance")
    @ResponseBody
    public ResponseEntity<Double> getAccountBalance(@PathVariable("accountId") int accountId) {
        Account account = accountService.getAccountById(accountId);
        if (account != null) {
            return ResponseEntity.ok(account.getBalance());
        } else {
            // Account not found
            return ResponseEntity.notFound().build();
        }
    }
    // Deposit money
    @PutMapping("/account/{accountId}/deposit")
    @ResponseBody
    public ResponseEntity<String> depositMoney(@PathVariable("accountId") int accountId,
                                               @RequestBody DepositRequest depositRequest) {
        Double amount = depositRequest.getAmount();
        System.out.println("amount: " + amount);
        if (amount == null) {
            return ResponseEntity.badRequest().body("Missing required parameter: 'amount'");
        }
        Account account = accountService.getAccountById(accountId);

        if (account != null) {
            account.deposit(amount);
            accountService.saveAccount(account);
            return ResponseEntity.ok("New balance: " + account.getBalance());
        } else {
            // Account not found
            return ResponseEntity.notFound().build();
        }
    }

    // Withdraw money
    @PutMapping("/account/{accountId}/withdraw")
    @ResponseBody
    public ResponseEntity<String> withdrawMoney(@PathVariable("accountId") int accountId,
                                                @RequestBody DepositRequest depositRequest) {
        Double amount = depositRequest.getAmount();
        if (amount == null) {
            return ResponseEntity.badRequest().body("Missing required parameter: 'amount'");
        }
        Account account = accountService.getAccountById(accountId);

        if (account != null) {
            account.withdraw(amount);
            accountService.saveAccount(account);
            return ResponseEntity.ok("New balance: " + account.getBalance());
        } else {
            // Account not found
            return ResponseEntity.notFound().build();
        }
    }
    // get my advisor
    @GetMapping("/client/{accountId}/advisor")
    @ResponseBody
    public ResponseEntity<String> getAdvisor(@PathVariable("accountId") int accountId) {
        Client client = clientService.getClientById(accountId);
        Employee employee = client.getAdvisor();
        if (employee != null) {
            return ResponseEntity.ok(join(" ", employee.getFirstName(), employee.getLastName()));
        } else {
            // Account not found
            return ResponseEntity.notFound().build();
        }
    }

    // as an advisor i want to be able to do the following:
    // get a list of all my clients
    @GetMapping("/advisor/{advisorId}/clients")
    @ResponseBody
    public ResponseEntity<String> getClients(@PathVariable("advisorId") int advisorId) {
        List<Client> clients = advisorService.getAdvisorById(advisorId).getClients();
        List<String> clientNames = new ArrayList<>(); // Initialize the list here
        if (clients != null) {
            for (Client client : clients) {
                clientNames.add(join(" ", client.getFirstName(), client.getLastName()));
            }
            return ResponseEntity.ok(String.join("\n", clientNames));
        } else {
            // Advisor not found
            return ResponseEntity.notFound().build();
        }
    }
        // get a list of all the clients
    @GetMapping("/clients")
    @ResponseBody
    public ResponseEntity<String> getAllClients() {
        Iterable<Client> clients = clientService.getAllClients();
        List<String> clientNames = new ArrayList<>(); // Initialize the list here
        if (clients != null) {
            for (Client client : clients) {
                clientNames.add(join(" ", client.getFirstName(), client.getLastName()));
            }
            return ResponseEntity.ok(String.join("\n", clientNames));
        } else {
            // Advisor not found
            return ResponseEntity.notFound().build();
        }
    }

}