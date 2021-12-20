/**
 * 
 */
package application.client.managers;

import application.client.controllers.MainPageController;
import application.client.controllers.PaymentPageController;
import application.client.controllers.SignUpPageController;
import application.client.controllers.clubStaff.AddRacePageController;
import application.client.controllers.clubStaff.BoatsPageController;
import application.client.controllers.clubStaff.ClubStaffMainPageController;
import application.client.controllers.clubStaff.PartnerInfoPageController;
import application.client.controllers.clubStaff.RacesPageController;
import application.client.controllers.clubStaff.SendPaymentPageController;
import application.client.controllers.clubStaff.SubscribersPageController;
import application.client.controllers.partner.AddBoatPageController;
import application.client.controllers.partner.BoatsPartnerPageController;
import application.client.controllers.partner.NewRegistrationController;
import application.client.controllers.partner.NotificationsPageController;
import application.client.controllers.partner.PartnerMainPageController;
import application.client.controllers.partner.PaymentsPartnerPageController;
import application.client.controllers.partner.RacesPartnerPageController;
import application.models.Partner;
import application.models.Race;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is used to manage the display of views.
 * 
 * @author Federico Canali
 */
public class ViewManager {
	/* CONSTANST */
	
	/**
	 * Represents the main page string. Used to start being passed to the method {@link #showLayout(String)} to be displayed.
	 **/
	public static final String MAIN_PAGE = "mainPage";
	
	/**
	 * Represents the sign up page string. Used to start being passed to the method {@link #showLayout(String)} to be displayed.
	 **/
	public static final String SIGN_UP_PAGE = "signUpPage";
	/**
	 * Represents the partner main page string. Used to start being passed to the method {@link #showLayout(String)} to be displayed.
	 **/
	public static final String PARTNER_MAIN_PAGE = "partnerMainPage";
	/**
	 * Represents the boats partner page string. Used to start being passed to the method {@link #showLayout(String)} to be displayed.
	 **/
	public static final String BOATS_PARTNER_PAGE = "boatsPartnerPage";
	/**
	 * Represents the add boat page string. Used to start being passed to the method {@link #showLayout(String)} to be displayed.
	 **/
	public static final String ADD_BOAT_PAGE = "addBoatPage";
	/**
	 * Represents the race partner page string. Used to start being passed to the method {@link #showLayout(String)} to be displayed.
	 **/
	public static final String RACE_PARTNER_PAGE = "racePartnerPage";
	/**
	 * Represents the new registration page string. Used to start being passed to the method {@link #showLayout(String)} to be displayed.
	 **/
	public static final String NEW_REGISTRATION_PAGE = "newRegistrationPage";
	/**
	 * Represents the club staff main page string. Used to start being passed to the method {@link #showLayout(String)} to be displayed.
	 **/
	public static final String CLUB_STAFF_MAIN_PAGE = "clubStaffMainPage";
	/**
	 * Represents the partner info page string. Used to start being passed to the method {@link #showLayout(String)} to be displayed.
	 **/
	public static final String PARTNER_INFO_PAGE = "partnerInfoPage";
	/**
	 * Represents the boats page string. Used to start being passed to the method {@link #showLayout(String)} to be displayed.
	 **/
	public static final String BOATS_PAGE = "boatsPage";
	/**
	 * Represents the races page string. Used to start being passed to the method {@link #showLayout(String)} to be displayed.
	 **/
	public static final String RACES_PAGE = "racesPage";
	/**
	 * Represents the subscribers page string. Used to start being passed to the method {@link #showLayout(String)} to be displayed.
	 **/
	public static final String SUBSCRIBERS_PAGE = "subscribersPage";
	/**
	 * Represents the send payment page string. Used to start being passed to the method {@link #showLayout(String)} to be displayed.
	 **/
	public static final String SEND_PAYMENT_PAGE = "sendNotificationPaymentPage";
	/**
	 * Represents the notifications page string. Used to start being passed to the method {@link #showLayout(String)} to be displayed.
	 **/
	public static final String NOTIFICATIONS_PAGE = "notificationsPage";
	/**
	 * Represents the payments partner page string. Used to start being passed to the method {@link #showLayout(String)} to be displayed.
	 **/
	public static final String PAYMENTS_PARTNER_PAGE = "paymentsPartnerPage";
	/**
	 * Represents the add race page string. Used to start being passed to the method {@link #showLayout(String)} to be displayed.
	 **/
	public static final String ADD_RACE_PAGE = "addRacePage";
	
