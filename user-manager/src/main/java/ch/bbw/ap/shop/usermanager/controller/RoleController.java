package ch.bbw.ap.shop.usermanager.controller;

import ch.bbw.ap.shop.m321shopcore.security.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @GetMapping
    public Role[] roles() {
        return Role.values();
    }
}
