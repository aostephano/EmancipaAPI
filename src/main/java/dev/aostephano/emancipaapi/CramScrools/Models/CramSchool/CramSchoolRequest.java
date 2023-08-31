package dev.aostephano.emancipaapi.CramScrools.Models.CramSchool;

import dev.aostephano.emancipaapi.CramScrools.Models.Address.Address;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record CramSchoolRequest(
    UUID uuid,
    @NotNull
    String name,
    @NotNull
    String cramSchoolSuffix,
    @NotNull
    String school,
    @NotNull
    Address address,
    @NotNull
    String businessHour,
    @NotNull
    String description
) {

}
