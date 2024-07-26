package com.justDab.demo.dao;

import com.justDab.demo.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository <Client, Integer> {
    Client getClientById(int clientId);
}
