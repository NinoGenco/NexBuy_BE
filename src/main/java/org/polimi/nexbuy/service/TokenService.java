package org.polimi.nexbuy.service;


import org.polimi.nexbuy.model.User;

public interface TokenService {

    void addToken(String token, User user) throws Exception;

    void deleteToken(String token);

}
