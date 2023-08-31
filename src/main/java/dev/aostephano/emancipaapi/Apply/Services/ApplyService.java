package dev.aostephano.emancipaapi.Apply.Services;

import dev.aostephano.emancipaapi.Apply.Models.Apply.Apply;
import dev.aostephano.emancipaapi.Apply.Models.Apply.ApplyRequest;
import dev.aostephano.emancipaapi.Apply.Models.Apply.ApplyResponse;
import dev.aostephano.emancipaapi.Apply.Models.Question.Question;
import dev.aostephano.emancipaapi.Apply.Repositories.ApplyRepository;
import dev.aostephano.emancipaapi.Apply.Repositories.QuestionRepository;
import dev.aostephano.emancipaapi.CramScrools.Models.Address.Address;
import dev.aostephano.emancipaapi.CramScrools.Models.CramSchool.CramSchool;
import dev.aostephano.emancipaapi.CramScrools.Models.CramSchool.CramSchoolRequest;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

  private final ApplyRepository applyRepository;
  private final QuestionRepository questionRepository;

  public ApplyService(ApplyRepository applyRepository, QuestionRepository questionRepository) {
    this.applyRepository = applyRepository;
    this.questionRepository = questionRepository;
  }


  public List<Apply> getAllApplys() {
    return applyRepository.findAll();
  }

  public List<Question> getQuestionsByApplyUuid(UUID applyUuid) {
    return questionRepository.findByApplyUuid(applyUuid);
  }

  public void saveApply(Apply newApply) {
    applyRepository.save(newApply);
  }

  public ResponseEntity<Apply> updateApplyByUuid(ApplyRequest applyRequest) {
    Optional<Apply> optionalApply = Optional.ofNullable(applyRepository.findByUuid(applyRequest.uuid()));

    if (optionalApply.isPresent()) {
      // Get the Apply from the optional
      Apply apply = optionalApply.get();

      //Update the Apply Object ref and the database
      apply.setName(applyRequest.name());
      apply.setEmail(applyRequest.email());
      apply.setPhone(applyRequest.phone());

      // Get the existing Questions object from the fetched Apply
      List<Question> questions = apply.getQuestions();
      List<Question> questionDataList = applyRequest.questions();

      for (int i = 0; i < questions.size() && i < questionDataList.size(); i++) {
        questions.get(i).setAnswer(questionDataList.get(i).getAnswer());
      }

      // Get the existing Address object from the fetched Apply
      Address existingAddress = apply.getAddress();

      // Update the existing Address object with the new data
      existingAddress.setAddress(applyRequest.address().getAddress());
      existingAddress.setCity(applyRequest.address().getCity());
      existingAddress.setState(applyRequest.address().getState());
      existingAddress.setPostalCode(applyRequest.address().getPostalCode());

      return ResponseEntity.ok(apply);
    } else {
      throw new EntityNotFoundException();
    }
  }

  public ResponseEntity<ApplyResponse> deleteApplyByUuid(ApplyRequest applyRequest) {
    Optional<Apply> optionalApply = Optional.ofNullable(applyRepository.findByUuid(applyRequest.uuid()));

    if (optionalApply.isPresent()) {
      Apply apply = optionalApply.get();
      apply.setActive(false);
      return ResponseEntity.noContent().build();
    } else {
      throw new EntityNotFoundException();
    }
  }

}