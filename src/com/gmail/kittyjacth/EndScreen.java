package com.gmail.kittyjacth;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import java.util.HashMap;

import javafx.geometry.*;

public class EndScreen {
	
	public static void displayEndScreen(Stage window, HashMap<TextField, TextField> questionsHash, GridPane centerPane) {
		BorderPane finalPane = new BorderPane();
		VBox centerBox = new VBox(10);
		Scene scene = new Scene(finalPane, 500, 300);
		Label congratulations = new Label("You did it! Well done!");
		Button replayButton = new Button("Play again");
		Button newQuizButton = new Button("New Quiz");
		Button quit = new Button("Quit");
		
		// Style
		congratulations.setStyle("-fx-font-size:20;");
		replayButton.setStyle("-fx-font-size:20;");
		newQuizButton.setStyle("-fx-font-size:20;");
		quit.setStyle("-fx-font-size:20;");
		centerBox.setAlignment(Pos.CENTER);
		
		// Events
		replayButton.setOnAction(e -> {
			Questions.insertQuestionsIntoQuestionsHash(questionsHash, centerPane);
			Questions.displayQuestions(window);
		});
		newQuizButton.setOnAction(e -> Questions.displayFillIn(window));
		quit.setOnAction(e -> AppWindow.closeProgram());
		
		// Layout
		finalPane.setCenter(centerBox);
		centerBox.getChildren().addAll(congratulations, replayButton, newQuizButton, quit);
		
		window.setScene(scene);
	}
	
}
