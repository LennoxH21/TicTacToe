package edu.bloomu.tic_tac_toe;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * This is a GUI representing the game of Tic-Tac-Toe.
 *
 * @author Lennox Haynes
 */

public class TicTacToeGUI extends Application {

    // Initializes the game of Tic-Tac-Toe
    private static TicTacToe game  = new TicTacToe();
    // Buttons for the game's grid.
    private static Button[][] buttons =
            new Button[game.getGridSize()][game.getGridSize()];

    @Override
    public void start(Stage primaryStage) {

        GridPane root = new GridPane();
        Scene scene = new Scene(root);

        // Adds the buttons to the root in the form of the Standard Tic-Tac-Toe board.
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {

                buttons[i][j] = new Button(); // Create a Button at row(i) and column(j).
                buttons[i][j].setPrefSize(125, 125); // Size of Buttons
                // Styles the buttons
                buttons[i][j].setStyle("-fx-border-color: BLACK; -fx-border-width: 2;" +
                        " -fx-font-size: 40px;");

                // Handler for each button.
                int row = i;
                int col = j;
                buttons[i][j].setOnAction(event -> {
                    game.move(row, col);
                    setButtons(); // Updates the buttons once selected

                    // Displays a message when the game is won.
                    if (game.isWon()) {
                        Alert winner = new Alert(AlertType.INFORMATION);
                        winner.setTitle("CONGRATULATIONS!");
                        winner.setHeaderText("PLAYER " + game.getTurn() + " HAS WON!");
                        winner.setContentText("Goodbye!");
                        primaryStage.setAlwaysOnTop(false);
                        winner.showAndWait();
                        Platform.exit();
                    }
                    else {
                        // Displays a message when nobody won.
                        if (game.stalemate()) {
                            Alert stalemate = new Alert(AlertType.INFORMATION);
                            stalemate.setTitle("GAME OVER");
                            stalemate.setHeaderText("Nobody won! Try Again!");
                            stalemate.setContentText("Goodbye!");
                            primaryStage.setAlwaysOnTop(false);
                            stalemate.showAndWait();
                            Platform.exit();
                        }
                    }
                });
                root.add(buttons[i][j], i, j); // Adds the button to the GridPane
            }
        }

        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setTitle("TIC-TAC-TOE");
        primaryStage.show();
    }

    /**
     * Updates the current state of the game.
     */
    private void setButtons() {

        // Updates the text of the selected Buttons.
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {

                // Places an 'X' on Player 1's selected Button.
                if (game.gameGrid()[i][j] == 1){
                    buttons[i][j].setText("X");
                }
                // Places an 'O' on Player 2's selected Button.
                if (game.gameGrid()[i][j] == 2){
                    buttons[i][j].setText("O");
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
