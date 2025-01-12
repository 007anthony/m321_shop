package ch.bbw.ap.shop.shoppingcart.client.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CartResponse {

    private Long id;
    private UserResponse user;
    private boolean active;

    private List<ProductResponse> products = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }
}
