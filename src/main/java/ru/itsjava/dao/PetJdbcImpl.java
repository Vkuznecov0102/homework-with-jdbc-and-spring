package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.itsjava.domains.Pet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class PetJdbcImpl implements PetJdbc {

    private final JdbcOperations jdbcOperations;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public int countPetByType(String type) {
        String SELECT_QUERY = "select count(*) from pet where type='" + type + "'";
        return jdbcOperations.queryForObject(SELECT_QUERY, Integer.class);
    }

    @Override
    public Pet getPetById(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        String GETBYID_QUERY = "select id,type,name from pet where id= :id";
        return namedParameterJdbcOperations.queryForObject(GETBYID_QUERY, params, new PetMapper());
    }

    @Override
    public void insertPet(Pet pet) {
        String INSERT_QUERY = "insert into pet(type,name) values (?,?)";
        jdbcOperations.update(INSERT_QUERY, pet.getType(), pet.getName());
    }

    @Override
    public void updatePet(Pet pet) {
        String UPDATE_QUERY = "update pet set type = :type, name = :name where id = :id";
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", pet.getId()).addValue("type", pet.getType()).addValue("name", pet.getName());
        namedParameterJdbcOperations.update(UPDATE_QUERY, params);
    }

    @Override
    public void deletePet(long id) {
        String DELETE_QUERY = "delete from pet where id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        int status = namedParameterJdbcOperations.update(DELETE_QUERY, namedParameters);
        if (status != 0) {
            System.out.println("Pet data deleted for ID " + id);
        } else {
            System.out.println("No Pet found with ID " + id);
        }

//        SqlParameterSource params=new MapSqlParameterSource("id",id);
//        namedParameterJdbcOperations.update(DELETE_QUERY, params);
    }

    private static class PetMapper implements RowMapper<Pet> {

        @Override
        public Pet mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Pet(resultSet.getLong("id"),
                    resultSet.getString("type"),
                    resultSet.getString("name"));
        }
    }
}
