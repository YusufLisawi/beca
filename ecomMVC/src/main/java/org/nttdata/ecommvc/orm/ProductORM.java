package org.nttdata.ecommvc.orm;

import org.nttdata.ecommvc.db.DBManager;
import org.nttdata.ecommvc.model.Product;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductORM {
    private final DBManager db;
    public ProductORM() {
        db = DBManager.getInstance();
    }

    public void createProduct(String description, double price, int quantity, boolean selected, int idCategory, String image) {
        String sql = "INSERT INTO product (description, price, quantity, selected, id_category, image) VALUES (?, ?, ?, ?, ?, ?)";
        db.runExecuteUpdate(sql, description, price, quantity, selected, idCategory, image);
    }

    public void deleteProduct(int id) {
        String sql = "DELETE FROM product WHERE id = ?";
        db.runExecuteUpdate(sql, id);
    }

    public void updateProduct(int id, String description, double price, int quantity, boolean selected, int idCategory, String image) {
        String sql = "UPDATE product SET description = ?, price = ?, quantity = ?, selected = ?, idCategory = ?, image = ? WHERE id = ?";
        db.runExecuteUpdate(sql, description, price, quantity, selected, idCategory, image, id);
    }

    public ArrayList<Product> listProducts() {
        ResultSet rs = db.runExecuteQuery("SELECT * FROM product");
        return getFromSet(rs);
    }

    public ArrayList<Product> listProductsByKeyword(String keyword) {
        ResultSet rs = db.runExecuteQuery("SELECT * FROM product WHERE description LIKE '%" + keyword + "%'");
        return getFromSet(rs);
    }

    private ArrayList<Product> getFromSet(ResultSet rs) {
        ArrayList<Product> products = new ArrayList<>();

        try {
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"), rs.getString("description"), rs.getDouble("price"), rs.getInt("quantity"), rs.getBoolean("selected"), rs.getInt("id_category"), rs.getString("image")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return products;
    }

}
