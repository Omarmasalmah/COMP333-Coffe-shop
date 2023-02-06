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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class FeedBackController {
	
	public static int feedId;
	 private ArrayList<Feedback> data;
		private ObservableList<Feedback> dataList;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Avg;

    @FXML
    private TextArea Avgg;

    @FXML
    private Button back;

    @FXML
    private TextField cusid;

    @FXML
    private TextField feednum;

    @FXML
    private TextField rating;

    @FXML
    private Button submit;

    @FXML
    private RadioButton star1;

    @FXML
    private RadioButton star2;

    @FXML
    private RadioButton star3;

    @FXML
    private RadioButton star4;

    @FXML
    private RadioButton star5;
    @FXML
    private TableColumn<Feedback,Integer> custid;

    @FXML
    private TableColumn<Feedback,Integer> fedNum;

    @FXML
    private TableColumn<Feedback,Integer> ratingg;


    @FXML
    private TableView<Feedback> table;

    @FXML
    void AvgOnAction(ActionEvent event) {
    	try {
    	try {
    		double xx = 0 ;
    	
        	connector.a.connectDB();
        	String sql = "select avg(feedback_rating) from feedback";
        	PreparedStatement ps = (PreparedStatement) connector.a.connectDB().prepareStatement(sql);
        	ResultSet r3 = ps.executeQuery();
       if (r3.next()) {
    	   xx = r3.getDouble(1);
       }
       Avgg.setText(xx + "");
        	
    			Stage stage;
    			stage = (Stage) submit.getScene().getWindow();
    			
    		} catch (SQLException e) {
    			
    		} catch (ClassNotFoundException e) {
    			
    		}
    	} catch (Exception e) {
			showDialog(null, "Wrong input!!", "Please check the input again", AlertType.ERROR);
		}
    	
    }

    @FXML
    void backOnAction(ActionEvent event) {
    	try { // open new stage
			Stage stage;
			Parent root;
			stage = (Stage) back.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
			Scene scene = new Scene(root, 901, 649);
			stage.setScene(scene);
			stage.setTitle("Sinjel Cafe");
			stage.show();

		} catch (IOException e1) {

		}

    }
    @FXML
    void star1OnAction(ActionEvent event) {
    }

    @FXML
    void star2OnAction(ActionEvent event) {
    	
    }

    @FXML
    void star3OnAction(ActionEvent event) {
    	 
    }

    @FXML
    void star4OnAction(ActionEvent event) {
    }

    @FXML
    void star5OnAction(ActionEvent event) {
    }

    @FXML
    void submitOnAction(ActionEvent event) {
    	 

    	try {
    		
    		 int state = 0;
    		 if(star1.isSelected()) {
    	       	   state=1;
             }
    		 if(star2.isSelected()) {
  	       	   state=2;
           }
    		 if(star3.isSelected()) {
  	       	   state=3;
           }
    		 if(star4.isSelected()) {
  	       	   state=4;
           }
    		 if(star5.isSelected()) {
  	       	   state=5;
           }
    		
    	/*	Feedback rc;
			rc = new Feedback(Integer.parseInt(cusid.getText()),Integer.parseInt(feednum.getText()),state);
			Feedback.feed= rc;
			insertData(rc);
			feednum.clear();
			cusid.clear();*/
    		 connector.a.connectDB();
    		 String sql = "Insert into feedback (customer_id,feedback_rating) values(?,?)";
  			PreparedStatement ps = (PreparedStatement) connector.a.connectDB().prepareStatement(sql);
  			ps.setInt(1, Integer.parseInt(cusid.getText()) );
  			ps.setInt(2, state);
  			ps.execute();
  			cusid.clear();
		} catch (Exception e) {
			showDialog(null, "Wrong input!!", "Please check the input again", AlertType.ERROR);
		}
    	
    	
    	initialize();
    	
    }

    

    

    @FXML
    public void initialize() {
	data = new ArrayList<>();
	dataList = FXCollections.observableArrayList(data);
	table.setEditable(true);
	//custid.setCellValueFactory(new PropertyValueFactory<Feedback,Integer>("customer_id"));
	//fedNum.setCellValueFactory(new PropertyValueFactory<Feedback,Integer>("feedback_Number"));
	//ratingg.setCellValueFactory(new PropertyValueFactory<Feedback,Integer>("feedback_Rating"));
	try {
		getData();
	}
	catch (Exception e) {
		showDialog("Watch out!!", "", "Wrong input!!\", \"Please check the input again", AlertType.ERROR);

	}
	table.setItems(dataList);

}

    private void getData() {
	PreparedStatement st2;
	try {
	st2 = connector.a.connectDB().prepareStatement("select MAX(feedback_number) from feedback;");
	ResultSet r2 = st2.executeQuery();
	if (r2.next()) {
		feedId = r2.getInt(1);
	}
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
	
	
	
	String SQL = "select * from feedback";
	try {
		connector.a.connectDB();
		java.sql.Statement state = connector.a.connectDB().createStatement();
		ResultSet rs = state.executeQuery(SQL);
		while (rs.next()) {
			Feedback feed = new Feedback(feedId,rs.getInt(2),rs.getInt(3));
			dataList.add(feed);
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

   

public void showDialog(String title, String header, String body, AlertType type) {
	Alert alert = new Alert(type); 
	alert.setTitle(title);
	alert.setHeaderText(header);
	alert.setContentText(body);

	alert.show();

}



}



/*


package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FeedBackController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Avg;

    @FXML
    private TextArea Avgg;

    @FXML
    private Button back;

    @FXML
    private TextField cusid;

    @FXML
    private TextField feednum;

    @FXML
    private RadioButton star1;

    @FXML
    private RadioButton star2;

    @FXML
    private RadioButton star3;

    @FXML
    private RadioButton star4;

    @FXML
    private RadioButton star5;

    @FXML
    private Button submit;

    @FXML
    private TableView<?> table;

    @FXML
    void AvgOnAction(ActionEvent event) {

    }

    @FXML
    void backOnAction(ActionEvent event) {

    }

    @FXML
    void star1OnAction(ActionEvent event) {

    }

    @FXML
    void star2OnAction(ActionEvent event) {

    }

    @FXML
    void star3OnAction(ActionEvent event) {

    }

    @FXML
    void star4OnAction(ActionEvent event) {

    }

    @FXML
    void star5OnAction(ActionEvent event) {

    }

    @FXML
    void submitOnAction(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert Avg != null : "fx:id=\"Avg\" was not injected: check your FXML file 'yyy.fxml'.";
        assert Avgg != null : "fx:id=\"Avgg\" was not injected: check your FXML file 'yyy.fxml'.";
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'yyy.fxml'.";
        assert cusid != null : "fx:id=\"cusid\" was not injected: check your FXML file 'yyy.fxml'.";
        assert feednum != null : "fx:id=\"feednum\" was not injected: check your FXML file 'yyy.fxml'.";
        assert star1 != null : "fx:id=\"star1\" was not injected: check your FXML file 'yyy.fxml'.";
        assert star2 != null : "fx:id=\"star2\" was not injected: check your FXML file 'yyy.fxml'.";
        assert star3 != null : "fx:id=\"star3\" was not injected: check your FXML file 'yyy.fxml'.";
        assert star4 != null : "fx:id=\"star4\" was not injected: check your FXML file 'yyy.fxml'.";
        assert star5 != null : "fx:id=\"star5\" was not injected: check your FXML file 'yyy.fxml'.";
        assert submit != null : "fx:id=\"submit\" was not injected: check your FXML file 'yyy.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'yyy.fxml'.";

    }

}
*/