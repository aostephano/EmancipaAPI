package dev.aostephano.emancipaapi.CramScrools.Models.Address;

public record AddressRequest(
    String address,
    String city,
    String state,
    String postalCode
) {
}
