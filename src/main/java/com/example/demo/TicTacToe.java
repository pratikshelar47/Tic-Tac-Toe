package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;



public class TicTacToe extends Application {

    private Label playerXscoreLabel, playerOscorelabel;
    private int playerXscore = 0;
    private int PlayerOscore = 0;
    private Button buttons[][] = new Button[3][3];
    private boolean playerXTurn = true;
    private BorderPane createContent(){
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        //Title
        Label title = new Label("Tic Tac Toe");
        title.setStyle("-fx-font-size : 35pt; -fx-font-weight : bold;");
        root.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);

        //Game Board
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);


        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                Button button = new Button();
                button.setPrefSize(100,100);
                button.setStyle("-fx-font-size : 15pt; -fx-font-weight : bold;");
                button.setOnAction(event->buttonClicked(button));
                buttons[i][j] = button;
                gridPane.add(button, j, i);
            }
        }
        root.setCenter(gridPane);


        //Score
        HBox ScoreBoard = new HBox(20);
        playerXscoreLabel = new Label("player X Score : 0");
        playerXscoreLabel.setStyle("-fx-font-size : 14pt; -fx-font-weight : bold;");
        playerOscorelabel = new Label("player O Score : 0");
        playerOscorelabel.setStyle("-fx-font-size : 14pt; -fx-font-weight : bold;");
        ScoreBoard.getChildren().addAll(playerXscoreLabel, playerOscorelabel);
        root.setBottom(ScoreBoard);
        ScoreBoard.setAlignment(Pos.CENTER);

        return root;
    }

    private void buttonClicked(Button button){
         if(button.getText().equals("")){
             if(playerXTurn){
                 button.setText("X");
             }
             else{
                 button.setText("O");
             }
             playerXTurn = !playerXTurn;
             CheckWinner();
         }
         return;
    }

    private void CheckWinner(){
        //row
        for(int row = 0; row < 3; row++){
            if(buttons[row][0].getText().equals(buttons[row][1].getText()) &&
               buttons[row][1].getText().equals(buttons[row][2].getText())&&
               !buttons[row][0].getText().isEmpty()
            ){
                // we will have winner
                String winner = buttons[row][0].getText();
                showWinner(winner);
                updateScore(winner);
                restBoard();
                return;
            }
        }

        //col
        for(int col = 0; col < 3; col++){
            if(buttons[0][col].getText().equals(buttons[1][col].getText()) &&
                    buttons[1][col].getText().equals(buttons[2][col].getText())&&
                    !buttons[0][col].getText().isEmpty()
            ){
                // we will have winner
                String winner = buttons[0][col].getText();
                showWinner(winner);
                updateScore(winner);
                restBoard();
                return;
            }
        }

        //Diagonal
        if(buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText())&&
                !buttons[0][0].getText().isEmpty()
        ){
            // we will have winner
            String winner = buttons[0][0].getText();
            showWinner(winner);
            updateScore(winner);
            restBoard();
            return;
        }

        if(buttons[2][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[0][2].getText())&&
                !buttons[0][2].getText().isEmpty()
        ){
            // we will have winner
            String winner = buttons[0][2].getText();
            showWinner(winner);
            updateScore(winner);
            restBoard();
            return;
        }

        //tie
        boolean tie = true;
        for(Button row [] : buttons){
            for(Button button : row){
                if(button.getText().isEmpty()){
                    tie = false;
                    break;
                }
            }
        }

        if(tie){
            tieMatch();
            restBoard();
        }
    }

    private void tieMatch(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setContentText("Game Over ! It's a tie.");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    private void showWinner(String winner){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Congratulations " + winner + "! You won the game");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    private void updateScore(String winner){
        if(winner.equals("X")){
            playerXscore++;
            playerXscoreLabel.setText("player X : " + playerXscore);
        }
        else{
            PlayerOscore++;
            playerOscorelabel.setText("player O : " + PlayerOscore);
        }
    }

    private void restBoard(){
        for(Button row [] : buttons){
            for(Button button : row){
                button.setText("");
            }
        }
    }


    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}