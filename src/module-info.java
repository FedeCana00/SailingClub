module final_assignment {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires java.desktop;
	requires javafx.graphics;
	
	opens application.client to javafx.graphics, javafx.fxml;
}
