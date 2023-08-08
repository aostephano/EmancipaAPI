package dev.aostephano.emancipaapi.Apply.Models.Question;

import dev.aostephano.emancipaapi.Apply.Models.Apply.Apply;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Question")
@Getter
@Setter
@NoArgsConstructor
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String questionText;
  private String answer;

  @ManyToOne
  @JoinColumn(name = "apply_id")
  private Apply apply;

  public Question(String questionText, Apply apply) {
    this.questionText = questionText;
    this.apply = apply;
  }

  public Question(String questionText, String answer) {
    this.questionText = questionText;
    this.answer = answer;
  }
}
