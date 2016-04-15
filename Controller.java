package c07.controller;

// This is just a way to hold Event objects.
class EventSet {
	private Event[] events = new Event[100];
	private int index = 0;
	private int next = 0;

	public void add(Event e) {
		if (index >= events.length)
			return;
		// (In reallife, throw exception)
		events[index++] = e;
	}

	public Event getNext() {
		boolean looped = false;
		int start = next;
		do {
			next = (next + 1) % events.length;
			// See if it has looped to the beginning:
			if (start == next)
				looped = true;
			// If it loops past start,the list
			// is empty:
			if ((next == (start + 1) % events.length) && looped)
				return null;
		} while (events[next] == null);
		return events[next];
	}

	public void removeCurrent() {
		events[next] = null;
	}
}

public class Controller {
	private EventSet actionsT1 = new EventSet();
	private EventSet actionsT2  = new EventSet();
	public void addEventT1(Event c) {
		actionsT1.add(c);
	}
	public void addEventT2(Event c) {
		actionsT2.add(c);
	}

	public void run() {
		Event e1, e2;
		while ((e = es.getNext()) != null) {
			if (e.ready()) {
				if(){
					e1.action();
					System.out.println(e1.description());
					actionsT1.removeCurrent();
					e2.action();
					System.out.println(e2.description());
					actionsT2.removeCurrent();
				}
				else(){
					e2.action();
					System.out.println(e2.description());
					actionsT2.removeCurrent();
					e1.action();
					System.out.println(e1.description());
					actionsT1.removeCurrent();
				}
				
			}
		}
	}
}