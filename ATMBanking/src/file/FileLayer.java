package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import account.AccountDetails;
import amount.ATMAmountDetails;

public class FileLayer 
{
	
	public File createFile(String fileName) throws Exception
	{
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
		File newFile=createFile(fileName);
		
		try(FileWriter writer=new FileWriter(newFile,true);)
		{
			writer.write("Denomintaion"+"\t"+"Number"+"\t\t"+"Total"+"\n");
			for(ATMAmountDetails amountObj : amountMap.values())
			{
				writer.write(amountObj.getAmount()+"\t\t"+amountObj.getNoteNos()+"\t\t"+amountObj.getTotal()+"\n");
			}	
		}
	}
	
	public List<String> readAmount(String fileName) throws Exception
	{
		List<String> newList=new ArrayList<>();
		File newFile=createFile(fileName);
		
		try(FileReader reader=new FileReader(newFile);)
		{
			try(BufferedReader read=new BufferedReader(reader);)
			{
			String temp=read.readLine();
			   temp=read.readLine();
			   newList.add(temp);
				while(temp!=null)
				{
					temp=read.readLine();
					newList.add(temp);
				}
			}
		}
		newList.remove(null);
		return newList;
	}

	public void setAccountDetails(String fileName, Map<Integer, AccountDetails> accountMap) throws Exception 
	{
		File fileObj=createFile(fileName);
		try(FileWriter fileWriter = new FileWriter(fileObj,true);)
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
		File fileObj=new File(fileName);
		String result="";
		
		try(FileReader read=new FileReader(fileObj);)
		{
			try(BufferedReader reader = new BufferedReader(read);)
			{
				String temp=reader.readLine();
				temp=reader.readLine();
				result=result+temp;
				while(temp!=null)
				{
					temp=reader.readLine();
					result=result+temp;
					result=result+"\n";
				}
			}
		}
		return result;
	}
}
