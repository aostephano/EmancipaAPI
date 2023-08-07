package dev.aostephano.emancipaapi.Apply.Models;

import dev.aostephano.emancipaapi.CramScrools.Models.Address.Address;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ApplyDTO(

        UUID uuid,
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String phone,
        Address address
) {
}
