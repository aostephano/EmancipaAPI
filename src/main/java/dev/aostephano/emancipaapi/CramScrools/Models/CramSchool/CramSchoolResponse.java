package dev.aostephano.emancipaapi.CramScrools.Models.CramSchool;
import dev.aostephano.emancipaapi.CramScrools.Models.Address.AddressResponse;

import java.util.UUID;

public record CramSchoolResponse(
    UUID uuid,
    String name,
    AddressResponse address,
    String businessHour,
    String description
)
{
}
