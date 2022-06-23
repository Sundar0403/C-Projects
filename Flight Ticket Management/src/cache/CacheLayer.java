package cache;

import check.Utility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import booking.BookingDetails;
import seat.SeatDetails;

public class CacheLayer {
	
	Scanner scan=new Scanner(System.in);
	List<SeatDetails> emptyList=new ArrayList<>();
	Map<String,Map<String,SeatDetails>> seatMap;
	Map<Integer,BookingDetails> bookingMap=new HashMap<>();
	Map<String,SeatDetails> filled=new HashMap<>();
	Utility utilObj=new Utility();
	
	public void setFlightDetails(String flightName,int seat[]) throws Exception
	{
		/*System.out.println("Enter the Number of Classes in the Flight :");
		int num=scan.nextInt();
		scan.nextLine();*/
		utilObj.checkString(flightName);
		
		getSeatDetails(flightName,"Business Class",seat[3],seat,0,1);
		
		System.out.println("Is the Flight is Only Business Class Flight or Not :");
		System.out.println("1.YES /n 2.NO");
		String cond=scan.nextLine();
		if(!cond.equals("YES"))
		{
			getSeatDetails(flightName,"Economy Class",seat[7],seat,4,13);
		}
		
	}
	
	public Map<String,SeatDetails> getSeatDetails(String flightName) throws Exception
	{
		utilObj.checkString(flightName);
		return seatMap.get(flightName);
	}
	
	public SeatDetails getSeat(String flightName,String seatNo) throws Exception
	{
		utilObj.checkString(seatNo);
		utilObj.checkString(flightName);
		return seatMap.get(flightName).get(seatNo);
	}
	
	public void removeSeatDetails(String flightName,String seatNo) throws Exception
	{
		utilObj.checkString(seatNo);
		utilObj.checkString(seatNo);
		Map<String,SeatDetails> seats=seatMap.get(flightName);
		seats.remove(seatNo);
	}
	
	public void setFilled(String seatNo,SeatDetails seatObj) throws Exception
	{
		utilObj.checkString(seatNo);
		utilObj.checkObject(seatObj);
		filled.put(seatNo, seatObj);
	}
	
	public void setFilledSeats(String seatNo,SeatDetails seatObj) throws Exception
	{
		utilObj.checkObject(seatObj);
		utilObj.checkString(seatNo);
		filled.put(seatNo, seatObj);
	}
	
	public SeatDetails getFilledSeats(String seatNo) throws Exception
	{
		utilObj.checkString(seatNo);
		return filled.get(seatNo);
	}
	
	public void setBookingDetails(int bookingId, BookingDetails bookingObj) throws Exception 
	{
		utilObj.checkObject(bookingObj);
		bookingMap.put(bookingId, bookingObj);
	}
	
	public Map<Integer,BookingDetails> getBooking() throws Exception
	{
		return bookingMap;
	}
	
	public BookingDetails getBookingDetails(int bookingId)
	{
		return bookingMap.get(bookingId);
	}
	
	public void removeFilledSeats(String seatNo) throws Exception 
	{
		utilObj.checkString(seatNo);
		filled.remove(seatNo);
	}
	
	public void updateSeatDetails(String flightName, String seatNo, SeatDetails seatObj) throws Exception 
	{
		utilObj.checkString(seatNo);
		utilObj.checkString(flightName);
		utilObj.checkObject(seatObj);
		Map<String,SeatDetails> emptyMap=seatMap.get(flightName);
		emptyMap.put(seatNo, seatObj);
	}
	
	public void removeBookingDetails(int bookingId, String flightName) throws Exception 
	{
		utilObj.checkString(flightName);
		bookingMap.remove(bookingId);
	}
	
