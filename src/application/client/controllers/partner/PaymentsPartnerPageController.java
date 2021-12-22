/**
 * 
 */
package application.client.controllers.partner;

import java.io.File;

import application.client.Client;
import application.client.controllers.PaymentPageController;
import application.client.managers.PaymentsManager;
import application.client.managers.ViewManager;
import application.models.CreditCard;
import application.models.MembershipFee;
import application.models.StorageFee;
import application.models.SubscriptionFee;
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
public class PaymentsPartnerPageController {
	/**
	 * Used to show the screen. 
	 **/
	public static void show() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loaderStart = new FXMLLoader();
			loaderStart.setLocation(Client.class.getResource("views/partner/PaymentsPartnerPage.fxml"));
			AnchorPane rootPane = (AnchorPane) loaderStart.load();
	        
	        // Show the scene containing the root layout.
	        Scene scene = new Scene(rootPane);
	        
	        //Initialize components
	        ImageView back = (ImageView) scene.lookup("#back");
	        ImageView payment = (ImageView) scene.lookup("#payment");
	        
	        setImageToImageView(back, payment);
	        
	        buttonCallBack(back);
	        
	        // table set up to pay
	        TableView<ToPay> tableViewToPay = (TableView<ToPay>) scene.lookup("#toPay");
	        
