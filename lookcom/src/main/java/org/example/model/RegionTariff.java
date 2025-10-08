package org.example.model;

import lombok.*;

@Setter @Getter @ToString @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
public class RegionTariff {
    private String region;
    private int    tariff_id;
    private int    cost;
}
