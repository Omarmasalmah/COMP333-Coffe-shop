package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class staticsProfitsController {
	

    @FXML
    private Label Profits;

    @FXML
    private Label netIncome;

    @FXML
    private Label numOrders;
    
    @FXML
	public void initialize() {

		try {
			int num = 0;
			connector.a.connectDB();
			PreparedStatement st2;
			st2 = connector.a.connectDB().prepareStatement("select count(*) from invoice;");
			ResultSet r2 = st2.executeQuery();
			if (r2.next()) {
				num = r2.getInt(1);
			}
			numOrders.setText(num + "");

			/////////////
			double num2 = 0;
			PreparedStatement st3;
			st3 = connector.a.connectDB().prepareStatement("select sum(profits) from invoice");
			ResultSet r3 = st3.executeQuery();
			if (r3.next()) {
				num2 = r3.getDouble(1);
				
			}
			Profits.setText(num2 + "$");
			/////////////////
			PreparedStatement st4;
			double num3 = 0;
			st4 = connector.a.connectDB().prepareStatement("select sum(sale_price) from invoice");
			ResultSet r4 = st4.executeQuery();
			if (r4.next()) {
				num3 = r4.getDouble(1);
			}
			netIncome.setText(num3 + "$");
			

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
