package chess;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.ObjectBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.shape.Sphere;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.PerspectiveCamera;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.stage.Modality;
import javafx.scene.transform.*;
import javafx.scene.shape.Box;

public class ChessController implements Initializable {
    @FXML
    private Slider slider;
    @FXML
    private Pane subScenePane;
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

        /* Populate a group of solids with 3D board squares. */
        Group solids = new Group();;

        Material blackMat = new PhongMaterial(Color.BLACK);
        Material whiteMat = new PhongMaterial(Color.WHITE);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Box box = new Box();

                box.setWidth(10);
                box.setHeight(10);
                box.setDepth(2);

                if (((i + j) & 1) == 1) {
                    box.setMaterial(blackMat);
                } else {
                    box.setMaterial(whiteMat);
                }

                Translate translation = new Translate(((double)i - 2.0) * 10,
                                                      ((double)j - 2.0) * 10,
                                                      0);

                box.getTransforms().add(translation);
                solids.getChildren().add(box);
            }
        }

        /* Create an internal scene for the 3D graphics. */
        SubScene sub = new SubScene((Parent)solids, 0, 0, true, null);

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setFarClip(160);

        camera.setTranslateY(80);
        camera.setTranslateZ(-80);

        Rotate dynrot = new Rotate(0, 0, -80, 0);
        dynrot.angleProperty().bind(slider.valueProperty());
        camera.getTransforms().add(dynrot);

        Rotate xrot = new Rotate(45, Rotate.X_AXIS);;
        camera.getTransforms().add(xrot);

        //camera.setRotationAxis(Rotate.X_AXIS);
          //camera.setRotate(40);
        //camera.setRotationAxis(Rotate.Z_AXIS);
          //camera.setRotate(45);
        sub.setCamera(camera);

        sub.widthProperty().bind(subScenePane.widthProperty());
        sub.heightProperty().bind(subScenePane.heightProperty());

        subScenePane.getChildren().add(sub);

    }    
    
}
