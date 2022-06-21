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
		System.out.println("Enter the Source Location :");
		String source=scan.nextLine();
		System.out.println("Enter the Destintaion Location :");
		String destination=scan.nextLine();
		
		String flightName="";
		
		List<String> flight=readFlightFile();
		for(int i=0;i<flight.size();i++)
		{
			if(flight.get(i).contains(destination) && flight.get(i).contains(source))
			{
				flightName=flight.get(i);
			}
		}
		int arr[]=readIndividualFile();
		
		logicObj.setSeatDetails(flightName,arr);
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
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
