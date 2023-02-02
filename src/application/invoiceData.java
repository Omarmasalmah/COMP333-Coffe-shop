package application;

public class invoiceData {

	private int order_id;
	private String order_date;
	private int quantity;
	private double sale_price;
	private int item_id;
	private int itemCat;
	private String itemName;
	private int emp_id;

	public invoiceData() {
		super();
	}

	public invoiceData(int order_id, int quantity, double sale_price, int item_id, int itemCat, String itemName, int emp_id, String order_date) {
		super();
		this.order_id = order_id;
		this.order_date = order_date;
		this.quantity = quantity;
		this.sale_price = sale_price;
		this.item_id = item_id;
		this.itemCat = itemCat;
		this.itemName = itemName;
		this.emp_id = emp_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSale_price() {
		return sale_price;
	}

	public void setSale_price(double sale_price) {
		this.sale_price = sale_price;
	}


	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getItemCat() {
		return itemCat;
	}

	public void setItemCat(int itemCat) {
		this.itemCat = itemCat;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}



	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	@Override
	public String toString() {
		return "invoiceData [order_id=" + order_id + ", item_id=" + item_id + ", quantity=" + quantity 
				+ ", itemCat=" + itemCat + ", itemName=" + itemName + ", sale_price=" + sale_price 
				+ ", emp_id=" + emp_id + "]";
	}

}