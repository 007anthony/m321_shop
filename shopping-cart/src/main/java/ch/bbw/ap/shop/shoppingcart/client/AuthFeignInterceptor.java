package ch.bbw.ap.shop.shoppingcart.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthFeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();

        requestTemplate.header("Authorization", "Bearer " + token);
    }
}
