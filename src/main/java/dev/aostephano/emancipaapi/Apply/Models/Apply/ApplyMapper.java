package dev.aostephano.emancipaapi.Apply.Models.Apply;

import dev.aostephano.emancipaapi.Apply.Models.Question.QuestionResponse;
import dev.aostephano.emancipaapi.CramScrools.Models.Address.AddressMapper;
import dev.aostephano.emancipaapi.CramScrools.Models.Address.AddressResponse;
import java.util.List;

public class ApplyMapper {

  public static ApplyResponse fromApplyToResponse(Apply apply) {
    AddressResponse addressResponse = AddressMapper.fromAddressToResponse(apply.getAddress());

    List<QuestionResponse> questionResponses = apply.getQuestions().stream()
        .map(question -> new QuestionResponse(question.getQuestionText(), question.getAnswer()))
        .toList();

    return new ApplyResponse(
        apply.getUuid(),
        apply.getName(),
        apply.getEmail(),
        apply.getPhone(),
        addressResponse,
        questionResponses
    );

  }
}
