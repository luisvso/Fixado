package fixando.br.fixando.dto;

import fixando.br.fixando.model.entity.FlashCard;

import java.util.List;

public record FlashCardResponse(
        List<FlashCard> flashCards
) {
}