	/* LAYOUT ROOT ELEMENTS */
	private Stage stage;
	
	/*
    * The instance is static so it is shared among all instances of the class. It is also private
    * so it is accessible only within the class.
    */
   private static ViewManager instance = null;

   /*
    * The constructor is private so it is accessible only within the class.
    */
   private ViewManager() {
       
   }

   /**
    * The constructor is called only if the static instance is null, so only the first time
    * that the getInstance() method is invoked.
    * All the other times the same instance object is returned.
    * @return the instance object is returned.
    **/
   public static ViewManager getInstance() {
       if (instance == null)
           instance = new ViewManager();
       return instance;
   }
   
   /**
     * show the root layout.
     * 
     * @param page is the string of the page you want to view.
     */
    public void showLayout(String page) {
        try {
            switch(page) {
            	case MAIN_PAGE:
            		MainPageController.show();
            		return;
            	case SIGN_UP_PAGE:
            		SignUpPageController.show();
            		return;
            	case PARTNER_MAIN_PAGE:
            		PartnerMainPageController.show();
            		return;
            	case BOATS_PARTNER_PAGE:
            		BoatsPartnerPageController.show();
            		return;
            	case ADD_BOAT_PAGE:
            		AddBoatPageController.show();
            		return;
            	case RACE_PARTNER_PAGE:
            		RacesPartnerPageController.show();
            		return;
            	case NEW_REGISTRATION_PAGE:
            		NewRegistrationController.show();
            		return;
            	case CLUB_STAFF_MAIN_PAGE:
            		ClubStaffMainPageController.show();
            		return;
            	case SEND_PAYMENT_PAGE:
            		SendPaymentPageController.show();
        			return;
            	case NOTIFICATIONS_PAGE:
            		NotificationsPageController.show();
            		return;
            	case PAYMENTS_PARTNER_PAGE:
            		PaymentsPartnerPageController.show();
            		return;
            	case ADD_RACE_PAGE:
            		AddRacePageController.show();
            		return;
        		default:
        			System.out.println("No page was found to display.");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * show the payment layout.
     * @param price
     * @param objectToInsert
     * @param operation
     */
    public void showLayout(float price, Object objectToInsert, String operation) {
        try {
        	
            PaymentPageController.show(price, objectToInsert, operation);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * show the club staff info layout.
     * @param partner
     */
    public void showLayout(String page, Object object) {
        try {
        	switch(page) {
	        	case PARTNER_INFO_PAGE:
	        		PartnerInfoPageController.show((Partner) object);
	        		return;
	        	case BOATS_PAGE:
	        		BoatsPageController.show((Partner) object);
	        		return;
	        	case RACES_PAGE:
	        		RacesPageController.show((Partner) object);
	        		return;
	        	case SUBSCRIBERS_PAGE:
	        		SubscribersPageController.show((Race) object);
	        		return;
	        	default:
	    			System.out.println("No page was found to display.");
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Used to initialize stage and main view group (e.g. AnchorPane)
     * and the start first page of boot.
     * 
     * @param primaryStage is the main root stage.
     **/
    public void initAndStart(Stage primaryStage) {
    	this.stage = primaryStage;
        this.stage.setTitle("Sailing Club");
        this.stage.setMinHeight(540);
        this.stage.setMinWidth(515);

        showLayout(MAIN_PAGE);
    }
    
    /**
     * Used to set scene of stage and show it.
     * 
     * @param scene is the main root scene.
     **/
    public void setScene(Scene scene) {
    	this.stage.setScene(scene);
        this.stage.show();
    }

}
