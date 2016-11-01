package chess;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NewGameWindowController implements Initializable {
    private BooleanBinding whiteGoesFirst;

    @FXML
    private Button closeButton;

    @FXML
    private Button playButton;

    @FXML
    private ToggleGroup whoGoesFirst;

    @FXML
    private void playButtonAction() {
        System.out.println((whiteGoesFirst.get() ? "White" : "Black")
                           + " goes first.");
        ((Stage)playButton.getScene().getWindow()).close();
    }

    @FXML
    private void closeButtonAction() {
        ((Stage)closeButton.getScene().getWindow()).close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* Bind whiteGoesFirst to the state of the radio buttons. */
        whiteGoesFirst = new BooleanBinding() {
            {
                super.bind(whoGoesFirst.selectedToggleProperty());
            }

            @Override
            protected boolean computeValue() {
                String value;

                value = (String)whoGoesFirst.getSelectedToggle().getUserData();
                if (value.equals("White")) {
			return true;
		} else {
			return false;
		}
            }
        };
    }    
}
