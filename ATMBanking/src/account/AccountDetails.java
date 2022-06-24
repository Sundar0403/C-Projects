package account;

public class AccountDetails 
{
	int accountNo;
	String accountHolderName;
	int pinNumber;
	double accountBalance;
	
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	public int getPinNumber() {
		return pinNumber;
	}
	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance() {
		this.accountBalance = 2000;
	}
	
	@Override
	public String toString() {
		return "AccountDetails [accountNo=" + accountNo + ", accountHolderName=" + accountHolderName + ", pinNumber="
				+ pinNumber + ", accountBalance=" + accountBalance + "]";
	}
	
	
}
