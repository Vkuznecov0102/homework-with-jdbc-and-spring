package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.itsjava.domains.Email;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class EmailJdbcImpl implements EmailJdbc {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public int countEmailByAddress(String address) {
        String countQuery = "select count(*) from email where address=:address";
        return namedParameterJdbcOperations.queryForObject(countQuery, Map.of("address", address), Integer.class);
    }

    @Override
    public Email getEmailById(long id) {
        String getByIdQuery = "select id,address from email where id= :id";
        return namedParameterJdbcOperations.queryForObject(getByIdQuery, Map.of("id", id), new EmailMapper());
    }

    @Override
    public void insertEmail(Email email) {
        String insertQuery = "insert into email(address) values (?)";
        namedParameterJdbcOperations.getJdbcOperations().update(insertQuery, email.getAddress());
    }

    @Override
    public void updateEmail(Email email) {
        String updateQuery = "update email set address=:address where id=:id";
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", email.getId()).addValue("address", email.getAddress());
        namedParameterJdbcOperations.update(updateQuery, params);
    }

    @Override
    public void deleteEmail(long id) {
        String deleteQuery = "delete from email where id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        int status = namedParameterJdbcOperations.update(deleteQuery, namedParameters);
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
