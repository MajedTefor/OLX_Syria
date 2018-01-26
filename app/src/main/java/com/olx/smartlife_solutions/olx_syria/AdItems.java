package com.olx.smartlife_solutions.olx_syria;

/**
 * Created by Crazy ITer on 1/26/2018.
 */

public class AdItems {
    String mainImageURL, itemName;

    public AdItems(String mainImageURL, String itemName) {
        this.mainImageURL = mainImageURL;
        this.itemName = itemName;
    }

    public void setMainImageURL(String mainImageURL) {
        this.mainImageURL = mainImageURL;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getMainImageURL() {

        return mainImageURL;
    }

    public String getItemName() {
        return itemName;
    }
}
