package chess.gui;

import chess.board.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.*;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.PerspectiveCamera;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.stage.Modality;
import javafx.scene.transform.*;
import javafx.scene.shape.Box;
import javafx.scene.Node;
import javafx.scene.shape.MeshView;

public class ChessController implements Initializable {
    int counter = 0;
    private Board board;
    private Group cellGroups[][];
    private Group solids;
    private Piece selection = null;
    private final IntegerProperty selectionX = new SimpleIntegerProperty(-100);
    private final IntegerProperty selectionY = new SimpleIntegerProperty(-100);
    private Stack<Object> prevMoveHistory;
    private Stack<Object> prevMoveFutures;
    private Stack<Object> moveHistory;
    private Stack<Object> moveFutures;
    private Board prevBoard;

    /* Color materials. */
    private final Material blackMat = new PhongMaterial(Color.web("#000"));
    private final Material hiblackMat = new PhongMaterial(Color.web("#666"));
    private final Material whiteMat = new PhongMaterial(Color.web("#aaa"));
    private final Material hiwhiteMat = new PhongMaterial(Color.web("#fff"));
    private final Material pieceBlackMat = new PhongMaterial(Color.web("#333"));
    private final Material pieceWhiteMat = new PhongMaterial(Color.web("#ddd"));

    //Initialize AI object
    AI myAI = new AI();
    int[] AIplacement = new int[4];

    @FXML
    private Label statusBar;
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

        ((NewGameWindowController) loader.getController())
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

    @FXML
    private void handleUndo() {
        Object delta = moveHistory.pop();

        board.undo(delta);
        moveFutures.push(delta);
        selection = null;
        selectionX.set(-100);
        selectionY.set(-100);

        if (moveHistory.size() > 0) {
            Object prevDelta = prevMoveHistory.pop();
            prevBoard.undo(prevDelta);
            prevMoveFutures.push(prevDelta);
        }

        counter--;
        updateStatusBar();

        redoButton.setDisable(false);

        if (moveHistory.size() == 0)
            undoButton.setDisable(true);
    }

    @FXML
    private void handleRedo() {
        Object delta = moveFutures.pop();
        board.redo(delta);
        moveHistory.push(delta);
        selection = null;
        selectionX.set(-100);
        selectionY.set(-100);

        if (moveHistory.size() > 1) {
            Object prevDelta = prevMoveFutures.pop();
            prevBoard.redo(prevDelta);
            prevMoveHistory.push(prevDelta);
        }

        counter++;
        board.updateTurn(counter);
        prevBoard.updateTurn(counter-1);

        undoButton.setDisable(false);

        if (board.black == 0) {
            handleWin(Piece.BLACK);
        } else if (board.white == 0) {
            handleWin(Piece.WHITE);
        } else {
            updateStatusBar();
        }

        if (moveFutures.size() == 0)
            redoButton.setDisable(true);
    }

    void updateStatusBar() {
        counter = board.getTurn();

        if (counter % 2 == 0) {
            statusBar.setText("Black's turn.");
        } else if (counter % 2 == 1) {
            statusBar.setText("White's turn.");
        }
    }

    private void handleWin(int color) {
        if (color == Piece.BLACK) {
            statusBar.setText("Black wins!");
        } else if (color == Piece.WHITE) {
            statusBar.setText("White wins!");
        }
    }

