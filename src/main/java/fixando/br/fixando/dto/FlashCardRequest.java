package fixando.br.fixando.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FlashCardRequest(
        @NotBlank(message = "The input text should not be blank, please insert some text.") String text,
        @NotNull(message = "Please select the number of flashCards you want to generate") int flashNumber) {
}