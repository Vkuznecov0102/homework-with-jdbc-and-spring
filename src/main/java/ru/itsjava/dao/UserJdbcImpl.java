package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.itsjava.domains.Email;
import ru.itsjava.domains.Pet;
import ru.itsjava.domains.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class UserJdbcImpl implements UserJdbc {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public int countUserByName(String fio) {
        String selectQuery = "select count(*) from user where fio=:fio";
        return namedParameterJdbcOperations.queryForObject(selectQuery, Map.of("fio", fio), Integer.class);
    }

    @Override
    public User getUserById(long id) {
        String getByIdQuery = "select user.id,user.fio,user.email_id,user.pet_id,email.id,email.address,pet.id,pet.type,pet.name from user,email,pet where user.id= :id";
        return namedParameterJdbcOperations.queryForObject(getByIdQuery, Map.of("id", id), new UserMapper());
    }

    @Override
    public void insertUser(User user) {
        String insertQuery = "insert into user(fio,email_id,pet_id) values (?,?,?)";
        namedParameterJdbcOperations.getJdbcOperations().update(insertQuery, user.getFio(), user.getMail().getId(), user.getPet().getId());
    }

    @Override
    public void updateUser(User user) {
        String updateQuery = "update user set fio = :fio where id = :id";
        SqlParameterSource params = new MapSqlParameterSource().addValue("fio", user.getFio()).addValue("id", user.getId());
        namedParameterJdbcOperations.update(updateQuery, params);
    }

    @Override
    public void deleteUser(long id) {
        String deleteQuery = "delete from user where id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        int status = namedParameterJdbcOperations.update(deleteQuery, namedParameters);
        if (status != 0) {
            System.out.println("User data deleted with ID " + id);
        } else {
            System.out.println("No user found with ID " + id);
        }
    }

    private static class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            return new User(resultSet.getLong("id"),
                    resultSet.getString("fio"),
                    new Email(resultSet.getLong("id"), resultSet.getString("address")),
                    new Pet(resultSet.getLong("id"), resultSet.getString("type"), resultSet.getString("name")));
        }
    }
}
