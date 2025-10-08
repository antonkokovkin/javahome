package org.example.model;

import lombok.*;

import java.util.Objects;

@Setter @Getter @ToString @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
public class Router {
    private int     id;
    private int     address_id;
    private String  model;
    private int     ports;
    private int     port_speed;
    private boolean is_active;
}
