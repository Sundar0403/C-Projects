package booking;

import java.util.List;

import passenger.PassengerDetails;

public class BookingDetails 
{
	int bookingId;
	String source;
	String destination;
	List<String> seatNo;
	boolean mealPreference;
	double amount;
	String flightName;
	
	List<PassengerDetails> passengerList;
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public List<String> getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(List<String> seatNo) {
		this.seatNo = seatNo;
	}
	public boolean getMealPreference() {
		return mealPreference;
	}
	public void setMealPreference(boolean mealPreference) {
		this.mealPreference = mealPreference;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public List<PassengerDetails> getPassengerList() {
		return passengerList;
	}
	public void setPassengerList(List<PassengerDetails> passengerList) {
		this.passengerList = passengerList;
	}
	@Override
	public String toString() {
		return "BookingDetails [bookingId=" + bookingId + ", source=" + source + ", destination=" + destination
				+ ", seatNo=" + seatNo + ", mealPreference=" + mealPreference
				+ ", amount=" + amount + ", flightName=" + flightName + ", passengerList=" + passengerList + "]";
	}
	
	
	
}
