package ru.javaschool.client.service;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import ru.javaschool.client.dto.BalanceDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.math.BigDecimal;


@Service
public class ATMService {

    public BalanceDTO getClientBalance(String responseEntityBody) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(responseEntityBody);

        BigDecimal balance = new BigDecimal((Long)jsonObject.get("balance"));

        return new BalanceDTO(balance);
    }
}
