package chess.gui;

import chess.board.*;
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
import javafx.scene.shape.Shape3D;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

public class ChessController implements Initializable {
    private Board board;
    private Group cellGroups[][];
    private Group solids;
    private Piece selection = null;

    /* Color materials. */
    private final Material blackMat = new PhongMaterial(Color.web("#000"));
    private final Material hiblackMat = new PhongMaterial(Color.web("#666"));
    private final Material whiteMat = new PhongMaterial(Color.web("#aaa"));
    private final Material hiwhiteMat = new PhongMaterial(Color.web("#fff"));
    private final Material pieceBlackMat = new PhongMaterial(Color.web("#333"));
    private final Material pieceWhiteMat = new PhongMaterial(Color.web("#ddd"));

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

    public void put(Board board, Piece piece, int x, int y) {
        final MeshView mv;

        if (piece instanceof Pawn) {
            mv = (MeshView)(new PawnMeshView());
        } else if (piece instanceof Queen) {
            mv = (MeshView)(new QueenMeshView());
        } else if (piece instanceof Knight) {
            mv = (MeshView)(new KnightMeshView());
        } else if (piece instanceof King) {
            mv = (MeshView)(new KingMeshView());
        } else if (piece instanceof Rook) {
            mv = (MeshView)(new RookMeshView());
        } else {
            mv = (MeshView)(new BishopMeshView());
        }

        mv.translateXProperty()
          .bind(
            piece
              .xProperty()
              .subtract(2)
              .multiply(10));
        mv.translateYProperty()
          .bind(
            piece
              .yProperty()
              .subtract(3)
              .multiply(10));
        mv.setRotationAxis(Rotate.Z_AXIS);
        mv.setTranslateZ(18);

        Material mat;
        if (piece.getColor() == Piece.BLACK) {
            mat = new PhongMaterial(Color.web("#333"));
        } else {
            mv.setRotate(180);
            mat = new PhongMaterial(Color.web("#ddd"));
        }
        mv.setMaterial(mat);

        /* Combine the x and y of the piece into an array, 'binding'. */
        ObjectBinding binding = new ObjectBinding() {
            {
                super.bind(piece.xProperty(),
                        piece.yProperty());
            }

            @Override
                protected Object computeValue() {
                    int value[] = {piece.xProperty().get(),
                        piece.yProperty().get()};

                    return (Object)value;
                }
        };

        /* Updates the cell group matrix whenever the piece moves. */
        binding.addListener((obs, old, neu) -> {
            if (old != null) {
                int[] oldints = (int[])old;
                cellGroups[oldints[0]][oldints[1]].getChildren().remove(mv);
            }

            int[] neuints = (int[])neu;
            cellGroups[neuints[0]][neuints[1]].getChildren().add(mv);
        });

        board.put(piece, x, y);
        cellGroups[x][y].getChildren().add(mv);
    }

