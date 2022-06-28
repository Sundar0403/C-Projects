package cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import account.AccountDetails;
import amount.ATMAmountDetails;
import transaction.TransactionDetails;

public class CacheLayer 
{
	
	Map<Double,ATMAmountDetails> amountMap=new HashMap<>();
	Map<Integer,AccountDetails> accountMap=new HashMap<>();
	Map<Integer,TransactionDetails> transactionMap=new HashMap<>();
	
	Scanner scan=new Scanner(System.in);

	public void setAmountValues()
	{
		System.out.println("Enter the Number of Different Type of Note :");
		int num=scan.nextInt();
		scan.nextLine();
		for(int i=0;i<num;i++)
		{
			
			System.out.println("Enter the Amount :");
			double amount=scan.nextDouble();
			
			scan.nextLine();
			ATMAmountDetails obj=amountMap.get(amount);
			obj.setAmount(amount);
			System.out.println("Enter the Note Numbers :");
			int nos=scan.nextInt();
			scan.nextLine();
			nos=nos+obj.getNoteNos();
			obj.setNoteNos(nos);
			obj.setTotal();
			amountMap.put(amount, obj);
		}
	}
	
	public Map<Double,ATMAmountDetails> getMap()
	{
		return amountMap;
	}

	public Map<Integer,AccountDetails> setAccountDetails(int accountNo, AccountDetails accountObj) 
	{
		accountMap.put(accountNo, accountObj);
		//System.out.println(accountMap);
		return accountMap;
	}
	
	public void setAmountDetails(Double amount,ATMAmountDetails amountObj)
	{
		amountMap.put(amount, amountObj);
	}

	public AccountDetails getAccountDetails(int accNo) 
	{
		AccountDetails accountObj=accountMap.get(accNo);
		return accountObj;
	}

	public void setTransactionDetails(int transId, TransactionDetails transObj) 
	{
		transactionMap.put(transId, transObj);
	}
	
	public Map<Integer,TransactionDetails> getTransactionMap()
	{
		return transactionMap;
	}

}
