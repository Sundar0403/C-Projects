package purchase;

public class PurchaseDetails 
{
	int invoiceNo;
	long invoiceDate;
	String category;
	String brand;
	String model;
	double price;
	
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "PurchaseDetails [invoiceNo=" + invoiceNo + ", invoiceDate=" + invoiceDate + ", category=" + category
				+ ", brand=" + brand + ", model=" + model + ", price=" + price + "]";
	}
	
	
}
