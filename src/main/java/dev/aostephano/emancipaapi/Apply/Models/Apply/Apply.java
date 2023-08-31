package dev.aostephano.emancipaapi.Apply.Models.Apply;

import dev.aostephano.emancipaapi.Apply.Models.Question.Question;
import dev.aostephano.emancipaapi.CramScrools.Models.Address.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Apply")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Apply {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private UUID uuid;
  private String name;
  private String email;
  private String phone;

  @OneToOne(cascade = CascadeType.ALL)
  private Address address;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "apply")
  private List<Question> questions = new ArrayList<>();

  private boolean active;

  public Apply(ApplyRequest applyRequest) {

    List<Question> questions = List.of(
        new Question("Question 1", this),
        new Question("Question 2", this),
        new Question("Question 3", this),
        new Question("Question 4", this),
        new Question("Question 5", this)
    );

    this.uuid = UUID.randomUUID();
    this.name = applyRequest.name();
    this.email = applyRequest.email();
    this.phone = applyRequest.phone();
    this.address = applyRequest.address();
    this.questions = questions;
    this.setActive(true);
  }
}