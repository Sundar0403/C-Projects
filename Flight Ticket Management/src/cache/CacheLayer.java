package cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import seat.SeatDetails;

public class CacheLayer {
	
	Scanner scan=new Scanner(System.in);
	List<SeatDetails> seatList=new ArrayList<>();
	
	public Map<Integer,SeatDetails> setFlightDetails(String flightName,int seat[])
	{
		System.out.println("Enter the Number of Classes in the Flight :");
		int num=scan.nextInt();
		scan.nextLine();
		for(int i=1;i<=seat[3];i++)
		{
			String className="Business Class";
			int seatNo=1;
			String seatN="";
			int count=0;
			for(int k=0;k<3;k++)
			{
				for(int j=0;j<seat[count];j++)
				{
					SeatDetails seatObj=new SeatDetails();
					seatN=getSeatNo(seatNo,i);
					seatObj.setSeatNo(seatN);
					String seatType="";
					if(j==0 || j==seat[count]-1)
					{
						seatType="Window";
					}
				}
				count++;
			}	
		}
		return null;
	}
	
	public String getSeatNo(int seatNo,int row)
	{
		String seatN="";
		
		seatNo=seatNo+64;
		char ch=(char)seatNo;
		seatN=""+row+ch;
		System.out.println(seatN);
		return seatN;
	}
}
