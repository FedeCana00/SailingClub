/**
 * 
 */
package application.client.controllers.partner;

import java.io.File;

import application.client.Client;
import application.client.managers.PartnerManager;
import application.client.managers.ViewManager;
import application.models.Notification;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * @author Federico Canali
 *
 */
public class NotificationsPageController {
	/**
	 * Used to show the screen. 
	 **/
	public static void show() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loaderStart = new FXMLLoader();
			loaderStart.setLocation(Client.class.getResource("views/partner/notificationsPage.fxml"));
			AnchorPane rootPane = (AnchorPane) loaderStart.load();
	        
	        // Show the scene containing the root layout.
	        Scene scene = new Scene(rootPane);
	        
	        //Initialize components
	        ImageView back = (ImageView) scene.lookup("#back");
	        ImageView payment = (ImageView) scene.lookup("#payment");
	        ImageView bell = (ImageView) scene.lookup("#bell");
	        Button btnPayment = (Button) scene.lookup("#btnGoToPayments");
	        
	        back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

	            @Override
	            public void handle(MouseEvent event) {
	                ViewManager.getInstance().showLayout(ViewManager.PARTNER_MAIN_PAGE);
	            }
	        });
	        
	        payment.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

	            @Override
	            public void handle(MouseEvent event) {
	                ViewManager.getInstance().showLayout(ViewManager.PAYMENTS_PARTNER_PAGE);
	            }
	        });
	        
	        btnPayment.setOnAction(event -> {
	        	ViewManager.getInstance().showLayout(ViewManager.PAYMENTS_PARTNER_PAGE);
	        });
	        
	        setImageToImageView(back, payment, bell);
	        
	        // table set up of notifications
	        TableView<Notification> tableViewBoats = (TableView<Notification>) scene.lookup("#notifications");
	        
	        TableColumn<Notification, String> column0 = new TableColumn<>("Message");
	        column0.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getMessage()));
	        column0.setStyle("-fx-alignment: CENTER");

	        tableViewBoats.getColumns().add(column0);
	        
	        PartnerManager.getInstance().setNotifications(); //update notifications before set table view
	        tableViewBoats.setItems(FXCollections.observableList(PartnerManager.getInstance().getNotifications()));
	        
	        ViewManager.getInstance().setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* used to set imageView images */
	private static void setImageToImageView(ImageView back, ImageView payment, ImageView bell){
		File file = new File("img/back-arrow.png");
        Image image = new Image(file.toURI().toString());
        back.setImage(image);
        
        file = new File("img/coin.png");
        image = new Image(file.toURI().toString());
        payment.setImage(image);
        
        file = new File("img/bell.png");
        image = new Image(file.toURI().toString());
        bell.setImage(image);
	}
}
