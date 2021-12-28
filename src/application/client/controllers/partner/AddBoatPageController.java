package application.client.controllers.partner;

import java.io.File;

import application.client.Client;
import application.client.managers.AlertManager;
import application.client.managers.UserManager;
import application.client.managers.ViewManager;
import application.models.Boat;
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
 * The class represents the fxml screen controller
 * of add boat page.
 * 
 * Handles events and configures the screen. In particular, this controller 
 * manages the addiction of boat.
 * 
 * Controller related to the fxml file is AddBoatPage.fxml inside {@link application.client.views.partner}.
 * 
 * @author Federico Canali
 */
public class AddBoatPageController {
	/**
	 * Used to show the screen. 
	 **/
	public static void show() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loaderStart = new FXMLLoader();
			loaderStart.setLocation(Client.class.getResource("views/partner/AddBoatPage.fxml"));
			AnchorPane rootPane = (AnchorPane) loaderStart.load();
	        
	        // Show the scene containing the root layout.
	        Scene scene = new Scene(rootPane);
	        
	        //Initialize components
	        TextField name = (TextField) scene.lookup("#name");
	        TextField length = (TextField) scene.lookup("#length");
	        Button btnAddBoat = (Button) scene.lookup("#btnAddBoat");
	        ImageView btnBack = (ImageView) scene.lookup("#back");
	        
	        setImageToImageView(btnBack);
	        buttonCallBack(btnAddBoat, btnBack, name, length);
	        
	        ViewManager.getInstance().setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* used to implements callback of buttons */
	private static void buttonCallBack(Button btnAddBoat, ImageView btnBack, TextField name, 
			TextField length) {
		
		btnAddBoat.setOnAction((event) -> {
			 
			 //reset all style
			 name.setStyle(null); length.setStyle(null);
			 
			 boolean areFilledCorrectly = true;
			 String errorMessageString = "";
			 
			 if(name.getText().isEmpty()) {
				 areFilledCorrectly = false;
				 changeBorderColor(name);
				 errorMessageString += "Name is empty!\n";
			 }
			 
			 if(name.getText().length() > 30) {
				 areFilledCorrectly = false;
				 changeBorderColor(name);
				 errorMessageString += "Name cannot be more than 30 characters!\n";
			 }
			 
			 if(length.getText().isEmpty()) {
				 areFilledCorrectly = false;
				 changeBorderColor(length);
				 errorMessageString += "Length is empty!\n";
			 } else {
				 // test if length is a positive number
				try{
					if(Integer.parseInt(length.getText()) <= 0) {
						areFilledCorrectly = false;
						errorMessageString += "Length is zero or a negative number!\n";
					}
				}catch (NumberFormatException ex) {
				    System.out.println("Is not a number...");
				    areFilledCorrectly = false;
				    errorMessageString += "Length is not a number!\n";
				}
			 }
			 
			 if(areFilledCorrectly) {
				 UserManager.getInstance().addNewBoat(new Boat(name.getText(), Integer.parseInt(length.getText())));
				 
				 //return to main page
				 AlertManager.getInstance().showInfoMessage("Boat " + name.getText() + " added successfully!");
				 ViewManager.getInstance().showLayout(ViewManager.BOATS_PARTNER_PAGE);
			 } else {
				 AlertManager.getInstance().showErrorMessage(errorMessageString);
			 }
			 
			 
		 });
		 
		 btnBack.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

	            @Override
	            public void handle(MouseEvent event) {
	                ViewManager.getInstance().showLayout(ViewManager.BOATS_PARTNER_PAGE);
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
