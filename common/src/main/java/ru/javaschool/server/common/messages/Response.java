package ru.javaschool.server.common.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Response {
    private int balance;
    private StatusOperation status;
}
