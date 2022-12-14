package components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import cache.CacheManager;
import components.CustomButton.ButtonStyle;
import components.icon.ActionInterface;
import components.icon.CustomIcon;
import components.notification.Notification;
import models.Country;
import models.Page;
import ui.CustomWindow;

public class CountryCard extends RoundPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8963606886328562954L;
	private Country country;
	private CustomButton closeButton;
	private JLabel imagelbl;
	private JLabel namelbl;
	private CustomButton gotoBtn;
	private CustomIcon icon;
	private CustomWindow frame;

	public CountryCard(Country country, CustomWindow frame) {
		initialize();
		this.frame = frame;
		setSize(new Dimension(350, 200));
		setPreferredSize(new Dimension(350, 200));
		setMaximumSize(new Dimension(350, 200));
		setMinimumSize(new Dimension(350, 200));
		this.country = country;
		icon.setShow(country.isVisible());
		gotoBtn.setVisible(country.isVisible());
		namelbl.setText(country.getName());

	}

	private void initialize() {
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 10, 330, 111);
		add(layeredPane);
		closeButton = new CustomButton();
		layeredPane.setLayer(closeButton, 1);
		closeButton.setBounds(310, 0, 20, 20);

		closeButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		closeButton.setText("X");
		closeButton.setStyle(ButtonStyle.CLOSE);
		closeButton.setFocusable(false);
		closeButton.setRound(20);
		closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Notification(frame, Notification.Type.INFO, Notification.Location.BOTTOM_RIGHT,
						"pressed " + country.getName(), (Image) CacheManager.getCacheItem(country.getImageUrl()))
						.showNotification();
			}

		});
		layeredPane.add(closeButton);
		imagelbl = new JLabel();
		imagelbl.setHorizontalTextPosition(SwingConstants.CENTER);
		imagelbl.setHorizontalAlignment(SwingConstants.CENTER);
		layeredPane.setLayer(imagelbl, 0);
		imagelbl.setSize(330, 111);
		imagelbl.setIcon(new ImageIcon(getClass().getResource("/images/loading3 new.gif")));

		layeredPane.add(imagelbl);

		namelbl = new JLabel();
		namelbl.setForeground(Color.WHITE);
		namelbl.setSize(200, 30);
		namelbl.setLocation(10, 132);
		add(namelbl);

		gotoBtn = new CustomButton();
		gotoBtn.setForeground(Color.WHITE);
		gotoBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
		gotoBtn.setRound(8);
		gotoBtn.setText("Go to");
		gotoBtn.setSize(62, 25);
		gotoBtn.setLocation(269, 158);
		gotoBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent event) {
				if (SwingUtilities.isLeftMouseButton(event)) {
					new Thread(new Runnable() {

						@Override
						public void run() {
							Page.getInstance().getPagefactory().getDetail(country);
						}

					}).start();
				}
			}
		});
		add(gotoBtn);

		icon = new CustomIcon();
		icon.setLocation(10, 158);
		icon.setSize(25, 25);
		icon.addActionInterface(new ActionInterface() {

			@Override
			public void action() {
				if (!icon.isShow()) {
					country.setVisible(false);
				} else {
					country.setVisible(true);
				}
				country.update(country);
				gotoBtn.setVisible(icon.isShow());
			}

		});
		add(icon);

		setLayout(null);

	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public CustomButton getCloseButton() {
		return closeButton;
	}

	public void setCloseButton(CustomButton closeButton) {
		this.closeButton = closeButton;
	}

	public JLabel getImagelbl() {
		return imagelbl;
	}

	public void setImagelbl(JLabel imagelbl) {
		this.imagelbl = imagelbl;
	}

	public JLabel getNamelbl() {
		return namelbl;
	}

	public void setName(JLabel name) {
		this.namelbl = name;
	}
}
