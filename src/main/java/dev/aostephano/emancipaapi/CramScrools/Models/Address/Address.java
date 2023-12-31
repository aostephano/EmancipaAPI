package dev.aostephano.emancipaapi.CramScrools.Models.Address;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String address;
  private String city;
  private String state;
  private String postalCode;

  public Address(AddressRequest addressRequest) {
    this.address = addressRequest.address();
    this.city = addressRequest.city();
    this.state = addressRequest.state();
    this.postalCode = addressRequest.postalCode();
  }
}
