package dev.aostephano.emancipaapi.CramScrools.Services;

import dev.aostephano.emancipaapi.CramScrools.Models.Address.Address;
import dev.aostephano.emancipaapi.CramScrools.Models.CramSchool.CramSchool;
import dev.aostephano.emancipaapi.CramScrools.Models.CramSchool.CramSchoolRequest;
import dev.aostephano.emancipaapi.CramScrools.Repositories.CramSchoolRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CramSchoolService {

  private final CramSchoolRepository cramSchoolRepository;

  public CramSchoolService(CramSchoolRepository cramSchoolRepository) {
    this.cramSchoolRepository = cramSchoolRepository;
  }

  public List<CramSchool> getAllCramSchools() {
    return cramSchoolRepository.findAll();
  }

  public void saveCramSchool(CramSchool newCramSchool) {
    cramSchoolRepository.save(newCramSchool);
  }

  public ResponseEntity<CramSchool> updateCramSchoolByUuid(CramSchoolRequest data) {

    Optional<CramSchool> optionalCramSchool = Optional.ofNullable(
        cramSchoolRepository.findByUuid(data.uuid()));

    if (optionalCramSchool.isPresent()) {

      // Get the CramSchool from the optional
      CramSchool cramSchool = optionalCramSchool.get();

      // Update the CramSchool Object ref and the database
      cramSchool.setName(data.name());
      cramSchool.setBusinessHour(data.businessHour());
      cramSchool.setDescription(data.description());

      // Get the existing Address object from the fetched CramSchool
      Address existingAddress = cramSchool.getAddress();

      // Update the existing Address object with the new data
      existingAddress.setAddress(data.address().getAddress());
      existingAddress.setCity(data.address().getCity());
      existingAddress.setState(data.address().getState());
      existingAddress.setPostalCode(data.address().getPostalCode());

      return ResponseEntity.ok(cramSchool);
    } else {
      throw new EntityNotFoundException();
    }

  }

  public ResponseEntity<CramSchool> deleteCramSchoolByUuid(CramSchoolRequest data) {
    Optional<CramSchool> optionalCramSchool = Optional.ofNullable(
        cramSchoolRepository.findByUuid(data.uuid()));
    if (optionalCramSchool.isPresent()) {
      var cramSchool = optionalCramSchool.get();
      cramSchool.setActive(false);
      return ResponseEntity.noContent().build();
    } else {
      throw new EntityNotFoundException();
    }
  }
}