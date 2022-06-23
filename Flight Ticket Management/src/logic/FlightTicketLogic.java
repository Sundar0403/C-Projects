package logic;

import java.io.IOException;
import check.Utility;

import java.util.ArrayList;
import java.util.List;
//import java.util.Map;
import java.util.Map;

import booking.BookingDetails;
import cache.CacheLayer;
import file.FileLayer;
import passenger.PassengerDetails;
import seat.SeatDetails;

public class FlightTicketLogic 
{
	FileLayer newFile=new FileLayer();
	CacheLayer cacheObj=new CacheLayer();
	Utility utilObj=new Utility();
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

	public void writeFileIndividual(String fileName, String flight) throws Exception 
	{
		utilObj.checkString(fileName);
		utilObj.checkString(flight);
		newFile.writeFileIndividual(fileName, flight);
	}

	public String readIndividualFile(String fileName) throws Exception 
	{
		System.out.println(fileName);
		utilObj.checkString(fileName);
		String result=newFile.readFileIndividual(fileName);
		return result;
	}

	public List<String> readFlightFile() throws IOException 
	{
		List<String> flightList=newFile.readFile();
		return flightList;
	}

	public void setSeatDetails(String flightName,int arr[]) throws Exception
	{
		utilObj.checkString(flightName);
		cacheObj.setFlightDetails(flightName,arr);
	}
	
	public void removeSeatDetails(String flightName,String seatNo) throws Exception
	{
		utilObj.checkString(seatNo);
		utilObj.checkString(flightName);
		cacheObj.removeSeatDetails(flightName, seatNo);
	}
	
	public void setFilled(String seatNO,SeatDetails seatObj) throws Exception
	{
		utilObj.checkString(seatNO);
		utilObj.checkObject(seatObj);
		cacheObj.setFilled(seatNO, seatObj);
	}
	
	public SeatDetails getFilled(String seatNo) throws Exception
	{
		utilObj.checkString(seatNo);
		SeatDetails seatObj=cacheObj.getFilledSeats(seatNo);
		return seatObj;
	}

	public SeatDetails getSeatDetails(String flightName,String seatNo) throws Exception
	{
		utilObj.checkString(seatNo);
		utilObj.checkString(flightName);
		SeatDetails seatObj=cacheObj.getSeat(flightName,seatNo);
		return seatObj;
	}
	
	public void removeBookingDetails(int bookingId, String flightName) throws Exception
	{
		utilObj.checkString(flightName);
		cacheObj.removeBookingDetails(bookingId,flightName);
	}

	public double getPayableAmount(int size, int aisleWindowCount, int mealCount,String classType) throws Exception 
	{
		utilObj.checkString(classType);
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
	
	public double getRefundAmount(BookingDetails bookingObj, String classType, int num,int aisleWindowCount) throws Exception 
	{
		utilObj.checkString(classType);
		utilObj.checkObject(bookingObj);
		double amount=aisleWindowCount*200;
		double paidAmount=bookingObj.getAmount()-(aisleWindowCount*200);
		if(num==1)
		{
			num++;
		}
		paidAmount=paidAmount/num;
		
		amount=amount+paidAmount;
		amount=amount-(num*200);
		
		return amount;
	}
	
	public double getAllRefund(BookingDetails bookingObj, int size) throws Exception 
	{
		utilObj.checkObject(bookingObj);
		double amount=bookingObj.getAmount();
		amount=amount-(size*200);
		return amount;
	}

	public void setBookingDetails(int bookingId, BookingDetails bookingObj) throws Exception 
	{
		utilObj.checkObject(bookingObj);
		cacheObj.setBookingDetails(bookingId,bookingObj);
	}
	
	public BookingDetails getBookingDetails(int bookingId)
	{
		BookingDetails bookingObj=cacheObj.getBookingDetails(bookingId);
		return bookingObj;
	}
	
	public List<String> getMealSeats() throws Exception
	{
		Map<Integer,BookingDetails> bookingMap=cacheObj.getBooking();
		List<String> mealList=new ArrayList<>();
		for(Map.Entry<Integer,BookingDetails> newMap : bookingMap.entrySet())
		{
			BookingDetails bookingObj=newMap.getValue();
			if(bookingObj.getMealPreference()==true)
			{
				List<String> seat=bookingObj.getSeatNo();
				for(int i=0;i<seat.size();i++)
				{
					mealList.add(seat.get(i));
				}
			}
		}
		return mealList;
	}
	
	public void removeFilledSeats(String seatNo) throws Exception 
	{
		utilObj.checkString(seatNo);
		
		cacheObj.removeFilledSeats(seatNo);
	}
	
	public void updateSeatDetails(String flightName, String seatNo, SeatDetails seatObj) throws Exception 
	{
		utilObj.checkString(seatNo);
		utilObj.checkString(flightName);
		utilObj.checkObject(seatObj);
		cacheObj.updateSeatDetails(flightName,seatNo,seatObj);
	}

	public void printTicket(BookingDetails bookingObj) throws Exception 
	{
		utilObj.checkObject(bookingObj);
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

	public List<String> getBusinessClass(String flight) throws Exception 
	{
		utilObj.checkString(flight);
		Map<String,SeatDetails> seatMap=cacheObj.getSeatMap(flight);
		List<String> seatNo=new ArrayList<>();
		for(Map.Entry<String, SeatDetails> seat:seatMap.entrySet())
		{
			SeatDetails seatObj=seat.getValue();
			seatNo.add(seatObj.getSeatNo()+" "+seatObj.getClassType()+" "+seatObj.getSeatType());
		}
		return seatNo;
	}

	public void printBookingDetails(int bookingId2) 
	{
		BookingDetails bookingObj=cacheObj.getBookingDetails(bookingId2);
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
		System.out.println("* Booking ID           : "+bookingObj.getBookingId());
		System.out.println("* Source Location      : "+bookingObj.getSource());
		System.out.println("* Destination Location : "+bookingObj.getDestination());
		System.out.println("* Meal Preference      : "+bookingObj.getMealPreference());
		List<String> seatNo=bookingObj.getSeatNo();
		for(int i=0;i<seatNo.size();i++)
		{
			System.out.println("* Seat Number          :"+seatNo.get(i));
		}
		List<PassengerDetails> passengerList=bookingObj.getPassengerList();
		for(int i=0;i<passengerList.size();i++)
		{
			PassengerDetails passengerObj=passengerList.get(i);
			System.out.println("* Passenger Name       :"+passengerObj.getPassengerName());
			System.out.println("* Passenger Age        :"+passengerObj.getPassengerAge());
			System.out.println("* Passenger MobNo      :"+passengerObj.getPassengerNumber());
			System.out.println("* Passenger Address    :"+passengerObj.getPassengerAddress());
		}
		System.out.println("* Booking Amount       : "+bookingObj.getAmount());
		System.out.println("* Flight Name          : "+bookingObj.getFlightName());
		System.out.println("* Total Tickets        : "+bookingObj.getPassengerList().size());
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
	}

}



