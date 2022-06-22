package logic;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import booking.BookingDetails;
import cache.CacheLayer;
import file.FileLayer;
import seat.SeatDetails;

public class FlightTicketLogic 
{
	FileLayer newFile=new FileLayer();
	CacheLayer cacheObj=new CacheLayer();
	int id=0;
	int bookingId=1000;
	int count=0;
	
	public int getId()
	{
		return ++id;
	}
	
	public int getBookingId()
	{
		return ++bookingId;
	}

	public void writeFileIndividual(String fileName, String flight) throws IOException 
	{
		newFile.writeFileIndividual(fileName, flight);
	}

	public String readIndividualFile(String fileName) throws Exception 
	{
		String result=newFile.readFileIndividual(fileName);
		return result;
	}

	public List<String> readFlightFile() throws IOException 
	{
		List<String> flightList=newFile.readFile();
		return flightList;
	}

	public void setSeatDetails(String flightName,int arr[]) 
	{
		cacheObj.setFlightDetails(flightName,arr);
	}
	
	public void removeSeatDetails(String flightName,String seatNo)
	{
		cacheObj.removeSeatDetails(flightName, seatNo);
	}
	
	public void setFilled(String seatNO,SeatDetails seatObj)
	{
		cacheObj.setFilled(seatNO, seatObj);
	}

	public SeatDetails getSeatDetails(String flightName,String seatNo) 
	{
		SeatDetails seatObj=cacheObj.getSeat(flightName,seatNo);
		return seatObj;
	}

	public double getPayableAmount(int size, int aisleWindowCount, int mealCount,String classType) 
	{
		
		double amount=0;
		double extraCharges=0;
		if(classType.equals("Business Class"))
		{
			amount=size*2000+size*(count*200);
			extraCharges=aisleWindowCount*200;
		}
		else if(classType.equals("Economy Class"))
		{
			amount=size*1000+size*(count*100);
			extraCharges=aisleWindowCount*100;
		}
		
		double mealAmount=mealCount*200;
		amount=amount+extraCharges+mealAmount;
		count++;
		return amount;
	}

	public void setBookingDetails(int bookingId, BookingDetails bookingObj) 
	{
		cacheObj.setBookingDetails(bookingId,bookingObj);
	}

	public void printTicket(BookingDetails bookingObj) 
	{
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
		System.out.println("* Booking ID           : "+bookingObj.getBookingId());
		System.out.println("* Source Location      : "+bookingObj.getSource());
		System.out.println("* Destination Location : "+bookingObj.getDestination());
		System.out.println("* Meal Preference      : "+bookingObj.getMealPreference());
		System.out.println("* Booking Amount       : "+bookingObj.getAmount());
		System.out.println("* Flight Name          : "+bookingObj.getFlightName());
		System.out.println("* Total Tickets        : "+bookingObj.getPassengerList().size());
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
	}

}
