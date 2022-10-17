package components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

import com.vdurmont.emoji.EmojiParser;

import components.CustomButton.ButtonStyle;
import components.apinotification.buttons.RejectButtonBar;
import components.image.ImageHelper;
import enums.MessageTypes;
import events.AddCardsItemEvent;
import events.AddCardsItemListener;
import events.CardsUpdateTimeEvent;
import events.CardsUpdateTimeListener;
import models.apinotifications.ApiNotification;
import sample.APItest;
import ui.ApiUI;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

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

	private ArrayList<CardsUpdateTimeListener> CardsUpdateTimelisteners = new ArrayList<CardsUpdateTimeListener>();
	private ArrayList<AddCardsItemListener> AddCardsItemlisteners = new ArrayList<AddCardsItemListener>();
	private RoundPanel panel;
	private JPanel panelx;

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
		if (color == null) {
			setBackground(new Color(15, 15, 15));
		} else {
			setBackground(color);
		}

		messageBody = new JPanel();
		messageBody.setLayout(new BoxLayout(messageBody, BoxLayout.Y_AXIS));
		messageBody.setPreferredSize(new Dimension(328, 62));
		messageBody.setOpaque(false);
		
		cardsItem = new CardsItem(notif);
		cardsItem.setAlignmentX(Component.LEFT_ALIGNMENT);
		messageBody.add(cardsItem);
		
		panelx = new JPanel();
		panelx.setOpaque(false);
		panelx.setMaximumSize(new Dimension(328, 32767));
		panelx.setMinimumSize(new Dimension(328, 10));
		FlowLayout fl_panelx = new FlowLayout(FlowLayout.LEFT, 0, 0);
		fl_panelx.setAlignOnBaseline(true);
		panelx.setLayout(fl_panelx);
		panelx.add(messageBody);
		add(panelx, BorderLayout.CENTER);

		
		
		
		titleBody = new JPanel();
		titleBody.setPreferredSize(new Dimension(10, 25));
		titleBody.setOpaque(false);
		add(titleBody, BorderLayout.NORTH);

		JPanel mainHeaderlbl = new JPanel();
		mainHeaderlbl.setOpaque(false);
		FlowLayout fl_mainHeaderlbl = new FlowLayout(FlowLayout.LEFT, 5, 5);
		fl_mainHeaderlbl.setAlignOnBaseline(true);
		mainHeaderlbl.setLayout(fl_mainHeaderlbl);

		titlelbl = new JLabel(notif.getType().getTitle());
		titlelbl.setForeground(new Color(255, 255, 255));
		titlelbl.setFont(new Font("SansSerif", Font.BOLD, 14));
		Image image = notif.getType().getImageObj();
		if (image != null) {
			titlelbl.setIcon(new ImageIcon(image));
		}
		mainHeaderlbl.add(titlelbl);

		datelabl = new JLabel(APItest.dateAndtime(notif.getLastexec())[1]);
		datelabl.setFont(new Font("SansSerif", Font.PLAIN, 11));
		datelabl.setForeground(new Color(255, 255, 255));
		mainHeaderlbl.add(datelabl);

		JPanel panel_3 = new JPanel();
		panel_3.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_3.setOpaque(false);
		panel_3.setLayout(new BorderLayout(0, 0));

		CustomButton closeButton = new CustomButton();
		closeButton.setIcon(new ImageIcon(ImageHelper.readImage("/images/close_1.png", 12, 12)));
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

		panel = new RoundPanel();
		panel.setBackground(new Color(0, 150, 255));
		panel.setRound(100);
		panel.setLayout(new BorderLayout(0, 0));

		counterlbl = new CustomLabel();
		counterlbl.setForeground(new Color(255, 255, 255));
		panel.add(counterlbl);
		counterlbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
		counterlbl.setHorizontalTextPosition(SwingConstants.CENTER);
		counterlbl.setHorizontalAlignment(SwingConstants.CENTER);
		counterlbl.setText(String.valueOf(notifs.size()));
		GroupLayout gl_titleBody = new GroupLayout(titleBody);
		gl_titleBody.setHorizontalGroup(gl_titleBody.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_titleBody.createSequentialGroup()
						.addComponent(mainHeaderlbl, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
						.addGap(23).addComponent(panel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE).addGap(8)));
		gl_titleBody.setVerticalGroup(gl_titleBody.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_titleBody.createSequentialGroup()
						.addGroup(gl_titleBody.createParallelGroup(Alignment.LEADING)
								.addComponent(mainHeaderlbl, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_titleBody.createSequentialGroup().addGap(6).addComponent(panel_3,
										GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_titleBody.createSequentialGroup().addGap(2).addComponent(panel,
										GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		titleBody.setLayout(gl_titleBody);
		counterlbl.setVisible(false);
		panel.setVisible(false);

		if (notifs.size() > 1) {
			counterlbl.setVisible(true);
			panel.setVisible(true);
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
						//panelx.setVisible(false);
						cardState = false;
					} else {
						setMaximumSize(new Dimension(328, 150));
						setPreferredSize(new Dimension(328, 150));
						//panelx.setVisible(true);
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
		this.addCardsUpdateTimeListener(new CardsUpdateTimeListener() {
			@Override
			public void update(CardsUpdateTimeEvent event) {
				self().getDatelabl().setText(event.getTimeMessage());
			}

		});
		this.addCardsItemListener(new AddCardsItemListener() {
			@Override
			public void add(AddCardsItemEvent event) {
				self().refresh(event.getNotification());
			}
		});
		addButtonBar(notif);

	}

	/**
	 * Refreshes current instance of the card element to show latest changes.
	 */
	private void refresh(ApiNotification notification) {
		String split[] = APItest.dateAndtime(notification.getLastexec());
		getTitlelbl().setText(notification.getType().getTitle());
		getDatelabl().setText(split[1]);
		cardsItem.getMessagelbl().setText(notification.getMessage());
		if (notifs.size() > 1) {
			getCounterlbl().setVisible(true);
			panel.setVisible(true);
//			if (notifs.size() > 99) {
//				getCounterlbl().setText("99+");
//			} else {
			getCounterlbl().setText(String.valueOf(notifs.size()));
			getCounterlbl().revalidate();
			panel.revalidate();
//			}
		}
	}

	private void addButtonBar(ApiNotification notif) {
		switch (notif.getType()) {
		case REJECT:
			panelx.add(new RejectButtonBar(notif));
			break;
		case NEWCOUNTRY:
			panelx.add(new RejectButtonBar(notif));
			break;
		case NOUPDATE:
			//btnBar.add(new RejectButtonBar());
			break;
		case ERROR:
			//btnBar.add(new RejectButtonBar());
			break;

		default:
			break;
		}

	}

	public void addItem(ApiNotification notification) {
		getNotifs().add(notification);
		processAddCardsItemEvent(new AddCardsItemEvent(this, notification));
	}

	private void processAddCardsItemEvent(AddCardsItemEvent event) {
		ArrayList<AddCardsItemListener> tempList;

		synchronized (this) {
			if (AddCardsItemlisteners.size() == 0)
				return;
			tempList = (ArrayList<AddCardsItemListener>) AddCardsItemlisteners.clone();
		}

		for (AddCardsItemListener listener : tempList) {
			listener.add(event);
		}
	}

	public synchronized void addCardsUpdateTimeListener(CardsUpdateTimeListener listener) {
		CardsUpdateTimelisteners.add(listener);
	}

	public synchronized void removeCardsUpdateTimeListener(CardsUpdateTimeListener listener) {
		CardsUpdateTimelisteners.remove(listener);
	}

	public synchronized void addCardsItemListener(AddCardsItemListener listener) {
		AddCardsItemlisteners.add(listener);
	}

	public synchronized void removeCardsItemListener(AddCardsItemListener listener) {
		AddCardsItemlisteners.remove(listener);
	}

	public void updateTime() {
		try {
			String split[] = APItest.dateAndtime(this.getNotif().getLastexec());
			String datetimenow = APItest.localTime();
			String split2[] = APItest.dateAndtime(datetimenow);

			Date exec = new SimpleDateFormat("dd/MM/yyyy hh:mm a").parse(this.getNotif().getLastexec());
			Date now = new SimpleDateFormat("dd/MM/yyyy hh:mm a").parse(datetimenow);
			long difference = now.getTime() - exec.getTime();
			long minutes = (difference / (1000 * 60)) % 60;
			long hour = (difference / (1000 * 60 * 60)) % 24;
			System.out.println(split[0] + " " + split2[0]);
			String message = " ";
			if (split[0].equals(split2[0])) {
				if (hour >= 1) {
					// long hours = minutes%60;
					message = hour == 1 ? String.format("%d hour ago", hour) : String.format("%d hours ago", hour);
				} else if (minutes > 0) {
					message = minutes == 1 ? String.format("%d minute ago", minutes)
							: String.format("%d minutes ago", minutes);
				} else {
					message = split[1];
				}
			} else {
				message = split[0];
			}

			processCardsUpdateTimeEvent(new CardsUpdateTimeEvent(this, message));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}

	private void processCardsUpdateTimeEvent(CardsUpdateTimeEvent event) {
		ArrayList<CardsUpdateTimeListener> tempList;

		synchronized (this) {
			if (CardsUpdateTimelisteners.size() == 0)
				return;
			tempList = (ArrayList<CardsUpdateTimeListener>) CardsUpdateTimelisteners.clone();
		}

		for (CardsUpdateTimeListener listener : tempList) {
			listener.update(event);
		}
	}

}
