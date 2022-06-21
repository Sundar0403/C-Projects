package logic;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import cache.CacheLayer;
import file.FileLayer;
import seat.SeatDetails;

public class FlightTicketLogic 
{
	FileLayer newFile=new FileLayer();
	CacheLayer cacheObj=new CacheLayer();
	int id=0;
	int bookingId=1000;
	
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

	public Map<Integer,SeatDetails> setSeatDetails(String flightName,int arr[]) 
	{
		Map<Integer,SeatDetails> newMap=cacheObj.setFlightDetails(flightName,arr);
		return newMap;
	}

}
