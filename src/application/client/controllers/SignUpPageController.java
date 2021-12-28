package application.client.controllers;

import java.io.File;

import application.client.Client;
import application.client.managers.AlertManager;
import application.client.managers.ClientManager;
import application.client.managers.ViewManager;
import application.models.Credentials;
import application.models.Partner;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * The class represents the fxml screen controller
 * of sign up page.
 * 
 * Handles events and configures the screen. In particular, this controller manages 
 * the registrations of new partners.
 * 
 * Controller related to the fxml file is SignUp.fxml inside {@link application.client.views}.
 * 
 * @author Federico Canali
 */
public class SignUpPageController {
	
	/**
	 * Used to show the screen. 
	 **/
	public static void show() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loaderStart = new FXMLLoader();
			loaderStart.setLocation(Client.class.getResource("views/SignUp.fxml"));
			AnchorPane rootPane = (AnchorPane) loaderStart.load();
	        
	        // Show the scene containing the root layout.
	        Scene scene = new Scene(rootPane);
	        
	        //Initialize components
	        TextField name = (TextField) scene.lookup("#name");
	        TextField surname = (TextField) scene.lookup("#surname");
	        TextField address = (TextField) scene.lookup("#address");
	        TextField fiscalCode = (TextField) scene.lookup("#fiscalCode");
	        TextField username = (TextField) scene.lookup("#username");
	        PasswordField password = (PasswordField) scene.lookup("#password");
	        Button btnSingUp = (Button) scene.lookup("#btnSignUp");
	        ImageView btnBack = (ImageView) scene.lookup("#back");
	        
	        setImageToImageView(btnBack);
	        buttonCallBack(btnSingUp, btnBack, name, surname, address, fiscalCode, username, password);
	        
	        ViewManager.getInstance().setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* used to implements callback of buttons */
	private static void buttonCallBack(Button signup, ImageView btnBack, TextField name, 
			TextField surname, TextField address, TextField fiscalCode, TextField username, PasswordField password) {
		
		 signup.setOnAction((event) -> {
			 
			 //reset all style
			 name.setStyle(null); surname.setStyle(null); address.setStyle(null);
			 fiscalCode.setStyle(null); username.setStyle(null); password.setStyle(null);
			 
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
			 
			 if(surname.getText().isEmpty()) {
				 areFilledCorrectly = false;
				 changeBorderColor(surname);
				 errorMessageString += "Surname is empty!\n";
			 }
			 
			 if(surname.getText().length() > 30) {
				 areFilledCorrectly = false;
				 changeBorderColor(name);
				 errorMessageString += "Surname cannot be more than 30 characters!\n";
			 }
			 
			 if(address.getText().isEmpty()) {
				 areFilledCorrectly = false;
				 changeBorderColor(address);
				 errorMessageString += "Address is empty!\n";
			 }
			 
			 if(address.getText().length() > 50) {
				 areFilledCorrectly = false;
				 changeBorderColor(name);
				 errorMessageString += "Address cannot be more than 50 characters!\n";
			 }
			 
			 if(fiscalCode.getText().length() != 16) {
				 areFilledCorrectly = false;
				 changeBorderColor(fiscalCode);
				 errorMessageString += "Fiscal code does not have 16 characters!\n";
			 }
			 
			 if(username.getText().isEmpty()) {
				 areFilledCorrectly = false;
				 changeBorderColor(username);
				 errorMessageString += "Username is empty!\n";
			 }
			 
			 if(username.getText().length() > 30) {
				 areFilledCorrectly = false;
				 changeBorderColor(name);
				 errorMessageString += "Username cannot be more than 30 characters!\n";
			 }
			 
			 //check if this username already exixsts
			 if(ClientManager.getInstance().hasSomeUserSameUserName(new Credentials(username.getText(), null))) {
				 areFilledCorrectly = false;
				 changeBorderColor(username);
				 errorMessageString += "'" + username.getText() + "'" + " already exists!\n";
			 }
			 
			 if(password.getText().length() < 5) {
				 areFilledCorrectly = false;
				 changeBorderColor(password);
				 errorMessageString += "Password is less than 5 characters!\n";
			 }
			 
			 if(password.getText().length() > 30) {
				 areFilledCorrectly = false;
				 changeBorderColor(name);
				 errorMessageString += "Password cannot be more than 30 characters!\n";
			 }
			 
			 if(areFilledCorrectly) {
				 ClientManager.getInstance()
				 	.addPerson(new Partner(name.getText(), surname.getText()
				 			, address.getText(), fiscalCode.getText(), new Credentials(username.getText(), password.getText())));
				 
				 //return to main page
				 AlertManager.getInstance().showInfoMessage("User registration successful!");
				 ViewManager.getInstance().showLayout(ViewManager.MAIN_PAGE);
			 } else {
				 AlertManager.getInstance().showErrorMessage(errorMessageString);
			 }
			 
			 
		 });
		 
		 btnBack.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

	            @Override
	            public void handle(MouseEvent event) {
	                ViewManager.getInstance().showLayout(ViewManager.MAIN_PAGE);
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
