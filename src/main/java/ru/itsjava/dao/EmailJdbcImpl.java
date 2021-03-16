package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.itsjava.domains.Email;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class EmailJdbcImpl implements EmailJdbc {

    private final JdbcOperations jdbcOperations;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public int countEmailByAddress(String address) {
        String SELECT_QUERY = "select count(*) from email where address='" + address + "'";
        return jdbcOperations.queryForObject(SELECT_QUERY, Integer.class);
    }

    @Override
    public Email getEmailById(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        String GETBYID_QUERY = "select id,address from email where id= :id";
        return namedParameterJdbcOperations.queryForObject(GETBYID_QUERY, params, new EmailMapper());
    }

    @Override
    public void insertEmail(Email email) {
        String INSERT_QUERY = "insert into email(address) values (?)";
        jdbcOperations.update(INSERT_QUERY, email.getAddress());
    }

    @Override
    public void updateEmail(Email email) {
        String UPDATE_QUERY = "update email set address=:address where id=:id";
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", email.getId()).addValue("address", email.getAddress());
        namedParameterJdbcOperations.update(UPDATE_QUERY, params);
    }

    @Override
    public void deleteEmail(long id) {
        String DELETE_QUERY = "delete from email where id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        int status = namedParameterJdbcOperations.update(DELETE_QUERY, namedParameters);
        if (status != 0) {
            System.out.println("Email data deleted for ID " + id);
        } else {
            System.out.println("No email found with ID " + id);
        }
    }

    private static class EmailMapper implements RowMapper<Email> {

        @Override
        public Email mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Email(resultSet.getLong("id"),
                    resultSet.getString("address"));
        }
    }
}
