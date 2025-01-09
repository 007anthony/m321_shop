package ch.bbw.ap.shop.productmanager.models;

import java.util.Set;

public class ProductRequest {
    private String product;
    private double price;
    private Long categoryId;

    private Set<Long> pictureIds;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Set<Long> getPictureIds() {
        return pictureIds;
    }

    public void setPictureId(Set<Long> pictureIds) {
        this.pictureIds = pictureIds;
    }


}
