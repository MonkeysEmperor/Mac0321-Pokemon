package Ex1;

// This is just a way to hold Event objects.
class Round {
	private Event[] events;
	private int index = 0;
	private int next = 0;
	private int highestPriority;
	



	public boolean verifyEventExistence() {
		if (events[highestPriority] == null)
			return false;
		else
			return true;
	}
	


	public Round() {
		events = new Event[1000];
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
		int i = 0;
		highestPriority = next;
		int equalPriority = 0;
		do {
			next = (next + 1) % events.length;
			// See if it has looped to the beginning:
			if (start == next)
				looped = true;

			// Treating the exception when "events" doesn't exist with the the
			// initial "next" as index
			if (events[highestPriority] == null && events[next] != null)
				highestPriority = next;

			// Test all the priorities
			if (events[next] != null && events[highestPriority] != null) {
				if (events[next].getPriority() > events[highestPriority].getPriority())
					highestPriority = next;
				else if (events[next].getPriority() == events[highestPriority].getPriority() && next != highestPriority)
					equalPriority += 1;
			}

			// If it loops past start,the list
			// is empty:
			if ((next == (start + 1) % events.length) && looped)
				return null;

			i++;

		} while (events[highestPriority] == null || i < events.length);
		
		if (equalPriority == 1)
			if (highestPriority - 1 >= 0)
				if (events[highestPriority - 1] != null)
					if (events[highestPriority - 1].getPriority() == events[highestPriority].getPriority())
						return events[highestPriority - 1];
		
		return events[highestPriority];
	}
	
	public void removeCurrent() {
		events[highestPriority] = null;
	}
	
	public Event[] getEvents() {
		return events;
	}

	public void setEvents(Event[] events) {
		this.events = events;
	}
	
	public int getHighestPriority() {
		return highestPriority;
	}

}

public class Controller {

	protected Round es;

	public Controller() {
		es = new Round();
	}

	public void addEvent(Event c) {
		es.add(c);
	}

	public void run() {
		Event e;
		while ((e = es.getNext()) != null) {
			if (e.ready()) {
				e.action();
				if (es.verifyEventExistence()) {
					System.out.println(e.description());
					es.removeCurrent();
				}
			}
		}
	}
	
	public void clearEvents(){
		for (int i = 0; i < es.getEvents().length; i ++){
			if (i != es.getHighestPriority())
				es.getEvents()[i] = null;
		}
	}
}