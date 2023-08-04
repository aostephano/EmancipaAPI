package dev.aostephano.emancipaapi.CramScrools.Controllers;

import dev.aostephano.emancipaapi.CramScrools.Models.CramSchool;
import dev.aostephano.emancipaapi.CramScrools.Models.CramSchoolDTO;
import dev.aostephano.emancipaapi.CramScrools.Repositories.CramSchoolRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/cursinho")
public class CramSchoolController {

    //Dependency Injection
    @Autowired
    private CramSchoolRepository cramSchoolRepository;

    //GET /api/cursinho: return all CramSchools
    @GetMapping("")
    public ResponseEntity<List<CramSchool>> getAllCramSchools() {
        List<CramSchool> allCramSchools = cramSchoolRepository.findAll();
        return ResponseEntity.ok(allCramSchools);
    }

    //POST /api/cursinho: register a new CramSchool
    @PostMapping("")
    public ResponseEntity<CramSchool> registerCramSchool(@RequestBody @Valid CramSchoolDTO data){
        CramSchool newCramSchool = new CramSchool(data);
        cramSchoolRepository.save(newCramSchool);
        return ResponseEntity.ok(newCramSchool);
    }


    //POST /api/cursinho: update a new CramSchool
    @Transactional
    @PutMapping("")
    public ResponseEntity<CramSchool> updateCramSchoolByUuid(@RequestBody @Valid CramSchoolDTO data){
        Optional<CramSchool> optionalCramSchool = Optional.ofNullable(cramSchoolRepository.findByUuid(data.uuid()));
        if (optionalCramSchool.isPresent()) {
            // Get the CramSchool from the optional
            CramSchool newCramSchool = optionalCramSchool.get();
            // Update the CramSchool Object ref and the database
            newCramSchool.setName(data.name());
            newCramSchool.setBusinessHour(data.businessHour());
            newCramSchool.setDescription(data.description());
            newCramSchool.setAddress(data.address());

            return ResponseEntity.ok(newCramSchool);
        } else {
            throw new EntityNotFoundException();
        }
    }

    //DELETE /api/cursinho: delete a CramSchool by UUID
    @Transactional
    @DeleteMapping("")
    public ResponseEntity<CramSchool> deleteCramSchool(@RequestBody @Valid CramSchoolDTO data){
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
