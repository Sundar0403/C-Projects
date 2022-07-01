package customer;

public class CustomerDetails 
{
	String userName;
	String customerName;
	String encryptedPwd;
	long customerMobNo;
	int customerCredits=0;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getEncryptedPwd() {
		return encryptedPwd;
	}
	
	public void setEncryptedPwd(String encryptedPwd) {
		this.encryptedPwd = encryptedPwd;
	}
	
	public long getCustomerMobNo() {
		return customerMobNo;
	}
	
	public void setCustomerMobNo(long customerMobNo) {
		this.customerMobNo = customerMobNo;
	}
	
	public int getCustomerCredits() {
		return customerCredits;
	}
	
	public void setCustomerCredits(int customerCredits) {
		this.customerCredits = customerCredits;
	}
	
	public boolean passwordCheck(String password) 
	{
		if(this.encryptedPwd.equals(password))
		{
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "CustomerDetails [userName=" + userName + ", customerName=" + customerName + ", encryptedPwd="
				+ encryptedPwd + ", customerMobNo=" + customerMobNo + ", customerCredits=" + customerCredits + "]";
	}

	
	
	
}
