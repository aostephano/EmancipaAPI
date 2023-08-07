package dev.aostephano.emancipaapi.CramScrools.Controllers;

import dev.aostephano.emancipaapi.CramScrools.Models.Address.Address;
import dev.aostephano.emancipaapi.CramScrools.Models.Address.AddressResponse;
import dev.aostephano.emancipaapi.CramScrools.Models.CramSchool.CramSchool;
import dev.aostephano.emancipaapi.CramScrools.Models.CramSchool.CramSchoolMapper;
import dev.aostephano.emancipaapi.CramScrools.Models.CramSchool.CramSchoolRequest;
import dev.aostephano.emancipaapi.CramScrools.Models.CramSchool.CramSchoolResponse;
import dev.aostephano.emancipaapi.CramScrools.Repositories.CramSchoolRepository;
import dev.aostephano.emancipaapi.CramScrools.Services.CramSchoolService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/cursinho")
public class CramSchoolController {

    //Dependency Injection
    private final CramSchoolRepository cramSchoolRepository;
    private final CramSchoolService cramSchoolService;

    public CramSchoolController(CramSchoolRepository cramSchoolRepository, CramSchoolService cramSchoolService) {
        this.cramSchoolRepository = cramSchoolRepository;
        this.cramSchoolService = cramSchoolService;
    }

    //POST /api/cursinho: register a new CramSchool
    @PostMapping("")
    public ResponseEntity<CramSchoolResponse> createNewCramSchool(@RequestBody @Valid CramSchoolRequest data){

        // Controller Responsibility
        CramSchool newCramSchool = new CramSchool(data);
        cramSchoolService.saveCramSchool(newCramSchool);

        // Response Treatment
        var cramSchoolResponse = CramSchoolMapper.fromCramSchoolToResponse(newCramSchool);

        return ResponseEntity.ok(cramSchoolResponse);
    }

    //GET /api/cursinho: return all CramSchools
    @GetMapping("")
    public ResponseEntity<List<CramSchoolResponse>> getAllCramSchools() {
        // Controller Responsibility
        var allCramSchools = cramSchoolService.getAllCramSchools();

        // Response Treatment
        var allCramSchoolsResponse = allCramSchools.stream()
        .map(CramSchoolMapper::fromCramSchoolToResponse)
        .toList();

        return ResponseEntity.ok(allCramSchoolsResponse);
    }


    //POST /api/cursinho: update a new CramSchool
    @Transactional
    @PutMapping("")
    public ResponseEntity<CramSchool> updateCramSchoolByUuid(@RequestBody @Valid CramSchoolRequest data){




        Optional<CramSchool> optionalCramSchool = Optional.ofNullable(cramSchoolRepository.findByUuid(data.uuid()));
        if (optionalCramSchool.isPresent()) {

            CramSchoolService.updateCramSchoolByUuid(data);

            // Get the CramSchool from the optional
            CramSchool newCramSchool = optionalCramSchool.get();

            // Update the CramSchool Object ref and the database
            newCramSchool.setName(data.name());
            newCramSchool.setBusinessHour(data.businessHour());
            newCramSchool.setDescription(data.description());

            // Get the existing Address object from the fetched CramSchool
            Address existingAddress = newCramSchool.getAddress();

            // Update the existing Address object with the new data
            existingAddress.setAddress(data.address().getAddress());
            existingAddress.setCity(data.address().getCity());
            existingAddress.setState(data.address().getState());
            existingAddress.setPostalCode(data.address().getPostalCode());

            return ResponseEntity.ok(newCramSchool);
        } else {
            throw new EntityNotFoundException();
        }
    }

    //DELETE /api/cursinho: delete a CramSchool by UUID
    @Transactional
    @DeleteMapping("")
    public ResponseEntity<CramSchool> deleteCramSchool(@RequestBody @Valid CramSchoolRequest data){
        Optional<CramSchool> optionalCramSchool = Optional.ofNullable(cramSchoolRepository.findByUuid(data.uuid()));
        if (optionalCramSchool.isPresent()) {
            CramSchool cramSchool = optionalCramSchool.get();
            cramSchool.setActive(false);
            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException();
        }
    }
}
