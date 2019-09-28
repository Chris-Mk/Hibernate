package app.ccb.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cards")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardListImportDto {

    @XmlElement(name = "card")
    private List<CardImportDto> cards;

    public CardListImportDto() {
    }

    public List<CardImportDto> getCards() {
        return cards;
    }

    public void setCards(List<CardImportDto> cards) {
        this.cards = cards;
    }
}
