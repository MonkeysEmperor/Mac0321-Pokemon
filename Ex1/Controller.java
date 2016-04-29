package Ex1;


// This is just a way to hold Event objects.
class Round {
	private Event[] events;
	private int index = 0;
	private int next = 0;
	
	public Round (){
		events = new Event [100];
	}
	public Round (int length){
		events = new Event [length];
	}
	
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
	
	private Round es;
	public Controller ()
	{
		es = new Round();
	}
	public Controller (int length)
	{
		es = new Round(length);
	}
	
	public void addEvent(Event c) {
		es.add(c);
	}
	
	public void run() {
		Event e;
		while ((e = es.getNext()) != null) {
			if (e.ready()) {
				e.action();
				System.out.println(e.description());
				es.removeCurrent();
			}
		}
	}
}