	public void getSeatDetails(String flightName,String className,int row,int[] seat,int num,int start) throws Exception
	{
		utilObj.checkString(className);
		utilObj.checkString(className);
		for(int i=1;i<=row;i++)
		{
			int seatNo=1;
			String seatN="";
			int cases=0;
			int a=start++;
			//int count=num;
			for(int k=0;k<3;k++)
			{
				if(cases==0)
				{
					for(int j=0;j<seat[num];j++)
					{
						SeatDetails seatObj=new SeatDetails();
						seatObj.setClassType(className);
						seatN=getSeatNo(seatNo++,a);
						seatObj.setSeatNo(seatN);
						seatObj.setFlightName(flightName);
						String seatType="";
						if(j==0)
						{
							seatType="Window";
							seatObj.setSeatType(seatType);
							seatObj.setAisleOrWindow(true);
							
							if(seatMap==null)
							{
								seatMap=new HashMap<>();
								
							}
							Map<String,SeatDetails> innerMap=seatMap.get(flightName);
							if(innerMap==null)
							{
								innerMap=new HashMap<>();
								
							}
							seatMap.put(flightName,innerMap);
							innerMap.put(seatN,seatObj);
							
						}
						else if(j==seat[num]-1)
						{
							seatType="Aisle";
							seatObj.setSeatType(seatType);
							seatObj.setAisleOrWindow(true);
							if(seatMap==null)
							{
								seatMap=new HashMap<>();
							}
							Map<String,SeatDetails> innerMap=seatMap.get(flightName);
							if(innerMap==null)
							{
								innerMap=new HashMap<>();
								
							}
							seatMap.put(flightName,innerMap);
							innerMap.put(seatN,seatObj);
							
						}
						else
						{
							seatType="Middle";
							seatObj.setSeatType(seatType);
							seatObj.setAisleOrWindow(false);
							if(seatMap==null)
							{
								seatMap=new HashMap<>();
							}
							Map<String,SeatDetails> innerMap=seatMap.get(flightName);
							
							if(innerMap==null)
							{
								innerMap=new HashMap<>();
								
							}
							seatMap.put(flightName,innerMap);
							innerMap.put(seatN,seatObj);
						}
						
						
						

					}
				
				}
				if(cases==1)
				{
					for(int j=0;j<seat[num+1];j++)
					{
						SeatDetails seatObj=new SeatDetails();
						seatObj.setClassType(className);
						seatN=getSeatNo(seatNo++,a);
						seatObj.setSeatNo(seatN);
						seatObj.setFlightName(flightName);
						String seatType="";
						if(j==0 || j==seat[num]-1)
						{
							seatType="Aisle";
							seatObj.setSeatType(seatType);
							seatObj.setAisleOrWindow(true);
							Map<String,SeatDetails> innerMap=seatMap.get(flightName);
							if(seatMap==null)
							{
								seatMap=new HashMap<>();
							}
							if(innerMap==null)
							{
								innerMap=new HashMap<>();
								
							}
							seatMap.put(flightName,innerMap);
							innerMap.put(seatN,seatObj);
							
						}
						else
						{
							seatType="Middle";
							seatObj.setSeatType(seatType);
							seatObj.setAisleOrWindow(false);
							Map<String,SeatDetails> innerMap=seatMap.get(flightName);
							if(seatMap==null)
							{
								seatMap=new HashMap<>();
							}
							if(innerMap==null)
							{
								innerMap=new HashMap<>();
								
							}
							seatMap.put(flightName,innerMap);
							innerMap.put(seatN,seatObj);
							
						}

					}
				}
				
				if(cases==2)
				{
					for(int j=0;j<seat[num+2];j++)
					{
						SeatDetails seatObj=new SeatDetails();
						seatObj.setClassType(className);
						seatN=getSeatNo(seatNo++,a);
						seatObj.setSeatNo(seatN);
						seatObj.setFlightName(flightName);
						String seatType="";
						if(j==0)
						{
							seatType="Aisle";
							seatObj.setSeatType(seatType);
							seatObj.setAisleOrWindow(true);
							
							Map<String,SeatDetails> innerMap=seatMap.get(flightName);
							if(seatMap==null)
							{
								seatMap=new HashMap<>();
								
							}
							if(innerMap==null)
							{
								innerMap=new HashMap<>();
								
							}
							seatMap.put(flightName,innerMap);
							innerMap.put(seatN,seatObj);
						}
						if(j==seat[num]-1)
						{
							seatType="Window";
							seatObj.setSeatType(seatType);
							seatObj.setAisleOrWindow(true);
							
							Map<String,SeatDetails> innerMap=seatMap.get(flightName);
							if(seatMap==null)
							{
								seatMap=new HashMap<>();
								
							}
							if(innerMap==null)
							{
								innerMap=new HashMap<>();
								
							}
							seatMap.put(flightName,innerMap);
							innerMap.put(seatN,seatObj);
							
						}
						else
						{
							seatType="Middle";
							seatObj.setSeatType(seatType);
							seatObj.setAisleOrWindow(false);
							
							Map<String,SeatDetails> innerMap=seatMap.get(flightName);
							if(seatMap==null)
							{
								seatMap=new HashMap<>();
								
							}
							if(innerMap==null)
							{
								innerMap=new HashMap<>();
								
							}
							seatMap.put(flightName,innerMap);
							innerMap.put(seatN,seatObj);
						}
						
					}
				}
				cases++;
			}	
		}
	}
	
	public String getSeatNo(int seatNo,int row)
	{
		String seatN="";
		
		seatNo=seatNo+64;
		char ch=(char)seatNo;
		seatN=""+row+ch;
		return seatN;
	}

	public Map<String, SeatDetails> getSeatMap(String flight) 
	{
		return seatMap.get(flight);
	}


	
}
