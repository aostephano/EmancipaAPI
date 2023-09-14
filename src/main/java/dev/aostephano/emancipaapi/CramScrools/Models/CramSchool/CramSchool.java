package dev.aostephano.emancipaapi.CramScrools.Models.CramSchool;

import dev.aostephano.emancipaapi.CramScrools.Models.Address.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "CramSchool")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CramSchool {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private UUID uuid;
  private String name;
  @Column(unique = true)
  private String cramSchoolSuffix;
  private String school;
  @OneToOne(cascade = CascadeType.ALL)
  private Address address;
  private String businessHour;
  private String description;
  private boolean active;

  public CramSchool(CramSchoolRequest cramSchoolRequest) {
    this.uuid = UUID.randomUUID();
    this.name = cramSchoolRequest.name();
    this.cramSchoolSuffix = cramSchoolRequest.cramSchoolSuffix();
    this.school = cramSchoolRequest.school();
    this.address = cramSchoolRequest.address();
    this.businessHour = cramSchoolRequest.businessHour();
    this.description = cramSchoolRequest.description();
    this.setActive(true);
  }
}