package com.negozio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/database")
@Profile("dev")
public class DatabaseController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/info")
    public Map<String, Object> getDatabaseInfo() {
        Map<String, Object> info = new HashMap<>();
        try (Connection connection = dataSource.getConnection()) {
            info.put("url", connection.getMetaData().getURL());
            info.put("driver", connection.getMetaData().getDriverName());
            info.put("version", connection.getMetaData().getDriverVersion());

            // Conta le tabelle
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'");
            List<String> tables = new ArrayList<>();
            while (rs.next()) {
                tables.add(rs.getString("TABLE_NAME"));
            }
            info.put("tables", tables);

            // Conta i prodotti (nome tabella plurale)
            rs = stmt.executeQuery("SELECT COUNT(*) as count FROM PRODOTTI");
            if (rs.next()) {
                info.put("product_count", rs.getInt("count"));
            }

        } catch (Exception e) {
            info.put("error", e.getMessage());
        }
        return info;
    }

    @GetMapping("/categorie")
    public List<Map<String, Object>> getCategorie() {
        List<Map<String, Object>> categorie = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CATEGORIE ORDER BY ID");

            while (rs.next()) {
                Map<String, Object> categoria = new HashMap<>();
                categoria.put("id", rs.getInt("ID"));
                categoria.put("nome", rs.getString("NOME"));
                categoria.put("descrizione", rs.getString("DESCRIZIONE"));
                categorie.add(categoria);
            }
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            categorie.add(error);
        }
        return categorie;
    }

    @GetMapping("/tables")
    public List<Map<String, Object>> getTables() {
        List<Map<String, Object>> tables = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'"
            );

            while (rs.next()) {
                Map<String, Object> table = new HashMap<>();
                String tableName = rs.getString("TABLE_NAME");
                table.put("name", tableName);

                // Conta i record per ogni tabella
                try (Statement countStmt = connection.createStatement()) {
                    ResultSet countRs = countStmt.executeQuery("SELECT COUNT(*) as count FROM " + tableName);
                    if (countRs.next()) {
                        table.put("count", countRs.getInt("count"));
                    }
                } catch (Exception e) {
                    table.put("count", "N/A");
                }

                tables.add(table);
            }
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            tables.add(error);
        }
        return tables;
    }
}