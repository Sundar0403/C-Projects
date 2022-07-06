package logic;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cache.CacheLayer;
import customer.CustomerDetails;
import file.FileLayer;
import kart.KartDetails;
import purchase.PurchaseDetails;

public class ZKartLogic 
{
	CacheLayer cacheObj=new CacheLayer();
	FileLayer fileObj=new FileLayer();
	private int invoiceNo=114400;
	
	public int getInvoiceNo()
	{
		return ++invoiceNo;
	}
	
	public String getEncryptPassword(String password) 
	{
		String encryptPwd="";
		for(int i=0;i<password.length();i++)
		{
			int value=password.charAt(i)+1;
			char ch=(char)value;
			if(password.charAt(i)=='Z')
			{
				ch='A';
			}
			else if(password.charAt(i)=='z')
			{
				ch='a';
			}
			else if(password.charAt(i)=='9')
			{
				ch='0';
			}
			encryptPwd+=ch;
		}
		
		return encryptPwd;
	}
	public void setKartDetails(String category, List<KartDetails> kartList,String fileName) throws Exception
	{		
		Map<String,List<KartDetails>> kartMap=cacheObj.setKartDetails(category,kartList,fileName);
		fileObj.writeKartDetails(fileName, kartMap);
	}
	public void setCustomerDetails(String userName, CustomerDetails customerObj, String fileName) throws Exception
	{
		Map<String,CustomerDetails> customerMap=cacheObj.setCustomerDetails(userName,customerObj,fileName);
		fileObj.writeCustomerDetails(fileName, customerMap);
	}
	
	public CustomerDetails getCustomerDetails(String userName) 
	{
		CustomerDetails customerObj=cacheObj.getCustomerDetails(userName);
		return customerObj;
	}
	public List<KartDetails> getKartDetails(String category) 
	{
		List<KartDetails> newList=cacheObj.getKartDetails(category);
		return newList;
	}

	public void setPurchaseDetails(int invoiceNo, List<PurchaseDetails> purchase, String fileName) throws Exception 
	{
		Map<Integer,List<PurchaseDetails>> purchaseMap=cacheObj.setPurchaseDetails(invoiceNo,purchase);
		fileObj.writePurchaseDetails(fileName,purchaseMap);
	}
	
	public void setInvoiceNo() throws Exception
	{
		Map<Integer,List<PurchaseDetails>> purchase=cacheObj.getInvoiceNo();
		for(int invoiceNo : purchase.keySet() )
		{
			this.invoiceNo=invoiceNo;
		}
	}

	public void generateInvoice(List<PurchaseDetails> purchase, double totalAmount, int creditsUsed,int creditsGained) 
	{
		System.out.println("-*-*-* INVOICE DETAILS -*-*-*");
		System.out.println();
		System.out.println();
		System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
			System.out.println("* Invoice No : "+purchase.get(0).getInvoiceNo());
			System.out.println("* InvoiceDate: "+new Date(purchase.get(0).getInvoiceDate()));
			System.out.println();
		
		for(int i=0;i<purchase.size();i++)
		{
			System.out.println("* Product "+(i+1)+" :");
			System.out.println("* 1.Category : "+purchase.get(i).getCategory());
			System.out.println("* 2.Brand    : "+purchase.get(i).getBrand());
			System.out.println("* 3.Model    : "+purchase.get(i).getModel());
			System.out.println("* 4.Price    : "+purchase.get(i).getPrice());
			System.out.println();
		}
			System.out.println("* TotalAmount: "+totalAmount);
			System.out.println("* CreditsUsed: "+creditsUsed);
			System.out.println("* CreditsGain: "+creditsGained);
			
			System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
	}

	public double getPrice(double price, int discount) 
	{
		double amount;
		amount=price*(discount)/100;
		price=price-amount;
		return price;
	}

	public void readCustomerDetails(String fileName) throws Exception 
	{
		fileObj.readCustomerFromFile(fileName);
	}

	public void readKartDetails(String fileName) throws Exception 
	{
		fileObj.readKartFromFile(fileName);
	}

	public void readPurchaseDetails(String fileName) throws Exception 
	{
		fileObj.readPurchaseFromFile(fileName);
	}


}
