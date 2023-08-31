package dev.aostephano.emancipaapi.Apply.Controllers;

import dev.aostephano.emancipaapi.Apply.Models.Apply.Apply;
import dev.aostephano.emancipaapi.Apply.Models.Apply.ApplyMapper;
import dev.aostephano.emancipaapi.Apply.Models.Apply.ApplyRequest;
import dev.aostephano.emancipaapi.Apply.Models.Apply.ApplyResponse;
import dev.aostephano.emancipaapi.Apply.Models.Question.Question;
import dev.aostephano.emancipaapi.Apply.Services.ApplyService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map; 

@RestController
@RequestMapping("api/cursinho/apply")
public class ApplyController {

  //Dependency Injection
  private final ApplyService applyService;

  public ApplyController(ApplyService applyService) {
    this.applyService = applyService;
  }

  //GET: api/cursinho/apply - Get all applys
  @GetMapping("")
  public ResponseEntity<List<ApplyResponse>> getAllApplys() {
    // Controller Responsibility
    var allApplys = applyService.getAllApplys();

    // Response Treatment
    var allApplysResponse = allApplys.stream().map(ApplyMapper::fromApplyToResponse).toList();

    return ResponseEntity.ok(allApplysResponse);
  }

  //POST: api/cursinho/apply - Create a new apply
  @Transactional
  @PostMapping("")
  public ResponseEntity<ApplyResponse> createApply(@RequestBody ApplyRequest applyRequest) {
    // Controller Responsibility
    var apply = new Apply(applyRequest);
    applyService.saveApply(apply);

    // Response Treatment
    var applyResponse = ApplyMapper.fromApplyToResponse(apply);

    return ResponseEntity.ok(applyResponse);
  }

  //PUT: api/cursinho/apply/ - Update an apply
  @Transactional
  @PutMapping("")
  public ResponseEntity<ApplyResponse> updateApplyByUuid(@RequestBody ApplyRequest applyRequest) {
    // Response Treatment
    var updatedApply = applyService.updateApplyByUuid(applyRequest);
    var applyResponse = ApplyMapper.fromApplyToResponse(
        Objects.requireNonNull(updatedApply.getBody()));

    return ResponseEntity.ok(applyResponse);
  }

//   DELETE: api/cursinho/apply/ - Deactive an apply by Uuid
  @Transactional
  @DeleteMapping("")
  public ResponseEntity<ApplyResponse> deleteApplyByUuid(@RequestBody ApplyRequest applyRequest){
    return applyService.deleteApplyByUuid(applyRequest);
  }

  @GetMapping("/test")
  public ResponseEntity<List<Question>> getYourStringBack(
      @RequestBody Map<String, String> requestBody) {

    String yourString = requestBody.get("uuid");

    // Handle missing UUID in the request
    if (yourString == null) {
      return ResponseEntity.badRequest().build();
    }

    UUID applyUuid = UUID.fromString(yourString);
    var questionsByUuid = applyService.getQuestionsByApplyUuid(applyUuid);
    System.out.println(questionsByUuid);

    return ResponseEntity.ok(questionsByUuid);
  }
}
