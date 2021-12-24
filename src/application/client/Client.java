package application.client;
	
import application.client.managers.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class is the main class of the client.
 * 
 * @author Federico Canali
 */
public class Client extends Application {
	
	/**
	 * {@inheritDoc} 
	 */
	@Override
    public void start(Stage primaryStage) {
        
    	ViewManager.getInstance().initAndStart(primaryStage);
    }

    /**
     * Used to launch the main page activity.
     * @param args is the argument of String array.
     **/
	public static void main(String[] args) {
		launch(args);
	}
}