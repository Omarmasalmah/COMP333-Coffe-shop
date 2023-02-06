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
   	void Item(ActionEvent event) {
   		try { // open new stage
   			
   			Stage stage;
   			Parent root;
   			stage = (Stage) btItem.getScene().getWindow();
   			stage.close();
   			root = FXMLLoader.load(getClass().getResource("Item.fxml"));
   			Scene scene = new Scene(root, 1100, 700);
   			stage.setScene(scene);
   			stage.setTitle("Items");
   			stage.show();

   		} catch (IOException e1) {
   		}
   		
   	}
    
    @FXML
   	void Emp(ActionEvent event) {
   		try { // open new stage
   			
   			Stage stage;
   			Parent root;
   			stage = (Stage) btEmp.getScene().getWindow();
   			stage.close();
   			root = FXMLLoader.load(getClass().getResource("Employee.fxml"));
   			Scene scene = new Scene(root, 950, 700);
   			stage.setScene(scene);
   			stage.setTitle("Employees");
   			stage.show();

   		} catch (IOException e1) {
   		}
   		
   	}
    
    @FXML
   	void Cat(ActionEvent event) {
   		try { // open new stage
   			
   			Stage stage;
   			Parent root;
   			stage = (Stage) btCat.getScene().getWindow();
   			stage.close();
   			root = FXMLLoader.load(getClass().getResource("Categores.fxml"));
   			Scene scene = new Scene(root, 933, 643);
   			stage.setScene(scene);
   			stage.setTitle("Categores");
   			stage.show();

   		} catch (IOException e1) {
   		}
   		
   	}
    
    @FXML
   	void Feedback(ActionEvent event) {
   		try { // open new stage
   			
   			Stage stage;
   			Parent root;
   			stage = (Stage) btFeed.getScene().getWindow();
   			stage.close();
   			root = FXMLLoader.load(getClass().getResource("Feedback.fxml"));
   			Scene scene = new Scene(root, 869, 699);
   			stage.setScene(scene);
   			stage.setTitle("Feedback");
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
