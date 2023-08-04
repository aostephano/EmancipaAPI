package dev.aostephano.emancipaapi.CramScrools.Models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "CramSchool")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CramSchool {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private UUID uuid;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    private String businessHour;
    private String description;
    private boolean active;

    public CramSchool(CramSchoolDTO cramSchoolDTO) {
        this.uuid = UUID.randomUUID();
        this.name = cramSchoolDTO.name();
        this.address = cramSchoolDTO.address();
        this.businessHour = cramSchoolDTO.businessHour();
        this.description = cramSchoolDTO.description();
        this.setActive(true);
    }

}