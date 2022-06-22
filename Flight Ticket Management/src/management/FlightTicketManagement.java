package management;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
		
		List<String> flight=readFlightFile();
		for(int i=0;i<flight.size();i++)
		{
			if(flight.get(i).contains(destination) && flight.get(i).contains(source))
			{
				flightName=flight.get(i);
				System.out.println(flightName);
			}
		}
		
		bookingObj.setFlightName(flightName);
		int arr[]=readIndividualFile();
		
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
				System.out.println("Enter the Seat You Want to Book :");
				String seatNo=scan.nextLine();
				SeatDetails seatObj=logicObj.getSeatDetails(flightName,seatNo);
				System.out.println("The Seat You Want to Book is :"+seatObj.getSeatType());
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
		bookingObj.setAmount(amount);
		bookingObj.setPassengerList(passengerList);
		
		logicObj.setBookingDetails(bookingId,bookingObj);
		System.out.println("Ticked Booked SuccessFully :");
		logicObj.printTicket(bookingObj);
	}
	
	public void createIndividualFile() throws IOException
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
	
	public int[] readIndividualFile() throws Exception
	{
		System.out.println("Enter the FileName :");
		String fileName=scan.nextLine();
		String result=logicObj.readIndividualFile(fileName);
		System.out.println(result);
		result=result.replace("Business class","");
		result=result.replace("Economy Class", "");
		result=result.replace(" : ","");
		result=result.replaceAll("\\[","");
		result=result.replaceAll("\\]","");
		result=result.replace(" ","");
		result=result.replace("\n",",");
		
		String arr[]=result.split(",");
		for(int i=0;i<arr.length;i++)
		{
			System.out.println(arr[i]+" ");
		}
		int seat[]=new int[arr.length];
		
		for(int i=0;i<seat.length;i++)
		{
			seat[i]=Integer.parseInt(arr[i]);
		}
		
		for(int i=0;i<seat.length;i++)
		{
			System.out.print(seat[i]+" ");
		}
		return seat;
	}
	
	public List<String> readFlightFile() throws Exception
	{
		List<String> flightList=new ArrayList<>();
		flightList=logicObj.readFlightFile();
		for(int i=0;i<flightList.size();i++)
		{
			System.out.println(flightList.get(i)+" ");
		}
		return flightList;
	}
	
	
	public static void main(String args[])
	{
		FlightTicketManagement flightObj=new FlightTicketManagement();
		
		try 
		{
			//flightObj.createIndividualFile();
			//flightObj.readFlightFile();
			flightObj.setBookingDetails();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
