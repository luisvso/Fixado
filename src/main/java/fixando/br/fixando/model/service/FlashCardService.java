package fixando.br.fixando.model.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import fixando.br.fixando.dto.FlashCardRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FlashCardService {

    public String generateFlashCards(FlashCardRequest dto){

        String prompt = """
                A partir do texto abaixo, gere %d flashcards para estudo.
                Retorne APENAS um JSON válido, sem explicações, no seguinte formato:
                {
                  "flashcards": [
                    { "front": "pergunta aqui", "back": "resposta aqui" }
                  ]
                } 
                
                Texto:
                %s
                """.formatted(dto.flashNumber(), dto.text());

        Client client = new Client();

        GenerateContentResponse  response = client.models.generateContent("gemini-2.5-flash", prompt, null);

        log.info("The result of the prompt is: " + response.text());

        return response.text();

    }

}
