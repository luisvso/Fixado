package fixando.br.fixando.dto;

public record FlashCardRequest(
        long id,
        String text,
        int flashNumber) {
}