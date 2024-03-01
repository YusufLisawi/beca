package org.nttdata.ecommvc.orm;

import org.nttdata.ecommvc.db.DBManager;
import org.nttdata.ecommvc.model.Category;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoryORM {
    private final DBManager db;
    public CategoryORM() {
        db = DBManager.getInstance();
    }

    public void createCategory(String name, String description) {
        String sql = "INSERT INTO category (name, description) VALUES (?, ?)";
        db.runExecuteUpdate(sql, name, description);
    }

    public void deleteCategory(int id) {
        String sql = "DELETE FROM category WHERE id = ?";
        db.runExecuteUpdate(sql, id);
    }

    public void updateCategory(int id, String name, String description) {
        String sql = "UPDATE category SET name = ?, description = ? WHERE id = ?";
        db.runExecuteUpdate(sql, name, description, id);
    }

    public ArrayList<Category> listCategories() {
        ResultSet rs = db.runExecuteQuery("SELECT * FROM category");
        return getFromSet(rs);
    }

    public ArrayList<Category> listCategoriesByKeyword(String keyword) {
        ResultSet rs = db.runExecuteQuery("SELECT * FROM category WHERE name LIKE '%" + keyword + "%'");
        return getFromSet(rs);
    }

    private ArrayList<Category> getFromSet(ResultSet rs) {
        ArrayList<Category> categories = new ArrayList<>();

        try {
            while (rs.next()) {
                categories.add(new Category(rs.getInt("id"), rs.getString("name"), rs.getString("description")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

}
