package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class itemController {
	  @FXML
	    private TextField AddCatId;

	    @FXML
	    private TextField AddDiscription;

	    @FXML
	    private TextField AddItemSize;

	    @FXML
	    private TextField AddOriginPrice;

	    @FXML
	    private TextField AddSalePrice;

	    @FXML
	    private Button BackButton;

	    @FXML
	    private Button DeleteBtn;

	    @FXML
	    private TextField DeleteCatId;

	    @FXML
	    private TextField DeleteParcode;

	    @FXML
	    private TextField OldCatId;

	    @FXML
	    private TableColumn<Item, Integer> QntyClmn;

	    @FXML
	    private Button RefreshBtn;

	    @FXML
	    private TableColumn<Item, Double> SalePrice;

	    @FXML
	    private TextField SearchField;

	    @FXML
	    private Button UpdateBtn;

	    @FXML
	    private Button addBtn;

	    @FXML
	    private TextField addName;

	    @FXML
	    private TextField addQuantity;

	    @FXML
	    private TableColumn<Item, Integer> cat_idColumn;

	    @FXML
	    private TableColumn<Item, String> discriptionColumn;

	    @FXML
	    private TableView<Item> itemTableData;

	    @FXML
	    private TableColumn<Item, String> item_nameColumn;

	    @FXML
	    private TextField oldParcode;

	    @FXML
	    private TableColumn<Item, Double> originPrice;

	    @FXML
	    private TableColumn<Item, Integer> par_codeColumn;

	    @FXML
	    private Button searchButton;

	    @FXML
	    private TableColumn<Item, String> sizeClmn;

	    @FXML
	    private TextField updatedDiscription;

	    @FXML
	    private TextField updatedID;

	    @FXML
	    private TextField updatedName;

	    @FXML
	    private TextField updatedOriginPrice;

	    @FXML
	    private TextField updatedQnty;

	    @FXML
	    private TextField updatedSalePrice;

	    @FXML
	    private TextField updatedSize;
	    @FXML
	    private TextField AddItemId;

	
	private ArrayList<Item> data;
	private ObservableList<Item> dataList;
	

	// Event Listener on Button[#UpdateBtn].onAction
	@FXML
	public void updateOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {
		// TODO Autogenerated
		try {
		if ( oldParcode.getText() != null) {
			connector.a.connectDB();
			
			if ( updatedName.getText() != null)
			updateitemName(Integer.parseInt(oldParcode.getText()),updatedName.getText());
			
			if ( updatedSalePrice.getText() != null)
			updateSaleprice(Integer.parseInt(oldParcode.getText()),Double.parseDouble(updatedSalePrice.getText()));
			
			if ( updatedSize.getText() != null)
			updateSize(Integer.parseInt(oldParcode.getText()), updatedSize.getText().toString());
			
			if ( updatedQnty.getText() != null)
			updateQnty(Integer.parseInt(oldParcode.getText()),Integer.parseInt(updatedQnty.getText()));
			
			if ( updatedOriginPrice.getText()!=null)
			updateoriginPrice(Integer.parseInt(oldParcode.getText()),Double.parseDouble(updatedOriginPrice.getText()));
			
			if ( updatedDiscription.getText()!= null)
			updateDisc(Integer.parseInt(oldParcode.getText()),updatedDiscription.getText().toString());
			
			if ( updatedID.getText()!= null)
			updateitemID(Integer.parseInt(oldParcode.getText()),Integer.parseInt(updatedID.getText()));
			
			oldParcode.clear();
			updatedName.clear();
			updatedID.clear();
			updatedDiscription.clear();
			updatedSalePrice.clear();
			updatedQnty.clear();
			updatedSize.clear();
			updatedID.clear();
			OldCatId.clear();
			updatedOriginPrice.clear();
			
			initialize();
			
		}
		}
		catch (Exception e) {
			oldParcode.clear();
			updatedName.clear();
			updatedID.clear();
			updatedDiscription.clear();
			updatedSalePrice.clear();
			updatedQnty.clear();
			updatedSize.clear();
			updatedID.clear();
			showDialog("ERROR","Wrong input!"," check your input again",AlertType.ERROR);
		}
		
	}
	
	// Event Listener on Button[#DeleteBtn].onAction
	@FXML
	public void deleteOnAction(ActionEvent event) {
		// TODO Autogenerated
		
		
		PreparedStatement st1 = null;
		ResultSet result0 = null;
		try {
			if (! DeleteParcode.getText().equals("")) {
				connector.a.connectDB();
				String sql = "delete from items where items.item_id = " + Integer.parseInt(DeleteParcode.getText()) + ";";
				PreparedStatement ps = (PreparedStatement) connector.a.connectDB().prepareStatement(sql);
				ps.execute();
				 DeleteParcode.clear();
				 DeleteCatId.clear();
				 initialize();
			}
			
		}
	 catch (Exception e) {
		 DeleteParcode.clear();
		 DeleteCatId.clear();
		 showDialog("ERROR","Wrong input!"," check your input again",AlertType.ERROR);
	 }
		
	}
	// Event Listener on Button[#addBtn].onAction
	
	
	
	
	
	
	@FXML
	
	public void addOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {
		// TODO Autogenerated
		
		PreparedStatement st2 = null;
		ResultSet result = null;
		try {
			Item rc;
			rc = new Item(Integer.parseInt(AddItemId.getText()),addName.getText(),Double.parseDouble(AddSalePrice.getText()),Double.parseDouble(AddOriginPrice.getText()),
					Integer.parseInt(addQuantity.getText()),AddDiscription.getText(),AddItemSize.getText(),
					Integer.parseInt(AddCatId.getText()));
			

			
			Item.it = rc;
			insertData(rc);
			
			AddItemId.clear();
			addName.clear();
			addQuantity.clear();
			AddSalePrice.clear();
			AddCatId.clear();
			AddOriginPrice.clear();
			AddItemSize.clear();
			AddDiscription.clear();
			initialize();
			
			
		} catch (Exception e) {
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
	public void refreshOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {
		// TODO Autogenerated
		
		oldParcode.clear();
		updatedName.clear();
		updatedID.clear();
		updatedDiscription.clear();
		updatedSalePrice.clear();
		updatedQnty.clear();
		updatedSize.clear();
		updatedID.clear();
		OldCatId.clear();
		updatedOriginPrice.clear();
		
		DeleteParcode.clear();
		 DeleteCatId.clear();
		 
		 AddItemId.clear();
			addName.clear();
			addQuantity.clear();
			AddSalePrice.clear();
			AddCatId.clear();
			AddOriginPrice.clear();
			AddItemSize.clear();
			AddDiscription.clear();
			SearchField.clear();
			
			initialize();
		
		
		
	}
	// Event Listener on Button[#searchButton].onAction
	
	//@Override
	@FXML
	public void initialize() {
		// TODO Auto-generated method stub
		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		itemTableData.setEditable(true);
		
		par_codeColumn.setCellValueFactory(new PropertyValueFactory<Item,Integer>("item_id"));
		item_nameColumn.setCellValueFactory(new PropertyValueFactory<Item,String>("item_name"));
		SalePrice.setCellValueFactory(new PropertyValueFactory<Item,Double>("sale_price"));
		originPrice.setCellValueFactory(new PropertyValueFactory<Item,Double>("origen_price"));
		QntyClmn.setCellValueFactory(new PropertyValueFactory<Item,Integer>("quantity"));
		sizeClmn.setCellValueFactory(new PropertyValueFactory<Item,String>("size"));
		cat_idColumn.setCellValueFactory(new PropertyValueFactory<Item,Integer>("cat_id"));
		discriptionColumn.setCellValueFactory(new PropertyValueFactory<Item,String>("discription"));
		
		try {
		getData();
		}
		catch (Exception e) {
			 showDialog("ERROR","Wrong input!"," check your input again",AlertType.ERROR);
		 }
		itemTableData.setItems(dataList);
		searchItem();
		
	}
	
	
	private void updateitemName (int id, String nn) throws ClassNotFoundException, SQLException {
		if ( updatedName.getText() != null) {
			connector.a.connectDB();
			connector.a.ExecuteStatement(
					"update  items set item_name = '" + nn + "' where item_id = " + Integer.parseInt(oldParcode.getText()) + ";");
			connector.a.connectDB().close();
			}
		
	}
	
	private void updateitemID (int id, Integer newID) throws ClassNotFoundException, SQLException {
		if ( updatedID.getText()!= null) {
			connector.a.connectDB();
			connector.a.ExecuteStatement(
					"update  items set item_id = " + newID + " where item_id = " + Integer.parseInt(oldParcode.getText()) + ";");
			connector.a.connectDB().close();
			}	
		
	}
	
	
	
	
	private void updateSaleprice (int id, Double nn) throws ClassNotFoundException, SQLException {
		if ( updatedSalePrice.getText() != null) {
			connector.a.connectDB();
			connector.a.ExecuteStatement(
					"update  items set sale_price = " + nn + " where item_id = " + Integer.parseInt(oldParcode.getText()) + ";");
			connector.a.connectDB().close();
			}
		
	}
	
	private void updateoriginPrice (int id, Double nn) throws ClassNotFoundException, SQLException {
		if ( updatedOriginPrice.getText()!=null) {
			connector.a.connectDB();
			connector.a.ExecuteStatement(
					"update  items set origen_price = " + nn + " where item_id = " + Integer.parseInt(oldParcode.getText()) + ";");
			connector.a.connectDB().close();
			}
		
	}
	
	private void updateQnty (int id, int nn) throws ClassNotFoundException, SQLException {
		if ( updatedQnty.getText() != null) {
			connector.a.connectDB();
			connector.a.ExecuteStatement(
					"update  items set quantity = " + nn + " where item_id = " + Integer.parseInt(oldParcode.getText()) + ";");
			connector.a.connectDB().close();
			}
		
	}
	
	private void updateSize (int id, String nn) throws ClassNotFoundException, SQLException {
		if ( updatedSize.getText() != null) {
			connector.a.connectDB();
			connector.a.ExecuteStatement(
					"update  items set size = '" + nn + "' where item_id = " + Integer.parseInt(oldParcode.getText()) + ";");
			connector.a.connectDB().close();
			}
		
	}
	
	private void updateDisc (int id, String nn) throws ClassNotFoundException, SQLException {
		if ( updatedDiscription.getText()!= null) {
			connector.a.connectDB();
			connector.a.ExecuteStatement(
					"update  items set discription = '" + nn + "' where item_id = " + Integer.parseInt(oldParcode.getText()) + ";");
			connector.a.connectDB().close();
			}
		
	}
	
	
	public void getData() throws ClassNotFoundException, SQLException {
		String sql = "select * from items;";
		
		try {
		connector.a.connectDB();
		
		java.sql.Statement statement2 = connector.a.connectDB().createStatement();
		ResultSet ResSet = statement2.executeQuery(sql);
		
		while (ResSet.next()) {
			Item i = new Item(ResSet.getInt(1),ResSet.getString(2),ResSet.getDouble(3),ResSet.getDouble(4),ResSet.getInt(5),ResSet.getString(6),
					ResSet.getString(7),ResSet.getInt(8));
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
	
	
	
	public void showDialog(String title, String header, String body, AlertType type) {
		Alert alert = new Alert(type); 
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(body);

		alert.show();

	}
	
	
	private void insertData(Item rc) {

		try {
		

			connector.a.connectDB();
			String sql = "insert into items(item_id,item_name ,sale_price,origen_price,quantity,discription,size,cat_id) value (?,?,?,?,?,?,?,?)";
			PreparedStatement ps = (PreparedStatement) connector.a.connectDB().prepareStatement(sql);
			ps.setInt(1,rc.getItem_id());
			ps.setString(2,rc.getItem_name());
			ps.setDouble(3, rc.getSale_price());
			ps.setDouble(4, rc.getOrigen_price());
			ps.setInt(5, rc.getQuantity());
			ps.setString(6, rc.getDiscription());
			ps.setString(7, rc.getSize());
			ps.setInt(8, rc.getCat_id());
			
			
			ps.execute();
			
		} catch (SQLException e) {
			
		} catch (ClassNotFoundException e) {
			
		}
	}
	
	
	private void searchItem() {
		FilteredList<Item> filteredData = new FilteredList<>(dataList, b -> true);
		SearchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(item -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (String.valueOf(item.getItem_id()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				} else if (item.getItem_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
				else
					return false; 
			});
		});
		SortedList<Item> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(itemTableData.comparatorProperty());
		itemTableData.setItems(sortedData);
	}
	
	
}

