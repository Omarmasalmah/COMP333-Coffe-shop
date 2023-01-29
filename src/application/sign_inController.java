package application;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class sign_inController {
	public static String ManegName;
	public static int empId;
	public static String ManegPassword;
	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private Button button_Login;

    @FXML
    private Button button_Signup;

    @FXML
    private Button button_cancel;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;
    
   
    
    @FXML
	void btnSign_in(ActionEvent event) {

		try {
			PreparedStatement st = connector.a.connectDB()
					.prepareStatement("select * from employee where employee_name = ? AND emp_password = ? ");
			st.setString(1, txtName.getText());
			st.setString(2, txtPassword.getText());
			ResultSet r1 = st.executeQuery();
//			if (txtName.getText().equals("") || txtPassword.getText().equals("")) {
//
//				showDialog("error", "you entered a wrong data", null, AlertType.ERROR);
//
//			} 
			System.out.println("enters");

			if (txtName.getText().isEmpty()) {
				System.out.println("nnnnnnnnnnnnnnnnn");

				Message.displayMassage("Please enter the user name","Warning");
				return;
			}
			if (txtPassword.getText().isEmpty()) {
				Message.displayMassage("Please enter the txtPassword","Warning");
				return;
			}
//			else {
			if (r1.next()) {
				if (r1.getString(2).toLowerCase().equals(txtName.getText().toLowerCase())
						&& (r1.getString(5).toLowerCase().equals(txtPassword.getText().toLowerCase()))) {
					ManegName = txtName.getText().toLowerCase();
					ManegPassword = txtPassword.getText().toLowerCase();

					Manager.mng.setName(ManegName);
					Manager.mng.setPassword(ManegPassword);
					try { // open new stage
						stage = (Stage) button_Login.getScene().getWindow();
						stage.close();
						root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
						scene = new Scene(root, 901, 649);
						stage.setScene(scene);
						stage.setTitle("Chose One");
						stage.show();

					} catch (IOException e1) {
						Message.displayMassage("There is no account at this Name and Password, Try again","error");
//							showDialog("error", "you entered a wrong data", null, AlertType.ERROR);
					}
				}

			} else {
				Message.displayMassage( "There is no account at this Name and Password, Try again","error");
//					showDialog("error", "you entered a wrong data", null, AlertType.ERROR);
			}

//				}

			PreparedStatement st2 = connector.a.connectDB()
					.prepareStatement("select * from employee where employee_name = ?");
			st2.setString(1, ManegName);
			ResultSet r2 = st2.executeQuery();
			if (r2.next()) {
				empId = r2.getInt(1);
//			System.out.println("id >>"+r2.getInt(1));
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
    
    @FXML
    void btnSign_up(ActionEvent event) {

    	try { // open new stage
			Stage stage;
			Parent root;
			stage = (Stage) button_Signup.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("Sign_up.fxml"));
			Scene scene = new Scene(root, 600, 458);
			stage.setScene(scene);
			stage.setTitle("Sign up");
			stage.show();

		} catch (IOException e1) {

		}
    }

    
    @FXML
    void btnCancelOnAction(ActionEvent event) {    
    	Stage stage = (Stage) button_cancel.getScene().getWindow();
    	stage.close();	
    	
    }

    @FXML
	void initialize() {
		assert txtName != null : "fx:id=\"UserName\" was not injected: check your FXML file 'Sign_in.fxml'.";
		assert txtPassword != null : "fx:id=\"PassWord\" was not injected: check your FXML file 'Sign_in.fxml'.";

	}
}
