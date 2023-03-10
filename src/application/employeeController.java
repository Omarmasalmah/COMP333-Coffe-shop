package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import java.sql.PreparedStatement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.DatePicker;

import javafx.scene.control.TableColumn;

public class employeeController {
	@FXML
	private TableView <employee> TableData;
	@FXML
    private TableColumn<employee, Date> BirthdateColumn;
	 @FXML
	 private TableColumn<employee, Date> EmpDateColumn;
	 @FXML
	  private TableColumn<employee, String> PhoneColumn;
	 @FXML
	  private TableColumn<employee, Double> amountColumn;
	 @FXML
	    private TableColumn<employee, Integer> empIDcloumn;
	  @FXML
	    private TableColumn<employee, String> empNameColumn;
	  @FXML
	    private TableColumn<employee, String> passwordColumn;
	@FXML
	private Button UpdateBtn;
	@FXML
	private TextField oldempID;
	@FXML
	private TextField updatedPhone;
	@FXML
	private TextField updatedPassword;
	@FXML
	private TextField updatedAmount;
	@FXML
	private TextField SearchFiled;
	@FXML
	private Button DeleteBtn;
	@FXML
	private Button addBtn;
	@FXML
	private TextField addName;
	@FXML
	private TextField Addphone;
	@FXML
	private Button BackButton;
	@FXML
	private TextField DeleteEmpId;
	@FXML
	private Button RefreshBtn;
	@FXML
	private TextField AddPassword;
	@FXML
	private TextField AddAmount;
	@FXML
	private Button SearchBtn;
	@FXML
	private DatePicker updatedEmpDate;
	@FXML
	private DatePicker AddEmpDate;
	@FXML
	private DatePicker AddBirthdate;
	@FXML
	private TextField updatedID;
	@FXML
    private DatePicker updatedBirthDate1;
	 @FXML
	 private TextField updatedName;
	 @FXML
	 private TextArea avgArea;
	 @FXML
	 private TextArea MaxArea;
	 @FXML
	 private Button FindAvg;
	 @FXML 
	 private Button FindMax1;
	 
