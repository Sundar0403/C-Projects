package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cache.CacheLayer;
import customer.CustomerDetails;
import kart.KartDetails;
import purchase.PurchaseDetails;

public class FileLayer {
	CacheLayer cacheObj = new CacheLayer();

	public File createFile(String fileName) throws IOException {
		File fileObj = new File(fileName);
		if (fileObj.createNewFile()) {
			System.out.println("New File is Created :");
		} else {
			System.out.println("File is Alredy Exists :");
		}
		return fileObj;
	}

	public void writeCustomerDetails(String fileName, Map<String, CustomerDetails> customerMap) throws Exception {
		File fileObj = createFile(fileName);

		try (FileWriter writer = new FileWriter(fileObj);) {
			try (BufferedWriter buffered = new BufferedWriter(writer);) {
				buffered.write("UserName" + "\t\t" + "Encrypted Password" + "\t\t" + "Customer Name" + "\t\t"
						+ "Mobile No" + "\t\t" + "Credits" + "\n");
				for (CustomerDetails customerObj : customerMap.values()) {
					buffered.write(customerObj.getUserName() + "\t\t" + customerObj.getEncryptedPwd() + "\t\t"
							+ customerObj.getCustomerName() + "\t\t" + customerObj.getCustomerMobNo() + "\t\t"
							+ customerObj.getCustomerCredits() + "\n");
				}
			}
		}

	}

	public void writeKartDetails(String fileName, Map<String, List<KartDetails>> kartMap) throws Exception {
		File fileObj = createFile(fileName);

		try (FileWriter writer = new FileWriter(fileObj);) {
			try (BufferedWriter buffered = new BufferedWriter(writer);) {
				buffered.write("Category" + "\t" + "Brand" + "\t" + "Model" + "\t" + "Price" + "\t" + "Stock" + "\t"
						+ "Discount" + "\n");
				for (List<KartDetails> kartList : kartMap.values()) {
					for (int i = 0; i < kartList.size(); i++) {
						KartDetails kartObj = kartList.get(i);
						buffered.write(kartObj.getCategory() + "\t\t" + kartObj.getBrand() + "\t\t" + kartObj.getModel()
								+ "\t\t" + kartObj.getPrice() + "\t\t" + kartObj.getStock() + "\t\t"
								+ kartObj.getDiscount() + "\n");
					}
				}
			}
		}

	}

	public void writePurchaseDetails(String fileName, Map<Integer, List<PurchaseDetails>> purchaseMap)
			throws Exception {
		File fileObj = createFile(fileName);

		try (FileWriter writer = new FileWriter(fileObj);) {
			try (BufferedWriter buffered = new BufferedWriter(writer);) {
				buffered.write("Invoice No" + "\t" + "Invoice Date" + "\t" + "Category" + "\t" + "Brand" + "\t"
						+ "Model" + "\t" + "Price" + "\n");
				for (List<PurchaseDetails> purchaseList : purchaseMap.values()) {
					for (int i = 0; i < purchaseList.size(); i++) {
						PurchaseDetails purchaseObj = purchaseList.get(i);
						SimpleDateFormat s = new SimpleDateFormat("dd mm yyyy hh:mm:ss");
						buffered.write(purchaseObj.getInvoiceNo() + "\t\t" + s.format(purchaseObj.getInvoiceDate())
								+ "\t\t" + purchaseObj.getCategory() + "\t\t" + purchaseObj.getBrand() + "\t\t"
								+ purchaseObj.getModel() + "\t\t" + purchaseObj.getPrice() + "\n");
					}
				}
			}
		}
	}

	public void readCustomerFromFile(String fileName) throws Exception {
		String result = readCustomerOrKartDetails(fileName);

		System.out.println(result);

		String arr[] = result.split("\n");

		for (int i = 0; i < arr.length; i++) {
			CustomerDetails customerObj = new CustomerDetails();
			String newArr[] = arr[i].split("\t\t");

			String userName = newArr[0];
			customerObj.setUserName(userName);

			String encryptPwd = newArr[1];
			customerObj.setEncryptedPwd(encryptPwd);

			String name = newArr[2];
			customerObj.setCustomerName(name);

			long mobNo = Long.parseLong(newArr[3]);
			customerObj.setCustomerMobNo(mobNo);

			int credits = Integer.parseInt(newArr[4]);
			customerObj.setCustomerCredits(credits);

			cacheObj.setCustomerDetails(userName, customerObj, fileName);
		}
	}

	public void readPurchaseFromFile(String fileName) throws Exception {
		String result = readCustomerOrKartDetails(fileName);
		
		System.out.println(result);

		String arr[] = result.split("\n");
		List<PurchaseDetails> purchaseList = new ArrayList<>();

		for (int i = 0; i < arr.length; i++) {
			PurchaseDetails purchaseObj = new PurchaseDetails();
			String newArr[] = arr[i].split("\t\t");

			int invoiceNo = Integer.parseInt(newArr[0]);
			purchaseObj.setInvoiceNo(invoiceNo);

			String date = newArr[1];
			SimpleDateFormat s = new SimpleDateFormat("dd mm yyyy hh:mm:ss");
			Date d = s.parse(date);
			long invoiceTime = d.getTime();
			System.out.println(invoiceTime);
			purchaseObj.setInvoiceDate(invoiceTime);

			String category = newArr[2];
			purchaseObj.setCategory(category);

			String brand = newArr[3];
			purchaseObj.setBrand(brand);

			String model = newArr[4];
			purchaseObj.setModel(model);

			double price = Double.parseDouble(newArr[5]);
			purchaseObj.setPrice(price);

			purchaseList.add(purchaseObj);

			cacheObj.setPurchaseDetails(invoiceNo, purchaseList);
		}
	}

	public void readKartFromFile(String fileName) throws Exception {
		String result = readCustomerOrKartDetails(fileName);
		
		System.out.println(result);

		String arr[] = result.split("\n");
		String firstCategory="";
		List<KartDetails> kartList=new ArrayList<>();; 

		for (int i = 0; i < arr.length; i++) {
			
			KartDetails kartObj = new KartDetails();
			String newArr[] = arr[i].split("\t\t");

			String category = newArr[0];
			if(!firstCategory.equals(category))
			{
				firstCategory=category;
				kartList=new ArrayList<>();
			}
			kartObj.setCategory(category);

			String brand = newArr[1];
			kartObj.setBrand(brand);

			String model = newArr[2];
			kartObj.setModel(model);

			double price = Double.parseDouble(newArr[3]);
			kartObj.setPrice(price);

			int stock = Integer.parseInt(newArr[4]);
			kartObj.setStock(stock);

			int discount = Integer.parseInt(newArr[5]);
			kartObj.setDiscount(discount);

			kartList.add(kartObj);

			cacheObj.setKartDetails(category, kartList, fileName);
		}
	}

	public String readCustomerOrKartDetails(String fileName) throws Exception {
		String result = "";
		File fileObj = createFile(fileName);

		try (FileReader reader = new FileReader(fileObj);) {
			try (BufferedReader buffered = new BufferedReader(reader);) {
				buffered.readLine();
				String temp = buffered.readLine();
				result += temp + "\n";
				while (temp != null) {
					temp = buffered.readLine();
					if (temp != null) {
						result += temp + "\n";
					}
				}
			}
		}
		return result;
	}
}
