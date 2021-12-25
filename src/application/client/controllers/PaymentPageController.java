/**
 * 
 */
package application.client.controllers;

import java.io.File;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import application.classes.TypeOfPaymentMethod;
import application.client.Client;
import application.client.managers.AlertManager;
import application.client.managers.ClientManager;
import application.client.managers.PaymentsManager;
import application.client.managers.UserManager;
import application.client.managers.ViewManager;
import application.models.CreditCard;
import application.models.MembershipFee;
import application.models.Payment;
import application.models.Registration;
import application.models.StorageFee;
import application.models.SubscriptionFee;
import application.models.ToPay;
import application.models.WireTransfer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * The class represents the fxml screen controller
 * of payment page.
 * 
 * Handles events and configures the screen. In particular, this controller 
 * manages the payments that the partners will have to make.
 * 
 * Controller related to the fxml file is Payment.fxml inside {@link application.client.views}.
 * 
 * @author Federico Canali
 */
public class PaymentPageController {
	
	/* CONSTANST OF OPEATION */
	/**
	 * Represents String of payment the payment of the subscription fee for a race.
	 **/
	public static final String OPERATION_SUBSCRIPTION_FEE = "operationSubscriptionFee";
	/**
	 * Represents String of payment the payment of the membership fee.
	 **/
	public static final String OPERATION_MEMBERSHIP_FEE = "operationMembershipFee";
	/**
	 * Represents String of payment the payment of the storage fee.
	 **/
	public static final String OPERATION_STORAGE_FEEE = "operationStorageFee";
	
