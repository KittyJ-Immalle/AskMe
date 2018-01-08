package com.gmail.kittyjacth;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.*;

public class AppWindow extends Application{
	
	Stage window;
	Scene sceneWelcome;
	Scene sceneExplanation;
	
	Scene sceneEnd;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("AskMe!");
		window.setResizable(false);
		
		Button buttonNext = new Button("Next");
		Label labelWelcome = new Label("Welcome!");
		Button buttonStart = new Button("Get started");
		Button buttonQuit = new Button("Quit");
		VBox boxWelcome = new VBox(5);
		
		buttonNext.setStyle("-fx-font-size:20;");
		labelWelcome.setStyle("-fx-font-size: 50;");
		buttonStart.setStyle("-fx-font-size:20;");
		buttonQuit.setStyle("-fx-font-size:20;");
		
		buttonStart.setOnAction(e -> displayExplanation(buttonNext));
		buttonQuit.setOnAction(e -> {
			closeProgram();
		});
		window.setOnCloseRequest(e -> {
			e.consume();
			closeProgram();
		});
		
		boxWelcome.getChildren().addAll(labelWelcome, buttonStart, buttonQuit);
		boxWelcome.setAlignment(Pos.CENTER);
		
		sceneWelcome = new Scene(boxWelcome, 400,300);
		
		window.setScene(sceneWelcome);
		window.show();
	}
	
	public void displayExplanation(Button buttonNext) {
		Label labelExp = new Label("Explanation");
		Text textExp = new Text(10, 50, "");
		BorderPane border = new BorderPane();
		VBox boxExpV = new VBox(5);
		Pane space = new Pane();
		HBox boxExpH = new HBox(5);
		
		// Style
		labelExp.setStyle("-fx-font-size: 30;");
		textExp.setText("This is an application meant for studying, quizes,...You will have to fill in your questions and answers. When I ask you the question, and you answer correctly, you get to go to the next question. However, if not answered correctly, you'll have to keep answering until you get it right.\nSkipping is allowed.");
		textExp.setWrappingWidth(490);
		textExp.setStyle("-fx-font-size: 15;");
		boxExpV.setPadding(new Insets(10,10,10,10));
		space.setMinWidth(400);
		boxExpH.setPadding(new Insets(10,10,10,10));
		
		// Events
		buttonNext.setOnAction(e -> Questions.displayFillIn(window));
		
		// Layout
		boxExpH.getChildren().addAll(space, buttonNext);
		boxExpV.getChildren().addAll(labelExp, textExp);
		border.setCenter(boxExpV);
		border.setBottom(boxExpH);
		
		sceneExplanation = new Scene(border, 500, 300);
		
		window.setScene(sceneExplanation);
	}
	
	public static void closeProgram() {
		Boolean answer = ConfirmBox.display("Quit?", "Are you sure you want to exit?");
		if (answer)
			Platform.exit();
	}

}
