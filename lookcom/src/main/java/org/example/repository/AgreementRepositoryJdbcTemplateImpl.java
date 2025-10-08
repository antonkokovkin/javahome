package org.example.repository;

import org.example.model.Abonent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class AgreementRepositoryJdbcTemplateImpl implements AgreementRepository {

    //language=SQL
    private static final String SQL_UPDATE_BALANCE = "UPDATE agreement SET balance = ? WHERE agreement.id = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL_ABONENT =
            """
                SELECT
                    agreement.id ,
                    agreement.resident_id ,
                    resident.last_name ,
                    resident.first_name ,
                    resident.passport ,
                    resident.phone ,
                    resident.email ,
                    agreement.apartment ,
                    agreement.port ,
                    agreement.balance ,
                    agreement.address_id ,
                    address.region ,
                    address.city ,
                    address.street ,
                    address.house ,
                    agreement.router_id ,
                    router.model ,
                    router.ports ,
                    router.port_speed ,
                    router.is_active ,
                    agreement.tariff_id ,
                    tariff.name ,
                    tariff.speed ,
                    tariff.iptv ,
                    tariff.archive ,
                    region_tariff.cost
                FROM
                    agreement , address , resident , tariff , region_tariff , router
                WHERE
                    agreement.address_id = address.id AND
                    agreement.resident_id = resident.id AND
                    agreement.tariff_id = tariff.id AND
                    agreement.router_id = router.id AND
                    region_tariff.tariff_id = tariff.id AND
                    region_tariff.region = address.region
                ORDER BY
                    resident.last_name , resident.first_name
            """;

    private JdbcTemplate jdbcTemplate;

    public AgreementRepositoryJdbcTemplateImpl( DataSource dataSource ) {
        this.jdbcTemplate = new JdbcTemplate( dataSource );
    }

    private static final RowMapper<Abonent> abonentRowMapper = (row, rowNumber ) -> {
        int     id             = row.getInt(     "id"          );
        int     resident_id    = row.getInt(     "resident_id" );
        String  last_name      = row.getString(  "last_name"   );
        String  first_name     = row.getString(  "first_name"  );
        String  passport       = row.getString(  "passport"    );
        String  phone          = row.getString(  "phone"       );
        String  email          = row.getString(  "email"       );
        int     apartment      = row.getInt(     "apartment"   );
        int     port           = row.getInt(     "port"        );
        int     balance        = row.getInt(     "balance"     );
        int     address_id     = row.getInt(     "address_id"  );
        String  region         = row.getString(  "region"      );
        String  city           = row.getString(  "city"        );
        String  street         = row.getString(  "street"      );
        String  house          = row.getString(  "house"       );
        int     router_id      = row.getInt(     "router_id"   );
        String  model          = row.getString(  "model"       );
        int     ports          = row.getInt(     "ports"       );
        int     port_speed     = row.getInt(     "port_speed"  );
        boolean is_active      = row.getBoolean( "is_active"   );
        int     tariff_id      = row.getInt(     "tariff_id"   );
        String  name           = row.getString(  "name"        );
        int     speed          = row.getInt(     "speed"       );
        boolean iptv           = row.getBoolean( "iptv"        );
        boolean archive        = row.getBoolean( "archive"     );
        int     cost           = row.getInt(     "cost"        );

        return new Abonent( id , resident_id , last_name , first_name , passport , phone , email , apartment , port , balance , address_id , region , city , street , house , router_id , model , ports , port_speed , is_active , tariff_id , name , speed , iptv , archive , cost );
    };

    @Override
    public void updateBalance( int id , int balance ) {
        jdbcTemplate.update( SQL_UPDATE_BALANCE , balance , id );
    }

    @Override
    public List<Abonent> findAbonentAll() {
        return jdbcTemplate.query( SQL_SELECT_ALL_ABONENT , abonentRowMapper );
    }
}

