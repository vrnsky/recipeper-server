package me.vrnsky.server.controllers;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

    public String upStatus() {
        return "UP";
    }
}
