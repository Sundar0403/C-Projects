package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cache.CacheLayer;
import customer.CustomerDetails;
import kart.KartDetails;

public class FileLayer 
{
	CacheLayer cacheObj=new CacheLayer();
	public File createFile(String fileName) throws IOException
	{
		File fileObj=new File(fileName);
		if(fileObj.createNewFile())
		{
			System.out.println("New File is Created :");
		}
		else
		{
			System.out.println("File is Alredy Exists :");
		}
		return fileObj;
	}
	
	public void writeCustomerDetails(String fileName,Map<String,CustomerDetails> customerMap) throws Exception
	{
		File fileObj=createFile(fileName);
		
		try(FileWriter writer=new FileWriter(fileObj);)
		{
			try(BufferedWriter buffered=new BufferedWriter(writer);)
			{
				buffered.write("UserName"+"\t"+"Encrypted Password"+"\t"+"Customer Name"+"\t"+"Mobile No"+"\t"+"Credits"+"\n");
				for(CustomerDetails customerObj : customerMap.values())
				{
					buffered.write(customerObj.getUserName()+"\t\t"+customerObj.getEncryptedPwd()+"\t\t"+customerObj.getCustomerName()+"\t\t"+customerObj.getCustomerMobNo()+"\t\t"+customerObj.getCustomerCredits()+"\n");
				}
			}
		}
		
	}
	
	public void writeKartDetails(String fileName,Map<String,List<KartDetails>> kartMap) throws Exception
	{
		File fileObj=createFile(fileName);
		
		try(FileWriter writer=new FileWriter(fileObj);)
		{
			try(BufferedWriter buffered=new BufferedWriter(writer);)
			{
				buffered.write("Category"+"\t"+"Brand"+"\t"+"Model"+"\t"+"Price"+"\t"+"Stock"+"\t"+"Discount"+"\n");
				for(List<KartDetails> kartList : kartMap.values())
				{
					for(int i=0;i<kartList.size();i++)
					{
						KartDetails kartObj=kartList.get(i);
						buffered.write(kartObj.getCategory()+"\t\t"+kartObj.getBrand()+"\t\t"+kartObj.getModel()+"\t\t"+kartObj.getPrice()+"\t\t"+kartObj.getStock()+"\t\t"+kartObj.getDiscount()+"\n");
					}
				}
			}
		}
		
	}
	
	public void readCustomerFromFile(String fileName) throws Exception
	{
		String result=readCustomerOrKartDetails(fileName);
		
		String arr[]=result.split("\n");
		
		for(int i=0;i<arr.length;i++)
		{
			CustomerDetails customerObj=new CustomerDetails();
			String newArr[]=arr[i].split("\t\t");
			
			String userName=newArr[0];
			customerObj.setUserName(userName);
			
			String encryptPwd=newArr[1];
			customerObj.setEncryptedPwd(encryptPwd);
			
			String name=newArr[2];
			customerObj.setCustomerName(name);
			
			long mobNo=Long.parseLong(newArr[3]);
			customerObj.setCustomerMobNo(mobNo);
			
			int credits=Integer.parseInt(newArr[4]);
			customerObj.setCustomerCredits(credits);
			
			cacheObj.setCustomerDetails(userName, customerObj);
		}
	}
	
	public void readKartFromFile(String fileName) throws Exception
	{
		String result=readCustomerOrKartDetails(fileName);
		
		String arr[]=result.split("\n");
		List<KartDetails> kartList=new ArrayList<>();
		
		for(int i=0;i<arr.length;i++)
		{
			KartDetails kartObj=new KartDetails();
			String newArr[]=arr[i].split("\t\t");
			
			String category=newArr[0];
			kartObj.setCategory(category);
			
			String brand=newArr[1];
			kartObj.setBrand(brand);
			
			String model=newArr[2];
			kartObj.setModel(model);
			
			double price=Double.parseDouble(newArr[3]);
			kartObj.setPrice(price);
			
			int stock=Integer.parseInt(newArr[4]);
			kartObj.setStock(stock);
			
			int discount=Integer.parseInt(newArr[5]);
			kartObj.setDiscount(discount);
			
			kartList.add(kartObj);
			
			cacheObj.setKartDetails(category, kartList);
		}
	}
	
	public String readCustomerOrKartDetails(String fileName) throws Exception
	{
		String result="";
		File fileObj=createFile(fileName);
		
		try(FileReader reader=new FileReader(fileObj);)
		{
			try(BufferedReader buffered=new BufferedReader(reader);)
			{
				buffered.readLine();
				String temp=buffered.readLine();
				result+=temp;
				while(temp!=null)
				{
					temp=buffered.readLine();
					if(temp!=null)
					{
						result+=temp;
					}
				}
			}
		}
		return result;
	}
}
