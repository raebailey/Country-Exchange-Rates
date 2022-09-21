package components.buttons;

import java.awt.Color;

import components.CustomButton.ButtonStyle;

public class ButtonColor {
	private Color background;
	private Color foreground;
	private Color backgroundHover;
	private Color backgroundPress;
	private Color foregroundHover;
	
	public ButtonColor(ButtonStyles style) {
		changeStyle(style);
	}

	public ButtonColor() {
	}
	
	/**
	 * Sets the style of button
	 * @param ButtonStyles style The enum that represents the style of button.
	 */
	public void changeStyle(ButtonStyles style) {
		this.background = style.getBackground();
		this.foreground = style.getForeground();
		this.backgroundHover = style.getBackground();
		this.backgroundPress = style.getBackgroundPress();
		this.foregroundHover = style.getForeground();
	}
	
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
