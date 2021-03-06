/*
 * Copyright (C) 2007-2014, GoodData(R) Corporation. All rights reserved.
 */
package com.gooddata;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static net.jadler.Jadler.closeJadler;
import static net.jadler.Jadler.initJadler;
import static net.jadler.Jadler.port;

public abstract class AbstractGoodDataIT {

    protected static final ObjectMapper MAPPER = new ObjectMapper();
    protected GoodData gd;

    @BeforeMethod
    public void commonSetUp() {
        initJadler().that().respondsWithDefaultContentType("application/json");
        gd = new GoodData("localhost", "sdk@gooddata.com", "sdk", port(), "http", new GoodDataSettings()) {
            @Override
            protected HttpClient createHttpClient(final String login, final String password, final String hostname,
                                                  final int port, final String protocol,
                                                  final HttpClientBuilder builder) {
                return builder.build();
            }
        };
    }

    @AfterMethod
    public void tearDown() {
        closeJadler();
    }
}
