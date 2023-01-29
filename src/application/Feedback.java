package application;


public class Feedback {
	
	private int feedback_id;
	private String feedback_comment;
	private int customer_id;
	private String feedback_date;
	
	
	public Feedback() {
		super();
	}

	

	public Feedback(int feedback_id, int customer_id, String feedback_comment, String feedback_date) {
		super();
		this.feedback_id = feedback_id;
		this.customer_id = customer_id;
		this.feedback_comment = feedback_comment;
		this.feedback_date = feedback_date;
		
	}



	public int getFeedback_id() {
		return feedback_id;
	}



	public void setFeedback_id(int feedback_id) {
		this.feedback_id = feedback_id;
	}



	public String getFeedback_comment() {
		return feedback_comment;
	}



	public void setFeedback_comment(String feedback_comment) {
		this.feedback_comment = feedback_comment;
	}



	public int getCustomer_id() {
		return customer_id;
	}



	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}



	public String getFeedback_date() {
		return feedback_date;
	}



	public void setFeedback_date(String feedback_date) {
		this.feedback_date = feedback_date;
	}


	


	

}
