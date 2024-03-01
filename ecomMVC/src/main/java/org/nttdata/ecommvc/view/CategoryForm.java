package org.nttdata.ecommvc.view;

import org.nttdata.ecommvc.model.Category;
import org.nttdata.ecommvc.orm.CategoryORM;

import java.util.ArrayList;

public class CategoryForm {
    private int id;
    private String name;
    private String description;
    private String keyword = "";
    private ArrayList<Category> categories;

    public CategoryForm() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
}
