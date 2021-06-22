package ru.javaschool.server.processing.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;
import ru.javaschool.server.common.messages.Request;
import ru.javaschool.server.common.messages.Response;
import ru.javaschool.server.common.messages.StatusOperation;
import ru.javaschool.server.processing.AccountDTO;
import ru.javaschool.server.processing.ClientDTO;
import ru.javaschool.server.processing.exception.HostNotFoundException;
import ru.javaschool.server.processing.service.ClientService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Log
public class HostRestController {

    private ClientService clientService;

    @GetMapping("/hosts")
    public String getHostsInfo() {
        return "{data: \"1 host available\"}";
    }

    @GetMapping("/hosts/{hostId}")
    public String getHostInfo(@PathVariable Long hostId) {
        if (hostId == 1) {
            return "{data: \"Host " + hostId + " ready\"}";
        }else{
            return "{data: \"Host " + hostId + " not ready\"}";
        }
    }

    @GetMapping("/hosts/{hostId}/clients")
    public List<ClientDTO> getClientsInfo(@PathVariable Long hostId) {
        if (hostId != 1) {
            throw new HostNotFoundException();
        }

        return clientService.getAllClients();

    }

    @PostMapping("/hosts/{hostId}/clients/{clientId}")
    public Response getBalance(@PathVariable("hostId") Long hostId,
                               @PathVariable("clientId") Long clientId,
                               @RequestBody Request request) throws ParseException {
        if (hostId != 1) {
            throw new RuntimeException("Host " + hostId + " is not ready!");
        }

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(request.getData());

        int PIN = Integer.parseInt(jsonObject.get("pin").toString());
        int accountId = Integer.parseInt(jsonObject.get("accountId").toString());

        log.info(request.toString());
        List<AccountDTO> accountDTOS = clientService.getClient(clientId).getAccountDTO();
        accountDTOS = accountDTOS.stream().filter((p)->p.getAccountId()==accountId).collect(Collectors.toList());

        if(accountDTOS.stream().count()<1){
            Response response = new Response(0, StatusOperation.ACCOUNT_NOT_FOUND);
            log.info(response.toString());
            return response;
        }
        if(!clientService.getClient(clientId).checkPIN(PIN)) {
            Response response = new Response(0, StatusOperation.INCORRECT_PIN);
            log.info(response.toString());
            return response;
        }

        Response response = new Response(accountDTOS.get(0).getBalance(), StatusOperation.SUCCESS);
        log.info(response.toString());
        return response;
    }
}
