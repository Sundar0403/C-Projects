package delivery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Logic.FoodDeliveryLogic;
import booking.OrderDetails;
import customer.CustomerDetails;

public class FoodDeliveryBooking 
{
	FoodDeliveryLogic logicObj=new FoodDeliveryLogic();
	Scanner scan=new Scanner(System.in);
	String pickUpLocation="";
	
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
		OrderDetails orderObj=new OrderDetails();
		
		System.out.println("Enter the Customer Id :");
		int custId=scan.nextInt();
		scan.nextLine();
		
		int bookingId=logicObj.getBookingId();
		orderObj.setOrderId(bookingId);
		
		int count=0;
		int amount=0;
		
		
		boolean flag=true;
		
		String delivery=logicObj.readDetails("delivery.txt");
		System.out.println(delivery);
		List<OrderDetails> orderList=new ArrayList<>();
		
		System.out.println("You are Alloted to Delivery Executive :"+logicObj.allotDelivery());
		
		while(flag==true)
		{
			orderList=new ArrayList<>();
			count++;
			
			System.out.println("Enter the Restuarant Name :");
			String restName=scan.nextLine();
			orderObj.setPickUpLocation(restName);
			
			System.out.println("Enter the Destination Point :");
			String destination=scan.nextLine();
			orderObj.setDropLocation(destination);
			
			System.out.println("Enter the Time :");
			String time=scan.nextLine();
			
			System.out.println("Deliver Executive Status Are Give Below :");
			
			String deliveryExecutive=logicObj.allotDelivery();
			orderObj.setDeliveryExecutive(deliveryExecutive);

			String times[]=time.split(":");
			System.out.println(Arrays.toString(times));
			String pickUpTime=logicObj.getPickUpTime(times);
			orderObj.setPickUpTime(pickUpTime);
			
			String deliveryTime=logicObj.getDeliveryTime(times);
			orderObj.setDeliveryTime(deliveryTime);
			
			if(count==1)
			{
				amount=amount+50;
				pickUpLocation=restName;
			}
			
			if(count>1)
			{
				if(!restName.equals(pickUpLocation))
				{
					amount=amount+50;
				}
				else
				{
					amount=amount+5;
				}
			}
			orderObj.setOrderAmount(amount);
			orderList.add(orderObj);
			
			System.out.println("Do You Want to Continue Order Food : ");
			System.out.println("1.YES\n2.NO");
			String val=scan.nextLine();
			if(val.equals("YES"))
			{
				flag=true;
			}
			else
			{
				flag=false;
			}
		}
		logicObj.setOrderDetails(bookingId,orderList);
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
