package org.example.repository;

import org.example.model.RouterPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

public class RouterRepositoryJdbcTemplateImpl implements RouterRepository {

    //language=SQL
    private static final String SQL_SELECT_PORT =
            """
            SELECT t1_id , t1_port FROM
            ( SELECT router.id AS t1_id , GENERATE_SERIES( 1 , router.ports ) AS t1_port
        	    FROM router , address
               WHERE router.address_id = address.id AND
        	         address.region = ? AND
        			 address.city = ? AND
        			 address.street = ? AND
        			 address.house = ? ) AS t1
                LEFT JOIN ( SELECT router.id AS t2_id, agreement.port AS t2_port
                FROM agreement , address , router
               WHERE agreement.address_id = address.id AND
        	         agreement.router_id = router.id ) AS t2
                  ON t1_id = t2_id AND t1_port = t2_port
               WHERE t2_port IS NULL
            ORDER BY t1_id , t1_port
            """;

    private JdbcTemplate jdbcTemplate;

    public RouterRepositoryJdbcTemplateImpl( DataSource dataSource ) {
        this.jdbcTemplate = new JdbcTemplate( dataSource );
    }

    private static final RowMapper<RouterPort> routerPortRowMapper = (row, rowNumber) -> {
        int router = row.getInt("t1_id"   );
        int port   = row.getInt("t1_port" );

        return new RouterPort( router , port );
    };

    // в доме может быть более 1 маршрутизатора, в списке порты со всех их
    @Override
    public List<RouterPort> getFreePort( String region , String city , String street , String house , int count ) {
        List<RouterPort> findRouterPort = jdbcTemplate.query( SQL_SELECT_PORT , routerPortRowMapper , region , city , street , house );

        // возвращаем все сколько нашли
        if ( count < 1 ) {
            return findRouterPort;
        }

        // возвращаем столько, сколько попросили, но может быть меньше, потому что больше нет
        return findRouterPort.stream().limit( count ).collect( Collectors.toList() );
    }
}
