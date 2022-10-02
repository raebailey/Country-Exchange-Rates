package events;

import java.util.EventObject;

import components.Cards;

public class CardsUpdateTimeEvent extends EventObject {
	private Cards card;
	private String timeMessage;
	
	public CardsUpdateTimeEvent(Cards source,String timeMessage) {
		super(source);
		card = source;
		this.timeMessage = timeMessage;
	}

	public Cards getCard() {
		return card;
	}

	public void setCard(Cards card) {
		this.card = card;
	}

	public String getTimeMessage() {
		return timeMessage;
	}

	public void setTimeMessage(String timeMessage) {
		this.timeMessage = timeMessage;
	}
	
	
	

}
