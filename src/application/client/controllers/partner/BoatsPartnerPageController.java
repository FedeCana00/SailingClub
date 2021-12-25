package application.client.controllers.partner;

import java.io.File;

import application.client.Client;
import application.client.managers.AlertManager;
import application.client.managers.UserManager;
import application.client.managers.ViewManager;
import application.models.Boat;
import application.models.Partner;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * The class represents the fxml screen controller
 * of partner's boat page.
 * 
 * Handles events and configures the screen. In particular, this controller 
 * manage the display of partner boats.
 * 
 * Controller related to the fxml file is BoatsPartnerPage.fxml inside {@link application.client.views.partner}.
 * 
 * @author Federico Canali
 */
public class BoatsPartnerPageController {
	
	/**
	 * Used to show the screen. 
	 **/
	public static void show() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loaderStart = new FXMLLoader();
			loaderStart.setLocation(Client.class.getResource("views/partner/BoatsPartnerPage.fxml"));
			AnchorPane rootPane = (AnchorPane) loaderStart.load();
	        
	        // Show the scene containing the root layout.
	        Scene scene = new Scene(rootPane);
	        
	        //Initialize components
	        Button btnNewBoat = (Button) scene.lookup("#btnAddBoat");
	        ImageView back = (ImageView) scene.lookup("#back");
	        ImageView anchor = (ImageView) scene.lookup("#anchor");
	        
	        setImageToImageView(back, anchor);
	        
	        buttonCallBack(back, btnNewBoat);
	        
	        // table set up of partner's boats
	        TableView<Boat> tableViewBoats = (TableView<Boat>) scene.lookup("#tableViewBoats");
	        
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
	        
	        addButtonsToTable(tableViewBoats);
	        
	        tableViewBoats.setItems(FXCollections.observableList(((Partner) UserManager.getInstance().getPerson()).getBoats()));
	        
	        ViewManager.getInstance().setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* used to implements callback of buttons */
	private static void buttonCallBack(ImageView back, Button btnNewBoat) {
		back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                ViewManager.getInstance().showLayout(ViewManager.PARTNER_MAIN_PAGE);
            }
        });
		 
		 btnNewBoat.setOnAction((event) -> {
			ViewManager.getInstance().showLayout(ViewManager.ADD_BOAT_PAGE);
		 });
	}
	
	/* used to add delete button to tableView */
	private static void addButtonsToTable(TableView<Boat> tableViewBoats) {
		TableColumn<Boat, Void> colBtnDelete = new TableColumn("Delete");

        Callback<TableColumn<Boat, Void>, TableCell<Boat, Void>> cellFactoryDelete = new Callback<TableColumn<Boat, Void>, TableCell<Boat, Void>>() {
            @Override
            public TableCell<Boat, Void> call(final TableColumn<Boat, Void> param) {
                final TableCell<Boat, Void> cell = new TableCell<Boat, Void>() {

                    private final Button btn = new Button("Delete");

                    {
                    	btn.setOnAction(new EventHandler<>() {

                			@Override
                			public void handle(javafx.event.ActionEvent arg0) {
                				Boat boat = getTableView().getItems().get(getIndex());
                				
                				UserManager.getInstance().removeBoat(boat);
                				AlertManager.getInstance().showInfoMessage("Boat " + boat.getName() + " has been deleted!");
                				
                				//update tableView
                				tableViewBoats.setItems(FXCollections.observableList(((Partner) UserManager.getInstance().getPerson()).getBoats()));
                				tableViewBoats.refresh();
                			}
                		});
                    	
                    	//setting button BUY style
                    	btn.setMaxWidth(Double.MAX_VALUE);
                    	btn.setStyle("-fx-background-color: RED;"
                    			+ "-fx-text-fill: WHITE;");
                    	
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtnDelete.setCellFactory(cellFactoryDelete);

        tableViewBoats.getColumns().add(colBtnDelete);
	}
	
	/* used to set imageView images */
	private static void setImageToImageView(ImageView back, ImageView anchor){
		File file = new File("img/back-arrow.png");
        Image image = new Image(file.toURI().toString());
        back.setImage(image);
        
        File fileAnchor = new File("img/anchor.png");
        Image imageAnchor = new Image(fileAnchor.toURI().toString());
        anchor.setImage(imageAnchor);
	}
}
