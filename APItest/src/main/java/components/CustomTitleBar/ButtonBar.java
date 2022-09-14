package components.CustomTitleBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javaswingdev.GoogleMaterialIcon;

public class ButtonBar extends JPanel {

	private TitleButton cmdClose;
	private TitleButton cmdMinimize;
	private GoogleMaterialIcon iconClose;
	private GoogleMaterialIcon iconMax;
	private GoogleMaterialIcon iconMinimize;
	private GoogleMaterialIcon iconRestore;

	public ButtonBar() {
		initComponents();
		setOpaque(false);
		cmdClose.setIcon(iconClose.toIcon());
		cmdMinimize.setIcon(iconMinimize.toIcon());
		cmdMinimize.setFont(cmdMinimize.getFont().deriveFont(0, 3));
	}

	public void initEvent(JFrame fram) {
		cmdClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});
		cmdMinimize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				fram.setState(JFrame.ICONIFIED);
			}
		});
	}

	private void initComponents() {

		iconClose = new javaswingdev.GoogleMaterialIcon();
		iconMax = new javaswingdev.GoogleMaterialIcon();
		iconMinimize = new javaswingdev.GoogleMaterialIcon();
		iconRestore = new javaswingdev.GoogleMaterialIcon();
		cmdClose = new TitleButton();
		cmdMinimize = new TitleButton();

		iconClose.setColor1(new java.awt.Color(111, 111, 111));
		iconClose.setColor2(new java.awt.Color(215, 215, 215));
		iconClose.setIcon(javaswingdev.GoogleMaterialDesignIcon.CLOSE);
		iconClose.setSize(18);

		iconMax.setColor1(new java.awt.Color(111, 111, 111));
		iconMax.setColor2(new java.awt.Color(215, 215, 215));
		iconMax.setIcon(javaswingdev.GoogleMaterialDesignIcon.CONTENT_COPY);
		iconMax.setSize(16);

		iconMinimize.setColor1(new java.awt.Color(111, 111, 111));
		iconMinimize.setColor2(new java.awt.Color(215, 215, 215));
		iconMinimize.setIcon(javaswingdev.GoogleMaterialDesignIcon.REMOVE);
		iconMinimize.setSize(18);

		iconRestore.setColor1(new java.awt.Color(111, 111, 111));
		iconRestore.setColor2(new java.awt.Color(215, 215, 215));
		iconRestore.setIcon(javaswingdev.GoogleMaterialDesignIcon.CROP_DIN);

		cmdClose.setHoverColor(new java.awt.Color(255, 48, 48));

		cmdMinimize.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 1, 1, 1));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addGap(0, 0, 0)
						.addComponent(cmdMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0).addComponent(cmdClose, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
								javax.swing.GroupLayout.PREFERRED_SIZE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(cmdClose, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
				.addComponent(cmdMinimize, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE));
	}
}