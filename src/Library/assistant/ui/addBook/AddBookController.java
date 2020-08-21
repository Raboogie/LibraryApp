package Library.assistant.ui.addBook;

import Library.assistant.database.DatabaseHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;


public class AddBookController implements Initializable {
    @FXML
    private JFXTextField bookTitle;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField author;

    @FXML
    private JFXTextField publisher;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private AnchorPane rootPane;

    DatabaseHandler databaseHandler;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        databaseHandler = DatabaseHandler.getInstance();
        checkData();
    }

    @FXML
    private void addBook(ActionEvent event) {
        String bookID = id.getText();
        String bookAuthor = author.getText();
        String bookName = bookTitle.getText();
        String bookPublisher = publisher.getText();

        // If any of the field are empty,dont add the book and abort processing the book.
        if (bookID.isBlank() || bookID.isEmpty() || bookAuthor.isBlank() || bookAuthor.isEmpty() || bookName.isBlank()
                || bookName.isEmpty() || bookPublisher.isBlank() || bookPublisher.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
            return;
        }

        String qu = "INSERT INTO BOOK VALUES ("+
                "'" + bookID + "',"+
                "'" + bookName + "',"+
                "'" + bookAuthor + "',"+
                "'" + bookPublisher + "',"+
                "" + true + ""+
                ")";
        System.out.println(qu);
        if (databaseHandler.execAction(qu)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Successful");
            alert.showAndWait();
        } else {
            // Error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failed");
            alert.showAndWait();
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    private void checkData() {
        String qu = "SELECT title FROM BOOK";
        ResultSet rs = databaseHandler.execQuery(qu);
        try {
            while (rs.next()) {
                String titleX = rs.getString("title");
                System.out.println(titleX);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddBookController.class.getName()).log(SEVERE, null, ex);
        }
    }
}
