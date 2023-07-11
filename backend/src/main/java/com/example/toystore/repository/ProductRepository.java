package com.example.toystore.repository;

import com.example.toystore.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class ProductRepository {
    @Autowired
    private Sql2o dataSource;

    public int insertProduct(Product product){
        try (Connection con = dataSource.open()) {
            String stmt =
                    """
                            INSERT INTO products (id, name, qty, thumbnail, image, price, status, note, typeId, sourceId)
                            VALUES (:id, :name, :qty, :thumbnail, :image, :price, :status, :note, :typeId, :sourceId)
                    """;
            return con.createQuery(stmt)
                    .bind(product)
                    .executeUpdate()
                    .getKey(Integer.class);
        }
    }



    public List<Product> getAllProduct() {
        try (Connection con = dataSource.open()) {
            String stmt =
                    """
                            SELECT p.id , p.name , p.qty , p.thumbnail , p.image, p.price , p.created  , p.updated, p.status, p.note, p.typeId, p.sourceId
                            FROM products p
                    """;
            return con.createQuery(stmt)
                    .executeAndFetch(Product.class);
        }
    }

    public Product getProductById(int id) {
        try (Connection con = dataSource.open()) {
            String stmt =
                    """
                            SELECT p.id , p.name , p.qty , p.thumbnail , p.image, p.price , p.created  , p.updated, p.status, p.note, p.typeId, p.sourceId
                            FROM products p
                            WHERE p.id = :id
                    """;
            return con.createQuery(stmt)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Product.class);
        }
    }

    public boolean updateProduct(Product product) {
        try (Connection con = dataSource.open()) {
            String stmt =
                    """
                            UPDATE products
                            SET name = :name, qty = :qty, thumbnail = :thumbnail, image = :image, price = :price, status = :status, note = :note, typeId = :typeId, sourceId = :sourceId
                            WHERE id = :id
                    """;
            return con.createQuery(stmt)
                    .bind(product)
                    .executeUpdate()
                    .getResult() == 1;
        }
    }

    public boolean deleteProduct(int id) {
        try (Connection con = dataSource.open()) {
            String stmt =
                    """
                            DELETE FROM products
                            WHERE id = :id
                    """;
            return con.createQuery(stmt)
                    .addParameter("id", id)
                    .executeUpdate()
                    .getResult() == 1;
        }
    }
}
