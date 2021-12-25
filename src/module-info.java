
/**
 * 
 */
module final_assignment {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires java.desktop;
	requires javafx.graphics;
	requires junit;
	requires org.junit.jupiter.api;
	
	opens application.client to javafx.graphics, javafx.fxml;
}
