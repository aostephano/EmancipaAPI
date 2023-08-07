package dev.aostephano.emancipaapi.CramScrools.Models.Address;

public class AddressMapper {

  public static AddressResponse fromAddressToResponse(Address address) {
    return new AddressResponse(
        address.getAddress(),
        address.getCity(),
        address.getState(),
        address.getPostalCode()
    );
  }

  public static Address fromRequestToAddress(AddressRequest addressRequest) {
    return new Address(
        addressRequest
    );
  }
}
