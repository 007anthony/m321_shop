package ch.bbw.ap.shop.shoppingcart.client.response;

import java.util.Set;

public class ProductResponse {

    private static class CategoryResponse {
        private Long id;
        private String category;

        public Long getId() {
            return this.id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getCategory() {
            return this.category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }

    private static class PictureResponse {
        private Long id;
        private String filename;

        public Long getId() {
            return this.id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFilename() {
            return this.filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }
    }
    private Long id;
    private String product;
    private double price;
    private CategoryResponse category;
    private Set<PictureResponse> pictures;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public CategoryResponse getCategory() {
        return category;
    }

    public void setCategory(CategoryResponse category) {
        this.category = category;
    }

    public Set<PictureResponse> getPictures() {
        return pictures;
    }

    public void setPictures(Set<PictureResponse> pictures) {
        this.pictures = pictures;
    }
}
