package Ex1;


abstract public class Event {
	private long evtTime;
	protected double priority;

	public Event(long eventTime) {
		evtTime = eventTime;
	}

	public boolean ready() {
		return System.currentTimeMillis() >= evtTime;
	}

	abstract public void action();

	abstract public String description();

	public double getPriority() {
		return priority;
	}

}