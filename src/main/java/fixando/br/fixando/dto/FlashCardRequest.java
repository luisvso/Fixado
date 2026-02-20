package fixando.br.fixando.dto;

import jakarta.validation.constraints.NotBlank;

public record FlashCardRequest(
        @NotBlank(message = "The input text should not be blank, please insert some text.") String text,
        int flashNumber) {
}