package dev.aostephano.emancipaapi.Apply.Services;

import dev.aostephano.emancipaapi.Apply.Models.Apply.Apply;
import dev.aostephano.emancipaapi.Apply.Models.Apply.ApplyRequest;
import dev.aostephano.emancipaapi.Apply.Models.Question.Question;
import dev.aostephano.emancipaapi.Apply.Repositories.ApplyRepository;
import dev.aostephano.emancipaapi.Apply.Repositories.QuestionRepository;
import dev.aostephano.emancipaapi.CramScrools.Models.Address.Address;
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

  public ResponseEntity<Apply> updateApplyByUuid(ApplyRequest data) {
    Optional<Apply> optionalApply = Optional.ofNullable(applyRepository.findByUuid(data.uuid()));

    if (optionalApply.isPresent()) {
      // Get the Apply from the optional
      Apply apply = optionalApply.get();

      //Update the Apply Object ref and the database
      apply.setName(data.name());
      apply.setEmail(data.email());
      apply.setPhone(data.phone());

      // Get the existing Questions object from the fetched Apply
      List<Question> questions = apply.getQuestions();
      List<Question> questionDataList = data.questions();

      for (int i = 0; i < questions.size() && i < questionDataList.size(); i++) {
        questions.get(i).setAnswer(questionDataList.get(i).getAnswer());
      }

      // Get the existing Address object from the fetched Apply
      Address existingAddress = apply.getAddress();

      // Update the existing Address object with the new data
      existingAddress.setAddress(data.address().getAddress());
      existingAddress.setCity(data.address().getCity());
      existingAddress.setState(data.address().getState());
      existingAddress.setPostalCode(data.address().getPostalCode());

      return ResponseEntity.ok(apply);
    } else {
      throw new EntityNotFoundException();
    }
  }
}