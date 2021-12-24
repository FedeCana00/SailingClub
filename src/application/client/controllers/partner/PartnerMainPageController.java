package application.client.controllers.partner;

import java.io.File;

import application.client.Client;
import application.client.managers.PartnerManager;
import application.client.managers.UserManager;
import application.client.managers.ViewManager;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * The class represents the fxml screen controller
 * of partner main page.
 * 
 * Handles events and configures the screen.
 * 
 * Controller related to the fxml file is MainPartnerPage.fxml inside {@link application.client.views.partner}.
 * 
 * @author Federico Canali
 */
public class PartnerMainPageController {
	
	/**
	 * Used to show the screen. 
	 **/
	public static void show() {
		try {		
			// Load root layout from fxml file.
			FXMLLoader loaderStart = new FXMLLoader();
			loaderStart.setLocation(Client.class.getResource("views/partner/MainPartenerPage.fxml"));
			AnchorPane rootPane = (AnchorPane) loaderStart.load();
	        
	        // Show the scene containing the root layout.
	        Scene scene = new Scene(rootPane);
	        
	        //TODO: complete this part
	        //Initialize components
	        Label notifications = (Label) scene.lookup("#notifyUser");
	        Label welcomeUser = (Label) scene.lookup("#welcomeUsername");
	        Button btnBoats = (Button) scene.lookup("#btnAnchor");
	        Button btnRaces = (Button) scene.lookup("#btnRace");
	        Button btnPayments = (Button) scene.lookup("#btnCoin");
	        Button btnRegistrationNewRace = (Button) scene.lookup("#btnAddRace");
	        ImageView anchor = (ImageView) scene.lookup("#anchor");
	        ImageView race = (ImageView) scene.lookup("#race");
	        ImageView coin = (ImageView) scene.lookup("#coin");
	        ImageView notification = (ImageView) scene.lookup("#bell");
	        ImageView logout = (ImageView) scene.lookup("#logout");
	        
	        setImageToImageView(anchor, race, coin, notification, logout);
	        
	        //set username inside welcome user label
	        welcomeUser.setText("Welcome " + UserManager.getInstance().getPerson().getCredentials().getUsername());
	        
	        notifications.setText(String.valueOf(PartnerManager.getInstance().getNotifications().size())); 
	        
	        //check if there are some notifications to user, else hide red symbol
	        if(PartnerManager.getInstance().getNotifications().isEmpty())
	        	notifications.setVisible(false);
	        
	        buttonCallBack(btnBoats, btnRaces, btnPayments, btnRegistrationNewRace, logout, notifications, notification
	        		, anchor, race, coin);
	        
	        ViewManager.getInstance().setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* used to implements callback of buttons */
	private static void buttonCallBack(Button btnBoats, Button btnRaces,  Button btnPayments
			,  Button btnRegistrationNewRace, ImageView logout, Label notifications, ImageView notification
			, ImageView anchor, ImageView race, ImageView coin) {
		
		btnBoats.setOnAction((event) -> {
			ViewManager.getInstance().showLayout(ViewManager.BOATS_PARTNER_PAGE);
		 });
		
		anchor.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
            	ViewManager.getInstance().showLayout(ViewManager.BOATS_PARTNER_PAGE);
            }
        });
		 
		btnRaces.setOnAction((event) -> {
			ViewManager.getInstance().showLayout(ViewManager.RACE_PARTNER_PAGE);
		 });
		
		race.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
            	ViewManager.getInstance().showLayout(ViewManager.RACE_PARTNER_PAGE);
            }
        });
		
		btnPayments.setOnAction((event) -> {
			ViewManager.getInstance().showLayout(ViewManager.PAYMENTS_PARTNER_PAGE);
		 });
		
		coin.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
            	ViewManager.getInstance().showLayout(ViewManager.PAYMENTS_PARTNER_PAGE);
            }
        });
		
		btnRegistrationNewRace.setOnAction((event) -> {
			ViewManager.getInstance().showLayout(ViewManager.NEW_REGISTRATION_PAGE);
		 });
		
		logout.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
            	UserManager.getInstance().logout();
                ViewManager.getInstance().showLayout(ViewManager.MAIN_PAGE);
            }
        });
		
		notifications.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
            	ViewManager.getInstance().showLayout(ViewManager.NOTIFICATIONS_PAGE);
            }
        });
		
		notification.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
            	ViewManager.getInstance().showLayout(ViewManager.NOTIFICATIONS_PAGE);
            }
        });
	}
	
	/* used to set imageView images */
	private static void setImageToImageView(ImageView anchor, ImageView race, ImageView coin, ImageView notification, ImageView logout){
		File file = new File("img/anchor.png");
        Image image = new Image(file.toURI().toString());
        anchor.setImage(image);
        
        file = new File("img/race.png");
        image = new Image(file.toURI().toString());
        race.setImage(image);
        
        file = new File("img/coin.png");
        image = new Image(file.toURI().toString());
        coin.setImage(image);
        
        file = new File("img/bell.png");
        image = new Image(file.toURI().toString());
        notification.setImage(image);
        
        file = new File("img/exit.png");
        image = new Image(file.toURI().toString());
        logout.setImage(image);
	}
}
