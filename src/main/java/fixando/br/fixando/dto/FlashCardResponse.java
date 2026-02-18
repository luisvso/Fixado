package fixando.br.fixando.dto;

import fixando.br.fixando.model.FlashCard;

import java.util.List;
import java.util.Map;

public record FlashCardResponse(
        List<FlashCard> flashCards
) {
}