    public void miniPut(Board board, Piece piece) {
        final MeshView mv;

        if (piece instanceof Pawn) {
            mv = (MeshView) (new PawnMeshView());
        } else if (piece instanceof Queen) {
            mv = (MeshView) (new QueenMeshView());
        } else if (piece instanceof Knight) {
            mv = (MeshView) (new KnightMeshView());
        } else if (piece instanceof King) {
            mv = (MeshView) (new KingMeshView());
        } else if (piece instanceof Rook) {
            mv = (MeshView) (new RookMeshView());
        } else {
            mv = (MeshView) (new BishopMeshView());
        }

        /* These transforms are just copied from the small box transforms
         * in #initialize(). */
        Translate smtrans = new Translate(0, 0, 92);
        smtrans.xProperty().bind(piece.xProperty().subtract(2.0).multiply(10));
        smtrans.yProperty().bind(piece.yProperty().subtract(2.0).multiply(10).subtract(175));

        Rotate smrotate = new Rotate(0, 0, 0, 0);
        smrotate.angleProperty().bind(slider.valueProperty());
        mv.getTransforms().add(smrotate);

        Rotate sminnerrotate = new Rotate(0, 0, 0, 0);
        sminnerrotate.angleProperty().bind(slider.valueProperty().negate());
        sminnerrotate
                .pivotXProperty()
                .bind(
                        piece
                                .xProperty()
                                .subtract(2)
                                .multiply(10)
                                .add(
                                        piece
                                                .xProperty()
                                                .multiply(10)
                                                .negate()
                                                .add(20)));
        sminnerrotate
                .pivotYProperty()
                .bind(
                        piece
                                .yProperty()
                                .subtract(2)
                                .multiply(10)
                                .subtract(175)
                                .add(
                                        piece
                                                .yProperty()
                                                .multiply(10)
                                                .negate()
                                                .add(30)));
        mv.getTransforms().add(sminnerrotate);
        mv.getTransforms().add(smtrans);

        Material mat;
        if (piece.getColor() == Piece.BLACK) {
            mat = new PhongMaterial(Color.web("#333"));
        } else {
            mv.getTransforms().add(new Rotate(180, Rotate.Z_AXIS));
            mat = new PhongMaterial(Color.web("#ddd"));
        }
        mv.setMaterial(mat);

        piece.positionProperty().addListener((obs, old, neu) -> {
            int[] oldints = (int[]) old;
            int[] neuints = (int[]) neu;

            if (old != null) {
                if (oldints[0] != -1 && oldints[1] != -1) {
                    if (solids.getChildren().contains(mv))
                        solids.getChildren().remove(mv);
                }
            }

            if (neuints[0] != -1 && neuints[1] != -1) {
                if (!solids.getChildren().contains(mv))
                    solids.getChildren().add(mv);
            }
        });

        board.put(piece);
        solids.getChildren().add(mv);
    }

    public void put(Board board, Piece piece) {
        final MeshView mv;

        if (piece instanceof Pawn) {
            mv = (MeshView) (new PawnMeshView());
        } else if (piece instanceof Queen) {
            mv = (MeshView) (new QueenMeshView());
        } else if (piece instanceof Knight) {
            mv = (MeshView) (new KnightMeshView());
        } else if (piece instanceof King) {
            mv = (MeshView) (new KingMeshView());
        } else if (piece instanceof Rook) {
            mv = (MeshView) (new RookMeshView());
        } else {
            mv = (MeshView) (new BishopMeshView());
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

    /* Updates the cell group matrix whenever the piece moves. */
        piece.positionProperty().addListener((obs, old, neu) -> {
            int[] oldints = (int[]) old;
            int[] neuints = (int[]) neu;

            if (old != null) {
                if (oldints[0] != -1 && oldints[1] != -1) {
                    cellGroups[oldints[0]][oldints[1]].getChildren().remove(mv);
                }
            }

            if (neuints[0] != -1 && neuints[1] != -1) {
                cellGroups[neuints[0]][neuints[1]].getChildren().add(mv);
            }

            /* Restore regular mesh color. */
            if (piece.getColor() == Piece.BLACK) {
                mv.setMaterial(pieceBlackMat);
            } else {
                mv.setMaterial(pieceWhiteMat);
            }
        });

        board.put(piece);
        cellGroups[piece.getX()][piece.getY()].getChildren().add(mv);
    }

    public void setupGame(boolean whiteGoesFirst,
                          boolean blackIsHuman,
                          boolean whiteIsHuman) {
        /* Set the turn counters to the correct turn. */
        int startingTurn =
            whiteGoesFirst ? Piece.WHITE : Piece.BLACK;

        counter = startingTurn;

        board.updateTurn(startingTurn);
        prevBoard.updateTurn(startingTurn);

        /* Set up the AIs, if there are any. */
        board.setAI(!whiteIsHuman, !blackIsHuman);

        /* Say whose turn it is. */
        updateStatusBar();

        /* Clear any selection. */
        selection = null;
        selectionX.set(-100);
        selectionY.set(-100);

        /* Clear out undo lists. */
        moveHistory.clear();
        moveFutures.clear();
        prevMoveHistory.clear();
        prevMoveFutures.clear();

        /* Undo/redo buttons start out disabled. */
        undoButton.setDisable(true);
        redoButton.setDisable(true);

        /* Get rid of models for old pieces. */
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                cellGroups[i][j].getChildren().removeIf(o -> o instanceof MeshView);
            }
        }

