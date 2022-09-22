package components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

import components.CustomButton.ButtonStyle;
import components.image.ImageHelper;
import models.ApiNotification;
import ui.ApiUI;
import java.awt.Component;

public class Cards extends RoundPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3852771081179394052L;
	private ApiUI ui;
	private ArrayList<ApiNotification> notifs;
	private JLabel datelabl;
	private JLabel messagelbl;
	private JLabel titlelbl;
	private CustomLabel counterlbl;
	private JPanel subbarBody;
	private JPanel messageBody;
	private JPanel titleBody;
	private RoundPanel subbar;
	private CardsItem cardsItem;
	private boolean cardState = false;

	/**
	 * Create the panel.
	 */
	public Cards(ApiNotification notif) {
		notifs = new ArrayList<>();
		notifs.add(notif);
		init();
	}
	

	public JLabel getDatelabl() {
		return datelabl;
	}

	public void setDatelabl(JLabel datelabl) {
		this.datelabl = datelabl;
	}

	public JLabel getMessagelbl() {
		return messagelbl;
	}

	public void setMessagelbl(JLabel messagelbl) {
		this.messagelbl = messagelbl;
	}

	public JLabel getTitlelbl() {
		return titlelbl;
	}

	public void setTitlelbl(JLabel titlelbl) {
		this.titlelbl = titlelbl;
	}

	public CustomLabel getCounterlbl() {
		return counterlbl;
	}

	public void setCounterlbl(CustomLabel counterlbl) {
		this.counterlbl = counterlbl;
	}

	public ApiNotification getNotif() {
		return notifs.get(notifs.size() - 1);
	}

	public ArrayList<ApiNotification> getNotifs() {
		return notifs;
	}

	public void setNotifs(ArrayList<ApiNotification> notifs) {
		this.notifs = notifs;
	}

	private Cards self() {
		return this;
	}

	public ApiUI getUi() {
		return ui;
	}

	public void setUi(ApiUI ui) {
		this.ui = ui;
	}

	public RoundPanel getSubbar() {
		return subbar;
	}

	public void setSubbar(RoundPanel subbar) {
		this.subbar = subbar;
	}

	/**
	 * Initializes components.
	 */

	private void init() {
		ApiNotification notif = getNotif();
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setMaximumSize(new Dimension(328, 100));
		setPreferredSize(new Dimension(328, 100));
		setLayout(new BorderLayout(0, 0));
		setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 150, 255)));
		Color color = notif.getType().getBackground();
		if(color==null) {
			setBackground(new Color(15, 15, 15));
		}else {
			setBackground(color);
		}

		messageBody = new JPanel();
		messageBody.setOpaque(false);
		add(messageBody, BorderLayout.CENTER);

		messageBody.setLayout(new BoxLayout(messageBody, BoxLayout.Y_AXIS));
		cardsItem = new CardsItem(notif);
		cardsItem.setAlignmentX(Component.LEFT_ALIGNMENT);
		messageBody.add(cardsItem);

		titleBody = new JPanel();
		titleBody.setPreferredSize(new Dimension(10, 25));
		titleBody.setOpaque(false);
		add(titleBody, BorderLayout.NORTH);

		JPanel mainHeaderlbl = new JPanel();
		mainHeaderlbl.setLocation(0, 0);
		mainHeaderlbl.setSize(194, 25);
		mainHeaderlbl.setOpaque(false);
		FlowLayout fl_mainHeaderlbl = new FlowLayout(FlowLayout.LEFT, 5, 5);
		fl_mainHeaderlbl.setAlignOnBaseline(true);
		mainHeaderlbl.setLayout(fl_mainHeaderlbl);
		titleBody.add(mainHeaderlbl);

		titlelbl = new JLabel(notif.getType().getTitle());
		titlelbl.setForeground(new Color(255, 255, 255));
		titlelbl.setFont(new Font("SansSerif", Font.BOLD, 14));
		Image image = notif.getType().getImageObj();
		if (image != null) {
			titlelbl.setIcon(new ImageIcon(image));
		}

		titleBody.setLayout(null);
		mainHeaderlbl.add(titlelbl);

		datelabl = new JLabel(notif.getLastexec());
		datelabl.setFont(new Font("SansSerif", Font.PLAIN, 11));
		datelabl.setForeground(new Color(255, 255, 255));
		mainHeaderlbl.add(datelabl);

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel_3.setBounds(308, 6, 12, 12);
		titleBody.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		CustomButton closeButton = new CustomButton();
		closeButton.setIcon(new ImageIcon(ImageHelper.readImage("/images/close_1.png",12,12)));
		closeButton.setBounds(310, 0, 20, 20);

		closeButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		closeButton.setStyle(ButtonStyle.CLOSE);
		closeButton.setFocusable(false);
		closeButton.setRound(20);
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ui.getPanel_3().remove(self());
				ui.getItems().remove(self());
			}

		});
		panel_3.add(closeButton);

		counterlbl = new CustomLabel();
		counterlbl.setBounds(217, 0, 28, 25);
		titleBody.add(counterlbl);
		counterlbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
		counterlbl.setHorizontalTextPosition(SwingConstants.CENTER);
		counterlbl.setHorizontalAlignment(SwingConstants.CENTER);
		counterlbl.setPreferredSize(new Dimension(32, 25));
		counterlbl.setText(String.valueOf(notifs.size()));
		counterlbl.setVisible(false);

		if (notifs.size() > 1) {
			counterlbl.setVisible(true);
		}

		subbarBody = new JPanel();
		subbarBody.setOpaque(false);
		FlowLayout fl_subbarBody = (FlowLayout) subbarBody.getLayout();
		fl_subbarBody.setAlignOnBaseline(true);
		add(subbarBody, BorderLayout.SOUTH);
		subbar = new RoundPanel();
		subbar.setBackground(new Color(245, 245, 245, 30));
		subbar.setPreferredSize(new Dimension(100, 2));
		subbar.setSize(new Dimension(200, 0));
		subbar.setVisible(false);
		self().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent me) {
				subbar.setVisible(true);
				subbar.setBackground(new Color(245, 245, 245));
			}

			@Override
			public void mouseExited(MouseEvent me) {
				subbar.setVisible(false);
				subbar.setBackground(new Color(245, 245, 245, 30));
			}

			@Override
			public void mousePressed(MouseEvent me) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					if (cardState) {
						setMaximumSize(new Dimension(328, 100));
						setPreferredSize(new Dimension(328, 100));
						cardState = false;
					} else {
						setMaximumSize(new Dimension(328, 150));
						setPreferredSize(new Dimension(328, 150));
						cardState = true;
					}
					repaint();
					revalidate();
				}
			}

			@Override
			public void mouseReleased(MouseEvent me) {
			}
		});
		subbarBody.add(subbar);

	}

	/**
	 * Refreshes current instance of the card element to show latest changes.
	 */
	public void refresh() {
		ApiNotification notif = getNotif();
		getTitlelbl().setText(notif.getType().getTitle());
		getDatelabl().setText(notif.getLastexec());
		cardsItem.getMessagelbl().setText(notif.getMessage());
		if (notifs.size() > 1) {
			getCounterlbl().setVisible(true);
			if (notifs.size() > 99) {
				getCounterlbl().setText("99+");
			} else {
				getCounterlbl().setText(String.valueOf(notifs.size()));
			}
		}

	}

}