    public void setupGame(boolean whiteGoesFirst,
                          boolean blackIsHuman,
                          boolean whiteIsHuman) {
        /* Get rid of models for old pieces. */
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                cellGroups[i][j].getChildren().removeIf(o -> o instanceof MeshView);
            }
        }

        /* Empty the board. */
        board.clear();

        /* Set up the pieces for a game. */
        put(board, new King(0, 0, Piece.BLACK), 0, 0);
        put(board, new Queen(1, 0, Piece.BLACK), 1, 0);
        put(board, new Bishop(2, 0, Piece.BLACK), 2, 0);
        put(board, new Knight(3, 0, Piece.BLACK), 3, 0);
        put(board, new Rook(4, 0, Piece.BLACK), 4, 0);
        put(board, new Pawn(0, 1, Piece.BLACK), 0, 1);
        put(board, new Pawn(1, 1, Piece.BLACK), 1, 1);
        put(board, new Pawn(2, 1, Piece.BLACK), 2, 1);
        put(board, new Pawn(3, 1, Piece.BLACK), 3, 1);
        put(board, new Pawn(4, 1, Piece.BLACK), 4, 1);

        put(board, new Rook(0, 4, Piece.WHITE), 0, 4);
        put(board, new Knight(1, 4, Piece.WHITE), 1, 4);
        put(board, new Bishop(2, 4, Piece.WHITE), 2, 4);
        put(board, new Queen(3, 4, Piece.WHITE), 3, 4);
        put(board, new King(4, 4, Piece.WHITE), 4, 4);
        put(board, new Pawn(0, 3, Piece.WHITE), 0, 3);
        put(board, new Pawn(1, 3, Piece.WHITE), 1, 3);
        put(board, new Pawn(2, 3, Piece.WHITE), 2, 3);
        put(board, new Pawn(3, 3, Piece.WHITE), 3, 3);
        put(board, new Pawn(4, 3, Piece.WHITE), 4, 3);

        System.out.println("ChessController got message about new game.");
        System.out.println("First player: " + (whiteGoesFirst ? "white" : "black"));
        System.out.println("Black is " + (blackIsHuman ? "human" : "machine"));
        System.out.println("White is " + (whiteIsHuman ? "human" : "machine"));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        board = new Board();

        /* Initialize the cell groups (tile + piece groups). */
        cellGroups = new Group[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                cellGroups[i][j] = new Group();
            }
        }

        /* Undo/redo buttons start out disabled. */
        undoButton.setDisable(true);
        redoButton.setDisable(true);

        /* Declare our group of solid shapes. */
        solids = new Group();;

        /* Populate the group with boxes to make the checkerboard. */
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                /* Create the big board. */
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
                final int pos[] = {i, j};

                /* Hilight the row/column of hovered tiles. */
                cellGroups[i][j].setOnMouseEntered(e -> {
                    int x = pos[0];
                    int y = pos[1];

                    for (Node child : cellGroups[x][y].getChildren()) {
                        if (child instanceof Box) {
                            if (((x + y) & 1) == 1) {
                                ((Box)child).setMaterial(hiblackMat);
                            } else {
                                ((Box)child).setMaterial(hiwhiteMat);
                            }
                        } else {
                            if (board.getPieceAt(x, y).getColor() == Piece.BLACK) {
                                ((MeshView)child).setMaterial(hiblackMat);
                            } else {
                                ((MeshView)child).setMaterial(hiwhiteMat);
                            }
                        }
                    }
                });

                /* Darken when mouse leaves. */
                cellGroups[i][j].setOnMouseExited(e -> {
                    int x = pos[0];
                    int y = pos[1];

                    for (Node child : cellGroups[x][y].getChildren()) {
                        if (child instanceof Box) {
                            if (((x + y) & 1) == 1) {
                                ((Box)child).setMaterial(blackMat);
                            } else {
                                ((Box)child).setMaterial(whiteMat);
                            }
                        } else {
                            if (board.getPieceAt(x, y).getColor() == Piece.BLACK) {
                                ((MeshView)child).setMaterial(pieceBlackMat);
                            } else {
                                ((MeshView)child).setMaterial(pieceWhiteMat);
                            }
                        }
                    }
                });

                /* Make tiles react to clicking. */
                cellGroups[i][j].setOnMouseClicked(e -> {
                    int x = pos[0];
                    int y = pos[1];

                    System.out.println("Tile clicked: "
                        + x + " " + y);
                    if (selection == null) {
                        /* Try to select the piece if there is one */
                        if (board.getPieceAt(x, y) != null) {
                            selection = board.getPieceAt(x, y);
                            System.out.println("Selected piece.");
                        }
                    } else {
                        /* Try to move the piece to that square. */
                        if (board.isLegalMove(
                              selection.movement(
                                board.getPlayingBoard()),
                              x, y)) {
                            /* remove mesh for taken pieces, if appropriate */
                            cellGroups[x][y].getChildren().removeIf(o -> o instanceof MeshView);
                            board.move(selection.getX(), selection.getY(), x, y);
                            System.out.println("Move was be successful.");
                        }

                        System.out.println("Selection freed.");
                        selection = null;
                    }
                });

                /* Position the tile on the board. */
                Translate translation = new Translate(((double)i - 2.0) * 10,
                                                      (((double)j - 2.0) * 10) - 10,
                                                      20);

                box.getTransforms().add(translation);
                cellGroups[i][j].getChildren().add(box);
                solids.getChildren().add(cellGroups[i][j]);
                
                /* Create the small board (for showing the previous move). */
                Box smallBox = new Box();
                
                smallBox.setWidth(10);
                smallBox.setHeight(10);
                smallBox.setDepth(2);
                
                /* Color each tile black or white. */
                if (((i + j) & 1) == 1) {
                    smallBox.setMaterial(blackMat);
                } else {
                    smallBox.setMaterial(whiteMat);
                }
                
                /* Transform mini-board so that it is always at the top */
                Translate smtrans = new Translate((((double)i - 2.0) * 10),
                                                  (((double)j - 2.0) * 10) - 175,
                                                  90);
                Rotate smrotate = new Rotate(0, 0, 0, 0);
                smrotate.angleProperty().bind(slider.valueProperty());
                smallBox.getTransforms().add(smrotate);
                Rotate sminnerrotate = new Rotate(0, ((((double)i - 2.0) * 10)) + (20 - (10 * i)),
                                                     ((((double)j - 2.0) * 10) - 175) + (30 - (10 * j)), 0);
                sminnerrotate.angleProperty().bind(slider.valueProperty().negate().subtract(0));
                smallBox.getTransforms().add(sminnerrotate);
                
                smallBox.getTransforms().add(smtrans);
                solids.getChildren().add(smallBox);
            }
        }

        /* Create an internal scene for the 3D graphics. */
        SubScene sub = new SubScene((Parent)solids, 0, 0, true, null);

        /* Declare the camera we will use for the board. */
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setFarClip(400);

        /* Put the camera at a distance from the board. */
        camera.setTranslateY(80);
        camera.setTranslateZ(-80);

        /* Make the slide-able rotation for the board. */
        Rotate rotate = new Rotate(0, 0, -90, 0);
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
