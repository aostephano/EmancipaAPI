package dev.aostephano.emancipaapi.Apply.Models.Apply;

import dev.aostephano.emancipaapi.Apply.Models.Question.QuestionResponse;
import dev.aostephano.emancipaapi.CramScrools.Models.Address.AddressResponse;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public record ApplyResponse(
    UUID uuid,
    @NotNull
    String name,
    @NotNull
    String email,
    @NotNull
    String phone,
    AddressResponse address,
    @NotNull
    List<QuestionResponse> questions,
    boolean active
) {

}
