package events;

public interface RateListener {
	/**
	 * Carries out action when RateEvent is fired
	 * @param event
	 */
	public void handleEvent(RateEvent event);
}
