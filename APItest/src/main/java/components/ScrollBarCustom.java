package components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollBar;

public class ScrollBarCustom extends JScrollBar {
	public ScrollBarCustom() {
		setUI(new ModernScrollBarUI());
		setPreferredSize(new Dimension(0, 8));
		setForeground(new Color(48, 144, 216));
		setBackground(new Color(30, 30, 30,0));
	}
}
