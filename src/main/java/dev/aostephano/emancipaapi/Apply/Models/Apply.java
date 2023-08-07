package dev.aostephano.emancipaapi.Apply.Models;

import dev.aostephano.emancipaapi.CramScrools.Models.Address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity(name = "Apply")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Apply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private UUID uuid;

    private String name;
    private String email;
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    public Apply(ApplyDTO applyDTO) {
        this.uuid = UUID.randomUUID();
        this.name = applyDTO.name();
        this.email = applyDTO.email();
        this.phone = applyDTO.phone();
        this.address = applyDTO.address();
    }
}