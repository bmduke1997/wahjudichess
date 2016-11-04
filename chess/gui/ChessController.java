package chess.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.SimpleFloatProperty;
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
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

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
    private ImageView imgView;

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
        System.out.println("ChessController got message about new game.");
        System.out.println("First player: " + (whiteGoesFirst ? "white" : "black"));
        System.out.println("Black is " + (blackIsHuman ? "human" : "machine"));
        System.out.println("White is " + (whiteIsHuman ? "human" : "machine"));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* Undo/redo buttons start out disabled. */
        undoButton.setDisable(true);
        redoButton.setDisable(true);

        /* Declare our group of solid shapes. */
        Group solids = new Group();;

        /* Initialize color materials for our tiles. */
        Material blackMat = new PhongMaterial(Color.web("#000"));
        Material hiblackMat = new PhongMaterial(Color.web("#666"));
        Material whiteMat = new PhongMaterial(Color.web("#aaa"));
        Material hiwhiteMat = new PhongMaterial(Color.web("#fff"));
        Material redMat = new PhongMaterial(Color.web("#f00"));
        Material greenMat = new PhongMaterial(Color.web("#0f0"));
        Material blueMat = new PhongMaterial(Color.web("#00f"));
        
        /* Add test meshes to the board. */
        PawnMeshView pawnMeshView = new PawnMeshView();
        pawnMeshView.setMaterial(redMat);
        pawnMeshView.setTranslateZ(-2);
        solids.getChildren().add(pawnMeshView);

        RookMeshView rookMeshView = new RookMeshView();
        rookMeshView.setMaterial(greenMat);
        rookMeshView.setTranslateX(-10);
        rookMeshView.setTranslateZ(-2);
        solids.getChildren().add(rookMeshView);

        KnightMeshView knightMeshView = new KnightMeshView();
        knightMeshView.setMaterial(blueMat);
        knightMeshView.setTranslateX(10);
        knightMeshView.setTranslateZ(-2);
        solids.getChildren().add(knightMeshView);

        /* Populate the group with boxes to make the checkerboard. */
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Box box = new Box();

                box.setWidth(10);
                box.setHeight(10);
                box.setDepth(2);

                /* Color each tile black or white. */
                if (((i + j) & 1) == 1) {
                    box.setMaterial(blackMat);
                } else {
                    box.setMaterial(whiteMat);
                }

                /* Save the tile's position to pass to its handlers. */
                int[] pos = new int[2];

                pos[0] = i;
                pos[1] = j;

                box.setUserData(pos);

                /* Hilight the row/column of hovered tiles. */
                box.setOnMouseEntered(e -> {
                    int[] passedPos = (int[])box.getUserData();

                    if (((passedPos[0] + passedPos[1]) & 1) == 1) {
                        box.setMaterial(hiblackMat);
                    } else {
                        box.setMaterial(hiwhiteMat);
                    }
                });

                /* Darken when mouse leaves. */
                box.setOnMouseExited(e -> {
                    int[] passedPos = (int[])box.getUserData();

                    if (((passedPos[0] + passedPos[1]) & 1) == 1) {
                        box.setMaterial(blackMat);
                    } else {
                        box.setMaterial(whiteMat);
                    }
                });

                /* Make tiles react to clicking. */
                box.setOnMouseClicked(e -> {
                    int[] passedPos = (int[])box.getUserData();
                    System.out.println("Tile clicked: "
                                       + pos[0] + " " + pos[1]);
                    
                    for (Node subSceneAsNode : subScenePane.getChildren()) {
                        WritableImage img =
                            ((SubScene)subSceneAsNode).snapshot(null, null);
                        
                        imgView.setImage(img);
                        imgView.setPreserveRatio(true);
                        imgView.fitWidthProperty().bind(subScenePane.widthProperty().multiply(0.25));
                        
                        subScenePane.getChildren().addAll(imgView);
                    }
                });

                /* Position the tile on the board. */
                Translate translation = new Translate(((double)i - 2.0) * 10,
                                                      ((double)j - 2.0) * 10,
                                                      0);

                box.getTransforms().add(translation);
                solids.getChildren().add(box);
            }
        }

        /* Create an internal scene for the 3D graphics. */
        SubScene sub = new SubScene((Parent)solids, 0, 0, true, null);

        /* Declare the camera we will use for the board. */
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setFarClip(160);

        /* Put the camera at a distance from the board. */
        camera.setTranslateY(80);
        camera.setTranslateZ(-80);

        /* Make the slide-able rotation for the board. */
        Rotate rotate = new Rotate(0, 0, -80, 0);
        rotate.angleProperty().bind(slider.valueProperty());
        camera.getTransforms().add(rotate);

        /* The chin-down rotation for looking at the board. */
        camera.getTransforms().add(new Rotate(45, Rotate.X_AXIS));

        /* Finally, make the subscene use the camera we've defined. */
        sub.setCamera(camera);

        /* Ensure that the subscene resizes along with the window. */
        sub.widthProperty().bind(subScenePane.widthProperty());
        sub.heightProperty().bind(subScenePane.heightProperty());

        /* Add the subscene to the window. */
        subScenePane.getChildren().add(sub);
    }    
}
