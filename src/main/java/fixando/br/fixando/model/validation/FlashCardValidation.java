package fixando.br.fixando.model.validation;

import fixando.br.fixando.dto.FlashCardRequest;
import fixando.br.fixando.model.exception.NumberInvalidException;
import org.springframework.stereotype.Component;

@Component
public class FlashCardValidation {


    public void valid(FlashCardRequest dto){
        numberInputCheck(dto);
    }

    public void numberInputCheck(FlashCardRequest dto){
        if(dto.flashNumber() != 2 && dto.flashNumber() != 5)
            throw new NumberInvalidException("The number chose by the user is invalid please, choose a number between 2 or 5");
    }

}