	/**
	 * Used to show the screen.
	 * @param price It represents the price to pay for this operation.
	 * @param objectToHandle Represents a possible object to manage once passed. It depends on the type of payment.
	 * @param operation Represents the type of payment that will have to be made.
	 **/
	public static void show(float price, Object objectToHandle, String operation) {
		try {
			// Load root layout from fxml file.
			FXMLLoader loaderStart = new FXMLLoader();
			loaderStart.setLocation(Client.class.getResource("views/Payment.fxml"));
			AnchorPane rootPane = (AnchorPane) loaderStart.load();
	        
	        // Show the scene containing the root layout.
	        Scene scene = new Scene(rootPane);
	        
	        //Initialize components
	        ChoiceBox<String> paymentMethod = (ChoiceBox<String>) scene.lookup("#paymentMethod");
	        TextField ownerName = (TextField) scene.lookup("#ownerName");
	        TextField ownerSurname = (TextField) scene.lookup("#ownerSurname");
	        Label priceLabel = (Label) scene.lookup("#price");
	        AnchorPane creditCardPane = (AnchorPane) scene.lookup("#creditCard");
	        AnchorPane wireTransferPane = (AnchorPane) scene.lookup("#wireTransfer");
	        Button btnPay = (Button) scene.lookup("#btnPay");
	        ImageView btnBack = (ImageView) scene.lookup("#back");
	        
	        //set price inside the label
	        priceLabel.setText(price + "€");
	        
	        //fill the owner name and the owner surname with the user logged in
	        ownerName.setText(UserManager.getInstance().getPerson().getName());
	        ownerSurname.setText(UserManager.getInstance().getPerson().getSurname());
	        
	        manageChoiceBox(paymentMethod, creditCardPane, wireTransferPane);
	        
	        setImageToImageView(btnBack);
	        buttonCallBack(btnPay, btnBack, ownerName, ownerSurname, paymentMethod, scene, price
	        		, objectToHandle, operation);
	        
	        ViewManager.getInstance().setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* used to add some values to choice box and attach listener */
	private static void manageChoiceBox(ChoiceBox<String> paymentMethod, AnchorPane creditCardPane, AnchorPane wireTransferPane) {
		//add some values
		paymentMethod.getItems().add(TypeOfPaymentMethod.CREDIT_CARD);
		paymentMethod.getItems().add(TypeOfPaymentMethod.WIRE_TRANSFER);
		
		//set the default selection
		paymentMethod.setValue(TypeOfPaymentMethod.CREDIT_CARD);
		
		//attach listener on change
		paymentMethod.setOnAction((event) -> {
			//set visibility of credit card anchor pane
			creditCardPane.setVisible(paymentMethod.getSelectionModel()
					.getSelectedItem().equals(TypeOfPaymentMethod.CREDIT_CARD));
			
			//set visibility of wire transfer anchor pane
			wireTransferPane.setVisible(paymentMethod.getSelectionModel()
					.getSelectedItem().equals(TypeOfPaymentMethod.WIRE_TRANSFER));
				
		});
	}
	
	/* used to implements callback of buttons */
	private static void buttonCallBack(Button btnPay, ImageView btnBack, TextField ownerName, 
		TextField ownerSurname, ChoiceBox<String> paymentMethod, Scene scene, float price, 
		Object objectToHandle, String operation) {
	
		btnPay.setOnAction((event) -> {
			
			 TextField emissionDate = (TextField) scene.lookup("#emissionDate");
			 TextField referenceCode = (TextField) scene.lookup("#referenceCode");
			 TextField cardNumber = (TextField) scene.lookup("#cardNumber");
			 TextField cvv = (TextField) scene.lookup("#cvv");
			 TextField expirationDate = (TextField) scene.lookup("#expirationDate");
			 
			 //reset all field error
			 emissionDate.setStyle(null); referenceCode.setStyle(null); cardNumber.setStyle(null);
			 cvv.setStyle(null); expirationDate.setStyle(null); ownerName.setStyle(null);
			 ownerSurname.setStyle(null);
			 
			 boolean areFilledCorrectly = true;
			 String errorMessageString = "";
			 
			 if(ownerName.getText().isEmpty()) {
				 areFilledCorrectly = false;
				 changeBorderColor(ownerName);
				 errorMessageString += "Owner name is empty!\n";
			 }
			 
			 if(ownerSurname.getText().isEmpty()) {
				 areFilledCorrectly = false;
				 changeBorderColor(ownerSurname);
				 errorMessageString += "Owner surname is empty!\n";
			 }
			 
			 if(paymentMethod.getValue().equals(TypeOfPaymentMethod.CREDIT_CARD)) {
				 
				 if(cardNumber.getText().isEmpty()) {
					 areFilledCorrectly = false;
					 changeBorderColor(cardNumber);
					 errorMessageString += "Card number is empty!\n";
				 } else {
					 // test if card number is a positive number
					try{
						if(Long.parseLong(cardNumber.getText()) <= 0) {
							areFilledCorrectly = false;
							changeBorderColor(cardNumber);
							errorMessageString += "Card number is zero or a negative number!\n";
						}
					} catch (NumberFormatException ex) {
					    System.out.println("Is not a number...");
					    areFilledCorrectly = false;
					    changeBorderColor(cardNumber);
					    errorMessageString += "Card number is not a number!\n";
					}
				 }
				 
				 if(cvv.getText().length() != 3) {
					 areFilledCorrectly = false;
					 changeBorderColor(cvv);
					 errorMessageString += "CVV length must be 3 digits!\n";
				 } else {
					 // test if card number is a positive number
					try{
						if(Long.parseLong(cardNumber.getText()) <= 0) {
							areFilledCorrectly = false;
							changeBorderColor(cvv);
							errorMessageString += "CVV can't be a negative number!\n";
						}
					} catch (NumberFormatException ex) {
					    System.out.println("Is not a number...");
					    areFilledCorrectly = false;
					    changeBorderColor(cvv);
					    errorMessageString += "CVV is not a number!\n";
					}
				 }
				 
				 if(expirationDate.getText().isEmpty()) {
					 areFilledCorrectly = false;
					 changeBorderColor(expirationDate);
					 errorMessageString += "Expiration date is empty!\n";
				 } else {
					 try {
						 //used to check if is ok, so if a date and it's after today
						 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
						 simpleDateFormat.setLenient(false);
						 Calendar calendar = Calendar.getInstance();
						 Date nowDate = new Date(calendar.getTimeInMillis());
						 java.util.Date expiry = simpleDateFormat.parse(expirationDate.getText());
						 
						 if(expiry.before(nowDate)) {
							 areFilledCorrectly = false;
							 changeBorderColor(expirationDate);
							 errorMessageString += "Expiration date is expired!\n";
						 }
					 } catch (Exception e) {
						 areFilledCorrectly = false;
						 changeBorderColor(expirationDate);
						 errorMessageString += "Expiration date is not filled correctly (format: MM/yy)!\n";
					}
				 }
				 
			 } else { // Wire Transfer check
				 
				 if(referenceCode.getText().isEmpty()) {
					 areFilledCorrectly = false;
					 changeBorderColor(referenceCode);
					 errorMessageString += "Reference code is empty!\n";
				 }
				 
				 if(emissionDate.getText().isEmpty()) {
					 areFilledCorrectly = false;
					 changeBorderColor(emissionDate);
					 errorMessageString += "Emission date is empty!\n";
				 } else {
					 try {
						 //used to check if is ok, so if a date and it's after today
						 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
						 simpleDateFormat.setLenient(false);
						 Calendar calendar = Calendar.getInstance();
						 Date nowDate = new Date(calendar.getTimeInMillis());
						 java.util.Date emission = simpleDateFormat.parse(emissionDate.getText());
						 
						 if(emission.after(nowDate)) {
							 areFilledCorrectly = false;
							 changeBorderColor(emissionDate);
							 errorMessageString += "Emission date is not valid!\n";
						 }
					 } catch (Exception e) {
						 areFilledCorrectly = false;
						 changeBorderColor(emissionDate);
						 errorMessageString += "Emission date is not filled correctly (format: dd/MM/YY)!\n";
					}
				 }
				 
			 }
			 
			 
			 if(areFilledCorrectly) {
				 Calendar calendar = Calendar.getInstance();
				 Date nowDate = new Date(calendar.getTimeInMillis());
						 
				 Payment payment = new Payment(UserManager.getInstance().getPerson().getId(), price, nowDate, null);
				 
				 switch (operation) {
					case OPERATION_SUBSCRIPTION_FEE:
						Registration registration = (Registration) objectToHandle;
						payment = new SubscriptionFee(UserManager.getInstance().getPerson().getId()
								, price, nowDate, null, registration.getRace().getId(), registration.getBoat().getId());
						break;
					case OPERATION_MEMBERSHIP_FEE:
						payment = new MembershipFee(UserManager.getInstance().getPerson().getId()
								, price, nowDate, null);
						break;
					case OPERATION_STORAGE_FEEE:
						payment = new StorageFee(UserManager.getInstance().getPerson().getId(), price, nowDate, null, ((ToPay) objectToHandle).getBoatId());
						break;
					default:
						throw new IllegalArgumentException("Unexpected value: " + operation);
				}
				 
				 if(paymentMethod.getValue().equals(TypeOfPaymentMethod.CREDIT_CARD)) {
					 CreditCard creditCard = new CreditCard(ownerName.getText()
							 , ownerSurname.getText(), Integer.parseInt(cvv.getText())
							 , Long.parseLong(cardNumber.getText()), expirationDate.getText());
					 
					 payment.setPaymentMethod(creditCard);
				 } else {
					 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
					 simpleDateFormat.setLenient(false);
					 java.util.Date emission;
					 try {
						emission = simpleDateFormat.parse(emissionDate.getText());
						 java.sql.Date sqlDate = new java.sql.Date(emission.getTime());
						 
						 WireTransfer wireTransfer = new WireTransfer(ownerName.getText()
								 , ownerSurname.getText(), sqlDate, referenceCode.getText());
						 
						 payment.setPaymentMethod(wireTransfer);
					 } catch (ParseException e) {
						return;
					 }
				 }
				 
				 //send request of payment to server
				 ClientManager.getInstance().insertPayment(payment);
				 
				 //insert other item in DB
				 handleOtherObject(objectToHandle, operation);
				 
				 //return to main page
				 AlertManager.getInstance().showInfoMessage("Payment made correctly!");
				 ViewManager.getInstance().showLayout(ViewManager.PARTNER_MAIN_PAGE);
				 
			 } else {
				 AlertManager.getInstance().showErrorMessage(errorMessageString);
			 }
			 
			 
		 });
		 
		 btnBack.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

	            @Override
	            public void handle(MouseEvent event) {
	                ViewManager.getInstance().showLayout(ViewManager.PARTNER_MAIN_PAGE);
	            }
	        });
	}
	
