package Logic;

import java.util.Map;

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
}
