package org.example.model;

import lombok.*;

@Setter @Getter @ToString @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
public class Resident {
    private int id;
    private String firstName;
    private String lastName;
    private String passport;
    private String phone;
    private String email;
}
