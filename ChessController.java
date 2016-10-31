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
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author murphy249
 */
public class ChessController implements Initializable {
    
    @FXML
    private Canvas canvas;
    @FXML
    private Pane container;
    
    public void drawCanvas() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* Canvas follows the size of its containing pane. */
        canvas.widthProperty().bind(container.widthProperty());
        canvas.heightProperty().bind(container.heightProperty());
        
        /* Redraw the canvas when it changes size. */
        canvas.widthProperty().addListener(e -> drawCanvas());
        canvas.heightProperty().addListener(e -> drawCanvas());
    }    
    
}
