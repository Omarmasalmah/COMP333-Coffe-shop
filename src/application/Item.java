package application;


public class Item {
	

	private int item_id;
	private String item_name;
	private double sale_price;
	private double origen_price;
	private int quantity;
	private String discription;
	private String size;
	private int cat_id;
	static Item it;
	
	
	public Item() {
		super();
	}
	public Item(int item_id, String item_name, double sale_price, double origen_price, int quantity, String discription,
			String size, int cat_id) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.sale_price = sale_price;
		this.origen_price = origen_price;
		this.quantity = quantity;
		this.discription = discription;
		this.size = size;
		this.cat_id = cat_id;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public double getSale_price() {
		return sale_price;
	}
	public void setSale_price(double sale_price) {
		this.sale_price = sale_price;
	}
	public double getOrigen_price() {
		return origen_price;
	}
	public void setOrigen_price(double origen_price) {
		this.origen_price = origen_price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getCat_id() {
		return cat_id;
	}
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	
	

	
	
	

}