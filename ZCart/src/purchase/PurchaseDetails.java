package purchase;

import java.util.List;

public class PurchaseDetails 
{
	int invoiceNo;
	long invoiceDate;
	List<String> category;
	List<String>  brand;
	List<String> model;
	List<String> price;
	
	public int getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public long getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(long invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public List<String> getCategory() {
		return category;
	}
	public void setCategory(List<String> category) {
		this.category = category;
	}
	public List<String> getBrand() {
		return brand;
	}
	public void setBrand(List<String> brand) {
		this.brand = brand;
	}
	public List<String> getModel() {
		return model;
	}
	public void setModel(List<String> model) {
		this.model = model;
	}
	public List<String> getPrice() {
		return price;
	}
	public void setPrice(List<String> price) {
		this.price = price;
	}
	
	
	
	
}
