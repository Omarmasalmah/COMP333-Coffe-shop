package application;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.converter.IntegerStringConverter;


public class orderesController {
	
	public static int cusId = 0;
	public static String cusName = "";
	public static int orderId;
	private double priceToShow = 0;
	String toFile = "";
	private ArrayList<invoiceData> data;
	private ObservableList<invoiceData> dataList;
	boolean isCreatOrder = false;
    @FXML
    private TableView<invoiceData> TableData;

    @FXML
    private Button addItem;

    @FXML
    private Button btnback;

    @FXML
    private Button cancelOrdre;

    @FXML
    private TableColumn<invoiceData, Number> counter;

    @FXML
    private Button deleteItem;


    @FXML
    private TableColumn<invoiceData, Number> itemCategory;

    @FXML
    private TableColumn<invoiceData, String> itemName;

    @FXML
    private TableColumn<invoiceData, Number> itemParcode;

    @FXML
    private TableColumn<invoiceData, Double> itemPrice;

    @FXML
    private TableColumn<invoiceData, Number> itemQuantity;

    @FXML
    private TableColumn<invoiceData, Number> itembyEmployee;

    @FXML
    private Label orderID;

    @FXML
    private Label price;

    @FXML
    private Button print;

    @FXML
    private TextField search;
    
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	LocalDateTime now = LocalDateTime.now();
	
