package logic;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import account.AccountDetails;
import amount.ATMAmountDetails;
import cache.CacheLayer;
import file.FileLayer;
import transaction.TransactionDetails;
import utility.UtilityClass;

public class ATMBankingLogic 
{
	UtilityClass utilObj=new UtilityClass();
	CacheLayer cacheObj=new CacheLayer();
	FileLayer fileObj=new FileLayer();
	private int accountNo;
	private int transNo;
	
	public int getAccountNo()
	{
		return ++accountNo;
	}
	
	public int getTransNo()
	{
		return ++transNo;
	}

	public Map<Double, ATMAmountDetails> getMap() throws Exception
	{
		Map<Double, ATMAmountDetails> amountMap=cacheObj.getMap();
		return amountMap;
	}

	public void createFile(String fileName) throws Exception 
	{
		utilObj.checkString(fileName);
		fileObj.createFile(fileName);
	}

	public void writeAmount(String fileName, Map<Double, ATMAmountDetails> amountMap) throws Exception 
	{
		utilObj.checkString(fileName);
		
		fileObj.writeAmount(fileName, amountMap);
	}

	public String readAmount(String fileName) throws Exception 
	{
		utilObj.checkString(fileName);
		String amount=fileObj.readAmount(fileName);
		return amount;
	}

	public void setAccountDetails(int accountNo, AccountDetails accountObj, String fileName) throws Exception 
	{
		utilObj.checkObject(accountObj);
		utilObj.checkString(fileName);
		
		Map<Integer,AccountDetails> accountMap= cacheObj.setAccountDetails(accountNo,accountObj);
		System.out.println(accountMap);
		fileObj.setAccountDetails(fileName,accountMap);
	}

	public String getAccountDetails(String fileName) throws Exception 
	{
		utilObj.checkString(fileName);
		String result=fileObj.readAccountDetails(getAccountDetails(fileName));
		return null;
	}
	
	public void setAmountDetails(Double amount,ATMAmountDetails amountObj) throws Exception
	{
		utilObj.checkObject(amountObj);
		cacheObj.setAmountDetails(amount,amountObj);
	}
	
	public void setTransactionDetails(int transId, TransactionDetails transObj) throws Exception 
	{
		utilObj.checkObject(transObj);
		cacheObj.setTransactionDetails(transId,transObj);
	}
	
	public Map<Integer, TransactionDetails> getTransactionDetails() throws Exception 
	{
		Map<Integer,TransactionDetails> transMap=cacheObj.getTransactionMap();
		return transMap;
	}
	
	public AccountDetails getAccount(int accNo) throws Exception
	{
		AccountDetails accountObj=cacheObj.getAccountDetails(accNo);
		return accountObj;
	}

	public boolean getValidate(int accNo, int pinNo) 
	{
		AccountDetails accountObj=cacheObj.getAccountDetails(accNo);
		
		if(accountObj.getPinNumber()==pinNo)
		{
			return true;
		}
		return false;
	}

