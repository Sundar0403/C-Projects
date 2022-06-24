package management;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import booking.BookingDetails;
import logic.FlightTicketLogic;
import passenger.PassengerDetails;
import seat.SeatDetails;

public class FlightTicketManagement 
{
	Scanner scan=new Scanner(System.in);
	List<PassengerDetails> passengerList=new ArrayList<>();
	FlightTicketLogic logicObj=new FlightTicketLogic();
	Map<Integer,BookingDetails> bookingMap=new HashMap<>();
	
	public void setPassengerDetails() throws Exception
	{
		System.out.println("Enter the Number of Passengers To Book the Seat :");
		int num=scan.nextInt();
		scan.nextLine();
		
		for(int i=0;i<num;i++)
		{
			PassengerDetails passengerObj=new PassengerDetails();
			System.out.println("Enter the Passenger Name :");
			String name=scan.nextLine();
			passengerObj.setPassengerName(name);
			
			System.out.println("Enter the Passenger Age :");
			int age=scan.nextInt();
			scan.nextLine();
			passengerObj.setPassengerAge(age);
			
			System.out.println("Enter the Passenger Number :");
			long number=scan.nextLong();
			scan.nextLine();
			passengerObj.setPassengerNumber(number);
			
			System.out.println("Enter the Passeneger Address :");
			String address=scan.nextLine();
			passengerObj.setPassengerAddress(address);
			
			System.out.println("Enter the Seat Preference :");
			String seatPreference=scan.nextLine();
			passengerObj.setSeatPreference(seatPreference);
			
			passengerList.add(passengerObj);
		}
	}
	
	public void setBookingDetails() throws Exception
	{
		BookingDetails bookingObj=new BookingDetails();
		int bookingId=logicObj.getBookingId();
		bookingObj.setBookingId(bookingId);
		System.out.println("Enter the Source Location :");
		String source=scan.nextLine();
		bookingObj.setSource(source);
		System.out.println("Enter the Destintaion Location :");
		String destination=scan.nextLine();
		bookingObj.setDestination(destination);
		
		String flightName="";
		String temp=source+"-"+destination;
		List<String> flight=readFlightFile();
		for(int i=0;i<flight.size();i++)
		{
			if(flight.get(i).contains(temp))
			{
				flightName=flight.get(i);
				System.out.println();
				System.out.println("The Available Flight is :");
				System.out.println("------"+flightName+"-----");
				System.out.println();
			}
		}
		
		bookingObj.setFlightName(flightName);
		int arr[]=readIndividualFile(flightName);
		Set<String> classes=new HashSet<>();
		logicObj.setSeatDetails(flightName,arr);
		int size=passengerList.size();
		List<String> seatList=new ArrayList<>();
		int aisleWindowCount=0;
		String classType="";
		for(int i=0;i<size;i++)
		{
			boolean flag=true;
			while(flag)
			{
				System.out.println("Enter the Seat You Want to Book : (eg.1A,12G etc...)");
				String seatNo=scan.nextLine();
				SeatDetails seatObj=logicObj.getSeatDetails(flightName,seatNo);
				System.out.println("The Seat You Want to Book is :"+seatObj.getSeatType());
				
				classes.add(seatObj.getClassType());
				if(classes.size()>1)
				{
					throw new Exception(" You Can't Book Tickets in Different Class :");
				}
				System.out.println("Please Confirm to Book :");
				System.out.println("1.YES\n 2.NO");
				String con=scan.nextLine();
				if(con.equals("YES"))
				{
					seatList.add(seatNo);
					if(seatObj.getSeatType().equals("Window") || seatObj.getSeatType().equals("Aisle"))
					{
						aisleWindowCount++;
					}
					logicObj.removeSeatDetails(flightName, seatNo);
					logicObj.setFilled(seatNo,seatObj);
					flag=false;
				}
				else
				{
					flag=true;
				}
				classType=seatObj.getClassType();
				
			}
		}
		bookingObj.setSeatNo(seatList);
		System.out.println("Enter the Meal Preference :");
		System.out.println("Say\n 1.YES \n 2.NO");
		String meal=scan.nextLine();
		int mealCount=0;
		if(meal.equals("YES"))
		{
			bookingObj.setMealPreference(true);
			mealCount=passengerList.size();
		}
		else
		{
			bookingObj.setMealPreference(false);
		}
		
		double amount=logicObj.getPayableAmount(size,aisleWindowCount,mealCount,classType);
		System.out.println("The Payable Amount for the Tickets + Meals is :"+amount);
		System.out.println("Enter the Amount to Pay :");
		double payable=scan.nextDouble();
		scan.nextLine();
		bookingObj.setAmount(payable);
		bookingObj.setPassengerList(passengerList);
		
		logicObj.setBookingDetails(bookingId,bookingObj);
		System.out.println("Ticked Booked SuccessFully :");
		logicObj.printTicket(bookingObj);
		passengerList=new ArrayList<>();
	}
	
