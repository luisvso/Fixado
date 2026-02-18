package fixando.br.fixando.controller;

import fixando.br.fixando.dto.FlashCardResponse;
import fixando.br.fixando.model.mapper.FlashCardMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fixando")
public class FixandoController {

    private final FlashCardMapper flashCardMapper;

    public FixandoController(FlashCardMapper flashCardMapper) {
        this.flashCardMapper = flashCardMapper;
    }

    @PostMapping("/generate")
    public ResponseEntity<FlashCardResponse> create() {

        return ResponseEntity.ok(new FlashCardResponse(flashCardMapper.resultToFlashcard()));

    }

    // @PostMapping("/downloads")
    // public ResponseEntity<> download(){
    // }

}
