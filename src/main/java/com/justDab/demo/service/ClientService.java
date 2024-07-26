package com.justDab.demo.service;

import com.justDab.demo.model.Client;

public interface ClientService {
    Client getClientById(int clientId);
    // get all clients in the database
    Iterable<Client> getAllClients();
}