	public void particularTicketCancellation() throws Exception
	{
		System.out.println("-*-*-*TICKET CANCELLATION PORTAL-*-*-*");
		System.out.println();
		System.out.println("Enter the Booking Id :");
		int bookingId=scan.nextInt();
		scan.nextLine();
		BookingDetails bookingObj=logicObj.getBookingDetails(bookingId);
		List<String> seatList=bookingObj.getSeatNo();
		if(seatList.size()==1)
		{
			System.out.println(" Use All Ticket Cancellation Portal :");
		}
		else
		{	
			System.out.println("Enter the Number of Tickets to Cancel :");
			int num=scan.nextInt();
			scan.nextLine();
			String classType="";
			int aisleWindowCount=0;
			for(int i=0;i<num;i++)
			{
				System.out.println("Enter the Ticket Number :");
				String seatNo=scan.nextLine();
				SeatDetails seatObj=logicObj.getFilled(seatNo);
				classType=seatObj.getClassType();
				logicObj.removeFilledSeats(seatNo);
				logicObj.updateSeatDetails(bookingObj.getFlightName(),seatNo,seatObj);
				seatList.remove(seatNo);
				if(seatObj.getSeatType().equals("Aisle") || seatObj.getSeatType().equals("Window"))
				{
					aisleWindowCount++;
				}
			}
		
			double refund=logicObj.getRefundAmount(bookingObj,classType,num,aisleWindowCount);
			System.out.println("Ticket Cancellation Done : ");
			System.out.println("Refunded Amount : "+refund);
		
			double newAmount=bookingObj.getAmount()-refund;
			bookingObj.setAmount(newAmount);
		}
	}
	
	public void allTicketCancellation() throws Exception
	{
		System.out.println("-*-*-*TICKET CANCELLATION PORTAL-*-*-*");
		System.out.println();
		System.out.println("Enter the Booking Id :");
		int bookingId=scan.nextInt();
		scan.nextLine();
		
		BookingDetails bookingObj=logicObj.getBookingDetails(bookingId);
		
		List<String> seatList=bookingObj.getSeatNo();

		for(int i=0;i<seatList.size();i++)
		{
			String seatNo=seatList.get(i);
			SeatDetails seatObj=logicObj.getFilled(seatNo);
			logicObj.removeFilledSeats(seatNo);
			logicObj.updateSeatDetails(bookingObj.getFlightName(), seatNo, seatObj);
		}
		
		double refund=logicObj.getAllRefund(bookingObj,seatList.size());
		
		System.out.println("All Ticket Cancellation Done : ");
		System.out.println("Refunded Amount : "+refund);
		
		logicObj.removeBookingDetails(bookingId,bookingObj.getFlightName());
	}
	
	public void createIndividualFile() throws Exception
	{
		System.out.println("Enter the Number of Flights :");
		int num=scan.nextInt();
		scan.nextLine();
		for(int a=0;a<num;a++)
		{
			System.out.println("Enter the FileName :");
			String fileName=scan.nextLine();
			for(int i=0;i<2;i++)
			{
				System.out.println("Enter the Class Name :");
				String className=scan.nextLine();
				int arr[]=new int[3];
				for(int j=0;j<3;j++)
				{
					arr[j]=scan.nextInt();
				}
				scan.nextLine();
				System.out.println("Enter the Number of Rows :");
				int row=scan.nextInt();
				scan.nextLine();
				System.out.println(Arrays.toString(arr));
				String flight=className+" : "+Arrays.toString(arr)+","+row+"\n";
				logicObj.writeFileIndividual(fileName,flight);
			}	
		
		}
	}
	
