/**
 * 
 */
package application.client.controllers.clubStaff;

import java.io.File;

import application.client.Client;
import application.client.managers.ClubStaffManager;
import application.client.managers.ViewManager;
import application.models.Partner;
import application.models.Registration;
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
public class RacesPageController {
	/**
	 * Used to show the screen. 
	 **/
	public static void show(Partner partner) {
		try {
			// Load root layout from fxml file.
			FXMLLoader loaderStart = new FXMLLoader();
			loaderStart.setLocation(Client.class.getResource("views/clubStaff/RacesPage.fxml"));
			AnchorPane rootPane = (AnchorPane) loaderStart.load();
	        
	        // Show the scene containing the root layout.
	        Scene scene = new Scene(rootPane);
	        
	        //Initialize components
	        Label title = (Label) scene.lookup("#title");
	        ImageView back = (ImageView) scene.lookup("#back");
	        
	        title.setText("Boats entered in the races of partner " + partner.getCredentials().getUsername());
	        
	        back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

	            @Override
	            public void handle(MouseEvent event) {
	                ViewManager.getInstance().showLayout(ViewManager.CLUB_STAFF_MAIN_PAGE);
	            }
	        });
	        
	        setImageToImageView(back);
	        
	        // table set up of partner's boats
	        TableView<Registration> tableView = (TableView<Registration>) scene.lookup("#races");
	        
	        TableColumn<Registration, String> column0 = new TableColumn<>("race name");
	        column0.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getRace().getName()));
	        column0.setStyle("-fx-alignment: CENTER");
	        
	        TableColumn<Registration, String> column1 = new TableColumn<>("race date");
	        column1.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getRace().getDate().toString()));
	        column1.setStyle("-fx-alignment: CENTER");


	        TableColumn<Registration, String> column2 = new TableColumn<>("boat name");
	        column2.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getBoat().getName()));
	        column2.setStyle("-fx-alignment: CENTER");

	        tableView.getColumns().add(column0);
	        tableView.getColumns().add(column1);
	        tableView.getColumns().add(column2);
	        
	        tableView.setItems(FXCollections.observableList(ClubStaffManager.getInstance().getRegistrationOfPartner(partner)));
	        
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
