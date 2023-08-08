package dev.aostephano.emancipaapi.Apply.Models.Question;

public class QuestionMapper {

  public static QuestionResponse fromQuestionToResponse(Question question) {
    return new QuestionResponse(
        question.getQuestionText(),
        question.getAnswer()
    );
  }

  public static Question fromRequestToQuestion(QuestionRequest questionRequest) {
    return new Question(
        questionRequest.questionText(),
        questionRequest.answer()
    );
  }

}
