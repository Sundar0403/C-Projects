package cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import account.AccountDetails;
import amount.ATMAmountDetails;

public class CacheLayer 
{
	
	Map<Double,ATMAmountDetails> amountMap=new HashMap<>();
	Map<Integer,AccountDetails> accountMap=new HashMap<>();
	Scanner scan=new Scanner(System.in);

	public CacheLayer()
	{
		System.out.println("Enter the Number of Different Type of Note :");
		int num=scan.nextInt();
		scan.nextLine();
		for(int i=0;i<num;i++)
		{
			ATMAmountDetails obj=new ATMAmountDetails();
			System.out.println("Enter the Amount :");
			double amount=scan.nextDouble();
			scan.nextLine();
			obj.setAmount(amount);
			System.out.println("Enter the Note Numbers :");
			int nos=scan.nextInt();
			scan.nextLine();
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
		return accountMap;
	}
}
