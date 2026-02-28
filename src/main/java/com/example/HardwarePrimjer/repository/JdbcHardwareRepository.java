package com.example.HardwarePrimjer.repository;

import com.example.HardwarePrimjer.domain.Category;
import com.example.HardwarePrimjer.domain.Hardware;
import com.example.HardwarePrimjer.domain.SearchHardware;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Primary
@Repository
@AllArgsConstructor
public class JdbcHardwareRepository implements HardwareRepository {

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Hardware> getAllHardware() {
        return jdbcTemplate.query("SELECT * FROM HARDWARE", new HardwareMapper());
    }

    @Override
    public List<Hardware> getHardwareByName(String hardwareName) {
        return jdbcTemplate.query("SELECT * FROM HARDWARE WHERE NAME = ?",
                new HardwareMapper(), hardwareName);
    }

    @Override
    public Optional<Hardware> getHardwareById(Integer id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("hardwareId", id);
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM HARDWARE WHERE ID = :hardwareId",
                new HardwareMapper(), parameters));
    }

    @Override
    public Hardware saveNewHardwarePost(Hardware hardware) {
        final String SQL =
                "SELECT ID FROM FINAL TABLE (INSERT INTO HARDWARE (name, code, price,quantity, categoryId) VALUES (?, ?, ?, ?,?)) HARDWARE";
        Integer generatedId = jdbcTemplate.queryForObject(SQL, Integer.class, hardware.getName(), hardware.getCode(), hardware.getPrice(), hardware.getQuantity(), hardware.getCategory().getId());
        hardware.setId(generatedId);
        return hardware;
    }

    @Override
    public List<Hardware> filterByParameters(SearchHardware searchHardware) {
        StringJoiner where = new StringJoiner(" AND ", " WHERE ", "").setEmptyValue("");
        Map<String, Object> params = new HashMap<>();

        if (Optional.ofNullable(searchHardware.getId()).isPresent()) {
            where.add("id = ?");
            params.put("id", searchHardware.getId());
        }

        if (!searchHardware.getName().isEmpty()) {
            where.add("name LIKE ?");
            params.put("name", searchHardware.getName());
        }

        if (!searchHardware.getCode().isEmpty()) {
            where.add("code LIKE ?");
            params.put("code", searchHardware.getCode());
        }

        where.add("price between ? AND ?");

        if (Optional.ofNullable(searchHardware.getLowerPrice()).isPresent()) {
            params.put("lowerPrice", searchHardware.getLowerPrice());
        } else {
            params.put("lowerPrice", BigDecimal.ZERO);
        }

        if (Optional.ofNullable(searchHardware.getUpperPrice()).isPresent()) {
            params.put("upperPrice", searchHardware.getUpperPrice());
        } else {
            params.put("upperPrice", BigDecimal.valueOf(Double.MAX_VALUE));
        }

        if (Optional.ofNullable(searchHardware.getCategory()).isPresent()) {
            where.add("categoryId = ?");
            params.put("categoryId", searchHardware.getCategory().getId());
        }

        String sql = "SELECT id, name, description, price, categoryId" +
                " FROM article" +
                where;

        PreparedStatementSetter pss = ps -> {

            int ordinal = 1;

            if (params.containsKey("id")) {
                ps.setInt(ordinal, (Integer) params.get("id"));
                ordinal++;
            }

            if (params.containsKey("name")) {
                ps.setString(ordinal, "'%" + (String) params.get("name") + "%'");
                ordinal++;
            }

            if (params.containsKey("description")) {
                ps.setString(ordinal, "'%" + (String) params.get("description") + "%'");
                ordinal++;
            }

            ps.setBigDecimal(ordinal, (BigDecimal) params.get("lowerPrice"));
            ordinal++;
            ps.setBigDecimal(ordinal, (BigDecimal) params.get("upperPrice"));
            ordinal++;

            if (params.containsKey("categoryId")) {
                ps.setInt(ordinal, (Integer) params.get("categoryId"));
            }
        };

        return jdbcTemplate.query(sql, pss, new HardwareMapper());
    }

    @Override
    public Optional<Hardware> updateHardware(Hardware hardwareToUpdate, Integer id) {
        if (hardwareByIdExists(id)) {
            final String SQL =
                    "UPDATE HARDWARE SET name = ?, code = ?, price = ?, categoryId = ? WHERE ID = ?";
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL);
                ps.setString(1, hardwareToUpdate.getName());
                ps.setString(2, hardwareToUpdate.getCode());
                ps.setBigDecimal(3, hardwareToUpdate.getPrice());
                ps.setInt(4, hardwareToUpdate.getCategory().getId());
                ps.setInt(5, id);
                return ps;
            });
            hardwareToUpdate.setId(id);
            return Optional.of(hardwareToUpdate);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean hardwareByIdExists(Integer id) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT (*) FROM HARDWARE WHERE ID = ?", Integer.class, id);
        return count > 0;
    }

    @Override
    public boolean deleteHardwareById(Integer id) {
        if (hardwareByIdExists(id)) {
            jdbcTemplate.update(
                    "DELETE FROM HARDWARE WHERE ID = ?", id);
            return true;
        } else {
            return false;
        }
    }

    private static class HardwareMapper implements RowMapper<Hardware> {

        public Hardware mapRow(ResultSet rs, int i) throws SQLException {

            Hardware newHardware = new Hardware();
            newHardware.setId(rs.getInt("ID"));
            newHardware.setName(rs.getString("NAME"));
            newHardware.setCode(rs.getString("CODE"));
            newHardware.setPrice(rs.getBigDecimal("PRICE"));
            newHardware.setQuantity(rs.getInt("QUANTITY"));

            Integer categoryId = rs.getInt("CATEGORYID");

            if (Category.CPU.getId().equals(categoryId)) {
                newHardware.setCategory(Category.CPU);
            } else if (Category.GPU.getId().equals(categoryId)){
                newHardware.setCategory(Category.GPU);
            }else if (Category.MBO.getId().equals(categoryId)){
                newHardware.setCategory(Category.MBO);
            }else if (Category.RAM.getId().equals(categoryId)){
                newHardware.setCategory(Category.RAM);
            }else if (Category.STORAGE.getId().equals(categoryId)){
                newHardware.setCategory(Category.STORAGE);
            }
            else {
                newHardware.setCategory(Category.OTHER);
            }

            return newHardware;
        }
    }
}
