/**
 * 
 */
package application.client.controllers.partner;

import java.io.File;

import application.client.Client;
import application.client.controllers.PaymentPageController;
import application.client.managers.ClientManager;
import application.client.managers.UserManager;
import application.client.managers.ViewManager;
import application.managers.AlertManager;
import application.models.Boat;
import application.models.Partner;
import application.models.Race;
import application.models.Registration;
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
 * TODO: transform into page not alert
 * @author Federico Canali
 *
 */
public class NewRegistrationController {
	
	static Boat selectedBoat;
	static Race selectedRace;
	
	/**
	 * Used to show the screen. 
	 **/
	public static void show() {
		try {
			
			//reset item selected
			selectedBoat = null;
			selectedRace = null;
			
			// Load root layout from fxml file.
			FXMLLoader loaderStart = new FXMLLoader();
			loaderStart.setLocation(Client.class.getResource("views/partner/newRegistrationPage.fxml"));
			AnchorPane rootPane = (AnchorPane) loaderStart.load();
	        
	        // Show the scene containing the root layout.
	        Scene scene = new Scene(rootPane);
	        
	        //Initialize components
	        ImageView back = (ImageView) scene.lookup("#back");
	        Button btnConfirm = (Button) scene.lookup("#btnConfirm");
	        
	        setImageToImageView(back);
	        
	        buttonCallBack(back, btnConfirm);
	        
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
	        
	        tableViewBoats.setItems(FXCollections.observableList(((Partner) UserManager.getInstance().getPerson()).getBoats()));
	        
	        // table set up of races
	        TableView<Race> tableViewRaces = (TableView<Race>) scene.lookup("#tableViewRaces");
	        
	        TableColumn<Race, String> columnr0 = new TableColumn<>("id");
	        columnr0.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getId())));
	        columnr0.setStyle("-fx-alignment: CENTER");
	        
	        TableColumn<Race, String> columnr1 = new TableColumn<>("name");
	        columnr1.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getName()));
	        columnr1.setStyle("-fx-alignment: CENTER");


	        TableColumn<Race, String> columnr2 = new TableColumn<>("date");
	        columnr2.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getDate().toString()));
	        columnr2.setStyle("-fx-alignment: CENTER");
	        
	        TableColumn<Race, String> columnr3 = new TableColumn<>("subscription price");
	        columnr3.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getSubscriptionPrice() + "€")));
	        columnr3.setStyle("-fx-alignment: CENTER");

	        tableViewRaces.getColumns().add(columnr0);
	        tableViewRaces.getColumns().add(columnr1);
	        tableViewRaces.getColumns().add(columnr2);
	        tableViewRaces.getColumns().add(columnr3);
	        
	        tableViewRaces.setItems(FXCollections.observableList(ClientManager.getInstance().getAllRaces()));
	        
	        addButtonsToTableViewBoats(tableViewBoats);
	        addButtonsToTableViewRaces(tableViewRaces);
	        
	        ViewManager.getInstance().setScene(scene);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* used to implements callback of buttons */
	private static void buttonCallBack(ImageView back, Button btnConfirm) {
		back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                ViewManager.getInstance().showLayout(ViewManager.PARTNER_MAIN_PAGE);
            }
        });
		
		btnConfirm.setOnAction((event) -> {
			if(selectedBoat == null) {
				AlertManager.getInstance().showErrorMessage("Please select your boat that you want to be entered in a race.");
				return;
			}
			
			if(selectedRace == null) {
				AlertManager.getInstance().showErrorMessage("Please select a race in which you want your boat to be entered.");
				return;
			}
			
			
			//check if this boat is already registered to this race
			for(Race race: selectedBoat.getRacesRegistered()) {
				if(race.getId() == selectedRace.getId()) {
					AlertManager.getInstance().showErrorMessage("This boat is already registered "
							+ "for this race! Please select another boat or another race ...");
					return;
				}
			}
			
			AlertManager.getInstance().showInfoMessage("After completing the registration payment, your boat will be registered for the race.");
			
			ViewManager.getInstance().showLayout(selectedRace.getSubscriptionPrice(), new Registration(selectedRace, selectedBoat), PaymentPageController.OPERATION_SUBSCRIPTION_FEE);
		});
	}
	
	/* used to add select button to tableView */
	private static void addButtonsToTableViewBoats(TableView<Boat> tableViewBoats) {
		TableColumn<Boat, Void> colBtnSelect = new TableColumn("Select");

        Callback<TableColumn<Boat, Void>, TableCell<Boat, Void>> cellFactorySelect= new Callback<TableColumn<Boat, Void>, TableCell<Boat, Void>>() {
            @Override
            public TableCell<Boat, Void> call(final TableColumn<Boat, Void> param) {
                final TableCell<Boat, Void> cell = new TableCell<Boat, Void>() {

                    private final Button btn = new Button("Select");

                    {
                    	btn.setOnAction(new EventHandler<>() {

                			@Override
                			public void handle(javafx.event.ActionEvent arg0) {
                				selectedBoat = tableViewBoats.getItems().get(getIndex());
                				tableViewBoats.refresh();
                			}
                		});
                    	
                    	//setting button BUY style
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
                        	//change color and text of button if the item selected
                        	if(selectedBoat != null)
                        		if(tableViewBoats.getItems().get(getIndex()).getId() == selectedBoat.getId()) {
                        			btn.setStyle("-fx-background-color: GREEN; -fx-text-fill: WHITE;");
                        			btn.setText("Selected");
                        		}
                        	
                        	setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtnSelect.setCellFactory(cellFactorySelect);

        tableViewBoats.getColumns().add(colBtnSelect);
	}
	
	/* used to add select button to tableView */
	private static void addButtonsToTableViewRaces(TableView<Race> tableViewRaces) {
		TableColumn<Race, Void> colBtnSelect = new TableColumn("Select");

        Callback<TableColumn<Race, Void>, TableCell<Race, Void>> cellFactorySelect= new Callback<TableColumn<Race, Void>, TableCell<Race, Void>>() {
            @Override
            public TableCell<Race, Void> call(final TableColumn<Race, Void> param) {
                final TableCell<Race, Void> cell = new TableCell<Race, Void>() {

                    private final Button btn = new Button("Select");

                    {
                    	btn.setOnAction(new EventHandler<>() {

                			@Override
                			public void handle(javafx.event.ActionEvent arg0) {
                				selectedRace = tableViewRaces.getItems().get(getIndex());
                				tableViewRaces.refresh();
                			}
                		});
                    	
                    	//setting button BUY style
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
                        	//change color and text of button if the item selected
                        	if(selectedRace != null)
                        		if(tableViewRaces.getItems().get(getIndex()).getId() == selectedRace.getId()) {
                        			btn.setStyle("-fx-background-color: GREEN; -fx-text-fill: WHITE;");
                        			btn.setText("Selected");
                        		}
                        	
                        	setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtnSelect.setCellFactory(cellFactorySelect);

        tableViewRaces.getColumns().add(colBtnSelect);
	}
	
	/* used to set imageView images */
	private static void setImageToImageView(ImageView back){
		File file = new File("img/back-arrow.png");
        Image image = new Image(file.toURI().toString());
        back.setImage(image);
	}
}
