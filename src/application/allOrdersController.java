package application;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatter.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.converter.StringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class allOrdersController {

	public static boolean isOpen = false;
	public static int ordId;

	private ArrayList<invoiceData> data;
	private ObservableList<invoiceData> dataList;
	@FXML
	private TableView<invoiceData> TableData;
	@FXML
	private Button btnback;
	@FXML
	private TableColumn<invoiceData, Integer> orderId;
	@FXML
	private TableColumn<invoiceData, Integer> byEmp;
	@FXML
	private TableColumn<invoiceData, Double> price;
	@FXML
	private TableColumn<invoiceData, String> date;
	@FXML
	private TableColumn<invoiceData, String> bill_type;

	@FXML
	private TextField by_emp_search;
	@FXML
	private DatePicker dateFrom;

	@FXML
	private Button search;


	@FXML
	private DatePicker dateTo;

	@FXML
	private Button deleteItem;

	@FXML
	private Button openOrder;

	@FXML
	private TextField ordeID;

	@FXML
	private Button refresh;

	@FXML
	private Button showStattics;

	@FXML
	private TableView<invoiceData> tableData;

	String SQL = "select * from invoice order by order_id;";
	boolean isSearch = false;
	String typ = "";

	@FXML
	void search(ActionEvent event) {
		isSearch = true;
		boolean isDate = false;
		boolean isType = false;
		if (dateFrom.getValue() == (null) && !(dateTo.getValue() == (null))
				|| !(dateFrom.getValue() == (null)) && dateTo.getValue() == (null)) {
			Message.displayMassage("please put 'date from' and 'date to' together", "error");
			return;
		}
		SQL = "select * from invoice ";

		if (dateFrom.getValue() != null || by_emp_search.getText() != "") {
			SQL += " where ";
		}

		if (dateFrom.getValue() != null) {
			SQL += " (order_date BETWEEN '" + dateFrom.getValue() + "'and '" + dateTo.getValue() + "') ";
			isDate = true;
		}
		
		if (by_emp_search.getText() != "") {
			if (isDate || isType) {
				SQL += " and ";
			}

			SQL += " emp_id =  " + Integer.parseInt(by_emp_search.getText());
		}

		SQL += " order by order_id; ";
		initialize();
	}

	@FXML
	public void initialize() {

		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		if (!isSearch) {
			TableData.setEditable(true);
			dateFrom.setValue(null);
			dateTo.setValue(null);
			by_emp_search.clear();
			ordeID.clear();
			

		}
		

		isSearch = false;
		by_emp_search.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					by_emp_search.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		orderId.setCellValueFactory(new PropertyValueFactory<invoiceData, Integer>("order_id"));
		date.setCellValueFactory(new PropertyValueFactory<invoiceData, String>("order_date"));
		price.setCellValueFactory(new PropertyValueFactory<invoiceData, Double>("full_price"));
		byEmp.setCellValueFactory(new PropertyValueFactory<invoiceData, Integer>("emp_id"));
		getData();
		TableData.setItems(dataList);

	}

	public void getData() {

		try {
			connector.a.connectDB();
			java.sql.Statement state = connector.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {

		invoiceData it = new invoiceData(rs.getInt(1),rs.getInt(2), rs.getDouble(3), rs.getInt(4),
						rs.getInt(5), rs.getString(6),rs.getInt(7), rs.getString(8));

				dataList.add(it);

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

	@FXML
	void back(ActionEvent event) {
		try { // open new stage
			Stage stage;
			Parent root;
			stage = (Stage) btnback.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("chooseOrders.fxml"));
			Scene scene = new Scene(root, 901, 649);
			stage.setScene(scene);
			stage.setTitle("Choose order");
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

		try {
			connector.a.connectDB();
			connector.a.ExecuteStatement("delete from invoice where  order_id=" + row.getOrder_id() + ";");
			connector.a.connectDB().close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		initialize();
	}

	@FXML
	void openOrder(ActionEvent event) {

		if (!ordeID.getText().equals("")) {
			isOpen = true;
			ordId = Integer.parseInt(ordeID.getText());

			try { // open new stage
				Stage stage;
				Parent root;
				stage = (Stage) openOrder.getScene().getWindow();
				stage.close();
				root = FXMLLoader.load(getClass().getResource("Order.fxml"));
				Scene scene = new Scene(root, 951, 781);
				stage.setScene(scene);
				stage.setTitle("Orders");
				stage.show();

			} catch (IOException e1) {
			}
		} else {
			Message.displayMassage("Please Put Order Number", "error");
		}
	}

	@FXML
	void showStattics(ActionEvent event) {

		if (Manager.mng.getName().equals("khalid")) {
			Scene scene;
			Stage stage = null;
			try { // open new stage
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("staticsOrder.fxml"));
				scene = new Scene(fxmlLoader.load(), 918, 463);
				stage = new Stage();
				stage.setTitle("statics Orders");
				stage.setScene(scene);
				stage.show();

			} catch (IOException e1) {
			}
		} else {
			Message.displayMassage("You do not have permission to access", "error");
		}
	}

	@FXML
	void refresh(ActionEvent event) {
		SQL = "select * from invoice order by order_id;";
		isSearch = false;
		initialize();
	}

}