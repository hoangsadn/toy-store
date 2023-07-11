package com.example.toystore.repository;


import com.example.toystore.dto.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class ProductTypeRepository {
    @Autowired
    private Sql2o dataSource;


    public int insertTypeProduct(ProductType product) {
        try (Connection con = dataSource.open()) {
            String stmt =
                    """
                            INSERT INTO product_types (name, status, note)
                            VALUES (:name, :status, :note)
                    """;
            return con.createQuery(stmt)
                    .bind(product)
                    .executeUpdate()
                    .getKey(Integer.class);
        }
    }

    public List<ProductType> getAllProductType() {
        try (Connection con = dataSource.open()) {
            String stmt =
                    """
                            SELECT id, name, status, note, created, updated
                            FROM product_types
                    """;
            return con.createQuery(stmt)
                    .executeAndFetch(ProductType.class);
        }
    }

    public ProductType getProductTypeById(int id) {
        try (Connection con = dataSource.open()) {
            String stmt =
                    """
                            SELECT id, name, status, note, created, updated
                            FROM product_types
                            WHERE id = :id
                    """;
            return con.createQuery(stmt)
                    .addParameter("id", id)
                    .executeAndFetchFirst(ProductType.class);
        }
    }

    public List<ProductType> getProductTypeByIds(List<Integer> typesIds) {
        try (Connection con = dataSource.open()) {
            String stmt =
                    """
                            SELECT id, name, status, note, created, updated
                            FROM product_types
                            WHERE id IN (:ids)
                    """;
            return con.createQuery(stmt)
                    .addParameter("ids", typesIds)
                    .executeAndFetch(ProductType.class);
        }
    }

    public boolean updateProductType(ProductType type) {
        try (Connection con = dataSource.open()) {
            String stmt =
                    """
                            UPDATE product_types
                            SET name = :name, status = :status, note = :note
                            WHERE id = :id
                    """;
            return con.createQuery(stmt)
                    .bind(type)
                    .executeUpdate()
                    .getResult() == 1;
        }
    }

    public boolean deleteProductType(int id) {
        try (Connection con = dataSource.open()) {
            String stmt =
                    """
                            DELETE FROM product_types
                            WHERE id = :id
                    """;
            return con.createQuery(stmt)
                    .addParameter("id", id)
                    .executeUpdate()
                    .getResult() == 1;
        }
    }
}
