package com.eprise.sapp.smod.scomp.route;

import com.eprise.sapp.smod.scomp.processor.SampleProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class SampleRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        //getCamelContext().getGlobalOptions().put("CamelJacksonEnableTypeConverter", "true");
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
    }
}
