package banking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import account.AccountDetails;
import amount.ATMAmountDetails;
import logic.ATMBankingLogic;
import transaction.TransactionDetails;

public class ATMBankingManagement extends Thread{
	
	Map<Double,ATMAmountDetails> amountMap=new HashMap<>();
	ATMBankingLogic logicObj=new ATMBankingLogic();
	Scanner scan=new Scanner(System.in);
	int accountNo=0;
	
	private void getAmountDetails() throws Exception
	{
		amountMap=logicObj.getMap();
		System.out.println("Enter the File Name to Create  :");
		String fileName=scan.nextLine();
		logicObj.writeAmount(fileName,amountMap);
		String newOne=logicObj.readAmount(fileName);
			System.out.println(newOne);
	}
	
	private void setAccountDetails() throws Exception
	{
		AccountDetails accountObj=new AccountDetails();
		int accountNo=logicObj.getAccountNo();
		accountObj.setAccountNo(accountNo);
		
		System.out.println("Enter the Account Holder Name :");
		String accName=scan.nextLine();
		accountObj.setAccountHolderName(accName);
		
		System.out.println("Enter the 4 digit PIN Number:");
		int pinNo=scan.nextInt();
		scan.nextLine();
		accountObj.setPinNumber(pinNo);
		
		System.out.println("Enter the File to Write AccountDetails :");
		String fileName=scan.nextLine();
		
		logicObj.setAccountDetails(accountNo,accountObj,fileName);
	}
	
	private void withdraw() throws Exception
	{	
		boolean flag=false;
		
		while(flag==false)
		{
			System.out.println("Enter the Account No : ");
			int accNo=scan.nextInt();
			scan.nextLine();
			
			System.out.println("Enter the PIN No :");
			int pinNo=scan.nextInt();
			scan.nextLine();
			
			flag=logicObj.getValidate(accNo,pinNo);
			
			if(flag==false)
			{
				System.out.println("Invalid Details are Entered :");
			}
			
			else if(flag==true)
			{
				System.out.println("Enter the Amount to Withdraw :");
				double amount=scan.nextInt();
				scan.nextLine();
				
				AccountDetails accountObj=logicObj.Withdraw(accNo,amount);
				
				logicObj.printWithdrawReceipt(accountObj,amount);
				
				TransactionDetails transObj=new TransactionDetails();
				String fileName=accountObj.getAccountNo()+"_transactions.txt";
				logicObj.setTransactionFileDetails(fileName);
				int transId=logicObj.getTransNo();
				
				transObj.setTransactionId(transId);
				
				accountNo=accountObj.getAccountNo();
				String transType="Rs."+amount+" Withdraw From Account No :"+accountNo;
				transObj.setTransactionType(transType);
				
				transObj.setTransactionAmount(amount);
				
				transObj.setAccountBalance(accountObj.getAccountBalance());
				
				logicObj.setTransactionDetails(transId,transObj);
				
			}
		}
	}
	
