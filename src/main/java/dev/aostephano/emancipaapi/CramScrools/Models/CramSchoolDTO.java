package dev.aostephano.emancipaapi.CramScrools.Models;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CramSchoolDTO (
        UUID uuid,
        @NotBlank
        String name,
        Address address,
        @NotBlank
        String businessHour,
        @NotBlank
        String description
)
{

}
