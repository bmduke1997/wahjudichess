package chess;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.ObjectBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AboutWindowController implements Initializable {
    
    @FXML
    private Button closeButton;

    @FXML
    private void closeButtonAction() {
        ((Stage)closeButton.getScene().getWindow()).close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