	public void run()
	{
		try 
		{
			sleep(5000);
			String fileName=accountNo+"_transactions.txt";
			Map<Integer,TransactionDetails> transMap=logicObj.getTransactionDetails();
			logicObj.setTransactionFile(fileName,transMap);
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private void amountTransfer() throws Exception
	{	
		boolean flag=false;
			
		while(flag==false)
		{
			System.out.println("Enter the Account No : ");
			int accNo=scan.nextInt();
			scan.nextLine();
				
			System.out.println("Enter the PIN No :");
			int pinNo=scan.nextInt();
			scan.nextLine();
				
			flag=logicObj.getValidate(accNo,pinNo);
				
			if(flag==false)
			{
				System.out.println("Invalid Details are Entered :");
			}
			
			else if(flag==true)
			{
				System.out.println("Enter the Receiver Account No : ");
				int receiveNo=scan.nextInt();
				scan.nextLine();
				
				System.out.println("Enter the Amount to Withdraw :");
				double amount=scan.nextInt();
				scan.nextLine();
				
				logicObj.amountTransfer(accNo,receiveNo,amount);
				
				logicObj.printTransferReceipt(accNo,receiveNo,amount);
				
				TransactionDetails transObj=new TransactionDetails();
				String fileName=accNo+"_transactions.txt";
				logicObj.setTransactionFileDetails(fileName);
				int transId=logicObj.getTransNo();
				
				transObj.setTransactionId(transId);
				
				String transType="Rs."+amount+" Transfer From Account No : "+accNo+" To : "+receiveNo;
				transObj.setTransactionType(transType);
				
				transObj.setTransactionAmount(amount);
				
				AccountDetails accountObj=logicObj.getAccount(accNo);
				
				transObj.setAccountBalance(accountObj.getAccountBalance());
				
				logicObj.setTransactionDetails(transId,transObj);
				
				Map<Integer,TransactionDetails> transMap=logicObj.getTransactionDetails();
				logicObj.setTransactionFile(fileName,transMap);
				
				TransactionDetails receiveObj=new TransactionDetails();
				String filesName=receiveNo+"_transactions.txt";
				logicObj.setTransactionFileDetails(filesName);
				
				int receiveId=logicObj.getTransNo();
				
				receiveObj.setTransactionId(receiveId);
				
				String transactionType="Rs."+amount+" Received From Account No : "+accNo+" To : "+receiveNo;
				receiveObj.setTransactionType(transactionType);
				
				receiveObj.setTransactionAmount(amount);
				
				AccountDetails accountObj1=logicObj.getAccount(receiveNo);
				
				receiveObj.setAccountBalance(accountObj1.getAccountBalance());
				
				logicObj.setTransactionDetails(transId,receiveObj);
				
				Map<Integer,TransactionDetails> transsMap=logicObj.getTransactionDetails();
				logicObj.setTransactionFile(filesName,transsMap);
			}
		}
	}
	
	private void getAmountFromFile() throws Exception
	{
		System.out.println("Enter the File Name to Get Amount Details :");
		String fileName=scan.nextLine();
		
		logicObj.readAmountDetails(fileName);
	}
	
	private void getAccountFromFile() throws Exception
	{
		System.out.println("Enter the File Name to Get Account Details :");
		String fileName=scan.nextLine();
		
		logicObj.setAccountFileDetails(fileName);
	}
	
	private void getTransactionFromFile() throws Exception
	{
		System.out.println("Enter the File Name to Get Account Details :");
		String fileName=scan.nextLine();
		
		logicObj.setTransactionFileDetails(fileName);
	}
	
	private void setAmountValues() throws Exception
	{
		logicObj.setAmountValues();
	}
	
	public static void main(String args[]) throws Exception
	{
		ATMBankingManagement bankingObj=new ATMBankingManagement();

		Scanner scan=new Scanner(System.in);
		int choice=0;
		
		boolean flag=true;
		
		while(flag)
		{
			System.out.println("--------------------------- WELCOME TO ATM BANKING -------------------------------");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("-*-*-* 1 . READ AMOUNT FROM FILE *-*-*-*");
			System.out.println("-*-*-* 2 . READ ACCOUNT FROM FILE *-*-*-");
			System.out.println("-*-*-* 3 . SET ACCOUNT DETAILS *-*-*-*-*");
			System.out.println("-*-*-* 4 . SET ATM AMOUNT DETAILS -*-*-*");
			System.out.println("-*-*-* 5 . WITHDRAW AMOUNT -*-*-*-*-**-*");
			System.out.println("-*-*-* 6 . TRANSFER AMOUNT -*-*-*-*-**-*");
			System.out.println("-*-*-* 7 . TRANSACTION DETAILS -*-*-*-*-");
			System.out.println("-*-*-* 8 . EXIT PORTAL -*-*-*-*-*-*-**-*");
			
			System.out.println();
			System.out.println();
			
			try
			{
				System.out.println("Enter Your Choice :");
				choice=scan.nextInt();
				scan.nextLine();
			}
			catch(Exception e)
			{
				System.out.println("Exception Occured : "+e.getMessage());
				e.printStackTrace();
			}
			
			switch(choice)
			{
				case 1:
						try
						{
							bankingObj.getAmountFromFile();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
						
				case 2:
						try
						{
							bankingObj.getAccountFromFile();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
						
				case 3:
						try
						{
							bankingObj.setAccountDetails();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
						
				case 4:
						try
						{
							bankingObj.setAmountValues();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
						
				case 5:
						try
						{
							bankingObj.withdraw();
							bankingObj.start();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
					
				case 6:
						try
						{
							bankingObj.amountTransfer();
							bankingObj.start();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
						
				case 7:
						try
						{
							bankingObj.getTransactionFromFile();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
						break;
						
				case 8:
						try
						{
							flag=false;
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured : "+e.getMessage());
							e.printStackTrace();
						}
			}
		}
		//bankingObj.getAmountDetails();
		//bankingObj.setAccountDetails();
	}

	
}
