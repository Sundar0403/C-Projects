package Logic;

import java.util.List;
import java.util.Map;

import booking.OrderDetails;
import cache.CacheLayer;
import customer.CustomerDetails;
import file.FileLayer;

public class FoodDeliveryLogic 
{
	CacheLayer cacheObj=new CacheLayer();
	FileLayer fileObj=new FileLayer();
	
	private int customerId=0;
	private int bookingId=1000;
	
	public int getCustomerId()
	{
		return ++customerId;
	}
	
	public int getBookingId()
	{
		return ++bookingId;
	}

	public void setCustomerDetails(int customerId, CustomerDetails customerObj,String fileName) throws Exception
	{
		Map<Integer,CustomerDetails> customerMap=cacheObj.setCustomerDetails(customerId,customerObj);
		fileObj.writerCustomerDetails(fileName, customerMap);
	}

	public String readDetails(String string) throws Exception 
	{
		String result=fileObj.readDetails(string);
		return result;
	}

	public void setDeliveryDetails(String fileName) throws Exception
	{
		Map<String, Integer> deliveryMap=cacheObj.setDeliveryExecutiveDetails();
		fileObj.setDeliveryDetails(fileName,deliveryMap);
	}

	public void readCustomerDetails(String string) throws Exception
	{
		
	}

	public void readDeliveryDetails(String string) throws Exception
	{
		
	}
	
	public String allotDelivery() throws Exception
	{
		String result="";
		int min=0;
		
		Map<String,Integer> deliveryMap=cacheObj.getDeliveryMap();
		
		for(Map.Entry<String,Integer> newSet : deliveryMap.entrySet())
		{
			if(min>=newSet.getValue())
			{
				result=newSet.getKey();
				break;
			}
			else
			{
				min=newSet.getValue();
			}
		}
		return result;
	}

	public List<String> getRestaurant() throws Exception 
	{
		return cacheObj.getRestaurant();
	}

	public String getPickUpTime(String[] times) 
	{
		int min=Integer.parseInt(times[times.length-1]);
		min+=15;
		String result=""+times[0]+":"+min;
		
		return result;
	}

	public String getDeliveryTime(String[] times) 
	{
		int min=Integer.parseInt(times[times.length-1]);
		min+=45;
		String result=""+times[0]+":"+min;
		
		return result;
	}

	public List<OrderDetails> getOrderDetails(int bookingId) throws Exception
	{
		return cacheObj.getOrderDetails(bookingId);
	}

	public void setOrderDetails(int bookingId, List<OrderDetails> orderList) throws Exception 
	{
		Map<Integer,List<OrderDetails>> orderMap=cacheObj.setOrderDetails(bookingId,orderList);
		fileObj.setOrderDetails("Order.txt",orderMap);
	}
}
