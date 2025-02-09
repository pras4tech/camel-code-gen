package com.eprise.sapp.smod.scomp.route;

import com.eprise.sapp.smod.scomp.processor.SampleProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.salesforce.SalesforceComponent;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SampleRoute extends RouteBuilder {


    @Autowired
    private SalesforceComponent salesforceComponent1;

    @Override
    public void configure() throws Exception {
        //getCamelContext().getGlobalOptions().put("CamelJacksonEnableTypeConverter", "true");
        getContext().addComponent("salesforceComponent1", salesforceComponent1);

        // REST API
        restConfiguration().component("servlet");
        rest().path("/api/sample")
                .get()
                .to("direct:test");

        from("direct:test")
                .convertBodyTo(String.class)
                .log("Call Received")
                .process( e -> {
                    System.out.println("test");
                })
                .bean(SampleProcessor.class, "process('test', ${body}, *)")
                .marshal().json(JsonLibrary.Jackson);

        // Platform Event
        from("salesforceComponent1:subscribe:{{salesforce.event.channel}}")
                .to("direct:processEvent");

        from("direct:processEvent")
                .log("${body}");
    }
}
