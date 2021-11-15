package com.metodywytworzenia.models;

import java.util.List;

public class Cart {

    private int cartId;
    private List<Item> itemList;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
