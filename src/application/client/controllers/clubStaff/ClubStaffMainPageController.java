/**
 * 
 */
package application.client.controllers.clubStaff;

import java.io.File;

import application.client.Client;
import application.client.managers.ClientManager;
import application.client.managers.ClubStaffManager;
import application.client.managers.UserManager;
import application.client.managers.ViewManager;
import application.managers.AlertManager;
import application.models.Boat;
import application.models.ClubStaff;
import application.models.Partner;
import application.models.Race;
import application.server.managers.RacesManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class ClubStaffMainPageController {
	
	/**
	 * Used to show the screen. 
	 **/
	public static void show() {
		try {
			//get/refresh list of partners and races
			ClientManager.getInstance().getAllPartners();
			ClubStaffManager.getInstance().refreshRaces();
			
			// Load root layout from fxml file.
			FXMLLoader loaderStart = new FXMLLoader();
			loaderStart.setLocation(Client.class.getResource("views/clubStaff/mainClubStaffPage.fxml"));
			AnchorPane rootPane = (AnchorPane) loaderStart.load();
	        
	        // Show the scene containing the root layout.
	        Scene scene = new Scene(rootPane);
	        
	        //Initialize components
	        Label welcomeUser = (Label) scene.lookup("#welcomeUsername");
	        Button btnAddRace = (Button) scene.lookup("#btnAddRace");
	        ImageView logout = (ImageView) scene.lookup("#logout");
	        ImageView payment = (ImageView) scene.lookup("#payment");
	        
	        setImageToImageView(logout, payment);
	        
	        ClubStaff user = (ClubStaff) UserManager.getInstance().getPerson();
	        
	        //set username inside welcome user label
	        welcomeUser.setText("Welcome " + user.getCredentials().getUsername());
	        
	        buttonCallBack(btnAddRace, logout, payment);
	        
	        // table set up of partners
	        TableView<Partner> tableViewPartner = (TableView<Partner>) scene.lookup("#tableVIewPartner");
	        
	        TableColumn<Partner, String> column0 = new TableColumn<>("id");
	        column0.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getId())));
	        column0.setStyle("-fx-alignment: CENTER");
	        
	        TableColumn<Partner, String> column1 = new TableColumn<>("username");
	        column1.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getCredentials().getUsername()));
	        column1.setStyle("-fx-alignment: CENTER");

	        tableViewPartner.getColumns().add(column0);
	        tableViewPartner.getColumns().add(column1);
	        
	        addButtonsToTablePartner(tableViewPartner);
	        
	        tableViewPartner.setItems(FXCollections.observableList(ClubStaffManager.getInstance().getPartners()));
	        
	        // table set up of races
	        TableView<Race> tableViewRaces = (TableView<Race>) scene.lookup("#tableViewRaces");
	        
	        TableColumn<Race, String> column_r0 = new TableColumn<>("id");
	        column_r0.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getId())));
	        column_r0.setStyle("-fx-alignment: CENTER");
	        
	        TableColumn<Race, String> column_r1 = new TableColumn<>("name");
	        column_r1.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getName()));
	        column_r1.setStyle("-fx-alignment: CENTER");
	        
	        TableColumn<Race, String> column_r2 = new TableColumn<>("date");
	        column_r2.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getDate().toString()));
	        column_r2.setStyle("-fx-alignment: CENTER");
	        
	        TableColumn<Race, String> column_r3 = new TableColumn<>("subscription price");
	        column_r3.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getSubscriptionPrice() + "€")));
	        column_r3.setStyle("-fx-alignment: CENTER");

	        tableViewRaces.getColumns().add(column_r0);
	        tableViewRaces.getColumns().add(column_r1);
	        tableViewRaces.getColumns().add(column_r2);
	        tableViewRaces.getColumns().add(column_r3);
	        
	        addButtonsToTableRaces(tableViewRaces);
	        
	        tableViewRaces.setItems(FXCollections.observableList(ClubStaffManager.getInstance().getRaces()));
	        
	        ViewManager.getInstance().setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* used to set button callback */
	private static void buttonCallBack(Button btnAddRace, ImageView logout, ImageView payment) {
		btnAddRace.setOnAction(event -> {
			ViewManager.getInstance().showLayout(ViewManager.ADD_RACE_PAGE);
		});
        
        logout.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
            	UserManager.getInstance().logout();
                ViewManager.getInstance().showLayout(ViewManager.MAIN_PAGE);
            }
        });
        
        payment.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                ViewManager.getInstance().showLayout(ViewManager.SEND_PAYMENT_PAGE);
            }
        });
	}
	
	/* used to add buttons to tableView */
	private static void addButtonsToTablePartner(TableView<Partner> tableViewPartners) {
		TableColumn<Partner, Void> colBtnBoats = new TableColumn("Boats");
		TableColumn<Partner, Void> colBtnRaces= new TableColumn("Races");
		TableColumn<Partner, Void> colBtnPayments = new TableColumn("Payments");
		TableColumn<Partner, Void> colBtnInfo = new TableColumn("Info");

		//Boats
        Callback<TableColumn<Partner, Void>, TableCell<Partner, Void>> cellFactoryBoats = new Callback<TableColumn<Partner, Void>, TableCell<Partner, Void>>() {
            @Override
            public TableCell<Partner, Void> call(final TableColumn<Partner, Void> param) {
                final TableCell<Partner, Void> cell = new TableCell<Partner, Void>() {

                    private final Button btn = new Button("Boats");

                    {
                    	btn.setOnAction(new EventHandler<>() {

                			@Override
                			public void handle(javafx.event.ActionEvent arg0) {
                				//pass partner
	            				ViewManager.getInstance().showLayout(ViewManager.BOATS_PAGE, getTableView().getItems().get(getIndex()));
                			}
                		});
                    	
                    	//setting button BUY style
                    	btn.setMaxWidth(Double.MAX_VALUE);
                    	btn.setStyle("-fx-background-color: #005f73;"
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
        
        //Races
	    Callback<TableColumn<Partner, Void>, TableCell<Partner, Void>> cellFactoryRaces = new Callback<TableColumn<Partner, Void>, TableCell<Partner, Void>>() {
	        @Override
	        public TableCell<Partner, Void> call(final TableColumn<Partner, Void> param) {
	            final TableCell<Partner, Void> cell = new TableCell<Partner, Void>() {
	
	                private final Button btn = new Button("Races");
	
	                {
	                	btn.setOnAction(new EventHandler<>() {
	
	            			@Override
	            			public void handle(javafx.event.ActionEvent arg0) {
	            				//pass partner
	            				ViewManager.getInstance().showLayout(ViewManager.RACES_PAGE, getTableView().getItems().get(getIndex()));
	            			}
	            		});
	                	
	                	//setting button BUY style
	                	btn.setMaxWidth(Double.MAX_VALUE);
	                	btn.setStyle("-fx-background-color: #9b2226;"
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
	    
	    //Payments
	    Callback<TableColumn<Partner, Void>, TableCell<Partner, Void>> cellFactoryPayments = new Callback<TableColumn<Partner, Void>, TableCell<Partner, Void>>() {
	        @Override
	        public TableCell<Partner, Void> call(final TableColumn<Partner, Void> param) {
	            final TableCell<Partner, Void> cell = new TableCell<Partner, Void>() {
	
	                private final Button btn = new Button("Payments");
	
	                {
	                	btn.setOnAction(new EventHandler<>() {
	
	            			@Override
	            			public void handle(javafx.event.ActionEvent arg0) {
	            				//pass partner
	            				ViewManager.getInstance().showLayout(ViewManager.PAYMENTS_OF_PARTNER_PAGE, getTableView().getItems().get(getIndex()));
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
	                        setGraphic(btn);
	                    }
	                }
	            };
	            return cell;
	        }
	    };
	    
	    //Info
	    Callback<TableColumn<Partner, Void>, TableCell<Partner, Void>> cellFactoryInfo = new Callback<TableColumn<Partner, Void>, TableCell<Partner, Void>>() {
	        @Override
	        public TableCell<Partner, Void> call(final TableColumn<Partner, Void> param) {
	            final TableCell<Partner, Void> cell = new TableCell<Partner, Void>() {
	
	                private final Button btn = new Button("info");
	
	                {
	                	btn.setOnAction(new EventHandler<>() {
	
	            			@Override
	            			public void handle(javafx.event.ActionEvent arg0) {
	            				//pass partner
	            				ViewManager.getInstance().showLayout(ViewManager.PARTNER_INFO_PAGE, getTableView().getItems().get(getIndex()));
	            			}
	            		});
	                	
	                	//setting button BUY style
	                	btn.setMaxWidth(Double.MAX_VALUE);
	                	btn.setStyle("-fx-background-color: BLUE;"
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

        colBtnBoats.setCellFactory(cellFactoryBoats);
        colBtnRaces.setCellFactory(cellFactoryRaces);
        colBtnPayments.setCellFactory(cellFactoryPayments);
        colBtnInfo.setCellFactory(cellFactoryInfo);

        tableViewPartners.getColumns().add(colBtnBoats);
        tableViewPartners.getColumns().add(colBtnRaces);
        tableViewPartners.getColumns().add(colBtnPayments);
        tableViewPartners.getColumns().add(colBtnInfo);
	}
	
	/* used to add delete button to tableView */
	private static void addButtonsToTableRaces(TableView<Race> tableViewRaces) {
		TableColumn<Race, Void> colBtnDelete= new TableColumn("Delete");
		TableColumn<Race, Void> colBtnSubscribers= new TableColumn("Subscribers");

		//Delete
        Callback<TableColumn<Race, Void>, TableCell<Race, Void>> cellFactoryDelete = new Callback<TableColumn<Race, Void>, TableCell<Race, Void>>() {
            @Override
            public TableCell<Race, Void> call(final TableColumn<Race, Void> param) {
                final TableCell<Race, Void> cell = new TableCell<Race, Void>() {

                    private final Button btn = new Button("Delete");

                    {
                    	btn.setOnAction(new EventHandler<>() {

                			@Override
                			public void handle(javafx.event.ActionEvent arg0) {
                				ClubStaffManager.getInstance().removeRace(getTableView().getItems().get(getIndex()));
                				
                				//update table
                				tableViewRaces.setItems(FXCollections.observableList(ClubStaffManager.getInstance().getRaces()));
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
        
      //Subscribers
        Callback<TableColumn<Race, Void>, TableCell<Race, Void>> cellFactorySubscribers = new Callback<TableColumn<Race, Void>, TableCell<Race, Void>>() {
            @Override
            public TableCell<Race, Void> call(final TableColumn<Race, Void> param) {
                final TableCell<Race, Void> cell = new TableCell<Race, Void>() {

                    private final Button btn = new Button("Subscribers");

                    {
                    	btn.setOnAction(new EventHandler<>() {

                			@Override
                			public void handle(javafx.event.ActionEvent arg0) {
                				//pass race
	            				ViewManager.getInstance().showLayout(ViewManager.SUBSCRIBERS_PAGE, getTableView().getItems().get(getIndex()));
                			}
                		});
                    	
                    	//setting button BUY style
                    	btn.setMaxWidth(Double.MAX_VALUE);
                    	btn.setStyle("-fx-background-color: BLUE;"
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
        colBtnSubscribers.setCellFactory(cellFactorySubscribers);

        tableViewRaces.getColumns().add(colBtnDelete);
        tableViewRaces.getColumns().add(colBtnSubscribers);
	}

	/* used to set imageView images */
	private static void setImageToImageView(ImageView logout, ImageView payment){
		File file = new File("img/exit.png");
        Image image = new Image(file.toURI().toString());
        logout.setImage(image);
        
        file = new File("img/coin.png");
        image = new Image(file.toURI().toString());
        payment.setImage(image);
	}
}
