/**
 * 
 */
package application.client.controllers.partner;

import java.io.File;

import application.client.Client;
import application.client.managers.UserManager;
import application.client.managers.ViewManager;
import application.models.Registration;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
public class RacesPartnerPageController {
	/**
	 * Used to show the screen. 
	 **/
	public static void show() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loaderStart = new FXMLLoader();
			loaderStart.setLocation(Client.class.getResource("views/partner/racesPartnerPage.fxml"));
			AnchorPane rootPane = (AnchorPane) loaderStart.load();
	        
	        // Show the scene containing the root layout.
	        Scene scene = new Scene(rootPane);
	        
	        //Initialize components
	        ImageView back = (ImageView) scene.lookup("#back");
	        ImageView race = (ImageView) scene.lookup("#race");
	        
	        setImageToImageView(back, race);
	        
	        buttonCallBack(back);
	        
	        // table set up of partner's boats
	        TableView<Registration> tableView = (TableView<Registration>) scene.lookup("#tableViewRaces");
	        
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
	        
	        tableView.setItems(FXCollections.observableList((UserManager.getInstance().getRegistration())));
	        
	        ViewManager.getInstance().setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* used to implements callback of buttons */
	private static void buttonCallBack(ImageView back) {
		back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                ViewManager.getInstance().showLayout(ViewManager.PARTNER_MAIN_PAGE);
            }
        });
	}
	
	/* used to set imageView images */
	private static void setImageToImageView(ImageView back, ImageView race){
		File file = new File("img/back-arrow.png");
        Image image = new Image(file.toURI().toString());
        back.setImage(image);
        
        File fileRace = new File("img/race.png");
        Image imageRace = new Image(fileRace.toURI().toString());
        race.setImage(imageRace);
	}
}
