package com.eprise.sapp.smod.scomp.processor;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SampleProcessor {
    public void process(String operation, String body, Exchange exchange) {
        exchange.getIn().setBody(Map.of("status", "success", "message", "Hello. Welcome to Camel World!"));
    }
}
