package chess;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.ObjectBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.stage.Modality;
import javafx.scene.transform.*;
import javafx.scene.shape.Box;

public class ChessController implements Initializable {
    @FXML
    private Group solids;
    @FXML
    private Pane container;
    @FXML
    private Button undoButton;
    @FXML
    private Button redoButton;

    @FXML
    private void openNewGameWindow() throws Exception {
        URL url = getClass().getResource("NewGameWindow.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        ((NewGameWindowController)loader.getController())
          .setChessController(this);
      
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("New Game...");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void openAboutWindow() throws Exception {
        URL url = getClass().getResource("AboutWindow.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("About Wahjudi Chess");
        stage.setScene(scene);
        stage.show();
    }
    
    public void setupGame(boolean whiteGoesFirst,
                          boolean blackIsHuman,
                          boolean whiteIsHuman) {
        System.out.println("Hello from the main window!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* Undo/redo buttons start out disabled. */
        undoButton.setDisable(true);
        redoButton.setDisable(true);

        /* Make the 3D board display. */
        Material blackMat = new PhongMaterial(Color.BLACK);
        Material whiteMat = new PhongMaterial(Color.WHITE);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Box box = new Box();

                box.setWidth(10);
                box.setHeight(1);
                box.setDepth(10);

                if (((i + j) & 1) == 1) {
                    box.setMaterial(blackMat);
                } else {
                    box.setMaterial(whiteMat);
                }

                Translate translation = new Translate(i * 10,
                                                      100,
                                                      (j - 30) * 10);

                box.getTransforms().add(translation);

                solids.getChildren().add(box);
            }
        }
    }    
    
}
