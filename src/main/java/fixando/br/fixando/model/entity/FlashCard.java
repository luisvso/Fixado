package fixando.br.fixando.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FlashCard {

    private Long id;
    private String front;
    private String back;

    public FlashCard(String front, String back){
        this.front = front;
        this.back = back;
    }

}