	 private ArrayList<employee> data;
	private ObservableList<employee> dataList;
	 
	 
	// Event Listener on Button[#UpdateBtn].onAction
	@FXML
	public void updateOnAction(ActionEvent event) {
		// TODO Autogenerated
		try {
			if (oldempID.getText() != null) {
				
				if (updatedPhone.getText() != null) {
					updatePhone(Integer.parseInt(oldempID.getText()),updatedPhone.getText().toString());
				}
				
				if (updatedEmpDate.getValue().toString() != null) {
					updateEmpDate(Integer.parseInt(oldempID.getText()),updatedEmpDate.getValue());
				}
				
				if (updatedPassword.getText() != null) {
					updatePassword(Integer.parseInt(oldempID.getText()),updatedPassword.getText().toString());
				}
				
				if (updatedAmount.getText() != null) {
					updateAmnt(Integer.parseInt(oldempID.getText()),Double.parseDouble(updatedAmount.getText()));
				   
				}
				
				if (updatedID.getText() != null) {
					updateID(Integer.parseInt(oldempID.getText()),Integer.parseInt(updatedID.getText()));
				}
				
				if (updatedBirthDate1.getValue().toString() != null) {
					updateBirthDate(Integer.parseInt(oldempID.getText()),updatedBirthDate1.getValue());
				}
				
				if (updatedName.getText() != null) {
					updateEmpName(Integer.parseInt(oldempID.getText()),updatedName.getText());
				}
				
				
				oldempID.clear();
				updatedPhone.clear();
				updatedPassword.clear();
				updatedAmount.clear();
				updatedID.clear();
				updatedEmpDate.setValue(null);
				updatedBirthDate1.setValue(null);
				updatedName.clear();
				initialize();
				
				
			}
			
		}
		catch (Exception e) {
			showDialog("ERROR","Wrong input!"," check your input again",AlertType.ERROR);
		}
		
		
		
		
	}
	// Event Listener on Button[#DeleteBtn].onAction
	@FXML
	public void deleteOnAction(ActionEvent event) {
		// TODO Autogenerated
		try {
			if (! DeleteEmpId.getText().equals("")) {
				connector.a.connectDB();
				String sql = "delete from employee where employee.emp_id = " + Integer.parseInt(DeleteEmpId.getText()) + ";";
				PreparedStatement ps = (PreparedStatement) connector.a.connectDB().prepareStatement(sql);
				ps.execute();
				DeleteEmpId.clear();
				initialize();
			}
			
			
		}
	 catch (Exception e) {
		 DeleteEmpId.clear();
		 showDialog("ERROR","Wrong input!"," check your input again",AlertType.ERROR);
	 }
		
		
		
		
		
	}
	// Event Listener on Button[#addBtn].onAction
	@FXML
	public void addOnAction(ActionEvent event) {
		// TODO Autogenerated
		
		
		try {
			
			
			
			connector.a.connectDB();
			String sql = "insert into employee(employee_name,birthday,emp_phone,date_of_employment,emp_password,amount_paid) value (?,?,?,?,?,?)";
			PreparedStatement ps = (PreparedStatement) connector.a.connectDB().prepareStatement(sql);
			ps.setString(1,addName.getText());
			ps.setString(2, AddBirthdate.getValue().toString());
			ps.setString(3, Addphone.getText());
			ps.setString(4, AddEmpDate.getValue().toString());
			ps.setString(5, AddPassword.getText());
			ps.setDouble(6, Double.parseDouble(AddAmount.getText()));
			
			
			
			
			ps.execute();
			
			addName.clear();
			Addphone.clear();
			AddPassword.clear();
			AddAmount.clear();
			AddEmpDate.setValue(null);
			AddBirthdate.setValue(null);
			initialize();
				
			
		}
		
		
		catch (Exception e) {
			showDialog("", "Wrong input!!", "Please check the input again", AlertType.ERROR);
		}
		
		
		
		
		
		
	}
	// Event Listener on Button[#BackButton].onAction
	@FXML
	public void backOnAction(ActionEvent event) throws IOException {
		// TODO Autogenerated
		
		Stage stage;
		Parent root;
		stage = (Stage) BackButton.getScene().getWindow();
		stage.close();
		root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Scene scene = new Scene(root, 901, 649);
		stage.setScene(scene);
		//stage.setTitle("Choose order");
		stage.show();
	}
	// Event Listener on Button[#RefreshBtn].onAction
	@FXML
	public void refreshOnAction(ActionEvent event) {
		// TODO Autogenerated
		
		updatedPhone.clear();
		updatedPassword.clear();
		updatedAmount.clear();
		updatedID.clear();
		updatedEmpDate.setValue(null);
		oldempID.clear();
		updatedBirthDate1.setValue(null);
		updatedName.clear();
		
		addName.clear();
		Addphone.clear();
		AddPassword.clear();
		AddAmount.clear();
		AddEmpDate.setValue(null);
		AddBirthdate.setValue(null);
		
		 DeleteEmpId.clear();
		 
		 SearchFiled.clear();
		 
		 avgArea.clear();
		 MaxArea.clear();
		 initialize();
		
		
	}
	// Event Listener on Button[#SearchBtn].onAction
	
	
	private void updatePhone (int id, String nn) throws ClassNotFoundException, SQLException {
		if ( updatedPhone.getText() != null) {
			connector.a.connectDB();
			connector.a.ExecuteStatement(
					"update  employee set emp_phone = '" + nn + "' where emp_id = " + Integer.parseInt(oldempID.getText()) + ";");
			connector.a.connectDB().close();
			}
		
	}
	
	private void updateEmpDate (int id, LocalDate nn) throws ClassNotFoundException, SQLException {
		if ( updatedEmpDate.getValue() != null) {
			 nn = updatedEmpDate.getValue();
			connector.a.connectDB();
			connector.a.ExecuteStatement(
					"update  employee set date_of_employment = '" + nn + "' where emp_id = " + Integer.parseInt(oldempID.getText()) + ";");
			connector.a.connectDB().close();
			}
		
	}
	
	
	private void updateBirthDate (int id, LocalDate nn) throws ClassNotFoundException, SQLException {
		if ( updatedBirthDate1.getValue() != null) {
			 nn = updatedBirthDate1.getValue();
			connector.a.connectDB();
			connector.a.ExecuteStatement(
					"update  employee set birthday = '" + nn + "' where emp_id = " + Integer.parseInt(oldempID.getText()) + ";");
			connector.a.connectDB().close();
			}
		
	}
	
	
	private void updatePassword (int id, String nn) throws ClassNotFoundException, SQLException {
		if ( updatedPassword.getText() != null) {
			connector.a.connectDB();
			connector.a.ExecuteStatement(
					"update  employee set emp_password = '" + nn + "' where emp_id = " + Integer.parseInt(oldempID.getText()) + ";");
			connector.a.connectDB().close();
			}
		
	}
	
	private void updateAmnt (int id, Double nn) throws ClassNotFoundException, SQLException {
		if ( updatedAmount.getText() != null) {
			connector.a.connectDB();
			connector.a.ExecuteStatement(
					"update  employee set amount_paid = " + nn + " where emp_id = " + Integer.parseInt(oldempID.getText()) + ";");
			connector.a.connectDB().close();
			}
		
	}
	
	private void updateID (int id, int nn) throws ClassNotFoundException, SQLException {
		if ( updatedID.getText() != null) {
			connector.a.connectDB();
			connector.a.ExecuteStatement(
					"update  employee set emp_id = " + nn + " where emp_id = " + Integer.parseInt(oldempID.getText()) + ";");
			connector.a.connectDB().close();
			}
		
	}
	
