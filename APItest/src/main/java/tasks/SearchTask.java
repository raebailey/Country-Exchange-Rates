package tasks;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cache.CacheManager;
import components.CountryCard;
import components.CustomButton;
import components.CustomButton.ButtonStyle;
import components.notification.Notification;

public class SearchTask extends Thread implements Runnable {
	private String searchTerm;
	private CountryCard[] cards;
	private CountryCard[] cardArr = null;
	private JPanel panel;
	private JFrame frame;

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
//				panel.removeAll();
				for (CountryCard card : cards) {
					if (card.getCountry().getName().contains(searchTerm)) {
						cardList.add(card);
						System.out.println(card.getCountry().getName()+"index: "+cardList.indexOf(card));
						panel.add(card,0);
					}
				}
				
				if(cardList.size()>0) {
					cardArr = new CountryCard[cardList.size()];
					CacheManager.addCache(searchTerm, cardList.toArray(cardArr));
					
				}else {
					new Notification(frame,Notification.Type.WARNING,Notification.Location.BOTTOM_RIGHT,"No results found. Please try again.",null).showNotification();
				}
				panel.revalidate();
			}
			
		}
	}
	
	public CountryCard[] getSearchResults() {
		return cardArr;
	}

	public SearchTask(CountryCard[] cards,JPanel panel,JFrame frame) {
		this.cards = cards;
		this.panel = panel;
		this.frame = frame;
	}
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	
	

}
