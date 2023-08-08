package dev.aostephano.emancipaapi.Apply.Models.Apply;

import dev.aostephano.emancipaapi.Apply.Models.Question.Question;
import dev.aostephano.emancipaapi.CramScrools.Models.Address.Address;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public record ApplyRequest(
    UUID uuid,
    @NotNull
    String name,
    @NotNull
    String email,
    @NotNull
    String phone,
    Address address,
    List<Question> questions
) {

}
