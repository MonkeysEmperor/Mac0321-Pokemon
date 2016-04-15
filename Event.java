package c07.controller;
abstract public class Event {
private long evtTime;
public Event(long eventTime) {
	evtTime = eventTime;
}
public boolean ready() {
	return
	System.currentTimeMillis() >= evtTime;
}
abstract public void action();
abstract public String description();
}
