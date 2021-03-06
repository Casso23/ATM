package ru.javaschool.server.processing.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javaschool.server.processing.exception.ClientNotFoundException;
import ru.javaschool.server.processing.AccountDTO;
import ru.javaschool.server.processing.ClientDTO;
import ru.javaschool.server.processing.entity.Account;
import ru.javaschool.server.processing.entity.Client;
import ru.javaschool.server.processing.repository.ClientCrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ClientService {
    private ClientCrudRepository clientCrudRepository;

    public ClientDTO getClient(Long id) {
        Client client = clientCrudRepository.findById(id)
                .orElseThrow(ClientNotFoundException::new);

        Set<Account> accountSet = client.getAccounts();
        List<AccountDTO> accountDTOSet = new ArrayList<>();

        for (Account account : accountSet) {

            accountDTOSet.add(new AccountDTO(account.getId().intValue(),
                    account.getBalance()));
        }

        return new ClientDTO(client.getId().intValue(),
                client.getPIN(),
                accountDTOSet);
    }

    public List<ClientDTO> getAllClients() {
        Iterable<Client> clientIterable = clientCrudRepository.findAll();
        List<ClientDTO> clients = new ArrayList<>();

        clientIterable.forEach(
                client -> {
                    Set<Account> accountSet = client.getAccounts();
                    List<AccountDTO> accountDTOSet = new ArrayList<>();

                    for (Account account : accountSet) {

                        accountDTOSet.add(new AccountDTO(account.getId().intValue(),
                                account.getBalance()));
                    }
                    clients.add(new ClientDTO(client.getId().intValue(), client.getPIN(), accountDTOSet));
                }
        );
        return clients;
    }
}
