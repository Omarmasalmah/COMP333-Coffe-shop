package application;


public class Feedback {
	
	private int feedback_Number;
	private int feedback_Rating;
	private int customer_id;
	
	public Feedback() {
		super();
	}

	static Feedback feed;

	public Feedback( int customer_id,int feedback_Number, int feedback_Rating) {
		super();
		this.feedback_Number = feedback_Number;
		this.customer_id = customer_id;
		this.feedback_Rating = feedback_Rating;
		
	}

	public int getFeedback_Number() {
		return feedback_Number;
	}

	public void setFeedback_Number(int feedback_Number) {
		this.feedback_Number = feedback_Number;
	}

	public int getFeedback_Rating() {
		return feedback_Rating;
	}

	public void setFeedback_Rating(int feedback_Rating) {
		this.feedback_Rating = feedback_Rating;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}





}