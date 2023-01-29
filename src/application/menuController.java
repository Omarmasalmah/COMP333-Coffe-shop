package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class menuController {

    @FXML
    private Button btCat;

    @FXML
    private Button btEmp;

    @FXML
    private Button btFeed;

    @FXML
    private Button btItem;

    @FXML
    private Button btLogout;

    @FXML
    private Button btOrder;
    
    
    @FXML
	void Order(ActionEvent event) {
		try { // open new stage
			
			Stage stage;
			Parent root;
			stage = (Stage) btOrder.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("chooseOrders.fxml"));
			Scene scene = new Scene(root, 369, 474);
			stage.setScene(scene);
			stage.setTitle("orders");
			stage.show();

		} catch (IOException e1) {
		}
		
	}
    
    @FXML
    void LogOut(ActionEvent event) {
		try { // open new stage
			Stage stage;
			Parent root;
			stage = (Stage) btLogout.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("Sign_in.fxml"));
			Scene scene = new Scene(root,600,400);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e1) {
		}
    }

}
