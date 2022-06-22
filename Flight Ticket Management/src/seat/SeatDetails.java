package seat;

public class SeatDetails 
{
	String seatNo;
	String flightName;
	String classType;
	String seatType;
	boolean aisleOrWindow;
	
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getSeatType() {
		return seatType;
	}
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	public boolean isAisleOrWindow() {
		return aisleOrWindow;
	}
	public void setAisleOrWindow(boolean aisleOrWindow) {
		this.aisleOrWindow = aisleOrWindow;
	}
	@Override
	public String toString() {
		return "SeatDetails [seatNo=" + seatNo + ", flightName=" + flightName + ", classType=" + classType
				+ ", seatType=" + seatType + ", aisleOrWindow=" + aisleOrWindow + "]";
	}
	
	
	
	
}
