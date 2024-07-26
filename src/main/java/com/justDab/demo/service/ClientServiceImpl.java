package com.justDab.demo.service;

import com.justDab.demo.dao.ClientRepository;
import com.justDab.demo.model.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client getClientById(int clientId) {
        return clientRepository.getClientById(clientId);
    }
    @Override
    public Iterable<Client> getAllClients() {
        return clientRepository.findAll();
    }
}
