package dev.se133.project.schedule;

import java.util.Set;
import java.util.TreeSet;

import dev.se133.project.commute.Commute;
import dev.se133.project.commute.Day;

/**
 * A commute schedule implementation.
 * Uses a {@code TreeSet} to keep commutes in sorted order.
 */
public class SortedCommuteSchedule implements CommuteSchedule {	// TODO Extend SortedSchedule
	private Set<Commute> commutes = new TreeSet<>();

	@Override
	public Commute[] getAllCommutes() {
		return commutes.toArray(new Commute[commutes.size()]);
	}

	@Override
	public Commute[] getAllCommutes(Day day) {
		Set<Commute> dayCommutes = new TreeSet<>();
		
		for (Commute commute : commutes) {
			if (commute.getStart().getTime().getDay().equals(day))
				dayCommutes.add(commute);
		}
		return dayCommutes.toArray(new Commute[dayCommutes.size()]);
	}

	@Override
	public void scheduleCommute(Commute commute) {
		commutes.add(commute);	// TODO Validate, check if overlaps with any existing commutes
	}

	@Override
	public boolean dropCommute(Commute commute) {
		return commutes.remove(commute);
	}
	@Override
	public Commute[] dropAllCommutes() {
		Commute[] allCommutes = getAllCommutes();	// Retrieve all commutes before clearing
		
		commutes.clear();
		
		return allCommutes;
	}
	@Override
	public Commute[] dropAllCommutes(Day day) {
		Commute[] dayCommutes = getAllCommutes(day);
		
		for (Commute dayCommute : dayCommutes)
			dropCommute(dayCommute);
		
		return dayCommutes;
	}
}