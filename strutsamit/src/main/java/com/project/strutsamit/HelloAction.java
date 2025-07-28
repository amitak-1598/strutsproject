package com.project.strutsamit;

import com.opensymphony.xwork2.ActionSupport;

public class HelloAction extends ActionSupport {

    private String message;

    @Override
    public String execute() {
        message = "Hello from Struts 2 in Amit's Project!";
        return SUCCESS;
    }

    public String getMessage() {
        return message;
    }
}