	        TableColumn<ToPay, String> column0 = new TableColumn<>("id");
	        column0.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getId())));
	        column0.setStyle("-fx-alignment: CENTER");
	        
	        TableColumn<ToPay, String> column1 = new TableColumn<>("type");
	        column1.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().isMembershipFee() ? "Membership fee" : "Storage fee"));
	        column1.setStyle("-fx-alignment: CENTER");


	        TableColumn<ToPay, String> column2 = new TableColumn<>("price");
	        column2.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getPrice()) + "€"));
	        column2.setStyle("-fx-alignment: CENTER");
	        
	        TableColumn<ToPay, String> column3 = new TableColumn<>("boat id");
	        column3.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getBoatId() != 0 ? String.valueOf(c.getValue().getBoatId()) : "-"));
	        column3.setStyle("-fx-alignment: CENTER");

	        tableViewToPay.getColumns().add(column0);
	        tableViewToPay.getColumns().add(column1);
	        tableViewToPay.getColumns().add(column2);
	        tableViewToPay.getColumns().add(column3);
	        
	        //add button pay to tableViewToPay
	        addButtonsToTable(tableViewToPay);
	        
	        tableViewToPay.setItems(FXCollections.observableList(PaymentsManager.getInstance().getToPays()));
	        
	        // table set up membership fee
	        TableView<MembershipFee> tableViewMembershipFee = (TableView<MembershipFee>) scene.lookup("#membershipFee");
	        
	        TableColumn<MembershipFee, String> column_m0 = new TableColumn<>("id");
	        column_m0.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getId())));
	        column_m0.setStyle("-fx-alignment: CENTER");
	        
	        TableColumn<MembershipFee, String> column_m1 = new TableColumn<>("payment date");
	        column_m1.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getDate().toString()));
	        column_m1.setStyle("-fx-alignment: CENTER");


	        TableColumn<MembershipFee, String> column_m2 = new TableColumn<>("price");
	        column_m2.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getPrice()) + "€"));
	        column_m2.setStyle("-fx-alignment: CENTER");
	        
	        TableColumn<MembershipFee, String> column_m3 = new TableColumn<>("payment method");
	        column_m3.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getPaymentMethod() instanceof CreditCard ? "Credit card" : "Wire transfer"));
	        column_m3.setStyle("-fx-alignment: CENTER");

	        tableViewMembershipFee.getColumns().add(column_m0);
	        tableViewMembershipFee.getColumns().add(column_m1);
	        tableViewMembershipFee.getColumns().add(column_m2);
	        tableViewMembershipFee.getColumns().add(column_m3);
	        
	        tableViewMembershipFee.setItems(FXCollections.observableList(PaymentsManager.getInstance().getMembershipFees()));
	        
	        // table set up storage fee
	        TableView<StorageFee> tableViewStorageFee = (TableView<StorageFee>) scene.lookup("#storageFee");
	        
	        TableColumn<StorageFee, String> column_s0 = new TableColumn<>("id");
	        column_s0.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getId())));
	        column_s0.setStyle("-fx-alignment: CENTER");
	        
	        TableColumn<StorageFee, String> column_s1 = new TableColumn<>("boat id");
	        column_s1.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getBoatId())));
	        column_s1.setStyle("-fx-alignment: CENTER");
	        
	        TableColumn<StorageFee, String> column_s2 = new TableColumn<>("payment date");
	        column_s2.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getDate().toString()));
	        column_s2.setStyle("-fx-alignment: CENTER");


	        TableColumn<StorageFee, String> column_s3 = new TableColumn<>("price");
	        column_s3.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getPrice()) + "€"));
	        column_s3.setStyle("-fx-alignment: CENTER");
	        
	        TableColumn<StorageFee, String> column_s4 = new TableColumn<>("payment method");
	        column_s4.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getPaymentMethod() instanceof CreditCard ? "Credit card" : "Wire transfer"));
	        column_s4.setStyle("-fx-alignment: CENTER");

	        tableViewStorageFee.getColumns().add(column_s0);
	        tableViewStorageFee.getColumns().add(column_s1);
	        tableViewStorageFee.getColumns().add(column_s2);
	        tableViewStorageFee.getColumns().add(column_s3);
	        tableViewStorageFee.getColumns().add(column_s4);
	        
	        tableViewStorageFee.setItems(FXCollections.observableList(PaymentsManager.getInstance().getStorageFees()));
	        
	        // table set up subscription fee
	        TableView<SubscriptionFee> tableViewSubscriptionFee = (TableView<SubscriptionFee>) scene.lookup("#subscriptionFee");
	        
	        TableColumn<SubscriptionFee, String> column_sb0 = new TableColumn<>("id");
	        column_sb0.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getId())));
	        column_sb0.setStyle("-fx-alignment: CENTER");
	        
	        TableColumn<SubscriptionFee, String> column_sb1 = new TableColumn<>("boat id");
	        column_sb1.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getBoatId())));
	        column_sb1.setStyle("-fx-alignment: CENTER");
	        
	        TableColumn<SubscriptionFee, String> column_sb2 = new TableColumn<>("race id");
	        column_sb2.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getRaceId())));
	        column_sb2.setStyle("-fx-alignment: CENTER");
	        
	        TableColumn<SubscriptionFee, String> column_sb3 = new TableColumn<>("payment date");
	        column_sb3.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getDate().toString()));
	        column_sb3.setStyle("-fx-alignment: CENTER");


	        TableColumn<SubscriptionFee, String> column_sb4 = new TableColumn<>("price");
	        column_sb4.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getPrice()) + "€"));
	        column_sb4.setStyle("-fx-alignment: CENTER");
	        
	        TableColumn<SubscriptionFee, String> column_sb5 = new TableColumn<>("payment method");
	        column_sb5.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getPaymentMethod() instanceof CreditCard ? "Credit card" : "Wire transfer"));
	        column_sb5.setStyle("-fx-alignment: CENTER");

	        tableViewSubscriptionFee.getColumns().add(column_sb0);
	        tableViewSubscriptionFee.getColumns().add(column_sb1);
	        tableViewSubscriptionFee.getColumns().add(column_sb2);
	        tableViewSubscriptionFee.getColumns().add(column_sb3);
	        tableViewSubscriptionFee.getColumns().add(column_sb4);
	        tableViewSubscriptionFee.getColumns().add(column_sb5);
	        
	        tableViewSubscriptionFee.setItems(FXCollections.observableList(PaymentsManager.getInstance().getSubscriptionFees()));
	        
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
	
	/* used to add pay button to tableView */
	private static void addButtonsToTable(TableView<ToPay> tableViewToPay) {
		TableColumn<ToPay, Void> colBtnPay = new TableColumn("Pay");

        Callback<TableColumn<ToPay, Void>, TableCell<ToPay, Void>> cellFactoryPay = new Callback<TableColumn<ToPay, Void>, TableCell<ToPay, Void>>() {
            @Override
            public TableCell<ToPay, Void> call(final TableColumn<ToPay, Void> param) {
                final TableCell<ToPay, Void> cell = new TableCell<ToPay, Void>() {

                    private final Button btn = new Button("Pay");

                    {
                    	btn.setOnAction(new EventHandler<>() {

                			@Override
                			public void handle(javafx.event.ActionEvent arg0) {
                				ToPay toPay = getTableView().getItems().get(getIndex());
                				
                				//go to payment page and handle payment
                				ViewManager.getInstance().showLayout(toPay.getPrice()
                						, toPay, toPay.isMembershipFee() ? PaymentPageController.OPERATION_MEMBERSHIP_FEE : PaymentPageController.OPERATION_STORAGE_FEEE);
                			}
                		});
                    	
                    	//setting button BUY style
                    	btn.setMaxWidth(Double.MAX_VALUE);
                    	btn.setStyle("-fx-background-color: GREEN;"
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

        colBtnPay.setCellFactory(cellFactoryPay);

        tableViewToPay.getColumns().add(colBtnPay);
	}
	
	/* used to set imageView images */
	private static void setImageToImageView(ImageView back, ImageView payment){
		File file = new File("img/back-arrow.png");
        Image image = new Image(file.toURI().toString());
        back.setImage(image);
        
        File fileRace = new File("img/coin.png");
        Image imageRace = new Image(fileRace.toURI().toString());
        payment.setImage(imageRace);
	}
}
