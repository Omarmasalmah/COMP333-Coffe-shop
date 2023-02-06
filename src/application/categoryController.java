package application;

import java.io.IOException;
import java.net.URL;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import java.sql.PreparedStatement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class categoryController {
    private ArrayList<Category> data;
	private ObservableList<Category> dataList;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField CategoryIDdelete;

    @FXML
    private Button Delete;

    @FXML
    private TextField NumOfitemsAdd;

    @FXML
    private TextField SearchFiled;

    @FXML
    private TableView<Category> TableData;

    @FXML
    private Button Update;

    @FXML
    private Button add;

    @FXML
    private TextField addCatid;

    @FXML
    private Button btnback;

    @FXML
    private TextField catname;

    @FXML
    private TableColumn<Category, Integer> id;

    @FXML
    private TableColumn<Category, String> name;

    @FXML
    private TextField newID;

    @FXML
    private TextField newName;

    @FXML
    private TextField newNumber;

    @FXML
    private TableColumn<Category, Integer> numberOfItem;

    @FXML
    private TextField oldID;
    @FXML
    private Button search;
    
   

    @FXML
    void addOnAction(ActionEvent event) {

    	try {
			Category rc;
			rc = new Category(Integer.parseInt(addCatid.getText()), catname.getText(), Integer.parseInt(NumOfitemsAdd.getText()));
			Category.cat= rc;
			insertData(rc);
			addCatid.clear();
			catname.clear();
			NumOfitemsAdd.clear();
				
		} catch (Exception e) {
			showDialog(null, "Wrong input!!", "Please check the input again", AlertType.ERROR);
		}
		initialize();
	}

    @FXML
    void back(ActionEvent event) throws IOException {
    	Stage stage;
		Parent root;
		stage = (Stage) btnback.getScene().getWindow();
		stage.close();
		root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Scene scene = new Scene(root, 901, 649);
		stage.setScene(scene);
		//stage.setTitle("Choose order");
		stage.show();
    }

    @FXML
    void deleteOnAction(ActionEvent event){
    	try {
			if (CategoryIDdelete.getText() != null) {
				int id = Integer.parseInt(CategoryIDdelete.getText());
				deleteRow(id);
			}
		
			CategoryIDdelete.clear();
		} catch (Exception e) {

		}
		initialize();

    }
    private void deleteRow(int id) {
		try {
//			System.out.println("delete from  employee where id =" + id + ";");
			connector.a.connectDB();
			connector.a.ExecuteStatement("delete from  categores where cat_id =" + id + ";");
			connector.a.connectDB().close();
//			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		initialize();
	}

    @FXML
   
    void updateOnAction(ActionEvent event) {
    	try {
			if (oldID.getText() != null) {
				int d = Integer.parseInt(oldID.getText()); 
				if (newName.getText().length() > 0) {
					updateName(d, newName.getText());
				}
				
				if (newNumber.getText().length() > 0) {
					updateNumber_Of_Item(d, Integer.parseInt(newNumber.getText()));
				}
				if (newID.getText().length() > 0) {
					updateCat_ID(d, Integer.parseInt(newID.getText())); 
				}
					
				
			//	refresh();
			    oldID.clear();
				newName.clear();
				newID.clear();
				newNumber.clear();
				initialize();

			}
		} catch (Exception e) {
			  oldID.clear();
				newName.clear();
				newID.clear();
				newNumber.clear();
			showDialog("Watch out!!", "", "Wrong input!!\", \"Please check the input again", AlertType.ERROR);
		}
		initialize();
    }
    

   
    private void insertData(Category rc) {

		try {
		

			connector.a.connectDB();
			String sql = "Insert into categores (cat_id,categores_name,number_of_item) values(?,?,?)";
			PreparedStatement ps = (PreparedStatement) connector.a.connectDB().prepareStatement(sql);
			ps.setInt(1, rc.getCat_id());
			ps.setString(2,rc.getCategores_name());
			ps.setInt(3, rc.getNumber_of_item());
			ps.execute();
			Stage stage;
			stage = (Stage) add.getScene().getWindow();
			//stage.close();

		} catch (SQLException e) {
			
		} catch (ClassNotFoundException e) {
			
		}
	}




public void showDialog(String title, String header, String body, AlertType type) {
	Alert alert = new Alert(type); 
	alert.setTitle(title);
	alert.setHeaderText(header);
	alert.setContentText(body);

	alert.show();

}


private void updateNumber_Of_Item(int cat_id, Integer newValue) {
	try {
//		System.out.println("update  employee set employee_name = '" + name + "' where id = " + ID_num);
		connector.a.connectDB();
		connector.a.ExecuteStatement("update  categores set number_of_item = " + newValue + " where cat_id = " + cat_id + ";");
		connector.a.connectDB().close();
//		System.out.println("Connection closed");

	} catch (SQLException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	
	
}


private void updateName(int cat_id, String newValue) {
	// TODO Auto-generated method stub
	try {
//		System.out.println("update  employee set employee_name = '" + name + "' where id = " + ID_num);
		connector.a.connectDB();
		connector.a.ExecuteStatement("update  categores set categores_name = '" + newValue + "' where cat_id = " + cat_id + ";");
		connector.a.connectDB().close();
//		System.out.println("Connection closed");

	} catch (SQLException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}

}


private void updateCat_ID(int d, Integer newValue) {
	try {
//		System.out.println("update  employee set employee_name = '" + name + "' where id = " + ID_num);
		connector.a.connectDB();
		connector.a
				.ExecuteStatement("update  categores set cat_id = " + newValue + " where cat_id = " + d + ";");
		connector.a.connectDB().close();
//		System.out.println("Connection closed");

	} catch (SQLException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	
}

private void searchRentalCategory() {
	FilteredList<Category> filteredData = new FilteredList<>(dataList, b -> true);
	SearchFiled.textProperty().addListener((observable, oldValue, newValue) -> {
		filteredData.setPredicate(category -> {
			if (newValue == null || newValue.isEmpty()) {
				return true;
			}
			String lowerCaseFilter = newValue.toLowerCase();

			if (String.valueOf(category.getCat_id()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true; // Filter matches car Id
			} else if (category.getCategores_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true; // Filter matches password
			}
			else
				return false; // Does not match.
		});
	});
	SortedList<Category> sortedData = new SortedList<>(filteredData);
	sortedData.comparatorProperty().bind(TableData.comparatorProperty());
	TableData.setItems(sortedData);
}


private void getData() {
	String SQL = "select * from categores order by cat_id";
	try {
		connector.a.connectDB();
		java.sql.Statement state = connector.a.connectDB().createStatement();
		ResultSet rs = state.executeQuery(SQL);
		while (rs.next()) {
			Category cat = new Category(rs.getInt(1),
					rs.getString(2).toString(),rs.getInt(3));
			dataList.add(cat);
		}
		rs.close();
		state.close();
		connector.a.connectDB().close();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		showDialog("Watch out!!", "", "Wrong input!!\", \"Please check the input again", AlertType.ERROR);

	}

}


@FXML
public void initialize() {
	data = new ArrayList<>();
	dataList = FXCollections.observableArrayList(data);
	TableData.setEditable(true);
	id.setCellValueFactory(new PropertyValueFactory<Category,Integer>("cat_id"));
	name.setCellValueFactory(new PropertyValueFactory<Category,String>("categores_name"));
	numberOfItem.setCellValueFactory(new PropertyValueFactory<Category,Integer>("number_of_item"));
	try {
		getData();
	}
	catch (Exception e) {
		showDialog("Watch out!!", "", "Wrong input!!\", \"Please check the input again", AlertType.ERROR);

	}
	TableData.setItems(dataList);
	searchRentalCategory();
}




}
