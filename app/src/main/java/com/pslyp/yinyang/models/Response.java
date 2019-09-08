package com.pslyp.yinyang.models;

public class Response {

    private int status;
    private String message;

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
