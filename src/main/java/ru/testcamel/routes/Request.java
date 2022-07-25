package ru.testcamel.routes;

import lombok.Data;

import java.io.Serializable;

@Data
public class Request implements Serializable {
    private String text;
}
