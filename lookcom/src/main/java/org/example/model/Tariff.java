package org.example.model;

import lombok.*;

@Setter @Getter @ToString @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
public class Tariff {
    private int     id;
    private String  name;
    private int     speed;
    private boolean iptv;
    private boolean archive;
}

