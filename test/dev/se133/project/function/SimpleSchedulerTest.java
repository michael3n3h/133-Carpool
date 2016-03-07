package dev.se133.project.function;

import java.util.Random;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dev.se133.project.commute.*;
import dev.se133.project.map.AddressMap;
import dev.se133.project.map.ArrayAddressMap;
import dev.se133.project.member.BasicMember;
import dev.se133.project.member.Member;
import dev.se133.project.member.MemberState;

public class SimpleSchedulerTest {
	private static final int xSize = 100, ySize = 100;
	private static AddressMap map;
	private static Car car;
	private static CommutePoint departure, arrival;
	
	private SimpleScheduler scheduler;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		map = buildMap(6);
		System.out.println(map);
		Member[] members = buildMembers(map);
		for (Member member : members) {
			System.out.println("Member: " + member.getName() + " Address: " + member.getAddress());
		}
		arrival = new CommutePoint(new Address("Address0"), Day.MONDAY, new Time(departure.getTime().getTotalMinutes() + 60));
		
		car = buildCar(members);
	}
	
	@Before
	public void setUp() throws Exception {
		scheduler = new SimpleScheduler(map, car, departure, arrival);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSchedule() {
		scheduler.schedule();
		
		Commute commute = scheduler.getCommute();
		
		System.out.println("Day: " + commute.getDay());
		for (CommutePoint stop : commute.getStops()) {
			System.out.println("\tTime: " + stop.getTime().getHour() + ":" + stop.getTime().getMinute());
			System.out.println("\tAddress: " + stop.getAddress().toString());
		}
	}

	private static Member[] buildMembers(AddressMap map) throws TimeOutOfBoundsException {
		Set<Address> addresses = map.getAllAddresses();
		Member[] members = new Member[addresses.size() - 1];
		
		int counter = 0;
		for (Address address : addresses) {
			System.out.println(address);
			if (counter < members.length)
				members[counter++] = new BasicMember(counter, "Member" + (counter - 1), address);
		}
		members[0].setState(new MemberState.Driver());	// 1 driver
		departure = new CommutePoint(members[0].getAddress(), Day.MONDAY, new Time(12, 10));
		
		return members;
	}
	
	private static AddressMap buildMap(int numAddresses) {
		int xSize = numAddresses * 2, ySize = xSize;	// Half of map empty
		ArrayAddressMap map = new ArrayAddressMap(xSize, ySize);
		
		Random random = new Random();
		for (int i = 0; i < numAddresses; i++) {
			while (!map.addAddress(new Address("Address" + i), random.nextInt(xSize), random.nextInt(ySize)));	// Keep randomizing until an empty spot found
		}
		return map;
	}
	
	private static Car buildCar(Member[] members) throws FullCarException, NoDriverException {
		Car car = new Car(members.length);
		
		for (Member member : members) {
			car.addPassenger(member);
			
			car.setDriver();
			System.out.println("Driver: " + car.getDriver().getName());
		}
		return car;
	}
}
