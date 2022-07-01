package logic;

import java.util.List;

import cache.CacheLayer;
import customer.CustomerDetails;
import kart.KartDetails;

public class ZKartLogic 
{
	CacheLayer cacheObj=new CacheLayer();
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
	public void setKartDetails(String category, List<KartDetails> kartList) throws Exception
	{		
		cacheObj.setKartDetails(category,kartList);
	}
	public void setCustomerDetails(String userName, CustomerDetails customerObj) throws Exception
	{
		cacheObj.setCustomerDetails(userName,customerObj);
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


}
