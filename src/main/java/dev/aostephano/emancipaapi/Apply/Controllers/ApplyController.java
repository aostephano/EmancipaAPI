package dev.aostephano.emancipaapi.Apply.Controllers;

import dev.aostephano.emancipaapi.Apply.Models.Apply;
import dev.aostephano.emancipaapi.Apply.Models.ApplyDTO;
import dev.aostephano.emancipaapi.Apply.Repositories.ApplyRepository;
import dev.aostephano.emancipaapi.Apply.Services.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cursinho/apply")
public class ApplyController {
    //Dependency Injection
    private final ApplyRepository applyRepository;
    public ApplyController(ApplyRepository applyRepository) {
        this.applyRepository = applyRepository;
    }

    //GET: api/cursinho/apply - Get all applys
    @GetMapping("")
    public ResponseEntity<List<Apply>> getAllCramSchools() {
        List<Apply> allApplys = applyRepository.findAll();
        return ResponseEntity.ok(allApplys);
    }

    //POST: api/cursinho/apply - Create a new apply
    @PostMapping("")
    public ResponseEntity<Apply> createNewApply(@RequestBody ApplyDTO applyDTO) {
        Apply newApply = new Apply(applyDTO);
        applyRepository.save(newApply);
        return ResponseEntity.ok(newApply);
    }
}

