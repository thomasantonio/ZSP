package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


	public class Main extends Application {
		@Override
		public void start(Stage primaryStage) {
			primaryStage.setTitle("Z�hlerstand");
			try {
				GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("MainView.fxml"));
				Scene scene = new Scene(root,500,400 );
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void main(String[] args) {
			launch(args);
		}
}
