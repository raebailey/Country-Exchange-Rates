package interfaces;

import java.awt.Component;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.animation.timing.interpolation.Interpolator;

import enums.AnimationTypes;

public class CustomAnimation {
	private Animator animator;
	private float animate = 10;
	private TimingTargetAdapter target;
	private AnimationTypes type;
	private Component card;
	
	public CustomAnimation(Component card) {
		this.card = card;
		this.target = new TimingTargetAdapter() {
			@Override
			public void timingEvent(float fraction) {
				animate = fraction;
			}
		};
		this.animator  = new Animator(2000,this.target);
		this.animator.setResolution(0);
		this.animator.setInterpolator(new Interpolator() {

			@Override
			public float interpolate(float x) {
				// TODO Auto-generated method stub
//				return (float) (1 - (Math.pow(1 - x, 5)));
				float n1 = 7.5625f;
		        float d1 = 2.75f;
		        double v;
		        if (x < 1 / d1) {
		            v = n1 * x * x;
		        } else if (x < 2 / d1) {
		            v = n1 * (x -= 1.5 / d1) * x + 0.75;
		        } else if (x < 2.5 / d1) {
		            v = n1 * (x -= 2.25 / d1) * x + 0.9375;
		        } else {
		            v = n1 * (x -= 2.625 / d1) * x + 0.984375;
		        }
		        return (float) v;
			}
			
		});
	}
	
	
	public Animator getAnimator() {
		return animator;
	}


	public void setAnimator(Animator animator) {
		this.animator = animator;
	}


	public float getAnimate() {
		return animate;
	}


	public void setAnimate(float animate) {
		this.animate = animate;
	}


	public TimingTargetAdapter getTarget() {
		return target;
	}


	public void setTarget(TimingTargetAdapter target) {
		this.target = target;
	}


	public AnimationTypes getType() {
		return type;
	}


	public void setType(AnimationTypes type) {
		this.type = type;
	}


	public void start() {
		if(!getAnimator().isRunning()) {
			getAnimator().start();
		}
		
	};
	
}
