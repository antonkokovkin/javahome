package org.example.model;

import lombok.*;

@Setter @Getter @ToString @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
public class Abonent {
    private int     id;
    private int     resident_id;
    private String  last_name;
    private String  first_name;
    private String  passport;
    private String  phone;
    private String  email;
    private int     apartment;
    private int     port;
    private int     balance;
    private int     address_id;
    private String  region;
    private String  city;
    private String  street;
    private String  house;
    private int     router_id;
    private String  model;
    private int     ports;
    private int     port_speed;
    private boolean is_active;
    private int     tariff_id;
    private String  name;
    private int     speed;
    private boolean iptv;
    private boolean archive;
    private int     cost;
}
