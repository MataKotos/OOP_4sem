package com.omgtu.repo;

import com.omgtu.model.Bouquet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BouquetRepo {

    public List<Bouquet> findAll() {
        List<Bouquet> bouquets = new ArrayList<>();
        String sql = "SELECT id, name, size, price, description, status FROM bouquets";

        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Bouquet bouquet = new Bouquet();
                bouquet.setName(rs.getString("name"));
                bouquet.setSize(rs.getString("size"));
                bouquet.setPrice(rs.getDouble("price"));
                bouquet.setDescription(rs.getString("description"));
                bouquet.setStatus(rs.getString("status"));
                bouquets.add(bouquet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bouquets;
    }

    public boolean save(Bouquet bouquet) {
        String sql = "INSERT INTO bouquets (name, size, price, description, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, bouquet.getName());
            ps.setString(2, bouquet.getSize());
            ps.setDouble(3, bouquet.getPrice());
            ps.setString(4, bouquet.getDescription());
            ps.setString(5, bouquet.getStatus());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

