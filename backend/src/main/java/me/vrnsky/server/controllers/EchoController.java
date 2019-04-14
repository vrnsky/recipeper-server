package me.vrnsky.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

    @Autowired
    private BuildProperties buildProperties;

    @RequestMapping(value = "version", method = RequestMethod.GET)
    public String getVersion() {
        return buildProperties.getVersion();
    }
}
