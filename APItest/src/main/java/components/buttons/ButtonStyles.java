package components.buttons;

import java.awt.Color;

public enum ButtonStyles {
	PRIMARY(new Color(0, 150, 255), new Color(255, 255, 255), new Color(0, 90, 255), new Color(220, 220, 220)),
	SECONDARY(new Color(203, 209, 219), new Color(58, 70, 81), new Color(81, 92, 108), new Color(230, 239, 255)),
	DESTRUCTIVE(new Color(255, 0, 0), new Color(255, 255, 255), new Color(198, 86, 0), new Color(255, 161, 90)),
	CLOSE(new Color(211, 211, 211, 30), new Color(255, 255, 255), new Color(120, 120, 120), new Color(255, 0, 0),
			new Color(255, 255, 255));

	private ButtonStyles(Color background, Color foreground, Color backgroundHover, Color backgroundPress) {
		this.background = background;
		this.foreground = foreground;
		this.backgroundHover = backgroundHover;
		this.backgroundPress = backgroundPress;
	}

	private ButtonStyles(Color background, Color foreground, Color backgroundHover, Color backgroundPress,
			Color foregroundHover) {
		this.background = background;
		this.foreground = foreground;
		this.backgroundHover = backgroundHover;
		this.backgroundPress = backgroundPress;
		this.foregroundHover = foregroundHover;
	}

	private Color background;
	private Color foreground;
	private Color backgroundHover;
	private Color backgroundPress;
	private Color foregroundHover;
	
	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	public Color getForeground() {
		return foreground;
	}

	public void setForeground(Color foreground) {
		this.foreground = foreground;
	}

	public Color getBackgroundHover() {
		return backgroundHover;
	}

	public void setBackgroundHover(Color backgroundHover) {
		this.backgroundHover = backgroundHover;
	}

	public Color getBackgroundPress() {
		return backgroundPress;
	}

	public void setBackgroundPress(Color backgroundPress) {
		this.backgroundPress = backgroundPress;
	}

	public Color getForegroundHover() {
		return foregroundHover;
	}

	public void setForegroundHover(Color foregroundHover) {
		this.foregroundHover = foregroundHover;
	}
}
