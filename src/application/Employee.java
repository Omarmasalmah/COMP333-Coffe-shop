package application;

public class employee {
	private int emp_id;
	private String employee_name;
	private String birthday;
	private String emp_phone;
	private String emp_password;
	private String date_of_employment;
	private double amount_paid;
    static employee emd;
	
	public employee() {
		super();
	}
	public employee(int emp_id, String employee_name, String birthday, String emp_phone, String emp_password,
			String date_of_employment, double amount_paid) {
		super();
		this.emp_id = emp_id;
		this.employee_name = employee_name;
		this.birthday = birthday;
		this.emp_phone = emp_phone;
		this.emp_password = emp_password;
		this.date_of_employment = date_of_employment;
		this.amount_paid = amount_paid;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmp_phone() {
		return emp_phone;
	}
	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}
	public String getEmp_password() {
		return emp_password;
	}
	public void setEmp_password(String emp_password) {
		this.emp_password = emp_password;
	}
	public String getDate_of_employment() {
		return date_of_employment;
	}
	public void setDate_of_employment(String date_of_employment) {
		this.date_of_employment = date_of_employment;
	}
	public double getAmount_paid() {
		return amount_paid;
	}
	public void setAmount_paid(double amount_paid) {
		this.amount_paid = amount_paid;
	}
		

}