	public AccountDetails Withdraw(int accNo, double amount) throws Exception
	{
		AccountDetails accountObj=cacheObj.getAccountDetails(accNo);
		
		double balance=accountObj.getAccountBalance();
		
		if(amount<100 && amount>10000)
		{
			throw new Exception("Withdraw Amount Must be Between 100 and 10000 :");
		}
		
		if(balance<amount)
		{
			throw new Exception("Insufficient Balance in Your Account to Withdraw :");
		}
		
		else
		{
			
			Map<Double,ATMAmountDetails> amountMap=cacheObj.getMap();
			if(amount>3000 && amount<5000)
			{
				amount=amount-2000.0;
				
				ATMAmountDetails amountObj=amountMap.get(2000.0);
				
				if(amountObj.getNoteNos()==0)
				{
					throw new Exception("Unable to Process Your Request Now :");
				}
				
				int notes=amountObj.getNoteNos()-1;
				amountObj.setNoteNos(notes);
				
				amountObj.setTotal();
				
				cacheObj.setAmountDetails(2000.0, amountObj);
				
				ATMAmountDetails amountObj2=amountMap.get(500.0);
				if(amountObj2.getNoteNos()==0)
				{
					throw new Exception("Unable to Process Your Request Now :");
				}
				
				double temp=amount-500;
				amount=amount-temp;
				int no=(int)temp/500;
				
				int fiveHundred=amountObj2.getNoteNos()-no;
				amountObj2.setNoteNos(fiveHundred);
				
				amountObj2.setTotal();
				
				cacheObj.setAmountDetails(500.0, amountObj2);
				
				ATMAmountDetails amountObj1=amountMap.get(100.0);
				if(amountObj1.getNoteNos()==0)
				{
					throw new Exception("Unable to Process Your Request Now :");
				}
				
				int oneHund=(int)amount/100;
				
				int hundred=amountObj1.getNoteNos()-oneHund;
				
				amountObj1.setNoteNos(hundred);
				
				amountObj1.setTotal();
				
				cacheObj.setAmountDetails(100.0, amountObj1);
			}
			
			else if(amount>5000)
			{
				amount=amount-4000.0;
				
				ATMAmountDetails amountObj=amountMap.get(2000.0);
				if(amountObj.getNoteNos()==0)
				{
					throw new Exception("Unable to Process Your Request Now :");
				}
				
				int notes=amountObj.getNoteNos()-2;
				amountObj.setNoteNos(notes);
				
				amountObj.setTotal();
				
				cacheObj.setAmountDetails(2000.0, amountObj);
				
				ATMAmountDetails amountObj2=amountMap.get(500.0);
				if(amountObj2.getNoteNos()==0)
				{
					throw new Exception("Unable to Process Your Request Now :");
				}
				
				amount=amount-500;
				int no=(int)amount/500;
				
				int fiveHundred=amountObj2.getNoteNos()-no;
				amountObj2.setNoteNos(fiveHundred);
				
				
				amountObj2.setTotal();
				
				cacheObj.setAmountDetails(500.0, amountObj2);
				
				ATMAmountDetails amountObj1=amountMap.get(100.0);
				
				if(amountObj1.getNoteNos()==0)
				{
					throw new Exception("Unable to Process Your Request Now :");
				}
				
				int oneHund=(int)amount/100;
				
				int hundred=amountObj1.getNoteNos()-oneHund;
				
				amountObj1.setNoteNos(hundred);
				
				amountObj1.setTotal();
				
				cacheObj.setAmountDetails(100.0, amountObj1);
			}
			
			else if(amount<3000 && amount>1000)
			{
				ATMAmountDetails amountObj2=amountMap.get(500.0);
				if(amountObj2.getNoteNos()==0)
				{
					throw new Exception("Unable to Process Your Request Now :");
				}
				
				double temp=amount-500;
				amount=amount-temp;
				int no=(int)temp/500;
				
				int fiveHundred=amountObj2.getNoteNos()-no;
				amountObj2.setNoteNos(fiveHundred);
				
				amountObj2.setTotal();
				
				cacheObj.setAmountDetails(500.0, amountObj2);
				
				ATMAmountDetails amountObj1=amountMap.get(100.0);
				if(amountObj1.getNoteNos()==0)
				{
					throw new Exception("Unable to Process Your Request Now :");
				}
				
				
				int oneHund=(int)amount/100;
				
				int hundred=amountObj1.getNoteNos()-oneHund;
				
				amountObj1.setNoteNos(hundred);
				
				amountObj1.setTotal();
				
				cacheObj.setAmountDetails(100.0, amountObj1);
			}
			
			else if(amount<1000)
			{
				ATMAmountDetails amountObj1=amountMap.get(100.0);
				if(amountObj1.getNoteNos()==0)
				{
					throw new Exception("Unable to Process Your Request Now :");
				}
				
				
				int oneHund=(int)amount/100;
				
				int hundred=amountObj1.getNoteNos()-oneHund;
				
				amountObj1.setNoteNos(hundred);
				
				amountObj1.setTotal();
				
				cacheObj.setAmountDetails(100.0, amountObj1);
			}
			
			balance=balance-amount;
			accountObj.setAccountBalance(balance);
			
			Map<Integer,AccountDetails> accountMap=cacheObj.setAccountDetails(accNo,accountObj);
			
			fileObj.setAccountDetails("Account.txt",accountMap);
			
			fileObj.writeAmount("Amount.txt",amountMap);
		
		}
		
		return accountObj;
	}
	
	public void readAmountDetails(String fileName) throws Exception
	{
		utilObj.checkString(fileName);
		String result=fileObj.readAmount(fileName);
		
		System.out.println(result);
		
		String arr[]=result.split("\n");
		
		String newArr[]=new String[arr.length];
		
		for(int i=0;i<newArr.length;i++)
		{
			newArr[i]=arr[i];
		}
		
		for(int i=0;i<newArr.length;i++)
		{
			String inner[]=newArr[i].split("\t\t");
			
			ATMAmountDetails amountObj=new ATMAmountDetails();
			
			double amount=Double.parseDouble(inner[0]);
			amountObj.setAmount(amount);
			
			int notes=Integer.parseInt(inner[1]);
			amountObj.setNoteNos(notes);
			
			amountObj.setTotal();
			
			cacheObj.setAmountDetails(amount,amountObj);
		}
	}
	
