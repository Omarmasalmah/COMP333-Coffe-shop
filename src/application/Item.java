package application;


public class Item {
	
	private int item_id;
	private String item_name;
	private int quantity;
	private String discription;
	private double origen_price;
	private double sale_price;
	private int cat_id;
	private String size;

	
	
	public Item() {
		super();
	}

	

	public Item(String item_name, int item_id, int quantity, String discription, double origen_price, double sale_price, int cat_id, String size) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.quantity = quantity;
		this.discription = discription;
		this.origen_price = origen_price;		
		this.sale_price = sale_price;
		this.cat_id = cat_id;
		this.size = size;

	}


	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public int getitem_id() {
		return item_id;
	}

	public void setitem_id(int item_id) {
		this.item_id = item_id;
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

	public double getOrigen_price() {
		return origen_price;
	}

	public void setOrigen_price(double origen_price) {
		this.origen_price = origen_price;
	}



	public double getSale_price() {
		return sale_price;
	}



	public void setSale_price(double sale_price) {
		this.sale_price = sale_price;
	}



	public int getCat_id() {
		return cat_id;
	}



	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}



	public String getSize() {
		return size;
	}



	public void setSize(String size) {
		this.size = size;
	}
	
	

}
