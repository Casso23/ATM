package ru.javaschool.server.processing;

import lombok.Value;

import java.util.List;

@Value
public class ClientDTO {
    private final int clientId;
    private final int PIN;
    private final List<AccountDTO> accountDTO;

    public boolean checkPIN(int PIN){
        return this.PIN==PIN;
    }
}
