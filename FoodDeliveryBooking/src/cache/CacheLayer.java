package cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import customer.CustomerDetails;

public class CacheLayer {
	
	Scanner scan=new Scanner(System.in);
	private Map<Integer,CustomerDetails> customerMap=new HashMap<>();
	private static Map<String,Integer> deliveryMap=new HashMap<>();
	private List<String> restaurant=new ArrayList<>();
	
	public Map<String,Integer> setDeliveryExecutiveDetails() throws Exception
	{
		System.out.println("Enter the Number of Delivery Executives :");
		int num=scan.nextInt();
		
		for(int i=0;i<num;i++)
		{
			deliveryMap.put("DE"+(i+1),0);
		}
		System.out.println(deliveryMap.toString());
		return deliveryMap;
	}
	
	public void setDelivery(String deliveryExecutive,int amount) throws Exception
	{
		deliveryMap.put(deliveryExecutive,amount);
	}
	
	public void setRestaurant() throws Exception
	{
		restaurant.add("A");
		restaurant.add("B");
		restaurant.add("C");
		restaurant.add("D");
		restaurant.add("E");
	}
	
	public Map<Integer, CustomerDetails> setCustomerDetails(int customerId, CustomerDetails customerObj) throws Exception 
	{
		customerMap.put(customerId, customerObj);
		return customerMap;
	}
	
	public Map<String, Integer> getDeliveryMap() throws Exception
	{
		return deliveryMap;
	}

}
