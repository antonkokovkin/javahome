package org.example.repository;

import org.example.model.Address;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class AddressRepositoryJdbcTemplateImpl implements AddressRepository {

    //language=SQL
    private static final String SQL_SELECT_ALL = "SELECT * FROM address";

    //language=SQL
    private static final String SQL_DELETE_NOTROUTER = "DELETE FROM address WHERE address.id NOT IN ( SELECT router.address_id FROM router )";

    private JdbcTemplate jdbcTemplate;

    public AddressRepositoryJdbcTemplateImpl( DataSource dataSource ) {
        this.jdbcTemplate = new JdbcTemplate( dataSource );
    }

    private static final RowMapper<Address> addressRowMapper = (row, rowNumber ) -> {
        int    id     = row.getInt(    "id"     );
        String region = row.getString( "region" );
        String city   = row.getString( "city"   );
        String street = row.getString( "street" );
        String house  = row.getString( "house"  );

        return new Address( id , region , city , street , house );
    };

    @Override
    public void deleteNotRouter() {
        jdbcTemplate.update( SQL_DELETE_NOTROUTER );
    }

    @Override
    public List<Address> findAddressAll() {
         return jdbcTemplate.query( SQL_SELECT_ALL , addressRowMapper );
    }
}
