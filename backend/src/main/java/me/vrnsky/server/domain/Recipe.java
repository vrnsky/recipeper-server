package me.vrnsky.server.domain;

import java.util.List;

public class Recipe {

    private int id;
    private String title;
    private String description;
    private User author;
    private List<Product> productList;

}