	public void printMealOrdered() throws Exception
	{
		System.out.println("-*-*-*THE SEATS MEAL ORDERD ARE -*-*-*");
		System.out.println();
		System.out.println();
		List<String> seatNo=logicObj.getMealSeats();
		for(int i=0;i<seatNo.size();i++)
		{
			System.out.println("**"+seatNo.get(i)+"**");
		}
	}
	
	private void printSeats() throws Exception 
	{
		System.out.println("Enter the Flight Name :");
		String flight=scan.nextLine();
		flight+="\n";
		System.out.println("-*-*-* THE AVAILABLE SEATS ARE -*-*-*");
		System.out.println();
		System.out.println();
		
		List<String> businessSeatNo=logicObj.getBusinessClass(flight);
		for(int i=0;i<businessSeatNo.size();i++)
		{
			System.out.println(businessSeatNo.get(i));
		}
	}
	
	public int[] readIndividualFile(String flightName) throws Exception
	{
		flightName.trim();
		String fileName="";
		for(int i=0;i<flightName.length();i++)
		{
			if((int)flightName.charAt(i)==10)
			{
				fileName=fileName+".txt";
			}
			else
			{
				fileName=fileName+flightName.charAt(i);
			}
		}
		String result=logicObj.readIndividualFile(fileName);
		System.out.println(fileName);

		result=result.replace("Business class","");
		result=result.replace("Economy Class", "");
		result=result.replace(" : ","");
		result=result.replaceAll("\\[","");
		result=result.replaceAll("\\]","");
		result=result.replace(" ","");
		result=result.replace("\n",",");
		
		String arr[]=result.split(",");
		System.out.println(Arrays.toString(arr));
		int seat[]=new int[arr.length];
		
		for(int i=0;i<seat.length;i++)
		{
			seat[i]=Integer.parseInt(arr[i]);
		}
		return seat;
	}
	
	public List<String> readFlightFile() throws Exception
	{
		List<String> flightList=new ArrayList<>();
		flightList=logicObj.readFlightFile();
		return flightList;
	}
	
	private void printBookingDetails() 
	{
		System.out.println("Enter the Booking Id :");
		int bookingId=scan.nextInt();
		logicObj.printBookingDetails(bookingId);
	}
	
	
	public static void main(String args[])
	{
		FlightTicketManagement flightObj=new FlightTicketManagement();
		try(Scanner scan=new Scanner(System.in);)
		{
			int choice=0;
			boolean flag=true;
			
			while(flag)
			{
				System.out.println("-*-*-* FLIGHT TICKET RESERVATION *-*-*-");
				System.out.println();
				System.out.println();
				System.out.println("-------1.FLIGHT DETAILS UPDATE :-------");
				System.out.println("-------2.PASSENEGER DETAILS :-------");
				System.out.println("-------3.BOOK THE TICKETS :-------");
				System.out.println("-------4.PARTICULAR TICKET CANCELLATION :-------");
				System.out.println("-------5.ALL TICKET CANCELLATION :-------");
				System.out.println("-------6.PRINT MEAL ORDERED SEATS :-------");
				System.out.println("-------7.AVAILABLE SEATS IN A FLIGHT--------");
				System.out.println("-------8.PRINT BOOKING DETAILS-----------");
				System.out.println("-------9.EXIT :-------");
				System.out.println("Enter Your Choice :");
				choice=scan.nextInt();
				scan.nextLine();
				switch(choice)
				{
				
					case 1:
						try
						{
							flightObj.createIndividualFile();
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
							flightObj.setPassengerDetails();
							
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
							flightObj.setBookingDetails();
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
							flightObj.particularTicketCancellation();
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
							flightObj.allTicketCancellation();
							
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
					
					case 6:
					
						try
						{	
							flightObj.printMealOrdered();
						}
					
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
						
					case 7:
						
						try
						{	
							flightObj.printSeats();
						}
					
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
						
					case 8:
						
						try
						{	
							flightObj.printBookingDetails();
						}
					
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
						
					case 9:
						
						try
						{	
							System.out.println("-*-*THANK YOU-*-*");
							flag=false;
						}
					
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;		
				}
			}
		}
	}

	
}



