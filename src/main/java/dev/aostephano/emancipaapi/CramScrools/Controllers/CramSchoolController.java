package dev.aostephano.emancipaapi.CramScrools.Controllers;

import dev.aostephano.emancipaapi.CramScrools.Models.CramSchool.CramSchool;
import dev.aostephano.emancipaapi.CramScrools.Models.CramSchool.CramSchoolMapper;
import dev.aostephano.emancipaapi.CramScrools.Models.CramSchool.CramSchoolRequest;
import dev.aostephano.emancipaapi.CramScrools.Models.CramSchool.CramSchoolResponse;
import dev.aostephano.emancipaapi.CramScrools.Repositories.CramSchoolRepository;
import dev.aostephano.emancipaapi.CramScrools.Services.CramSchoolService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cursinho")
public class CramSchoolController {

  //Dependency Injection
  private final CramSchoolService cramSchoolService;

  public CramSchoolController(CramSchoolRepository cramSchoolRepository,
      CramSchoolService cramSchoolService) {
    this.cramSchoolService = cramSchoolService;
  }


  //GET /api/cursinho: return all CramSchools
  @GetMapping("")
  public ResponseEntity<List<CramSchoolResponse>> getAllCramSchools() {
    // Controller Responsibility
    var allCramSchools = cramSchoolService.getAllCramSchools();

    // Response Treatment
    var allCramSchoolsResponse = allCramSchools.stream()
        .map(CramSchoolMapper::fromCramSchoolToResponse).toList();

    return ResponseEntity.ok(allCramSchoolsResponse);
  }

  //POST /api/cursinho: register a new CramSchool
  @PostMapping("")
  public ResponseEntity<CramSchoolResponse> createNewCramSchool(
      @RequestBody @Valid CramSchoolRequest data) {

    // Controller Responsibility
    var newCramSchool = new CramSchool(data);
    cramSchoolService.saveCramSchool(newCramSchool);

    // Response Treatment
    var cramSchoolResponse = CramSchoolMapper.fromCramSchoolToResponse(newCramSchool);

    return ResponseEntity.ok(cramSchoolResponse);
  }


  //PUT /api/cursinho: update a new CramSchool
  @Transactional
  @PutMapping("")
  public ResponseEntity<CramSchoolResponse> updateCramSchoolByUuid(
      @RequestBody @Valid CramSchoolRequest data) {

    // Response Treatment
    var updatedCramSchool = cramSchoolService.updateCramSchoolByUuid(data);
    var cramSchoolResponse = CramSchoolMapper.fromCramSchoolToResponse(
        Objects.requireNonNull(updatedCramSchool.getBody()));

    // Controller Responsibility
    return ResponseEntity.ok(cramSchoolResponse);
  }

  //DELETE /api/cursinho: delete a CramSchool by UUID
  @Transactional
  @DeleteMapping("")
  public ResponseEntity<CramSchool> deleteCramSchool(@RequestBody @Valid CramSchoolRequest data) {
    return cramSchoolService.deleteCramSchoolByUuid(data);
  }
}
