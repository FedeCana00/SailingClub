package application.client.controllers.clubStaff;

import java.io.File;

import application.client.Client;
import application.client.managers.ViewManager;
import application.models.Partner;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * The class represents the fxml screen controller
 * of partner informations page.
 * 
 * Handles events and configures the screen. In particular, this controller 
 * manages the display of a partner's informations like username, fiscal code, ....
 * 
 * Controller related to the fxml file is PartnerInfoPage.fxml inside {@link application.client.views.clubStaff}.
 * 
 * @author Federico Canali
 */
public class PartnerInfoPageController {
	
	/**
	 * Used to show the screen.
	 * @param partner Represents the partner whose informations we want to view.
	 **/
	public static void show(Partner partner) {
		try {
			// Load root layout from fxml file.
			FXMLLoader loaderStart = new FXMLLoader();
			loaderStart.setLocation(Client.class.getResource("views/clubStaff/PartnerInfoPage.fxml"));
			AnchorPane rootPane = (AnchorPane) loaderStart.load();
	        
	        // Show the scene containing the root layout.
	        Scene scene = new Scene(rootPane);
	        
	        //Initialize components
	        Label id = (Label) scene.lookup("#id");
	        Label name = (Label) scene.lookup("#name");
	        Label surname = (Label) scene.lookup("#surname");
	        Label address = (Label) scene.lookup("#address");
	        Label fiscalCode = (Label) scene.lookup("#fiscalCode");
	        Label username = (Label) scene.lookup("#username");
	        ImageView back = (ImageView) scene.lookup("#back");
	        ImageView info = (ImageView) scene.lookup("#info");
	        
	        //set up all textField
	        id.setText(String.valueOf(partner.getId()));
	        name.setText(partner.getName());
	        surname.setText(partner.getSurname());
	        address.setText(partner.getAddress());
	        fiscalCode.setText(partner.getFiscalCode());
	        username.setText(partner.getCredentials().getUsername());
	        
	        back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

	            @Override
	            public void handle(MouseEvent event) {
	                ViewManager.getInstance().showLayout(ViewManager.CLUB_STAFF_MAIN_PAGE);
	            }
	        });
	        
	        setImageToImageView(back, info);
	        
	        ViewManager.getInstance().setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* used to set imageView images */
	private static void setImageToImageView(ImageView back, ImageView info){
		File file = new File("img/back-arrow.png");
        Image image = new Image(file.toURI().toString());
        back.setImage(image);
        
        file = new File("img/info.png");
        image = new Image(file.toURI().toString());
        info.setImage(image);
	}
	
}
