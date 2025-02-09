package com.eprise.sapp.smod.scomp.config;

import org.apache.camel.component.salesforce.AuthenticationType;
import org.apache.camel.component.salesforce.SalesforceComponent;
import org.apache.camel.component.salesforce.SalesforceEndpointConfig;
import org.apache.camel.component.salesforce.SalesforceLoginConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${salesforce.USERNAME_PASWORD.loginUrl}")
    private String loginUrl;

    @Value("${salesforce.USERNAME_PASWORD.clientId}")
    private String clientId;

    @Value("${salesforce.USERNAME_PASWORD.clientSecret}")
    private String clientSecret;

    @Value("${salesforce.USERNAME_PASWORD.grant}")
    private String grant;

    @Value("${salesforce.USERNAME_PASWORD.apiVersion}")
    private String apiVersion;

    @Value("${salesforce.USERNAME_PASWORD.proxyHost}")
    private String proxyHost;

    @Value("${salesforce.USERNAME_PASWORD.proxyPort}")
    private Integer proxyPort;

    public SalesforceLoginConfig type2LoginConfig() {
        SalesforceLoginConfig s = new SalesforceLoginConfig();
        s.setLoginUrl(loginUrl);
        s.setClientId(clientId);
        s.setClientSecret(clientSecret);
        s.setType(AuthenticationType.USERNAME_PASSWORD);
        s.setPassword(grant);
        return s;
    }

    @Bean
    public SalesforceComponent salesforceComponent1() {
        SalesforceComponent s = new SalesforceComponent();
        s.setLoginConfig(type2LoginConfig());
        SalesforceEndpointConfig e = new SalesforceEndpointConfig();
        e.setApiVersion(apiVersion);
        s.setConfig(e);
        s.setHttpProxySecure(false);
        s.setHttpProxyHost(proxyHost);
        s.setHttpProxyPort(proxyPort);
        return s;
    }

}