        /* Empty the board. */
        board.clear();
        prevBoard.clear();

        /* Set up the pieces for a game. */
        put(board, new King(0, 0, Piece.BLACK));
        put(board, new Queen(1, 0, Piece.BLACK));
        put(board, new Bishop(2, 0, Piece.BLACK));
        put(board, new Knight(3, 0, Piece.BLACK));
        put(board, new Rook(4, 0, Piece.BLACK));
        put(board, new Pawn(0, 1, Piece.BLACK));
        put(board, new Pawn(1, 1, Piece.BLACK));
        put(board, new Pawn(2, 1, Piece.BLACK));
        put(board, new Pawn(3, 1, Piece.BLACK));
        put(board, new Pawn(4, 1, Piece.BLACK));

        put(board, new Rook(0, 4, Piece.WHITE));
        put(board, new Knight(1, 4, Piece.WHITE));
        put(board, new Bishop(2, 4, Piece.WHITE));
        put(board, new Queen(3, 4, Piece.WHITE));
        put(board, new King(4, 4, Piece.WHITE));
        put(board, new Pawn(0, 3, Piece.WHITE));
        put(board, new Pawn(1, 3, Piece.WHITE));
        put(board, new Pawn(2, 3, Piece.WHITE));
        put(board, new Pawn(3, 3, Piece.WHITE));
        put(board, new Pawn(4, 3, Piece.WHITE));

        /* Set up the pieces on the miniboard. */
        miniPut(prevBoard, new King(0, 0, Piece.BLACK));
        miniPut(prevBoard, new Queen(1, 0, Piece.BLACK));
        miniPut(prevBoard, new Bishop(2, 0, Piece.BLACK));
        miniPut(prevBoard, new Knight(3, 0, Piece.BLACK));
        miniPut(prevBoard, new Rook(4, 0, Piece.BLACK));
        miniPut(prevBoard, new Pawn(0, 1, Piece.BLACK));
        miniPut(prevBoard, new Pawn(1, 1, Piece.BLACK));
        miniPut(prevBoard, new Pawn(2, 1, Piece.BLACK));
        miniPut(prevBoard, new Pawn(3, 1, Piece.BLACK));
        miniPut(prevBoard, new Pawn(4, 1, Piece.BLACK));

        miniPut(prevBoard, new Rook(0, 4, Piece.WHITE));
        miniPut(prevBoard, new Knight(1, 4, Piece.WHITE));
        miniPut(prevBoard, new Bishop(2, 4, Piece.WHITE));
        miniPut(prevBoard, new Queen(3, 4, Piece.WHITE));
        miniPut(prevBoard, new King(4, 4, Piece.WHITE));
        miniPut(prevBoard, new Pawn(0, 3, Piece.WHITE));
        miniPut(prevBoard, new Pawn(1, 3, Piece.WHITE));
        miniPut(prevBoard, new Pawn(2, 3, Piece.WHITE));
        miniPut(prevBoard, new Pawn(3, 3, Piece.WHITE));
        miniPut(prevBoard, new Pawn(4, 3, Piece.WHITE));

