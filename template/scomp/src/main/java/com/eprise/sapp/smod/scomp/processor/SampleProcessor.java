package com.eprise.sapp.smod.scomp.processor;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SampleProcessor {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void process(String operation, String body, Exchange exchange) {

        String result = jdbcTemplate.queryForObject("select CURRENT_USER", String.class);

        exchange.getIn().setBody(Map.of("status", "success", "db user", result, "message", "Hello. Welcome to Camel World!"));
    }
}
