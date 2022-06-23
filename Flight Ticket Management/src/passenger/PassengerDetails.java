package passenger;

public class PassengerDetails 
{
	String passengerName;
	int passengerAge;
	long passengerNumber;
	String passengerAddress;
	String seatPreference;
	
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public int getPassengerAge() {
		return passengerAge;
	}
	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}
	public long getPassengerNumber() {
		return passengerNumber;
	}
	public void setPassengerNumber(long passengerNumber) {
		this.passengerNumber = passengerNumber;
	}
	public String getPassengerAddress() {
		return passengerAddress;
	}
	public void setPassengerAddress(String passengerAddress) {
		this.passengerAddress = passengerAddress;
	}
	public String getSeatPreference() {
		return seatPreference;
	}
	public void setSeatPreference(String seatPreference) {
		this.seatPreference = seatPreference;
	}
	
	@Override
	public String toString() {
		return "PassengerDetails [ passengerName=" + passengerName + ", passengerAge="
				+ passengerAge + ", passengerNumber=" + passengerNumber + ", passengerAddress=" + passengerAddress
				+ "]";
	}
	
	
	
}
