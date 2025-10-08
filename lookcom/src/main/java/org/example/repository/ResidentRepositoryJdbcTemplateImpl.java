package org.example.repository;

import org.example.model.Resident;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class ResidentRepositoryJdbcTemplateImpl implements ResidentRepository{
    //language=SQL
    private static final String SQL_INSERT = "insert into resident( first_name , last_name , passport , phone , email ) values( ? , ? , ? , ? , ? )";

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from resident order by id";

    //language=SQL
    private static final String SQL_SELECT_BY_NAME = "select * from resident where resident.first_name like ? order by id";

    private JdbcTemplate jdbcTemplate;

    public ResidentRepositoryJdbcTemplateImpl( DataSource dataSource ) {
        this.jdbcTemplate = new JdbcTemplate( dataSource );
    }

    private static final RowMapper<Resident> userRowMapper = ( row, rowNumber ) -> {
        int    id        = row.getInt(    "id"         );
        String firstName = row.getString( "first_name" );
        String lastName  = row.getString( "last_name"  );
        String passport  = row.getString( "passport"   );
        String phone     = row.getString( "phone"      );
        String email     = row.getString( "email"      );
        return new Resident( id , firstName , lastName , passport , phone , email );
    };

    @Override
    public List<Resident> findAll() {
        return jdbcTemplate.query( SQL_SELECT_ALL , userRowMapper );
    }

    @Override
    public List<Resident> findByName( String name ) {
        return jdbcTemplate.query( SQL_SELECT_BY_NAME , userRowMapper , name );
    }

    @Override
    public void save( Resident resident ) {
        jdbcTemplate.update( SQL_INSERT , resident.getFirstName() , resident.getLastName() , resident.getPassport() , resident.getPhone() , resident.getEmail() );
    }
}

