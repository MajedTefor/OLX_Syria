package com.olx.smartlife_solutions.olx_syria;

/**
 * Created by Crazy ITer on 1/25/2018.
 */

public class FavoriteItems {
    private String imageURL, name, price;
    private boolean isFavorite;

    public FavoriteItems(String imageURL, String name, String price, boolean isFavorite) {
        this.imageURL = imageURL;
        this.name = name;
        this.price = price;
        this.isFavorite = isFavorite;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getImageURL() {

        return imageURL;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
}
