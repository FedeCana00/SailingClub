/**
 * 
 */
package application.client.controllers;

import application.client.Client;
import application.client.managers.ClientManager;
import application.client.managers.UserManager;
import application.managers.AlertManager;
import application.client.managers.ViewManager;
import application.models.ClubStaff;
import application.models.Credentials;
import application.models.Partner;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * The class represents the fxml screen controller
 * of MainPage. 
 * Handles events and configures the screen.
 * 
 * @author Federico Canali
 */
public class MainPageController {

	/**
	 * Used to show the screen. 
	 **/
	public static void show() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loaderStart = new FXMLLoader();
			loaderStart.setLocation(Client.class.getResource("views/main.fxml"));
			AnchorPane rootPane = (AnchorPane) loaderStart.load();
	        
	        // Show the scene containing the root layout.
	        Scene scene = new Scene(rootPane);
	        
	        //Initialize components
	        TextField username = (TextField) scene.lookup("#username");
	        PasswordField password = (PasswordField) scene.lookup("#password");
	        Button btnLogIn = (Button) scene.lookup("#btnLogIn");
	        Button btnSingUp = (Button) scene.lookup("#btnSignUp");
	        
	        buttonCallBack(btnLogIn, btnSingUp, username, password);
	        
	        ViewManager.getInstance().setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* used to implements callback of buttons */
	private static void buttonCallBack(Button login, Button signup, TextField username, PasswordField password) {
		 login.setOnAction((event) -> {
			 
			 if(username.getText().isEmpty() || password.getText().isEmpty()) {
				 AlertManager.getInstance().showErrorMessage("Before logging in, fill in all fields.");
				 return;
			 }
			 
			 if(ClientManager.getInstance().login(new Credentials(username.getText(), password.getText()))) {
				 if(ClientManager.getInstance().userInformation()) {
					 //determinate which view show
					 if(UserManager.getInstance().getPerson() instanceof Partner) {
						 ViewManager.getInstance().showLayout(ViewManager.PARTNER_MAIN_PAGE);
					 } else if (UserManager.getInstance().getPerson() instanceof ClubStaff) {
						 ViewManager.getInstance().showLayout(ViewManager.CLUB_STAFF_MAIN_PAGE);
					 } else {
						 System.out.println("Type of user not identified!");
					 }
					 return;
				 }
			 } else
				AlertManager.getInstance().showErrorMessage("The login credentials are incorrect. Please try again ...");
		 });
		 
		 signup.setOnAction((event) -> {
			ViewManager.getInstance().showLayout(ViewManager.SIGN_UP_PAGE);
		 });
	}
}
