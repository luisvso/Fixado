package fixando.br.fixando.controller;

import fixando.br.fixando.dto.FlashCardRequest;
import fixando.br.fixando.dto.FlashCardResponse;
import fixando.br.fixando.model.mapper.FlashCardMapper;
import fixando.br.fixando.model.service.FlashCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<FlashCardResponse> create(@RequestBody FlashCardRequest flashCardRequest) {

        log.info("Trying to acces the env variable: " +  System.getenv("GOOGLE_API_KEY") );

        String responsePrompt = flashCardService.generateFlashCards(flashCardRequest);

        return ResponseEntity.ok(new FlashCardResponse(flashCardMapper.resultToFlashcard()));

    }

}
