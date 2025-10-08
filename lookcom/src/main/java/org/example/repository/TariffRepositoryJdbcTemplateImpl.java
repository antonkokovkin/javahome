package org.example.repository;

import org.example.model.TariffCost;
import org.example.model.TariffEx;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class TariffRepositoryJdbcTemplateImpl implements TariffRepository {

    //language=SQL
    private static final String SQL_SELECT_COUNT =
            """
            SELECT tariff.* , c
            FROM tariff , ( SELECT tariff.id as tid , count(*) AS c
            FROM agreement , tariff
            WHERE agreement.tariff_id = tariff.id
            GROUP BY tariff.id )
            WHERE tariff.id = tid
            ORDER BY c DESC
            """;

    //language=SQL
    private static final String SQL_INSERT_TARIFF =
            """
            INSERT INTO
                region_tariff( tariff_id , region , cost )
            VALUES
                ( ( SELECT id FROM tariff WHERE tariff.name = ? ) , ? , ? );
            """;

    //language=SQL
    private static final String SQL_SELECT_ALL_REGION = "SELECT tariff.* , region_tariff.region , region_tariff.cost FROM tariff , region_tariff WHERE region_tariff.tariff_id = tariff.id";

    //language=SQL
    private static final String SQL_SELECT_BY_REGION =  SQL_SELECT_ALL_REGION + " AND region_tariff.region = ?";

    private JdbcTemplate jdbcTemplate;

    public TariffRepositoryJdbcTemplateImpl( DataSource dataSource ) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final RowMapper<TariffEx> tariffExRowMapper = ( row, rowNumber ) -> {
        int     id      = row.getInt(     "id"      );
        String  name    = row.getString(  "name"    );
        int     speed   = row.getInt(     "speed"   );
        boolean iptv    = row.getBoolean( "iptv"    );
        boolean archive = row.getBoolean( "archive" );
        int     count   = row.getInt(     "c"       );

        return new TariffEx( id , name , speed , iptv , archive , count );
    };

    private static final RowMapper<TariffCost> tariffCostRowMapper = ( row, rowNumber ) -> {
        int     id      = row.getInt(     "id"      );
        String  name    = row.getString(  "name"    );
        int     speed   = row.getInt(     "speed"   );
        boolean iptv    = row.getBoolean( "iptv"    );
        boolean archive = row.getBoolean( "archive" );
        String  region  = row.getString(  "region"  );
        int     cost    = row.getInt(     "cost"    );

        return new TariffCost( id , name , speed , iptv , archive , region , cost );
    };

    @Override
    public List<TariffEx> tariffCount() {
        return jdbcTemplate.query( SQL_SELECT_COUNT, tariffExRowMapper);
    }

    @Override
    public void addTariffRegion( String tariffName , String regionName , int cost ) {
        jdbcTemplate.update( SQL_INSERT_TARIFF , tariffName , regionName , cost );
    }

    @Override
    public List<TariffCost> findTariffByRegion( String regionName ) {
        return jdbcTemplate.query( SQL_SELECT_BY_REGION , tariffCostRowMapper , regionName );
    }

    @Override
    public List<TariffCost> findTariffAllRegion() {
        return jdbcTemplate.query( SQL_SELECT_ALL_REGION , tariffCostRowMapper );
    }
}
