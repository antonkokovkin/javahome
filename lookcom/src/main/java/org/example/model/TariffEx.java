package org.example.model;

import lombok.*;

@Setter @Getter @NoArgsConstructor
@ToString( callSuper = true )
@EqualsAndHashCode( callSuper = true )
public class TariffEx extends Tariff {
    private int     count;

    public TariffEx( int id , String name , int speed , boolean iptv , boolean archive , int count ) {
        super(  id , name , speed , iptv , archive );
        this.count = count;
    }
}
