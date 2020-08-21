package Library.assistant.ui.main;

import Library.assistant.database.DatabaseHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindow extends Application {
    public void start(Stage stage) throws  Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        // call the DatabaseHandler now so the program will load faster after it starts.
        DatabaseHandler.getInstance();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