        //If both are AI, this will run until there is a winner
        if ((!whiteIsHuman && whiteGoesFirst)  || (!blackIsHuman && !whiteGoesFirst)) {
            board.teamCapture();

            //While no one has won
            while (board.black > 0 && board.white > 0) {

                //White's turn
                if (counter%2 == 1) {
                    System.out.println("WHITE TEST");
                    //Get desired AI location
                    AIplacement = myAI.AImove(board, counter%2); //translation
                    //board.move(AIplacement[0], AIplacement[1], AIplacement[2], AIplacement[3]);
                    board.checkRestrictions(board, myAI.selection, myAI.AImovements, AIplacement[2], AIplacement[3]);

                    boolean isTransform = selection instanceof Pawn
                            && (AIplacement[3] == 0 || AIplacement[3] == 4);
                    if(board.hasLegalMove) {
                        System.out.println("legal move");
                        Object delta = board.move(AIplacement[0], AIplacement[1], AIplacement[2], AIplacement[3]);
                        if (delta != null) {
                                /* Transform pawn to king at end of board. */
                            if (isTransform) {
                                board.remove(AIplacement[2], AIplacement[3]);

                                Piece king = new King(AIplacement[2], AIplacement[3], counter % 2);
                                board.assocMorphed(delta, king);

                                put(board, king);
                            }

                            redoButton.setDisable(true);
                            moveFutures.clear();

                            //counter++;
                            updateStatusBar();

                            undoButton.setDisable(false);
                            if (moveHistory.size() > 0) {
                                   /*  If there has been a previous board state,
                                     * reflect it on the miniboard. */
                                Object prevDelta = moveHistory.peek();

                                Object newDelta = prevBoard
                                        .copyMove(
                                                prevDelta);

                                if (prevBoard.wasTransform(newDelta)) {
                                    Piece miniKing = new King(prevBoard.getCapturer(newDelta).getX(), prevBoard.getCapturer(newDelta).getY(), counter % 2);
                                    prevBoard.removeCapturer(newDelta);

                                    prevBoard.assocMorphed(newDelta, miniKing);
                                    miniPut(prevBoard, miniKing);
                                }

                                prevMoveHistory.push(newDelta);
                            }
                            moveHistory.push(delta);
                        }
                    }
                    else{
                        break;
                    }
                    //Reset counter to turn, thus switching AI turns
                    counter = board.getTurn();
                }

                //Black's turn
                else if(counter%2 == 0) {
                    System.out.println("BLACK TEST");
                    //Get desired AI location
                    AIplacement = myAI.AImove(board, counter%2); //translation
                    //board.move(AIplacement[0], AIplacement[1], AIplacement[2], AIplacement[3]);
                    board.checkRestrictions(board, myAI.selection, myAI.AImovements, AIplacement[2], AIplacement[3]);

                    boolean isTransform = selection instanceof Pawn
                            && (AIplacement[3] == 0 || AIplacement[3] == 4);
                    if(board.hasLegalMove) {
                        System.out.println("legal move");
                        Object delta = board.move(AIplacement[0], AIplacement[1], AIplacement[2], AIplacement[3]);
                        if (delta != null) {
                            /* Transform pawn to king at end of board. */
                            if (isTransform) {
                                board.remove(AIplacement[0], AIplacement[1]);

                                Piece king = new King(AIplacement[0], AIplacement[1], counter % 2);
                                board.assocMorphed(delta, king);

                                put(board, king);
                            }

                            redoButton.setDisable(true);
                            moveFutures.clear();

                            //counter++;
                            updateStatusBar();

                            undoButton.setDisable(false);
                            if (moveHistory.size() > 0) {
                                    /* If there has been a previous board state,
                                     * reflect it on the miniboard. */
                                Object prevDelta = moveHistory.peek();

                                Object newDelta = prevBoard
                                        .copyMove(
                                                prevDelta);

                                if (prevBoard.wasTransform(newDelta)) {
                                    Piece miniKing = new King(prevBoard.getCapturer(newDelta).getX(), prevBoard.getCapturer(newDelta).getY(), counter % 2);
                                    prevBoard.removeCapturer(newDelta);

                                    prevBoard.assocMorphed(newDelta, miniKing);
                                    miniPut(prevBoard, miniKing);
                                }

                                prevMoveHistory.push(newDelta);
                            }
                            moveHistory.push(delta);
                        }
                    }
                    else break;
                    //Reset counter to turn, thus switching AI turns
                    counter = board.getTurn();
                }

                if (whiteIsHuman || blackIsHuman) break;

            }
        }

