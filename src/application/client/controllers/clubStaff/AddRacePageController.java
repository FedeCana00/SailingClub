package application.client.controllers.clubStaff;

import java.io.File;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import application.client.Client;
import application.client.managers.ClientManager;
import application.client.managers.ClubStaffManager;
import application.client.managers.UserManager;
import application.client.managers.ViewManager;
import application.managers.AlertManager;
import application.models.Boat;
import application.models.Race;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * @author Federico Canali
 *
 */
public class AddRacePageController {
	/**
	 * Used to show the screen. 
	 **/
	public static void show() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loaderStart = new FXMLLoader();
			loaderStart.setLocation(Client.class.getResource("views/clubStaff/addRacePage.fxml"));
			AnchorPane rootPane = (AnchorPane) loaderStart.load();
	        
	        // Show the scene containing the root layout.
	        Scene scene = new Scene(rootPane);
	        
	        //Initialize components
	        TextField name = (TextField) scene.lookup("#name");
	        TextField date = (TextField) scene.lookup("#date");
	        TextField price = (TextField) scene.lookup("#price");
	        Button btnAddBoat = (Button) scene.lookup("#btnAddBoat");
	        ImageView btnBack = (ImageView) scene.lookup("#back");
	        
	        setImageToImageView(btnBack);
	        buttonCallBack(btnAddBoat, btnBack, name, date, price);
	        
	        ViewManager.getInstance().setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* used to implements callback of buttons */
	private static void buttonCallBack(Button btnAddBoat, ImageView btnBack, TextField name, 
			TextField date, TextField price) {
		
		btnAddBoat.setOnAction((event) -> {
			 
			 //reset all style
			 name.setStyle(null); price.setStyle(null); date.setStyle(null);
			 
			 boolean areFilledCorrectly = true;
			 String errorMessageString = "";
			 
			 if(name.getText().isEmpty()) {
				 areFilledCorrectly = false;
				 changeBorderColor(name);
				 errorMessageString += "Name is empty!\n";
			 }
			 
			 if(price.getText().isEmpty()) {
				 areFilledCorrectly = false;
				 changeBorderColor(price);
				 errorMessageString += "Length is empty!\n";
			 } else {
				 // test if length is a positive number
				try{
					if(Float.parseFloat(price.getText()) <= 0) {
						areFilledCorrectly = false;
						changeBorderColor(price);
						errorMessageString += "Price is zero or a negative number!\n";
					}
				} catch (NumberFormatException ex) {
				    System.out.println("Is not a number...");
				    areFilledCorrectly = false;
				    changeBorderColor(price);
				    errorMessageString += "Price is not a number!\n";
				}
			 }
			 
			 if(date.getText().isEmpty()) {
				 areFilledCorrectly = false;
				 changeBorderColor(date);
				 errorMessageString += "Date is empty!\n";
			 } else {
				 try {
					 //used to check if is ok, so if a date and it's after today
					 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
					 simpleDateFormat.setLenient(false);
					 Calendar calendar = Calendar.getInstance();
					 Date nowDate = new Date(calendar.getTimeInMillis());
					 java.util.Date d = simpleDateFormat.parse(date.getText());
					 
					 if(d.before(nowDate)) {
						 areFilledCorrectly = false;
						 changeBorderColor(date);
						 errorMessageString += "Date is not valid!\n";
					 }
				 } catch (Exception e) {
					 areFilledCorrectly = false;
					 changeBorderColor(date);
					 errorMessageString += "Ddate is not filled correctly (format: dd/MM/YY)!\n";
				}
			 }
			 
			 if(areFilledCorrectly) {
				 
				 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
				 simpleDateFormat.setLenient(false);
				 java.util.Date d;
				 try {
					d = simpleDateFormat.parse(date.getText());
					java.sql.Date sqlDate = new java.sql.Date(d.getTime());
					 
					ClientManager.getInstance().addRace(new Race(name.getText(), sqlDate, Float.parseFloat(price.getText())));
					 
					//return to main page
					AlertManager.getInstance().showInfoMessage("Race " + name.getText() + " added successfully!");
					ViewManager.getInstance().showLayout(ViewManager.CLUB_STAFF_MAIN_PAGE);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			 } else {
				 AlertManager.getInstance().showErrorMessage(errorMessageString);
			 }	 
		 });
		 
		 btnBack.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

	            @Override
	            public void handle(MouseEvent event) {
	                ViewManager.getInstance().showLayout(ViewManager.CLUB_STAFF_MAIN_PAGE);
	            }
	        });
	}
	
	/* used to set imageView images */
	private static void setImageToImageView(ImageView bntBack){
		File file = new File("img/back-arrow.png");
        Image image = new Image(file.toURI().toString());
        bntBack.setImage(image);
	}
	
	/* used to change the color of the textField due to a fill error */
	private static void changeBorderColor(TextField field) {
		field.setStyle("-fx-border-color: RED");
	}
}
