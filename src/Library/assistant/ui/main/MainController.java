package Library.assistant.ui.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

public class MainController implements Initializable {
    @FXML
    private void loadAddBook(ActionEvent event) {
        loadWindow("/Library/assistant/ui/addBook/AddBookMenu.fxml", "Add new Book");
    }

    @FXML
    private void loadAddMember(ActionEvent event) {
        loadWindow("/Library/assistant/ui/addMember/AddMember.fxml","Add new Member");
    }

    @FXML
    private void loadBookTable(ActionEvent event) {
        loadWindow("/Library/assistant/ui/listBook/BookList.fxml","Book List");
    }

    @FXML
    private void loadMemberTable(ActionEvent event) {
        loadWindow("/Library/assistant/ui/memberList/MemberList.fxml","Member List");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loadWindow(String location, String title) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(location));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (IOException e) {
            Logger.getLogger(MainController.class.getName()).log(SEVERE, null, e);
            //e.printStackTrace();
        }
    }


}
