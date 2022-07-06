package zkart;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import admin.AdminDetails;
import customer.CustomerDetails;
import kart.KartDetails;
import logic.ZKartLogic;
import purchase.PurchaseDetails;

public class ZKart 
{
	ZKartLogic logicObj=new ZKartLogic();
	Scanner scan=new Scanner(System.in);
	boolean flag=false;
	String role="";
	
	private void setCustomerDetails() throws Exception
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
		
		System.out.println("Enter the File to Write CustomerDetails :");
		String fileName=scan.nextLine();
		
		logicObj.setCustomerDetails(userName,customerObj,fileName);
	
	}
	
	private void setKartDetails() throws Exception
	{
		if(role.equals("admin"))
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
		
			System.out.println("Enter the fileName to Write Kart Details :");
			String fileName=scan.nextLine();
		
			logicObj.setKartDetails(category,kartList,fileName);
		}
		else
		{
			System.out.println("Only Admins are Allowed to Access it :");
		}
	}
	public void login() throws Exception
	{
		
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
	
	private void purchase() throws Exception
	{
		if(flag==true)
		{
			KartDetails kartObj=new KartDetails();
			List<PurchaseDetails> purchase=new ArrayList<>();
		
			int invoiceNo=logicObj.getInvoiceNo();
		
			double totalAmount=0;
		
			long invoiceDate=System.currentTimeMillis();
		
		
			boolean flag=true;
		
			while(flag==true)
			{
				PurchaseDetails purchaseObj=new PurchaseDetails();
				purchaseObj.setInvoiceNo(invoiceNo);
				purchaseObj.setInvoiceDate(invoiceDate);
			
				System.out.println("Enter the Category Name :");
				String category=scan.nextLine();
				purchaseObj.setCategory(category);
		
				List<KartDetails> kartList=logicObj.getKartDetails(category);
		
				System.out.println("Enter the Brand Name :");
				String brand=scan.nextLine();
				purchaseObj.setBrand(brand);
		
				for(int i=0;i<kartList.size();i++)
				{
					if(kartList.get(i).getBrand().equals(brand))
					{
						kartObj=kartList.get(i);
					}
				}
		
				if(kartObj.getStock()!=0)
				{
					int stock=kartObj.getStock()-1;
					kartObj.setStock(stock);

					logicObj.setKartDetails(category, kartList, "Kart.txt");
					String model=kartObj.getModel();
					purchaseObj.setModel(model);
					double price=kartObj.getPrice();
					purchaseObj.setPrice(price);
					price=logicObj.getPrice(price,kartObj.getDiscount());
					totalAmount=totalAmount+price;
					
				}
			
				purchase.add(purchaseObj);
				System.out.println();
				System.out.println("Are You Willing to Add Another Item in the Cart :");
				System.out.println("1.YES\n 2.NO");
				String cond=scan.nextLine();
				if(cond.equals("YES"))
				{
					flag=true;
				}
				else
				{
					flag=false;
				}
			}
			System.out.println("The Total Amount You Need to Pay is : "+totalAmount);
		
			System.out.println("Are You Willing to Use Your Customer Credits :");
			System.out.println("1.YES \n2.No");
			String cond=scan.nextLine();
			int creditsUsed=0;
			int creditsGained=0;
			if(cond.equals("YES"))
			{
				System.out.println("Enter the Username :");
				String userName=scan.nextLine();
				CustomerDetails customerObj=logicObj.getCustomerDetails(userName);
				creditsUsed=customerObj.getCustomerCredits();
				totalAmount=totalAmount-creditsUsed;
				System.out.println("The Total Amount You Need to Pay is : "+totalAmount);
				System.out.println("Enter the Amount Payable :");
				double payable=scan.nextDouble();
				creditsGained=(int)(payable/1000)*20;
				customerObj.setCustomerCredits(creditsGained);
				logicObj.setCustomerDetails(userName,customerObj,"Customer.txt");
				scan.nextLine();
			
			}
			else
			{
				System.out.println("Enter the Username :");
				String userName=scan.nextLine();
				CustomerDetails customerObj=logicObj.getCustomerDetails(userName);
				System.out.println("Enter the Amount Payable :");
				double payable=scan.nextDouble();
				creditsGained=(int)(payable/1000)*20;
				int creditsRemains=customerObj.getCustomerCredits();
				customerObj.setCustomerCredits(creditsRemains+creditsGained);
				logicObj.setCustomerDetails(userName,customerObj,"Customer.txt");
				scan.nextLine();
			}
			System.out.println("Enter the File Name to Store Purchase Details :");
			String fileName=scan.nextLine();
			logicObj.setPurchaseDetails(invoiceNo,purchase,fileName);
			logicObj.generateInvoice(purchase,totalAmount,creditsUsed,creditsGained);
		}
		else
		{
			throw new Exception("Unauthorised Access to the Portal :");
		}
	}
	
	private void readCustomerDetails() throws Exception
	{
		if(role.equals("admin"))
		{
			logicObj.readCustomerDetails("Customer.txt");
		}
		else
		{
			System.out.println("Only Admins Are Allowd to Access it :");
		}
	}
	
	private void readKartDetails() throws Exception
	{
		if(role.equals("admin"))
		{
			logicObj.readKartDetails("Kart.txt");
		}
		else
		{
			System.out.println("Only Admins Are Allowd to Access it :");
		}
	}
	
	private void readPurchaseDetails() throws Exception
	{
		if(role.equals("admin"))
		{
			logicObj.readPurchaseDetails("Purchase.txt");
			logicObj.setInvoiceNo();
		}
		else
		{
			System.out.println("Only Admins Are Allowd to Access it :");
		}
	}
	
	private void getThresholdStockDetails() throws Exception
	{
		if(role.equals("admin"))
		{
			System.out.println("Enter the Category Name :");
			String category=scan.nextLine();
		
			List<KartDetails> kartList=logicObj.getKartDetails(category);
		
			for(int i=0;i<kartList.size();i++)
			{
				KartDetails kartObj=kartList.get(i);
			
				if(kartObj.getStock()<=10)
				{
					System.out.println("* Brand : "+kartObj.getBrand());
					System.out.println("* Model : "+kartObj.getModel());
					System.out.println();
				}
			}
		}
		else
		{
			System.out.println("Only Admins Are Allowd to Access it :");
		}
	}
	
	private void updateStockInKart() throws Exception
	{
		if(role.equals("admin"))
		{
			System.out.println("Enter the Category Name :");
			String category=scan.nextLine();
			int stock=0;
		
			List<KartDetails> kartList=logicObj.getKartDetails(category);
		
			for(int i=0;i<kartList.size();i++)
			{
				KartDetails kartObj=kartList.get(i);
			
				stock=kartObj.getStock()+15;
				kartObj.setStock(stock);
			}
			logicObj.setKartDetails(category, kartList, category);
		}
		else
		{
			System.out.println("Only Admins Are Allowd to Access it :");
		}
	}
	
	private void logout() throws Exception
	{
		flag=false;
		System.out.println("Logout Done Successfully :");
	}
	
	public static void main(String args[]) throws Exception
	{
		ZKart zkartObj=new ZKart();
		Scanner scan=new Scanner(System.in);
		int choice=0;
		boolean flag=true;
		
		while(flag==true)
		{
			System.out.println();
			System.out.println("-*-*-* 1 . READ CUSTOMER DETAILS -*-*-*-*-*");
			System.out.println("-*-*-* 2 . READ KART DETAILS -*-*-*-*-*-*-*");
			System.out.println("-*-*-* 3 . SIGN UP WITH USER DETAILS -*-*-*");
			System.out.println("-*-*-* 4 . LOGIN TO THE KART -*-*-*-*-*-*-*");
			System.out.println("-*-*-* 5 . ADD THE ITEMS TO THE KART -*-*-*");
			System.out.println("-*-*-* 6 . PURCHASE THE ITEMS IN THE KART -");
			System.out.println("-*-*-* 7 . READ PURCHASE DETAILS -*-*-*-*-*");
			System.out.println("-*-*-* 8 . THRESHOLD STOCK DETAILS -*-*-*-*");
			System.out.println("-*-*-* 9 . UPDATE THE STOCK DETAILS *-*-*-*");
			System.out.println("-*-*-* 10 . LOGOUT FROM THE KART *-*-*-*-*-");
			System.out.println("-*-*-* 11 . EXIT ZKART PORTAL -*-*-*-*-*-*-");
			System.out.println();
			System.out.println();
			try
			{
				System.out.println("Enter your Choice :");
				choice=scan.nextInt();
				scan.nextLine();
			}
			catch(Exception e)
			{
				System.out.println("Integer Input Only : "+e.getMessage());
				break;
				//e.printStackTrace();
			}
			
			switch(choice)
			{
				case 1 :
						try
						{
							zkartObj.readCustomerDetails();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
				case 2 :
						try
						{
							zkartObj.readKartDetails();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
				case 3 :
						try
						{
							zkartObj.setCustomerDetails();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
				case 4 :
						try
						{
							zkartObj.login();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
				case 5 :
						try
						{
							zkartObj.setKartDetails();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
				case 6 :
						try
						{
							zkartObj.purchase();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
				case 7 :
						try
						{
							zkartObj.readPurchaseDetails();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
						
				case 8 :
						try
						{
							zkartObj.getThresholdStockDetails();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
						
				case 9 :
						try
						{
							zkartObj.updateStockInKart();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
						
				case 10:
						try
						{
							zkartObj.logout();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
						
				case 11 :
						try
						{
							flag=false;
							System.out.println("Thank You Very Much !!!");
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
			}
		}
		scan.close();
	}
}
