package kart;

public class KartDetails 
{
	String category;
	String brand;
	String model;
	double price;
	int stock=0;
	int discount;
	
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
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getDiscount() {
		return discount;
	}
	
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	
	@Override
	public String toString() {
		return "KartDetails [category=" + category + ", brand=" + brand + ", model=" + model + ", price=" + price
				+ ", stock=" + stock + ", discount=" + discount + "]";
	}
}
