package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class addItemTOrderController {

	
	@FXML
	private TextField Item_name;

	@FXML
	private Button addToCart;

	@FXML
	private ToggleButton byItemName;

	@FXML
	private ToggleButton byParcode;

	@FXML
	private Button cancelItem;

	@FXML
	private ToggleGroup cartTgl;

	@FXML
	private HBox itemName;

	@FXML
	private TextField item_parcode;

	@FXML
	private Spinner<Integer> item_quant;
	
	@FXML
    private ComboBox<String> item_size;

	@FXML
	private HBox parcode;

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	LocalDateTime now = LocalDateTime.now();

	@FXML
	public void initialize() {
		
		String[] arraySize = {"S" , "M" , "L"};
		parcode.setVisible(true);
		//itemName.setVisible(false);
	//	parcode.setLayoutX(67);
	//	parcode.setLayoutY(270);
		SpinnerValueFactory<Integer> quant = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 200, 1);
		item_size.getItems().addAll(arraySize);
		item_quant.setValueFactory(quant);
	}

	@FXML
	void addToCart(ActionEvent event) {

		PreparedStatement st2 = null;
		ResultSet r2 = null;
		int qunt;
		try {
			if (!item_parcode.getText().equals("")) {
				st2 = connector.a.connectDB().prepareStatement(
						"select * from items where item_id=" + Integer.parseInt(item_parcode.getText())+ " and size= '"+ item_size.getValue() + "';");
System.out.println("ooooo"+ item_size.getValue());
			} else {
				st2 = connector.a.connectDB()
						.prepareStatement("select * from items where item_name= '" + Item_name.getText() +"' and size= '"+ item_size.getValue() + "';");
				System.out.println("nnnnnnnn "+ Item_name.getText());

			}
			r2 = st2.executeQuery();

			if (r2.next()) {
				System.out.println("name >>" + r2.getString(2));
				System.out.println("id >>" + r2.getInt(1));
				System.out.println("quant >>" + r2.getInt(5));
//				System.out.println("seal price >>" + r2.getDouble(5));
//				System.out.println("orginal price >>" + r2.getDouble(6));

			} else {
				Message.displayMassage("this items not found !", "error");
				return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (r2.getInt(5) - item_quant.getValue() >= 0) {
		    	
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

				java.util.Date myDate = null;
				@SuppressWarnings("unused")
				java.sql.Date sqlDate;
				try {
					myDate = formatter.parse(dtf.format(now));

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				sqlDate = new java.sql.Date(myDate.getTime());

				try {

//					System.out.println(r2.getDate(9).toString());
//					System.out.println(dtf.format(now).toString());
					connector.a.connectDB();
					String sql = "insert into invoice(quantity ,sale_price ,original_price ,item_id,order_id,profits,order_date) values (?,?,?,?,?,?,?);";
					PreparedStatement ps = (PreparedStatement) connector.a.connectDB().prepareStatement(sql);
					connector.a.ExecuteStatement("update items set quantity = " + (r2.getInt(5) - item_quant.getValue())
							+ " where item_id = " + r2.getInt(1)
							+ " and cat_id = " + r2.getInt(8) + ";");

					ps.setInt(1, item_quant.getValue());
					ps.setDouble(2, r2.getDouble(4));
					ps.setDouble(3, r2.getDouble(3));
					ps.setDouble(6, (item_quant.getValue()*r2.getDouble(4))-(item_quant.getValue()*r2.getDouble(3)));
					ps.setInt(4, r2.getInt(1));
					ps.setInt(5, orderesController.orderId);
					ps.setTimestamp(7, new java.sql.Timestamp(myDate.getTime()));
					ps.execute();

				} catch (SQLException e) {
					if (e.getErrorCode() == 1062) {
						Message.displayMassage("this items is already added !", "error");
						return;
					}
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				Stage stage = (Stage) cancelItem.getScene().getWindow();
				stage.close();
			} else {
				if (r2.getInt(5) > 0) {
					Message.displayMassage(
							"There is not enough quantity of this product!\r\n" + "There is only: " + r2.getInt(3),
							"warning");
				} else {
					Message.displayMassage("out of stock !", "warning");

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void byItemName(ActionEvent event) {

		item_parcode.clear();
		Item_name.clear();
		parcode.setVisible(false);
		itemName.setVisible(true);
		itemName.setLayoutX(67);
		itemName.setLayoutY(270);

	}

	@FXML
	void byParcode(ActionEvent event) {
		item_parcode.clear();
		Item_name.clear();
		parcode.setVisible(true);
		itemName.setVisible(false);
		parcode.setLayoutX(67);
		parcode.setLayoutY(270);

	}

	@FXML
	void cancelItem(ActionEvent event) {

		Stage stage = (Stage) cancelItem.getScene().getWindow();
		stage.close();
	}

}