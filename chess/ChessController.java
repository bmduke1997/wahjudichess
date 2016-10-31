/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.ObjectBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

/**
 *
 * @author murphy249
 */
public class ChessController implements Initializable {
    
    @FXML
    private Canvas canvas;
    @FXML
    private Pane container;
    @FXML
    private Button undoButton;
    @FXML
    private Button redoButton;

    @FXML
    private void openNewGameWindow() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("NewGameWindow.fxml"));
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setTitle("New Game...");
        stage.setScene(scene);
        stage.show();
    }
    
    private void drawCanvas() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* Undo/redo buttons start out disabled. */
        undoButton.setDisable(true);
        redoButton.setDisable(true);

        /* Canvas follows the size of its containing pane. */
        canvas.widthProperty().bind(container.widthProperty());
        canvas.heightProperty().bind(container.heightProperty());
        
        /* Redraw the canvas when it changes size. */
        canvas.widthProperty().addListener(e -> drawCanvas());
        canvas.heightProperty().addListener(e -> drawCanvas());
    }    
    
}