    @FXML
    void CancelOrder(ActionEvent event) {

    	try {
			if (!allOrdersController.isOpen) {
				connector.a.connectDB();
				connector.a.ExecuteStatement("delete from  invoice where order_id =" + orderId + ";");
				connector.a.ExecuteStatement("delete from  orderes where order_id =" + orderId + ";");
				connector.a.connectDB().close();
			} else {
				allOrdersController.isOpen = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try { // open new stage
			Stage stage;
			Parent root;
			stage = (Stage) cancelOrdre.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
			Scene scene = new Scene(root, 901, 649);
			stage.setScene(scene);
			stage.setTitle("Choose One");
			stage.show();

		} catch (IOException e1) {

		}
	}

    @FXML
    void addNew(ActionEvent event) {
    	Scene scene;
		Stage stage = null;
		try { // open new stage
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("addItem2Order.fxml"));
			scene = new Scene(fxmlLoader.load(), 394, 598);
			stage = new Stage();
			stage.setTitle("add new item");
			stage.setScene(scene);
			stage.show();

		} catch (IOException e1) {
		}

		stage.setOnHidden(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent paramT) {

				initialize();

			}
		});
    }

    @FXML
    void back(ActionEvent event) {
    	try {
			if (!allOrdersController.isOpen) {
				connector.a.connectDB();
				connector.a.ExecuteStatement("delete from  invoice where order_id =" + orderId + ";");
				connector.a.ExecuteStatement("delete from  orderes where order_id =" + orderId + ";");
				connector.a.connectDB().close();
			} else {
				allOrdersController.isOpen = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try { // open new stage
			Stage stage;
			Parent root;
			stage = (Stage) btnback.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
			Scene scene = new Scene(root, 901, 649);
			stage.setScene(scene);
			stage.setTitle("Choose One");
			stage.show();

		} catch (IOException e1) {

		}

    }

    @FXML
    void deleteItem(ActionEvent event) {
    	ObservableList<invoiceData> selectedRows = TableData.getSelectionModel().getSelectedItems();
		ArrayList<invoiceData> rows = new ArrayList<>(selectedRows);
		if (rows.size() == 0) {
			return;
		}
		deleteRow(rows.get(0));

	}

	private void deleteRow(invoiceData row) {
		priceToShow -= (row.getFull_sale_price() * row.getQuantity());

		try {
			connector.a.connectDB();
//			System.out.println("delete from invoice where  order_id=" + row.getOrder_id() + " and par_code="
//					+ row.getPar_code() + ";");
			connector.a.ExecuteStatement("delete from invoice where  order_id=" + row.getOrder_id() + " and par_code="
					+ row.getItem_id() + ";");
			PreparedStatement st2 = connector.a.connectDB()
					.prepareStatement("select * from item where par_code=" + row.getItem_id() + ";");
			ResultSet r2 = st2.executeQuery();
			if (r2.next()) {
//				System.out.println("name >>" + r2.getString(1));
//				System.out.println("id >>" + r2.getInt(2));
//				System.out.println("quant >>" + r2.getInt(3));
//				System.out.println("seal price >>" + r2.getDouble(5));
//				System.out.println("orginal price >>" + r2.getDouble(6));

			}
			connector.a.ExecuteStatement("update item set quantity = " + (r2.getInt(3) + row.getQuantity())
					+ " where item_id = " + r2.getInt(2) + "' and cat_id = " + r2.getInt(7) + ";");

			connector.a.connectDB().close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		initialize();
    }

    @FXML
    void print(ActionEvent event) {

    }
    
    @FXML
	public void initialize() {

		if (!allOrdersController.isOpen) {
			if (!isCreatOrder) {
				PreparedStatement st2;
				try {
					connector.a.connectDB();
					String sql = "insert into orders(id) value(?);";
					PreparedStatement ps = (PreparedStatement) connector.a.connectDB().prepareStatement(sql);
					ps.setInt(1, sign_inController.empId);
					ps.execute();
					st2 = connector.a.connectDB().prepareStatement("select MAX(order_id) from orders;");
					ResultSet r2 = st2.executeQuery();
					if (r2.next()) {
						orderId = r2.getInt(1);
					}
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				isCreatOrder = true;
			}
		} else {
			orderId = allOrdersController.ordId;
		}
		orderID.setText(String.valueOf(orderId));
	
			
		
		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		TableData.setEditable(true);
		counter.setSortable(false);
		counter.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(TableData.getItems().indexOf(column.getValue()) + 1));
		itemParcode.setCellFactory(new PropertyValueFactory<invoiceData, Integer>("item_id"));
		itemName.setCellValueFactory(new PropertyValueFactory<invoiceData, String>("itemName"));
		itemQuantity.setCellValueFactory(new PropertyValueFactory<invoiceData, Integer>("quantity"));
		itemQuantity.setCellFactory(TextFieldTableCell.<invoiceData, Integer>forTableColumn(new IntegerStringConverter()));
		itemQuantity.setOnEditCommit((CellEditEvent<invoiceData, Integer> t) -> {
			int old = t.getOldValue();
			((invoiceData) t.getTableView().getItems().get(t.getTablePosition().getRow())).setQuantity(t.getNewValue()); // display
			// only
//			System.out.println(t.getRowValue().getPar_code());

			updateQuantity(t.getRowValue().getItem_id(), t.getRowValue().getOrder_id(), t.getNewValue(), old);
		});
		itemCategory.setCellValueFactory(new PropertyValueFactory<invoiceData, Integer>("itemCat"));
		itemPrice.setCellValueFactory(new PropertyValueFactory<invoiceData, Double>("full_sale_price"));

		itembyEmployee.setCellValueFactory(new PropertyValueFactory<invoiceData, Integer>("emp_id"));
		getData();
		priceBefore.setText(priceToShow + " $");
		price.setText(priceWithDisc + " $");
		TableData.setItems(dataList);

		searchRentalEmployee();

	}

	public void getData() {
		priceToShow = 0;
		String SQL, od;
		if (allOrdersController.isOpen) {
			SQL = "select * from invoice where order_id=" + allOrdersController.ordId + ";";
			od = "select * from orders where order_id=" + allOrdersController.ordId + ";";
		} else {
			SQL = "select * from invoice where order_id=" + orderId + ";";
			od = "select * from orders where order_id=" + orderId + ";";
		}
		try {
			connector.a.connectDB();
			java.sql.Statement state = connector.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
//			ResultSet ord = state.executeQuery(od);
			int counter = 1;
			toFile = "";
			toFile += "\t" + "Sinjel Cafe" + "\n";
			toFile += "===========================================" + "\n";
			toFile += "Order id : " + orderId + "\n";
			toFile += "Date : " + dtf.format(now) + "\n";
			toFile += "by Employee : " + sign_inController.empId + "\n";
			toFile += "================= Products ================" + "\n";
			toFile += "# | Item Name   | Quantity | Price " + "\n";
			while (rs.next()) {
				String SQL2 = "select * from item where item_id=" + rs.getInt(1) + ";";
				java.sql.Statement state2 = connector.a.connectDB().createStatement();
				ResultSet rs2 = state2.executeQuery(SQL2);

				if (rs2.next()) {
					java.sql.Statement state3 = connector.a.connectDB().createStatement();
					ResultSet rs3 = state3.executeQuery(od);
					if (rs3.next()) {

					invoiceData it = new invoiceData(orderId, rs.getInt(1),rs.getDouble(2),rs.getInt(3), rs2.getInt(7), rs2.getString(2), rs3.getInt(2));
						dataList.add(it);
						toFile += counter + "| " + rs2.getString(1) + " | " + rs.getInt(1) + " | "
								+ (rs.getInt(1) * rs.getDouble(2)) + "\n";
						toFile += "-------------------------------------------" + "\n";
						priceToShow += (rs.getDouble(2) * rs.getInt(1));
//						System.out.println(">>" + (priceToShow * disc));
//						System.out.println("$ :" + priceWithDisc);
					}
				}
				counter++;
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
	}
}


