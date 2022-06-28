package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import account.AccountDetails;
import amount.ATMAmountDetails;
import logic.ATMBankingLogic;
import transaction.TransactionDetails;
import utility.UtilityClass;

public class FileLayer 
{
	UtilityClass utilObj=new UtilityClass();
	public File createFile(String fileName) throws Exception
	{
		utilObj.checkString(fileName);
		File fileObj=new File(fileName);
		
		if(fileObj.createNewFile())
		{
			System.out.println("New File is Created :");
		}
		else
		{
			System.out.println("File is Already Exists :");
		}
		return fileObj;
	}
	
	public void writeAmount(String fileName,Map<Double,ATMAmountDetails> amountMap) throws Exception
	{
		utilObj.checkString(fileName);
		File newFile=createFile(fileName);
		
		try(FileWriter writer=new FileWriter(newFile);)
		{
			writer.write("Denomintaion"+"\t"+"Number"+"\t\t"+"Total"+"\n");
			for(ATMAmountDetails amountObj : amountMap.values())
			{
				writer.write(amountObj.getAmount()+"\t\t"+amountObj.getNoteNos()+"\t\t"+amountObj.getTotal()+"\n");
			}	
		}
	}
	
	public String readAmount(String fileName) throws Exception
	{
		utilObj.checkString(fileName);
		File newFile=createFile(fileName);
		String result="";
		try(FileReader reader=new FileReader(newFile);)
		{
			try(BufferedReader read=new BufferedReader(reader);)
			{
				read.readLine();
			   String temp=read.readLine();
			   result=result+temp;
			   result=result+"\n";
				while(temp!=null)
				{
					temp=read.readLine();
					if(temp!=null)
					{
						result=result+temp;
						//System.out.println(result);
						result=result+"\n";
					}	
				}
			}
		}
		return result;
	}

	public void setAccountDetails(String fileName, Map<Integer, AccountDetails> accountMap) throws Exception 
	{
		utilObj.checkString(fileName);
		File fileObj=createFile(fileName);
		try(FileWriter fileWriter = new FileWriter(fileObj);)
		{
			try(BufferedWriter writer = new BufferedWriter(fileWriter);)
			{
				writer.write("AccountNo"+"\t"+"AccountHolderName"+"\t"+"PIN No"+"\t"+"AccountBalance"+"\n");
				
				for(AccountDetails accountObj:accountMap.values())
				{
					writer.write(accountObj.getAccountNo()+"\t\t"+accountObj.getAccountHolderName()+"\t\t"+accountObj.getPinNumber()+"\t\t"+accountObj.getAccountBalance()+"\n");
				}
			}
		}
	}
	
	public String readAccountDetails(String fileName) throws Exception
	{
		utilObj.checkString(fileName);
		
		File fileObj=new File(fileName);
		String result="";
		
		try(FileReader read=new FileReader(fileObj);)
		{
			try(BufferedReader reader = new BufferedReader(read);)
			{
				String temp=reader.readLine();
				temp=reader.readLine();
				result=result+temp;
				result=result+"\n";
				while(temp!=null)
				{
					temp=reader.readLine();
					if(temp!=null)
					{
						result=result+temp;
						result=result+"\n";
					}	
				}
			}
		}
		return result;
	}

	public void setTransactionDetails(String fileName, Map<Integer, TransactionDetails> transMap) throws Exception 
	{
		utilObj.checkString(fileName);
		File fileObj=createFile(fileName);
		try(FileWriter fileWriter = new FileWriter(fileObj);)
		{
			try(BufferedWriter writer = new BufferedWriter(fileWriter);)
			{
				writer.write("Transaction Id"+"\t"+"Transaction Type"+"\t"+"Transaction Amount"+"\t"+"Account Balance"+"\n");
				
				for(TransactionDetails transObj:transMap.values())
				{
					writer.write(transObj.getTransactionId()+"\t\t"+transObj.getTransactionType()+"\t\t"+transObj.getTransactionAmount()+"\t\t"+transObj.getAccountBalance()+"\n");
				}
			}
		}
	}
}
