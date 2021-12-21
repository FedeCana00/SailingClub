/**
 * 
 */
package application.client.controllers.clubStaff;

import java.io.File;
import application.client.Client;
import application.client.managers.ClubStaffManager;
import application.client.managers.ViewManager;
import application.managers.AlertManager;
import application.models.ToPay;
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
 * @author Federico Canali
 *
 */
public class SendPaymentPageController {
	/**
	 * Used to show the screen. 
	 **/
	public static void show() {
		try {	
			// Load root layout from fxml file.
			FXMLLoader loaderStart = new FXMLLoader();
			loaderStart.setLocation(Client.class.getResource("views/clubStaff/sendPaymentPage.fxml"));
			AnchorPane rootPane = (AnchorPane) loaderStart.load();
	        
	        // Show the scene containing the root layout.
	        Scene scene = new Scene(rootPane);
	        
	        //Initialize components
	        ImageView back = (ImageView) scene.lookup("#back");
	        ImageView refresh = (ImageView) scene.lookup("#refresh");
	        
	        back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

	            @Override
	            public void handle(MouseEvent event) {
	                ViewManager.getInstance().showLayout(ViewManager.CLUB_STAFF_MAIN_PAGE);
	            }
	        });
	        
	        setImageToImageView(back, refresh);
	        
	        // table set up of to pay
	        TableView<ToPay> tableView = (TableView<ToPay>) scene.lookup("#payment");
	        
	        TableColumn<ToPay, String> column0 = new TableColumn<>("id");
	        column0.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getId())));
	        column0.setStyle("-fx-alignment: CENTER");
	        
	        TableColumn<ToPay, String> column1 = new TableColumn<>("partner id");
	        column1.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getPartnerId())));
	        column1.setStyle("-fx-alignment: CENTER");


	        TableColumn<ToPay, String> column2 = new TableColumn<>("price");
	        column2.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getPrice())));
	        column2.setStyle("-fx-alignment: CENTER");
	        
	        TableColumn<ToPay, String> column3 = new TableColumn<>("Fee type");
	        column3.setCellValueFactory(c-> new SimpleStringProperty(
	        		c.getValue().isMembershipFee() ? "Membership" : "Storage"));
	        column3.setStyle("-fx-alignment: CENTER");
	        
	        TableColumn<ToPay, String> column4 = new TableColumn<>("boat id");
	        column4.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getBoatId() != 0 ? String.valueOf(c.getValue().getBoatId()) : "-"));
	        column4.setStyle("-fx-alignment: CENTER");

	        tableView.getColumns().add(column0);
	        tableView.getColumns().add(column1);
	        tableView.getColumns().add(column2);
	        tableView.getColumns().add(column3);
	        tableView.getColumns().add(column4);
	        
	        addButtonsToTable(tableView);
	        
	        //set table items with to pay not yet sent
	        tableView.setItems(FXCollections.observableList(ClubStaffManager.getInstance().getToPays()));
	        
	        //handle click on refresh
	        refresh.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

	            @Override
	            public void handle(MouseEvent event) {
	            	tableView.setItems(FXCollections.observableList(ClubStaffManager.getInstance().getToPays()));
	            	tableView.refresh();
	            }
	        });
	        
	        ViewManager.getInstance().setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* used to add delete button to tableView */
	private static void addButtonsToTable(TableView<ToPay> tableView) {
		TableColumn<ToPay, Void> colBtnSend = new TableColumn("Send");

        Callback<TableColumn<ToPay, Void>, TableCell<ToPay, Void>> cellFactorySend = new Callback<TableColumn<ToPay, Void>, TableCell<ToPay, Void>>() {
            @Override
            public TableCell<ToPay, Void> call(final TableColumn<ToPay, Void> param) {
                final TableCell<ToPay, Void> cell = new TableCell<ToPay, Void>() {

                    private final Button btn = new Button("Send");

                    {
                    	btn.setOnAction(new EventHandler<>() {

                			@Override
                			public void handle(javafx.event.ActionEvent arg0) {
                				ToPay toPay = getTableView().getItems().get(getIndex());
                				
                				ClubStaffManager.getInstance().sendNotificationOfPayment(toPay);
                				AlertManager.getInstance().showInfoMessage("Notification sent to partner");
                				
                				//refresh date inside manager before update tableView
                				ClubStaffManager.getInstance().refreshToPays();
                				//update tableView
                				tableView.setItems(FXCollections.observableList(ClubStaffManager.getInstance().getToPays()));
                				tableView.refresh();
                			}
                		});
                    	
                    	//set button style
                    	btn.setMaxWidth(Double.MAX_VALUE);
                    	btn.setStyle("-fx-background-color: ORANGE;"
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

        colBtnSend.setCellFactory(cellFactorySend);

        tableView.getColumns().add(colBtnSend);
	}
	
	/* used to set imageView images */
	private static void setImageToImageView(ImageView back, ImageView refresh){
		File file = new File("img/back-arrow.png");
        Image image = new Image(file.toURI().toString());
        back.setImage(image);
        
        file = new File("img/refresh.png");
        image = new Image(file.toURI().toString());
        refresh.setImage(image);
	}
}
