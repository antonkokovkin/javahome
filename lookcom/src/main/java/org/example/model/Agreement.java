package org.example.model;

import lombok.*;

@Setter @Getter @ToString @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
public class Agreement {
    private int id;
    private int resident_id;
    private int tariff_id;
    private int address_id;
    private int apartment;
    private int router_id;
    private int port;
    private int balance;
}