	/* used to handle other item in DB afer paid the price */
	private static void handleOtherObject(Object object, String operation) {
		
		switch (operation) {
			case OPERATION_SUBSCRIPTION_FEE:
				insertSubscriptionFee((Registration) object);
				PaymentsManager.getInstance().refreshAfterPaidSubscriptionFee();
				return;
			case OPERATION_MEMBERSHIP_FEE:
				deleteToPay((ToPay) object);
				PaymentsManager.getInstance().refreshAfterPaidMembershipFee();
				return;
			case OPERATION_STORAGE_FEEE:
				deleteToPay((ToPay) object);
				PaymentsManager.getInstance().refreshAfterPaidStorageFee();
				return;
			default:
				throw new IllegalArgumentException("Unexpected value: " + operation);
		}
	}
	
	/* insert subscription fee */
	private static void insertSubscriptionFee(Registration registration) {
		//add new registration
		UserManager.getInstance().addRegistration(registration);
	}
	
	/* delete toPay */
	private static void deleteToPay(ToPay toPay) {
		ClientManager.getInstance().deleteToPay(toPay);
	}
	
	/* used to set imageView images */
	private static void setImageToImageView(ImageView bntBack){
		File file = new File("img/back-arrow.png");
        Image image = new Image(file.toURI().toString());
        bntBack.setImage(image);
	}
	
	/* used to change the color of the textField due to a fill error */
	private static void changeBorderColor(TextField field) {
		field.setStyle("-fx-border-color: RED");
	}
}
