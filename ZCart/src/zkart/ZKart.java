package zkart;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import admin.AdminDetails;
import customer.CustomerDetails;
import kart.KartDetails;
import logic.ZKartLogic;

public class ZKart 
{
	ZKartLogic logicObj=new ZKartLogic();
	Scanner scan=new Scanner(System.in);
	String role="";
	
	public void setCustomerDetails() throws Exception
	{
		CustomerDetails customerObj=new CustomerDetails();
		
		System.out.println("Enter the Username :");
		String userName=scan.nextLine();
		customerObj.setUserName(userName);
		
		System.out.println("Enter the Customer Name :");
		String name=scan.nextLine();
		customerObj.setCustomerName(name);
		
		System.out.println("Enter the Password :");
		String password=scan.nextLine();
		password=logicObj.getEncryptPassword(password);
		customerObj.setEncryptedPwd(password);
		
		System.out.println("Enter the Customer Mobile No :");
		long mobNo=scan.nextLong();
		scan.nextLine();
		customerObj.setCustomerMobNo(mobNo);
		
		logicObj.setCustomerDetails(userName,customerObj);
	
	}
	
	public void setKartDetails() throws Exception
	{
		KartDetails kartObj=new KartDetails();
		
		System.out.println("Enter the Category of the Product :");
		String category=scan.nextLine();
		kartObj.setCategory(category);
		
		List<KartDetails> kartList=logicObj.getKartDetails(category);
		if(kartList==null)
		{
			kartList=new ArrayList<>();
		}
		
		System.out.println("Enter the Brand of the Category :");
		String brand=scan.nextLine();
		kartObj.setBrand(brand);
		
		System.out.println("Enter the Model :");
		String model=scan.nextLine();
		kartObj.setModel(model);
		
		System.out.println("Enter the Price :");
		double price=scan.nextDouble();
		scan.nextLine();
		kartObj.setPrice(price);
		
		System.out.println("Enter the Stock Available :");
		int stock=scan.nextInt();
		scan.nextLine();
		kartObj.setStock(stock);
		
		System.out.println("Enter the Discount :");
		int discount=scan.nextInt();
		scan.nextLine();
		kartObj.setDiscount(discount);
		
		kartList.add(kartObj);
		
		logicObj.setKartDetails(category,kartList);
	}
	
	public void login() throws Exception
	{
		boolean flag=false;
		String userName="";
		String password="";
		while(flag==false)
		{
			System.out.println("Enter the Username :");
			userName=scan.nextLine();
		
			CustomerDetails customerObj=logicObj.getCustomerDetails(userName);
		
			System.out.println("Enter the Password :");
			password=scan.nextLine();
			AdminDetails adminObj=new AdminDetails();
			
			if(adminObj.getAdminUserName().equals(userName) && adminObj.getAdminPassword().equals(password))
			{
				flag=true;
				role="admin";
				System.out.println("-*-*-* Admin Login Done Successfully -*-*-*");
			}
			else
			{
				password=logicObj.getEncryptPassword(password);
		
				flag=customerObj.passwordCheck(password);
				
				System.out.println("-*-*-* User Login Done Successfully -*-*-*");
			}	
		}
		
		
		
	}
	
	public void purchase() throws Exception
	{
		
	}
	
	public static void main(String args[]) throws Exception
	{
		ZKart zkartObj=new ZKart();
		Scanner scan=new Scanner(System.in);
		zkartObj.setKartDetails();
	}
}
