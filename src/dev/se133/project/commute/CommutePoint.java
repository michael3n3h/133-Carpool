package dev.se133.project.commute;

import java.util.HashMap;

/**
 * An immutable fixed location and time of a commute.
 */
public class CommutePoint implements Comparable<CommutePoint> {
	private Address address;
	private Time time;
	private  HashMap<Address, Double > commuteMap = new HashMap<>();	// TODO Move to AddressMap object?
	
	/**
	 * Constructs a new point at the specified address and time.
	 * @param address commute point's address of occurrence
	 * @param time commute point's time of occurrence
	 */
	public CommutePoint(Address address, Time time) {
		setAddress(address);
		setTime(time);
	}
	private void setAddress(Address address) {
		this.address = address;
	}
	private void setTime(Time time) {
		this.time = time;
	}
	
	/** @return address */
	public Address getAddress() {
		return address;
	}
	/** @return time */
	public Time getTime() {
		return time;
	}
	/** @return distance to address */
	private double getCommuteDistance(Address address) {
		return commuteMap.get(address);
	}
	/** Adds a new entry to the commuteMap hash. */
	private void addCommuteDistance(Address address, double distance) {
		commuteMap.put(address, distance);
	}
	
	private void removeCommuteDistance(Address address) {
		commuteMap.remove(address);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CommutePoint))
			return false;
		CommutePoint other = (CommutePoint) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
	
	/**
	 * Compares the times of this {@code CommutePoint} and a specified {@code CommutePoint}.
	 */
	@Override
	public int compareTo(CommutePoint o) {
		return time.compareTo(o.getTime());
	}
}
