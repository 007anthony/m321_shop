package ch.bbw.ap.shop.shoppingcart.service.impl;

import ch.bbw.ap.shop.shoppingcart.client.UserClient;
import ch.bbw.ap.shop.shoppingcart.client.response.UserResponse;
import ch.bbw.ap.shop.shoppingcart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserClient userClient;


    @Override
    public UserResponse getCurrentUser() {

        return userClient.getMe();
    }
}
