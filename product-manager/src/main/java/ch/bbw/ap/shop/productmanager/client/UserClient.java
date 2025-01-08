package ch.bbw.ap.shop.productmanager.client;

import ch.bbw.ap.shop.productmanager.client.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("user-manager")
public interface UserClient {

    @GetMapping("/auth/me")
    UserResponse getMe(@RequestHeader("Authorization") String bearerToken);
}
