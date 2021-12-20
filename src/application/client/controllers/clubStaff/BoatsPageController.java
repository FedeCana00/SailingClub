/**
 * 
 */
package application.client.controllers.clubStaff;

import java.io.File;

import application.client.Client;
import application.client.managers.UserManager;
import application.client.managers.ViewManager;
import application.models.Boat;
import application.models.Partner;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
public class BoatsPageController {
	/**
	 * Used to show the screen. 
	 **/
	public static void show(Partner partner) {
		try {
			// Load root layout from fxml file.
			FXMLLoader loaderStart = new FXMLLoader();
			loaderStart.setLocation(Client.class.getResource("views/clubStaff/boatsPage.fxml"));
			AnchorPane rootPane = (AnchorPane) loaderStart.load();
	        
	        // Show the scene containing the root layout.
	        Scene scene = new Scene(rootPane);
	        
	        //Initialize components
	        Label title = (Label) scene.lookup("#title");
	        ImageView back = (ImageView) scene.lookup("#back");
	        
	        title.setText("Boats of " + partner.getCredentials().getUsername());
	        
	        back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

	            @Override
	            public void handle(MouseEvent event) {
	                ViewManager.getInstance().showLayout(ViewManager.CLUB_STAFF_MAIN_PAGE);
	            }
	        });
	        
	        setImageToImageView(back);
	        
	        // table set up of partner's boats
	        TableView<Boat> tableViewBoats = (TableView<Boat>) scene.lookup("#boats");
	        
	        TableColumn<Boat, String> column0 = new TableColumn<>("id");
	        column0.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getId())));
	        column0.setStyle("-fx-alignment: CENTER");
	        
	        TableColumn<Boat, String> column1 = new TableColumn<>("name");
	        column1.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getName()));
	        column1.setStyle("-fx-alignment: CENTER");


	        TableColumn<Boat, String> column2 = new TableColumn<>("length");
	        column2.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getLength())));
	        column2.setStyle("-fx-alignment: CENTER");

	        tableViewBoats.getColumns().add(column0);
	        tableViewBoats.getColumns().add(column1);
	        tableViewBoats.getColumns().add(column2);
	        
	        tableViewBoats.setItems(FXCollections.observableList(partner.getBoats()));
	        
	        ViewManager.getInstance().setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* used to set imageView images */
	private static void setImageToImageView(ImageView back){
		File file = new File("img/back-arrow.png");
        Image image = new Image(file.toURI().toString());
        back.setImage(image);
	}
}
