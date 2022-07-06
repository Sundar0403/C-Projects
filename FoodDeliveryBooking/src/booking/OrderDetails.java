package booking;

public class OrderDetails 
{	
	int orderId;
	String deliveryExecutive;
	String pickUpLocation;
	String dropLocation;
	String pickUpTime;
	String deliveryTime;
	int orderAmount;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getDeliveryExecutive() {
		return deliveryExecutive;
	}
	public void setDeliveryExecutive(String deliveryExecutive) {
		this.deliveryExecutive = deliveryExecutive;
	}
	public String getPickUpLocation() {
		return pickUpLocation;
	}
	public void setPickUpLocation(String pickUpLocation) {
		this.pickUpLocation = pickUpLocation;
	}
	public String getDropLocation() {
		return dropLocation;
	}
	public void setDropLocation(String dropLocation) {
		this.dropLocation = dropLocation;
	}
	public String getPickUpTime() {
		return pickUpTime;
	}
	public void setPickUpTime(String pickUpTime) {
		this.pickUpTime = pickUpTime;
	}
	public String getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public int getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	@Override
	public String toString() {
		return "OrderDetails [orderId=" + orderId + ", deliveryExecutive=" + deliveryExecutive + ", pickUpLocation="
				+ pickUpLocation + ", dropLocation=" + dropLocation + ", pickUpTime=" + pickUpTime + ", deliveryTime="
				+ deliveryTime + ", orderAmount=" + orderAmount + "]";
	}
	
}