	private void updateEmpName (int id, String nn) throws ClassNotFoundException, SQLException {
		if ( updatedName.getText() != null) {
			connector.a.connectDB();
			connector.a.ExecuteStatement(
					"update  employee set employee_name = '" + nn + "' where emp_id = " + Integer.parseInt(oldempID.getText()) + ";");
			connector.a.connectDB().close();
			}
		
	}
	
	
	public void showDialog(String title, String header, String body, AlertType type) {
		Alert alert = new Alert(type); 
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(body);

		alert.show();

	}
	
	 @FXML
	    void FindMaxOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
		 double max =0;
		 String name = "";
		 int id = 0;
		 PreparedStatement ps;
		 ps = (PreparedStatement) connector.a.connectDB().prepareStatement("select max(amount_paid) from employee");
		 ResultSet rset = ps.executeQuery();
		 
		 if(rset.next())
			max = rset.getDouble(1); 
		 
		 PreparedStatement ps2;
		 ps2 = (PreparedStatement) connector.a.connectDB().prepareStatement("select emp_id,employee_name from employee where amount_paid = " +max);
         ResultSet rset2 = ps2.executeQuery();
         
         if (rset2.next()) {
        	 name = rset2.getString(2);
        	 id = rset2.getInt(1);
         }
         MaxArea.setText("Max: "+max+"\nEmployee: "+name+"\nID: "+id);
	    }
	   
	 
	    
	 
	 
	 @FXML
	    void FindAvgOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {
		 double avg =0;
		 PreparedStatement ps3;
		 ps3 = (PreparedStatement) connector.a.connectDB().prepareStatement("select avg(amount_paid) from employee");
		 ResultSet rset3 = ps3.executeQuery();
		 if (rset3.next())
			 avg = rset3.getDouble(1);
		 avgArea.setText("Average: "+avg);

	    }
	 
	 
	 @FXML
		public void initialize() {
		 data = new ArrayList<>();
			dataList = FXCollections.observableArrayList(data);
			TableData.setEditable(true);
			
			BirthdateColumn.setCellValueFactory(new PropertyValueFactory<employee,Date>("birthday"));
			EmpDateColumn.setCellValueFactory(new PropertyValueFactory<employee,Date>("date_of_employment"));
			PhoneColumn.setCellValueFactory(new PropertyValueFactory<employee,String>("emp_phone"));
			
			amountColumn.setCellValueFactory(new PropertyValueFactory<employee,Double>("amount_paid"));
			empIDcloumn.setCellValueFactory(new PropertyValueFactory<employee,Integer>("emp_id"));
			
			empNameColumn.setCellValueFactory(new PropertyValueFactory<employee,String>("employee_name"));
			passwordColumn.setCellValueFactory(new PropertyValueFactory<employee,String>("emp_password"));
			
			try {
				getData();
				}
				catch (Exception e) {
					 showDialog("ERROR","Wrong input!"," check your input again",AlertType.ERROR);
				 }
			TableData.setItems(dataList);
			searchEmp();
	 }
	 
	 
	 public void getData() throws  ClassNotFoundException, SQLException {
		 String sql = "select * from employee;";
		 try {
			 connector.a.connectDB();
			 java.sql.Statement statement2 = connector.a.connectDB().createStatement();
			ResultSet ResSet = statement2.executeQuery(sql);
			 while (ResSet.next()) {
				 employee i = new employee(ResSet.getInt(1),ResSet.getString(2),ResSet.getString(3),
						 ResSet.getString(4),ResSet.getString(5),
						 ResSet.getString(6),ResSet.getDouble(7));
			       dataList.add(i);
			 }
			 ResSet.close();
			 statement2.close();
			 connector.a.connectDB().close();
			 
		 }
		 catch (Exception e) {
			 showDialog("ERROR","Wrong input!"," check your input again",AlertType.ERROR);
		 }
		 
		 
		 
		 
	 }
	 
	 
	 private void searchEmp() {
			FilteredList<employee> filteredData = new FilteredList<>(dataList, b -> true);
			SearchFiled.textProperty().addListener((observable, oldValue, newValue) -> {
				filteredData.setPredicate(employee -> {
					if (newValue == null || newValue.isEmpty()) {
						return true;
					}
					String lowerCaseFilter = newValue.toLowerCase();

					if (String.valueOf(employee.getEmp_id()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true; 
					} else if (employee.getEmployee_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true; 
					}
					else
						return false; 
				});
			});
			SortedList<employee> sortedData = new SortedList<>(filteredData);
			sortedData.comparatorProperty().bind(TableData.comparatorProperty());
			TableData.setItems(sortedData);
		}
	 
	 
	
	
	
	
}