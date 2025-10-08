package org.example.model;

import lombok.*;

@Setter @Getter @ToString( callSuper = true ) @NoArgsConstructor @EqualsAndHashCode( callSuper = true )
public class TariffCost extends Tariff {
    private String region;
    private int    cost;

    public TariffCost( int id , String name , int speed , boolean iptv , boolean archive , String region , int cost ) {
        super(  id , name , speed , iptv , archive );
        this.region = region;
        this.cost   = cost;
    }
}
