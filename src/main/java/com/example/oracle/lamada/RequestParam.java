package com.example.oracle.lamada;

import io.swagger.models.auth.In;

import java.util.List;

public class RequestParam {
    private String method;
    private String server;
    private int timeOut;

    private List<Inner> params;
    private List<Inner> headers;
    private class Inner{
        private String key;
        private String value;
    }
}
