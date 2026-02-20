package fixando.br.fixando.dto;

import fixando.br.fixando.model.entity.FlashCard;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record FlashCardResponse(
        @NotNull(message = "The list of flashcards should not be null") List<FlashCard> flashCards
) {
}
