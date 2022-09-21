package components.buttons;

import java.awt.Color;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.interpolation.PropertySetter;

public class ButtonAnimation {
	private final Animator animator;
	private final ButtonColor style;
	private final String property;
	private TimingTarget target;

	public ButtonAnimation(int duration, ButtonColor style, String property) {
		this.style = style;
		this.property = property;
		this.animator = new Animator(duration);
		this.animator.setResolution(1);
	}

	public void start(Color... colors) {
		stop();
		animator.removeTarget(target);
		target = new PropertySetter(style, property, colors);
		animator.addTarget(target);
		animator.start();
	}

	public void addTarget(TimingTarget target) {
		animator.addTarget(target);
	}

	public void stop() {
		if (animator.isRunning()) {
			animator.stop();
		}
	}

}
