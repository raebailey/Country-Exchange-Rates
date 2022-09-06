package tasks;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import cache.CacheManager;
import components.CountryCard;

public class SearchTask extends Thread implements Runnable {
	private String searchTerm;
	private CountryCard[] cards;
	private CountryCard[] cardArr = null;
	private JPanel panel;

	@Override
	public void run() {
		searchTerm = searchTerm.trim();
		if (!searchTerm.isEmpty()) {
			Object object = CacheManager.getCacheItem(searchTerm);
			if (object != null) {
				if (object instanceof CountryCard[]) {
					cardArr = (CountryCard[]) object;
				}
			} else {
				ArrayList<CountryCard> cardList = new ArrayList<CountryCard>();
				for (CountryCard card : cards) {
					if (card.getCountry().getName().contains(searchTerm)) {
						cardList.add(card);
					}
				}
				if(cardList.size()>0) {
					cardArr = new CountryCard[cardList.size()];
					CacheManager.addCache(searchTerm, cardList.toArray(cardArr));
				}else {
					panel.add(new JLabel("No results found. Please try again."));
				}
			}
		}
	}
	
	public CountryCard[] getSearchResults() {
		return cardArr;
	}

	public SearchTask(CountryCard[] cards,JPanel panel) {
		this.cards = cards;
		
		this.panel = panel;
	}
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	
	

}
