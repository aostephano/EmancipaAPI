package dev.aostephano.emancipaapi.CramScrools.Models.CramSchool;

import dev.aostephano.emancipaapi.CramScrools.Models.Address.AddressResponse;
import java.util.UUID;

public record CramSchoolResponse(
    UUID uuid,
    String name,
    String cramSchoolSuffix,
    String school,
    AddressResponse address,
    String businessHour,
    String description,
    boolean active
) {

}
