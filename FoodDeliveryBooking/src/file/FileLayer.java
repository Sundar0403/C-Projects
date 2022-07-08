package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import booking.OrderDetails;
import cache.CacheLayer;
import customer.CustomerDetails;

public class FileLayer 
{
	CacheLayer cacheObj=new CacheLayer();
	
	public File createNewFile(String fileName) throws Exception
	{
		File fileObj=new File(fileName);
		
		if(fileObj.createNewFile())
		{
			System.out.println("New File is Created :");
		}
		else
		{
			System.out.println("File is Already Exisits :");
		}
		return fileObj;
	}
	
	public void readCustomerDetails(String fileName) throws Exception
	{
		String result=readDetails(fileName);
		
		String arr[]=result.split("\n");
		
		for(int i=0;i<arr.length;i++)
		{
			CustomerDetails customerObj=new CustomerDetails();
			String inner[]=arr[0].split("\t\t");
			
			int customerId=Integer.parseInt(inner[0]);
			customerObj.setCustomerId(customerId);
			
			String customerName=inner[1];
			customerObj.setCustomerName(customerName);
			
			String location=inner[2];
			customerObj.setLocation(location);
			
			long mobNo=Long.parseLong(inner[3]);
			customerObj.setMobileNo(mobNo);
			
			cacheObj.setCustomerDetails(customerId, customerObj);
		}
	}
	
	public void writerCustomerDetails(String fileName,Map<Integer,CustomerDetails> customerMap) throws Exception
	{
		File fileObj=createNewFile(fileName);
		
		try(FileWriter writer=new FileWriter(fileObj);)
		{
			try(BufferedWriter buffered=new BufferedWriter(writer);)
			{
				buffered.write("Customer Id"+"\t"+"Customer Name"+"\t"+"Location"+"\t"+"Mobile Number"+"\n");
				
				for(CustomerDetails customerObj : customerMap.values())
				{
					buffered.write(customerObj.getCustomerId()+"\t\t"+customerObj.getCustomerName()+"\t\t"+customerObj.getLocation()+"\t\t"+customerObj.getMobileNo()+"\n");
				}
			}
		}
	}
	
	public String readDetails(String fileName) throws Exception
	{
		File fileObj=createNewFile(fileName);
		
		String result="";
		try(FileReader reader=new FileReader(fileObj);)
		{
			try(BufferedReader buffered=new BufferedReader(reader);)
			{
				buffered.readLine();
				String temp=buffered.readLine();
				result+=temp+"\n";
				while(temp!=null)
				{
					temp=buffered.readLine();
					if(temp!=null)
					{
						result+=temp+"\n";
					}
				}
			}
		}
		return result;
	}
	
	public void readDeliveryDetails(String fileName) throws Exception
	{	
		String result=readDetails(fileName);
		
		String arr[]=result.split("\n");
		
		for(int i=0;i<arr.length;i++)
		{
			String inner[]=arr[0].split("\t\t");
			
			String deliveryExecutive=inner[0];
			
			int amount=Integer.parseInt(inner[1]);
			
			cacheObj.setDelivery(deliveryExecutive, amount);
		}
	}

	public void setDeliveryDetails(String fileName, Map<String, Integer> deliveryMap) throws Exception
	{
		File fileObj=createNewFile(fileName);
		
		try(FileWriter writer=new FileWriter(fileObj);)
		{
			try(BufferedWriter buffered=new BufferedWriter(writer);)
			{
				buffered.write("Delivery"+"\t"+"Amount"+"\n");
				
				for(Map.Entry<String,Integer> delivery : deliveryMap.entrySet())
				{
					buffered.write(delivery.getKey()+"\t\t"+delivery.getValue()+"\n");
				}
			}
		}
	}

	public void setOrderDetails(String fileName, Map<Integer, List<OrderDetails>> orderMap) throws Exception 
	{
		File fileObj=createNewFile(fileName);
		
		try(FileWriter writer=new FileWriter(fileObj);)
		{
			try(BufferedWriter buffered=new BufferedWriter(writer);)
			{
				buffered.write("Delivery"+"\t"+"Amount"+"\n");
				
				for(List<OrderDetails> delivery : orderMap.values())
				{
					for(int i=0;i<delivery.size();i++)
					{
						OrderDetails orderObj=delivery.get(i);
						buffered.write(orderObj.getOrderId()+"\t\t"+orderObj.getDeliveryExecutive()+"\t\t"+orderObj.getPickUpLocation()+"\t\t"+orderObj.getDropLocation()+"\t\t"+orderObj.getPickUpTime()+"\t\t"+orderObj.getDeliveryTime()+"\t\t"+orderObj.getOrderAmount());
					}
				}
			}
		}
	}
}
