package fixando.br.fixando.controller;

import fixando.br.fixando.dto.FlashCardRequest;
import fixando.br.fixando.dto.FlashCardResponse;
import fixando.br.fixando.model.mapper.FlashCardMapper;
import fixando.br.fixando.model.service.FlashCardService;
import fixando.br.fixando.model.validation.FlashCardValidation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@RestController
@RequestMapping("api/v1/fixando")
public class FixandoController {

    private final FlashCardMapper flashCardMapper;
    private final FlashCardService flashCardService;
    private final FlashCardValidation flashCardValidation;

    public FixandoController(FlashCardMapper flashCardMapper, FlashCardService flashCardService, FlashCardValidation flashCardValidation) {
        this.flashCardMapper = flashCardMapper;
        this.flashCardService = flashCardService;
        this.flashCardValidation = flashCardValidation;
    }

    @PostMapping("/generate")
    public ResponseEntity<FlashCardResponse> create(@RequestBody @Valid FlashCardRequest flashCardRequest) throws Exception {

       flashCardValidation.valid(flashCardRequest);

        String responsePrompt = flashCardService.generateFlashCards(flashCardRequest);

        // creating an object mapper to map the JSON to the Record
        ObjectMapper objectMapper = new ObjectMapper();

        // replacing the begining of the prompt and the end, so the JSON can interpretate properly
        String json = responsePrompt.replaceAll("```json", "").replaceAll("```", "").trim();

        // mapping the JSON into a flashCardResponse
        FlashCardResponse result = objectMapper.readValue(json, FlashCardResponse.class);

        log.info("Tentando retornar : " + result.toString());

        return ResponseEntity.ok(result);
    }

}
