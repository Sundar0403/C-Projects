package booking;

import java.util.List;

import passenger.PassengerDetails;

public class BookingDetails 
{
	int bookingId;
	String source;
	String destination;
	String allotedSeat;
	String mealPreference;
	double amount;
	String flightNo;
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
	public String getAllotedSeat() {
		return allotedSeat;
	}
	public void setAllotedSeat(String allotedSeat) {
		this.allotedSeat = allotedSeat;
	}
	public String getMealPreference() {
		return mealPreference;
	}
	public void setMealPreference(String mealPreference) {
		this.mealPreference = mealPreference;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
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
				+ ", allotedSeat=" + allotedSeat + ", mealPreference=" + mealPreference + ", amount=" + amount
				+ ", flightNo=" + flightNo + ", passengerList=" + passengerList + "]";
	}
	
	
	
}
