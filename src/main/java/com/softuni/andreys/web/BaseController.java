package com.softuni.andreys.web;

public abstract class BaseController {

    protected String redirect(String path) {
        return "redirect:" + path;
    }
}
