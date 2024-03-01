package org.nttdata.ecommvc.model;

public class Product {
    private int id;
    private String description;
    private double price;
    private int quantity;
    private boolean selected;
    private int idCategory;
    private String image;

    public Product() {}

    public Product(int id, String description, double price, int quantity, boolean selected, int idCategory, String image) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.selected = selected;
        this.idCategory = idCategory;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
