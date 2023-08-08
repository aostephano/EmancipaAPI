package dev.aostephano.emancipaapi.Apply.Repositories;

import dev.aostephano.emancipaapi.Apply.Models.Question.Question;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {

  List<Question> findByApplyUuid(UUID applyUuid);
}
