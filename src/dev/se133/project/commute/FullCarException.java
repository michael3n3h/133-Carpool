package dev.se133.project.commute;

/**
 * Exception thrown when adding a new inhabitant to a {@code Car} would overflow its capacity.
 */
public class FullCarException extends Exception{
	private static final long serialVersionUID = -3247087105986782821L;

	/**
	 * Constructs a custom instance of this exception.
	 */
	public FullCarException(int numberOfSeats) {
		super(buildMessage(numberOfSeats));
	}
	
	private static String buildMessage(int numberOfSeats) {
		String message = "This vehicle has reached it's maximum capacity of " + numberOfSeats + " number of seats.";
		
		return message;
	}
}