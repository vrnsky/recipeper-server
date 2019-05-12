package me.vrnsky.server.service;

public interface SecurityService {
    String findLoggedInUsername();

    void login(String username, String password);
}