            System.out.println("ChessController got message about new game.");
            System.out.println("First player: " + (whiteGoesFirst ? "white" : "black"));
            System.out.println("Black is " + (blackIsHuman ? "human" : "machine"));
            System.out.println("White is " + (whiteIsHuman ? "human" : "machine"));

        }

        @Override
        public void initialize (URL url, ResourceBundle rb){
            board = new Board();
            prevBoard = new Board();

        /* Initialize the game history stack. */
            moveHistory = new Stack<>();
            moveFutures = new Stack<>();
            prevMoveHistory = new Stack<>();
            prevMoveFutures = new Stack<>();

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

            statusBar
                    .styleProperty()
                    .bind(
                            Bindings.concat(
                                    "-fx-font-size: ",
                                    subScenePane.widthProperty().divide(35).asString()));

        /* Declare our group of solid shapes. */
            solids = new Group();

        /* Make the selection ring. */
            Group selectionRing = new Group();
            Material blueMat = new PhongMaterial(Color.web("#cff"));
            Box top = new Box(10, 1, 0.2);
            top.setMaterial(blueMat);
            top.setTranslateY(-4.5);
            Box left = new Box(1, 10, 0.2);
            left.setTranslateX(-4.5);
            left.setMaterial(blueMat);
            Box bottom = new Box(10, 1, 0.2);
            bottom.setMaterial(blueMat);
            bottom.setTranslateY(4.5); /* maybe 1 less? */
            Box right = new Box(1, 10, 0.2);
            right.setMaterial(blueMat);
            right.setTranslateX(4.5); /* maybe 1 less? */
            selectionRing.getChildren().addAll(top, bottom, left, right);

            selectionRing
                    .translateXProperty()
                    .bind(selectionX.subtract(2).multiply(10).subtract(0));
            selectionRing
                    .translateYProperty()
                    .bind(selectionY.subtract(2).multiply(10).subtract(10));
            selectionRing.setTranslateZ(18.8 + (top.getDepth() / 2));
            solids.getChildren().add(selectionRing);

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
                                    ((Box) child).setMaterial(hiblackMat);
                                } else {
                                    ((Box) child).setMaterial(hiwhiteMat);
                                }
                            } else {
                                if (board.getPieceAt(x, y).getColor() == Piece.BLACK) {
                                    ((MeshView) child).setMaterial(hiblackMat);
                                } else {
                                    ((MeshView) child).setMaterial(hiwhiteMat);
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
                                    ((Box) child).setMaterial(blackMat);
                                } else {
                                    ((Box) child).setMaterial(whiteMat);
                                }
                            } else {
                                if (board.getPieceAt(x, y).getColor() == Piece.BLACK) {
                                    ((MeshView) child).setMaterial(pieceBlackMat);
                                } else {
                                    ((MeshView) child).setMaterial(pieceWhiteMat);
                                }
                            }
                        }
                    });

                /* Make tiles react to clicking. */
                    cellGroups[i][j].setOnMouseClicked(e -> {
                        int x = pos[0];
                        int y = pos[1];

                    /*compute if the current player's team has a capture,
                      of so restrict so that when moving a piece the oly valid move is a capture.
                     */
                        board.teamCapture();

                        counter = board.getTurn();

                        if (selection == null) {
                        /* Try to select the piece if there is one */
                            if (board.getPieceAt(x, y) != null &&
                                  board.isCurrentPlayerHuman () &&
                                  board.pieceBelongsToCurrentPlayer(x, y)) {
                                selection = board.getPieceAt(x, y);
                                selectionX.set(x);
                                selectionY.set(y);
                            }
                        } else {
                        /* Try to move the piece to that square. */
                            selection.clear();
                            board.checkRestrictions(board, selection,
                                    selection.movement(board.getPlayingBoard()),
                                    x, y);

                            boolean isTransform = selection instanceof Pawn
                                    && (y == 0 || y == 4);

                        /* remove mesh for taken pieces, if appropriate */
                            if (board.hasLegalMove) {
                                Object delta = board.move(selection.getX(),
                                        selection.getY(),
                                        x, y);
                                if (delta != null) {
                                /* Transform pawn to king at end of board. */
                                    if (isTransform) {
                                        board.remove(x, y);

                                        Piece king = new King(x, y, counter % 2);
                                        board.assocMorphed(delta, king);

                                        put(board, king);
                                    }

                                    redoButton.setDisable(true);
                                    moveFutures.clear();

                                    //counter++;
                                    updateStatusBar();

                                    undoButton.setDisable(false);
                                    if (moveHistory.size() > 0) {
                                    /* If there has been a previous board state,
                                     * reflect it on the miniboard. */
                                        Object prevDelta = moveHistory.peek();

                                        Object newDelta = prevBoard
                                                .copyMove(
                                                        prevDelta);

                                        if (prevBoard.wasTransform(newDelta)) {
                                            Piece miniKing = new King(prevBoard.getCapturer(newDelta).getX(), prevBoard.getCapturer(newDelta).getY(), counter % 2);
                                            prevBoard.removeCapturer(newDelta);

                                            prevBoard.assocMorphed(newDelta, miniKing);
                                            miniPut(prevBoard, miniKing);
                                        }

                                        prevMoveHistory.push(newDelta);
                                    }
                                    moveHistory.push(delta);




                                    if (board.someAi()){

                                        board.teamCapture();

                                        counter = board.getTurn();

                                        //Get desired AI location
                                        AIplacement = myAI.AImove(board, counter%2); //translation
                                        //board.move(AIplacement[0], AIplacement[1], AIplacement[2], AIplacement[3]);
                                        board.checkRestrictions(board, myAI.selection, myAI.AImovements, AIplacement[2], AIplacement[3]);

                                        isTransform = myAI.selection instanceof Pawn
                                                && (AIplacement[3] == 0 || AIplacement[3] == 4);
                                        if(board.hasLegalMove) {
                                            System.out.println("legal move");
                                            delta = board.move(AIplacement[0], AIplacement[1], AIplacement[2], AIplacement[3]);
                                            if (delta != null) {
                                                /* Transform pawn to king at end of board. */
                                                if (isTransform) {
                                                    board.remove(AIplacement[2], AIplacement[3]);

                                                    Piece king = new King(AIplacement[2], AIplacement[3], counter % 2);
                                                    board.assocMorphed(delta, king);

                                                    put(board, king);
                                                }

                                                redoButton.setDisable(true);
                                                moveFutures.clear();

                                                //counter++;
                                                updateStatusBar();

                                                undoButton.setDisable(false);
                                                if (moveHistory.size() > 0) {
                                   /*  If there has been a previous board state,
                                     * reflect it on the miniboard. */
                                                    Object prevDelta = moveHistory.peek();

                                                    Object newDelta = prevBoard
                                                            .copyMove(
                                                                    prevDelta);

                                                    if (prevBoard.wasTransform(newDelta)) {
                                                        Piece miniKing = new King(prevBoard.getCapturer(newDelta).getX(), prevBoard.getCapturer(newDelta).getY(), counter % 2);
                                                        prevBoard.removeCapturer(newDelta);

                                                        prevBoard.assocMorphed(newDelta, miniKing);
                                                        miniPut(prevBoard, miniKing);
                                                    }

                                                    prevMoveHistory.push(newDelta);
                                                }
                                                moveHistory.push(delta);
                                            }
                                        }
                                        //Reset counter to turn, thus switching AI turns
                                        counter = board.getTurn();

                                    }





                                }
                            }

                            selectionX.set(-100);
                            selectionY.set(-100);
                            selection = null;
                        }

                        if (board.black == 0) {
                            handleWin(Piece.BLACK);
                        } else if (board.white == 0) {
                            handleWin(Piece.WHITE);
                        }
                    });

                /* Position the tile on the board. */
                    Translate translation = new Translate(((double) i - 2.0) * 10,
                            (((double) j - 2.0) * 10) - 10,
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
                    Translate smtrans = new Translate((((double) i - 2.0) * 10),
                            (((double) j - 2.0) * 10) - 175,
                            94);
                    Rotate smrotate = new Rotate(0, 0, 0, 0);
                    smrotate.angleProperty().bind(slider.valueProperty());
                    smallBox.getTransforms().add(smrotate);
                    Rotate sminnerrotate = new Rotate(0, ((((double) i - 2.0) * 10)) + (20 - (10 * i)),
                            ((((double) j - 2.0) * 10) - 175) + (30 - (10 * j)), 0);
                    sminnerrotate.angleProperty().bind(slider.valueProperty().negate().subtract(0));
                    smallBox.getTransforms().add(sminnerrotate);

                    smallBox.getTransforms().add(smtrans);
                    solids.getChildren().add(smallBox);
                }
            }

        /* Create an internal scene for the 3D graphics. */
            SubScene sub = new SubScene((Parent) solids, 0, 0, true, null);

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
