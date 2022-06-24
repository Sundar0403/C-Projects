package logic;

import java.util.List;
import java.util.Map;

import account.AccountDetails;
import amount.ATMAmountDetails;
import cache.CacheLayer;
import file.FileLayer;

public class ATMBankingLogic 
{
	CacheLayer cacheObj=new CacheLayer();
	FileLayer fileObj=new FileLayer();
	private int accountNo;
	
	public int getAccountNo()
	{
		return ++accountNo;
	}

	public Map<Double, ATMAmountDetails> getMap() throws Exception
	{
		Map<Double, ATMAmountDetails> amountMap=cacheObj.getMap();
		return amountMap;
	}

	public void createFile(String fileName) throws Exception 
	{
		fileObj.createFile(fileName);
	}

	public void writeAmount(String fileName, Map<Double, ATMAmountDetails> amountMap) throws Exception 
	{
		fileObj.writeAmount(fileName, amountMap);
	}

	public List<String> readAmount(String fileName) throws Exception 
	{
		List<String> amount=fileObj.readAmount(fileName);
		return amount;
	}

	public void setAccountDetails(int accountNo, AccountDetails accountObj, String fileName) throws Exception 
	{
		Map<Integer,AccountDetails> accountMap= cacheObj.setAccountDetails(accountNo,accountObj);
		System.out.println(accountMap);
		fileObj.setAccountDetails(fileName,accountMap);
	}

	public String getAccountDetails(String fileName) throws Exception 
	{
		String result=fileObj.readAccountDetails(getAccountDetails(fileName));
		return null;
	}
}
