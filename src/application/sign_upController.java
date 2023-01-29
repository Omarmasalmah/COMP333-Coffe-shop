package application;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class sign_upController {

    @FXML
    private Button button_Signup_in_signup;

    @FXML
    private Button button_login_of_signup;

    @FXML
    private DatePicker dateOFworkfield;

    @FXML
    private DatePicker datefield;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtphone;
    
    @FXML
    private TextField txtsalary;
    
    int statmentID;

    
    
    @FXML
	void signupOnAction(ActionEvent event) {
		Employee rc;
		try {
		rc = new Employee(txtName.getText(), datefield.getValue().toString(), dateOFworkfield.getValue().toString(),
				txtPassword.getText(), Integer.parseInt(txtphone.getText()), Double.parseDouble(txtsalary.getText()));
        
		Employee.emd=rc;
		insertData(rc);
		txtName.clear();
		txtPassword.clear();
		txtphone.clear();
		txtsalary.clear();
		}
		catch (Exception e) {
		     showDialog(null, "Wrong input!!", "Please check the input again", AlertType.ERROR);   
		}
	}
	public void showDialog(String title, String header, String body, AlertType type) {
		Alert alert = new Alert(type); // infotrmation or error or..
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(body);

		alert.show();

	}
	private void insertData(Employee rc) {

		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			java.util.Date myDate = null;
			java.sql.Date sqlDate;
			try {
				myDate = formatter.parse(rc.getBirthday());

			} catch (Exception e) {
				   showDialog(null, "Wrong Format For the date!!", "Please use this format for data : \n year-month-day", AlertType.ERROR);   
			}

			sqlDate = new java.sql.Date(myDate.getTime());

			java.util.Date myDate2 = null;
			java.sql.Date sqlDate2;
			try {
				myDate2 = formatter.parse(rc.getDate_of_employment());

			} catch (Exception e) {
				   showDialog(null, "Wrong Format For the date!!", "Please use this format for data : \n year-month-day", AlertType.ERROR);   
			}
			sqlDate2 = new java.sql.Date(myDate.getTime());

//			System.out.println("Insert into employee (id,employee_name,birthday,date_of_employment) values("
//					+ rc.getId() + ",'" + rc.getEmployee_name() + "','" + sqlDate + "','" + sqlDate2 + "')");

			connector.a.connectDB();
			String sql = "Insert into employee (employee_name,birthday,date_of_employment,emp_password,phone,amount_paid) values(?,?,?,?,?,?)";
			PreparedStatement ps = (PreparedStatement) connector.a.connectDB().prepareStatement(sql);
			ps.setString(4, rc.getEmp_password());
			ps.setString(1, rc.getEmployee_name());
			ps.setInt(5, rc.getPhone());
			ps.setDouble(6, rc.getAmount_paid());
			ps.setTimestamp(2, new java.sql.Timestamp(myDate.getTime()));
			ps.setTimestamp(3, new java.sql.Timestamp(myDate2.getTime()));
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
			String SQL = "select id from employee where employee_name='" + rc.getEmployee_name()
					+ "' and emp_password='" + rc.getEmp_password() + "';";
			try {
				connector.a.connectDB();
				java.sql.Statement state = connector.a.connectDB().createStatement();
				ResultSet rs = state.executeQuery(SQL);
				while (rs.next()) {
					statmentID = rs.getInt(1);
				}
				rs.close();
				state.close();
				connector.a.connectDB().close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Stage stage;
			stage = (Stage) button_Signup_in_signup.getScene().getWindow();
			stage.close();
//		else if (Contract) {
//			String SQL = "select id from employee where employee_name='" + rc.getEmployee_name()
//					+ "' and emp_password='" + rc.getEmp_password() + "';";
//			try {
//				connector.a.connectDB();
//				java.sql.Statement state = connector.a.connectDB().createStatement();
//				ResultSet rs = state.executeQuery(SQL);
//				while (rs.next()) {
//					statmentID = rs.getInt(1);
//				}
//				rs.close();
//				state.close();
//				connector.a.connectDB().close();
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			try {
//				connector.a.connectDB();
//				String sql = "Insert into contrect_employee (id,amount_paid) values(?,?)";
//				PreparedStatement ps = (PreparedStatement) connector.a.connectDB().prepareStatement(sql);
//				ps.setInt(1, statmentID);
//				ps.setDouble(2, rc.getAmount_paid());
//				ps.execute();
//
//			} catch (SQLException e) {
//
//			} catch (ClassNotFoundException e) {
//
//			}
//
//		}
	}
	
	 @FXML
	    void button_Login(ActionEvent event) {

	    	try { // open new stage
				Stage stage;
				Parent root;
				stage = (Stage) button_login_of_signup.getScene().getWindow();
				stage.close();
				root = FXMLLoader.load(getClass().getResource("Sign_in.fxml"));
				Scene scene = new Scene(root, 600, 400);
				stage.setScene(scene);
				stage.setTitle("Sinjel Cafe");
				stage.show();

			} catch (IOException e1) {

			}
	    }


}


