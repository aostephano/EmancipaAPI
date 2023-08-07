package dev.aostephano.emancipaapi.CramScrools.Models.Address;

import jakarta.validation.constraints.NotNull;

public record AddressRequest(
    @NotNull
    String address,
    @NotNull
    String city,
    @NotNull
    String state,
    @NotNull
    String postalCode
) {

}
