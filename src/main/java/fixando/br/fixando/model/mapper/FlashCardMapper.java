package fixando.br.fixando.model.mapper;

import fixando.br.fixando.dto.FlashCardRequest;
import fixando.br.fixando.model.FlashCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class FlashCardMapper {

    // Prompt Simulator
    private List<Map<String, String>> flashlists = new ArrayList<>();
    private Map<String, String> card1 = new HashMap<>();
    private Map<String, String> card2 = new HashMap<>();

    private List<FlashCard> flashCards = new ArrayList<>();

    public List<FlashCard> resultToFlashcard(){

        card1.put("front", "O que é fotossíntese?");
        card1.put("back", "Processo que as plantas usam para produzir energia a partir da luz.");

        card2.put("front", "Qual é a capital da França?");
        card2.put("back", "Paris");

        flashlists.add(card1);
        flashlists.add(card2);


        for (Map<String, String> i : flashlists){
            flashCards.add( new FlashCard(i.get("front"), i.get("back")));
            log.info("Trying to add the following card: " + i.get("front") + i.get("back"));
        }

        log.info("Trying to return the list " + flashCards.get(0).getFront() + " and " + flashCards.get(0).getBack() + " of ID: " + flashCards.get(0).getId());

        return flashCards;
    }


}