	public void setAccountFileDetails(String fileName) throws Exception
	{
		utilObj.checkString(fileName);
		String result=fileObj.readAccountDetails(fileName);
		
		System.out.println(result);
		
		String arr[]=result.split("\n");
		
		String newArr[]=new String[arr.length];
		
		for(int i=0;i<newArr.length;i++)
		{
			newArr[i]=arr[i];
		}
		
		for(int i=0;i<newArr.length;i++)
		{
			String inner[]=newArr[i].split("\t\t");
			
			AccountDetails accountObj=new AccountDetails();
			
			int accountNo=Integer.parseInt(inner[0]);
			
			this.accountNo=accountNo;
			accountObj.setAccountNo(accountNo);
			
			String name=inner[1];
			accountObj.setAccountHolderName(name);
			
			int pinNo=Integer.parseInt(inner[2]);
			accountObj.setPinNumber(pinNo);
			
			double balance=Double.parseDouble(inner[3]);
			accountObj.setAccountBalance(balance);
			
			cacheObj.setAccountDetails(accountNo,accountObj);
		}
	}
	
	public void setTransactionFileDetails(String fileName) throws Exception
	{
		utilObj.checkString(fileName);
		String result=fileObj.readAccountDetails(fileName);
		
		System.out.println(result);
		
		String arr[]=result.split("\n");
		
		String newArr[]=new String[arr.length];
		
		for(int i=0;i<newArr.length;i++)
		{
			newArr[i]=arr[i];
		}
		
		for(int i=0;i<newArr.length;i++)
		{
			String inner[]=newArr[i].split("\t\t");
			
			TransactionDetails transObj=new TransactionDetails();
			
			int transNo=Integer.parseInt(inner[0]);
			
			this.transNo=transNo;
			transObj.setTransactionId(transNo);
			
			String type=inner[1];
			transObj.setTransactionType(type);
			
			double amount=Double.parseDouble(inner[2]);
			transObj.setTransactionAmount(amount);
			
			double balance=Double.parseDouble(inner[3]);
			transObj.setAccountBalance(balance);
			
			cacheObj.setTransactionDetails(transNo,transObj);
		}
	}
	
	public void setAccountFromFile(int accountNo2, AccountDetails accountObj) throws Exception
	{
		utilObj.checkObject(accountObj);
		cacheObj.setAccountDetails(accountNo2, accountObj);
	}

	public void amountTransfer(int accNo, int receiveNo, double amount) throws Exception 
	{
		if(accNo==receiveNo)
		{
			throw new Exception("Invalid Details are Entered for Amount Transfer :");
		}
		AccountDetails accountObj=cacheObj.getAccountDetails(accNo);
		
		double balance=accountObj.getAccountBalance()-amount;
		accountObj.setAccountBalance(balance);
		
		cacheObj.setAccountDetails(accNo, accountObj);
		
		AccountDetails receiverObj=cacheObj.getAccountDetails(receiveNo);
		
		double receiveBalance=receiverObj.getAccountBalance()+amount;
		receiverObj.setAccountBalance(receiveBalance);
		
		Map<Integer,AccountDetails> accountMap=cacheObj.setAccountDetails(accNo, accountObj);
		
		fileObj.setAccountDetails("Account.txt", accountMap);
	}

	public void printWithdrawReceipt(AccountDetails accountObj,double amount) throws Exception
	{
		utilObj.checkObject(accountObj);
		System.out.println("-*-*-* WITHDRAW RECEIPT -*-*-*");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
		System.out.println("* Account No         :"+accountObj.getAccountNo());
		System.out.println("* Account Holder Name:"+accountObj.getAccountHolderName());
		System.out.println("* Withdraw Amount    :"+amount);
		System.out.println("* Account Balance    :"+accountObj.getAccountBalance());
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
		System.out.println();
		System.out.println();
		System.out.println("Amount Withdraw Done Successfully :");
	}

	public void setAmountValues() throws Exception 
	{
		cacheObj.setAmountValues();
		Map<Double,ATMAmountDetails> amountMap=cacheObj.getMap();
		writeAmount("Amount.txt",amountMap);
	}

	public void printTransferReceipt(int accNo, int receiveNo, double amount) 
	{
		System.out.println("-*-*-* TRANSFER RECEIPT -*-*-*");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
		System.out.println("* Sender Account No         :"+accNo);
		System.out.println("* Receiver Account No       :"+receiveNo);
		System.out.println("* Transfer Amount           :"+amount);
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
		System.out.println();
		System.out.println();
		System.out.println("Amount Transfer Done Successfully :");
	}

	public void setTransactionFile(String fileName, Map<Integer, TransactionDetails> transMap) throws Exception 
	{
		utilObj.checkString(fileName);
		fileObj.setTransactionDetails(fileName, transMap);
	}

}



