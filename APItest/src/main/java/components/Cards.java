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

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;


import components.CustomButton.ButtonStyle;
import models.ApiNotification;
import ui.ApiUI;

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
	private JPanel panel_2;
	private JPanel panel_4;
	private RoundPanel subbar;
	private JLabel messagelbl1;
	private JLabel messagelbl2;
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
		setBackground(new Color(15, 15, 15));

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		add(panel, BorderLayout.CENTER);

		messagelbl = new JLabel(notif.getMessage());

		messagelbl1 = new JLabel("New label");
		messagelbl1.setForeground(new Color(255, 255, 255));

		messagelbl2 = new JLabel("New label");
		messagelbl2.setForeground(new Color(255, 255, 255));
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(messagelbl);
		panel.add(messagelbl1);
		panel.add(messagelbl2);

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(10, 25));
		panel_1.setOpaque(false);
		add(panel_1, BorderLayout.NORTH);

		JPanel mainHeaderlbl = new JPanel();
		mainHeaderlbl.setLocation(0, 0);
		mainHeaderlbl.setSize(194, 25);
		mainHeaderlbl.setOpaque(false);
		FlowLayout fl_mainHeaderlbl = new FlowLayout(FlowLayout.LEFT, 5, 5);
		fl_mainHeaderlbl.setAlignOnBaseline(true);
		mainHeaderlbl.setLayout(fl_mainHeaderlbl);
		panel_1.add(mainHeaderlbl);

		titlelbl = new JLabel(notif.getType().getTitle());
		titlelbl.setForeground(new Color(255, 255, 255));
		titlelbl.setFont(new Font("SansSerif", Font.BOLD, 14));
		Image image = notif.getType().getPath();
		if (image != null) {
			titlelbl.setIcon(new ImageIcon(image));
		}

		panel_1.setLayout(null);
		mainHeaderlbl.add(titlelbl);

		datelabl = new JLabel(notif.getLastexec());
		datelabl.setFont(new Font("SansSerif", Font.PLAIN, 11));
		datelabl.setForeground(new Color(255, 255, 255));
		mainHeaderlbl.add(datelabl);

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel_3.setBounds(308, 6, 12, 12);
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		CustomButton closeButton = new CustomButton();
		closeButton.setIcon(new ImageIcon(Cards.class.getResource("/images/close_1.png")));
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

		panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setPreferredSize(new Dimension(35, 10));
		add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(null);

		counterlbl = new CustomLabel();
		counterlbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
		counterlbl.setHorizontalTextPosition(SwingConstants.CENTER);
		counterlbl.setHorizontalAlignment(SwingConstants.CENTER);
		counterlbl.setBounds(5, 25, 28, 28);
		counterlbl.setPreferredSize(new Dimension(32, 32));
		counterlbl.setRound(100);
		counterlbl.setText(String.valueOf(notifs.size()));
		counterlbl.setVisible(false);

		if (notifs.size() > 1) {
			counterlbl.setVisible(true);
		}
		panel_2.add(counterlbl);

		panel_4 = new JPanel();
		panel_4.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignOnBaseline(true);
		add(panel_4, BorderLayout.SOUTH);
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
		panel_4.add(subbar);

	}

	/**
	 * Refreshes current instance of the card element to show latest changes.
	 */
	public void refresh() {
		ApiNotification notif = getNotif();
		Image image = notif.getType().getPath();
		if (image != null) {
			titlelbl.setIcon(new ImageIcon(image));
		}
		getTitlelbl().setText(notif.getType().getTitle());
		getDatelabl().setText(notif.getLastexec());
		getMessagelbl().setText(notif.getMessage());
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
