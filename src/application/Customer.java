package application;

public class Customer {
	private int customer_id;
	private String customer_name;
	private String phone;
    
    static Customer cust;
    
	public Customer() {
		super();
	}
	
	public Customer(String customer_name,  String phone) {
		super();
		this.customer_name = customer_name;
		this.phone = phone;
	
	}


	

	public int getId() {
		return customer_id;
	}
	
	public void setId(int customer_id) {
		this.customer_id = customer_id;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

}
