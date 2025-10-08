package org.example.model;

import lombok.*;

import java.util.Objects;

@Setter @Getter @ToString @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class Address {
    private int    id;
    private String region;
    private String city;
    private String street;
    private String house;
}
