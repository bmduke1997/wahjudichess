package chess.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NewGameWindowController implements Initializable {
    private BooleanBinding whiteGoesFirst;
    private BooleanBinding blackIsHuman;
    private BooleanBinding whiteIsHuman;
    private ChessController chessController;

    @FXML
    private ComboBox<String> blackTypeComboBox;

    @FXML
    private ComboBox<String> whiteTypeComboBox;

    @FXML
    private Button cancelButton;

    @FXML
    private Button playButton;

    @FXML
    private ToggleGroup whoGoesFirst;

    @FXML
    private void playButtonAction() {
        chessController.setupGame(whiteGoesFirst.get(),
                                  blackIsHuman.get(),
                                  whiteIsHuman.get());
        ((Stage)playButton.getScene().getWindow()).close();
    }

    @FXML
    private void cancelButtonAction() {
        ((Stage)cancelButton.getScene().getWindow()).close();
    }

    public void setChessController(ChessController c) {
        chessController = c;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chessController = null;

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

        /* Bond that turns the type of player of black into a bool. */
        blackIsHuman = new BooleanBinding() {
            {
                super.bind(blackTypeComboBox.valueProperty());
            }

            @Override
            protected boolean computeValue() {
                String value;

                value = (String)blackTypeComboBox.getValue();
                if (value.equals("Human")) {
			return true;
		} else {
			return false;
		}
            }
        };

        /* Bond that turns the type of player of white into a bool. */
        whiteIsHuman = new BooleanBinding() {
            {
                super.bind(whiteTypeComboBox.valueProperty());
            }

            @Override
            protected boolean computeValue() {
                String value;

                value = (String)whiteTypeComboBox.getValue();
                if (value.equals("Human")) {
			return true;
		} else {
			return false;
		}
            }
        };
    }    
}
