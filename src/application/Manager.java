package application;


public class Manager {

	String name;
	String password;
	
	static Manager mng = new Manager();
	
	public Manager() {
		super();
	
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
