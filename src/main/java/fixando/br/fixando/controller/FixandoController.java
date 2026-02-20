package fixando.br.fixando.controller;

import fixando.br.fixando.dto.FlashCardRequest;
import fixando.br.fixando.dto.FlashCardResponse;
import fixando.br.fixando.model.mapper.FlashCardMapper;
import fixando.br.fixando.model.service.FlashCardService;
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

    public FixandoController(FlashCardMapper flashCardMapper, FlashCardService flashCardService) {
        this.flashCardMapper = flashCardMapper;
        this.flashCardService = flashCardService;
    }

    @PostMapping("/generate")
    public ResponseEntity<FlashCardResponse> create(@RequestBody FlashCardRequest flashCardRequest) throws Exception {

        log.info("Trying to acces the env variable: " +  System.getenv("GOOGLE_API_KEY") );

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
