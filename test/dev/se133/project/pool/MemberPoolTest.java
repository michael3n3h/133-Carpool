package dev.se133.project.pool;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dev.se133.project.commute.*;
import dev.se133.project.member.BasicMember;
import dev.se133.project.member.MemberState;
import dev.se133.project.member.factory.MemberPool;
import dev.se133.project.schedule.CommuteSchedule;
import dev.se133.project.schedule.SortedCommuteSchedule;
import dev.se133.project.state.State;

@SuppressWarnings("javadoc")
public class MemberPoolTest {
	private MemberPool pool;

	@Before
	public void setUp() throws Exception {
		pool = new MemberPool();
	}

	@After
	public void tearDown() throws Exception {
		//
	}

	@Test
	public void testAddMember() {
		int id = 0;
		String name = "Member0";
		Address address = new Address("Address0");
		
		CommuteScheduleOLD preferredCommutes = new SortedCommuteSchedule();
		preferredCommutes.scheduleCommute(makeCommute(5));
		
		MemberState state = new MemberState.Driver();
		
		pool.addMember(new BasicMember(id, name, address, preferredCommutes, state));
		
		int numDrivers = pool.getDrivers().size(),
				numPassengers = pool.getPassengers().size();
		assertEquals(1, numDrivers);	// Should be a driver
		assertEquals(0, numPassengers);
		
		state = new MemberState.Passenger();
		pool.addMember(new BasicMember(id, name, address, preferredCommutes, state));
		
		numDrivers = pool.getDrivers().size();
		numPassengers = pool.getPassengers().size();
		assertEquals(1, numDrivers);	// Should be a driver
		assertEquals(1, numPassengers);	}

	@Test
	public void testGetDrivers() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPassengers() {
		fail("Not yet implemented");
	}

	private static Commute makeCommute(int numStops) {
		Commute commute = new Commute();
		
		Address address;
		Time time = new Time();
		for (int i = 0; i < numStops; i++) {
			address = new Address("Address" + i);
			time = Time.timeAfter(time, i);
			commute.addStop(new Stop(time, address));
		}
		return commute;
	}
}
