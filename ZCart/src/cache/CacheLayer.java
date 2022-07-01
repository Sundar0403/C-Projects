package cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import customer.CustomerDetails;
import kart.KartDetails;

public class CacheLayer 
{
	Map<String,CustomerDetails> customerMap=new HashMap<>();
	Map<String,List<KartDetails>> kartMap=new HashMap<>();
	
	public void setKartDetails(String category, List<KartDetails> kartList) throws Exception
	{
		kartMap.put(category, kartList);
		System.out.println(kartMap);
	}

	public void setCustomerDetails(String userName, CustomerDetails customerObj) throws Exception
	{
		customerMap.put(userName, customerObj);
	}

	public CustomerDetails getCustomerDetails(String userName) 
	{
		CustomerDetails customerObj=customerMap.get(userName);
		return customerObj;
	}

	public List<KartDetails> getKartDetails(String category) 
	{
		return kartMap.get(category);
	}
	
}
