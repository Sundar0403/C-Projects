package delivery;

import java.util.Map;
import java.util.Scanner;

import Logic.FoodDeliveryLogic;
import booking.OrderDetails;
import customer.CustomerDetails;

public class FoodDeliveryBooking 
{
	FoodDeliveryLogic logicObj=new FoodDeliveryLogic();
	Scanner scan=new Scanner(System.in);
	
	private void setCustomerDetails() throws Exception
	{
		CustomerDetails customerObj=new CustomerDetails();
		int customerId=logicObj.getCustomerId();
		customerObj.setCustomerId(customerId);
		
		System.out.println("Enter the Customer Name :");
		String name=scan.nextLine();
		customerObj.setCustomerName(name);
	
		System.out.println("Enter the Customer Location :");
		String location=scan.nextLine();
		customerObj.setLocation(location);
		
		System.out.println("Enter the Customer Mobile Number :");
		long mobNo=scan.nextLong();
		scan.nextLine();
		customerObj.setMobileNo(mobNo);
		
		System.out.println("Enter the Filename to Write Customer Details :");
		String fileName=scan.nextLine();
		
		logicObj.setCustomerDetails(customerId,customerObj,fileName);
	}
	
	public void setDeliveryExecutiveDetails() throws Exception
	{
		logicObj.setDeliveryDetails("delivery.txt");
	}
	
	public void foodOrder() throws Exception
	{
		System.out.println("Deliver Executive Status Are Give Below :");
		
		String delivery=logicObj.readDetails("delivery.txt");
		System.out.println(delivery);
		
		OrderDetails orderObj=new OrderDetails();
		
		int bookingId=logicObj.getBookingId();
		orderObj.setOrderId(bookingId);
		
		String deliveryExecutive=scan.nextLine();
	}
	
	private void readCustomerDetails() throws Exception
	{
		logicObj.readCustomerDetails("Customer.txt");
	}
	
	private void readDeliveryDetails() throws Exception
	{
		logicObj.readDeliveryDetails("delivery.txt");
	}

	private void readOrderDetails() 
	{
		
	}
	
	public static void main(String args[]) 
	{
		FoodDeliveryBooking bookingObj=new FoodDeliveryBooking();
		Scanner scan=new Scanner(System.in);
		int choice=0;
		boolean flag=true;
		
		while(flag==true)
		{
			System.out.println("-*-*-*-* 1 . READ CUSTOMER DETAILS -*-*-*-*-*");
			System.out.println("-*-*-*-* 2 . READ ORDER DETAILS  -*-*-*-*-*-*");
			System.out.println();
			System.out.println();
			
			try
			{
				System.out.println("Enter Your Choice :");
				choice=scan.nextInt();
				scan.nextLine();
			}
			catch(Exception e)
			{
				System.out.println("Integer Numbers Only : ");
				e.printStackTrace();
				break;
			}
			
			switch(choice)
			{
				
				case 1:
						try
						{
							bookingObj.readCustomerDetails();
						}	
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
					
				case 2:
						try
						{
							bookingObj.readOrderDetails();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
			
				case 3:
						try
						{
							bookingObj.setCustomerDetails();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
						
				case 4:
						try
						{
							bookingObj.setDeliveryExecutiveDetails();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}		
						break;
						
				case 5:
						try
						{
							bookingObj.foodOrder();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
			}
		}
		scan.close();
	}
}
