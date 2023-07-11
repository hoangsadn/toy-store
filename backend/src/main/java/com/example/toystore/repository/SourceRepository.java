package com.example.toystore.repository;

import com.example.toystore.dto.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class SourceRepository {
    @Autowired
    private Sql2o dataSource;


    public List<Source> getAllProductSource() {
        try (Connection con = dataSource.open()) {
            String stmt =
                    """
                            SELECT id, name, status, note, phone, address, created, updated
                            FROM sources
                    """;
            return con.createQuery(stmt)
                    .executeAndFetch(Source.class);
        }
    }


    public int insertProductSource(Source source) {
        try (Connection con = dataSource.open()) {
            String stmt =
                    """
                            INSERT INTO sources (name, status, note, phone, address)
                            VALUES (:name, :status, :note, :phone, :address)
                    """;
            return con.createQuery(stmt)
                    .bind(source)
                    .executeUpdate()
                    .getKey(Integer.class);
        }
    }

    public Source getProductSourceById(int id) {
        try (Connection con = dataSource.open()) {
            String stmt =
                    """
                            SELECT id, name, status, note, phone, address, created, updated
                            FROM sources
                            WHERE id = :id
                    """;
            return con.createQuery(stmt)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Source.class);
        }
    }

    public List<Source> getProductSourceByIds(List<Integer> sourcesIds) {
        try (Connection con = dataSource.open()) {
            String stmt =
                    """
                            SELECT id, name, status, note, phone, address, created, updated
                            FROM sources
                            WHERE id IN (:ids)
                    """;
            return con.createQuery(stmt)
                    .addParameter("ids", sourcesIds)
                    .executeAndFetch(Source.class);
        }
    }

    public boolean updateProductSource(Source source) {
        try (Connection con = dataSource.open()) {
            String stmt =
                    """
                            UPDATE sources
                            SET name = :name, status = :status, note = :note, phone = :phone, address = :address
                            WHERE id = :id
                    """;
            return con.createQuery(stmt)
                    .bind(source)
                    .executeUpdate()
                    .getResult() == 1;
        }
    }

    public boolean deleteProductSource(int id) {
        try (Connection con = dataSource.open()) {
            String stmt =
                    """
                            DELETE FROM sources
                            WHERE id = :id
                    """;
            return con.createQuery(stmt)
                    .addParameter("id", id)
                    .executeUpdate()
                    .getResult() == 1;
        }
    }
}
