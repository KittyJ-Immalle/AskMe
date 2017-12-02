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
		//Next button
		Button buttonNext = new Button("Next");
		buttonNext.setStyle("-fx-font-size:20;");
		
		window = primaryStage;
		window.setTitle("AskMe!");
		window.setResizable(false);
		
		Label labelWelcome = new Label("Welcome!");
		labelWelcome.setStyle("-fx-font-size: 50;");
		
		Button buttonStart = new Button("Get started");
		buttonStart.setStyle("-fx-font-size:20;");
		buttonStart.setOnAction(e -> explanation(buttonNext));
		
		Button buttonQuit = new Button("Quit");
		buttonQuit.setStyle("-fx-font-size:20;");
		
		VBox boxWelcome = new VBox(5);
		boxWelcome.getChildren().addAll(labelWelcome, buttonStart, buttonQuit);
		boxWelcome.setAlignment(Pos.CENTER);
		
		sceneWelcome = new Scene(boxWelcome, 400,300);
		
		
		window.setScene(sceneWelcome);
		window.show();
	}
	
	public void explanation(Button buttonNext) {
		Label labelExp = new Label("Explanation");
		labelExp.setStyle("-fx-font-size: 30;");
		
		Button buttonSave = new Button("Save");
		buttonSave.setStyle("-fx-font-size:20;");
		
		Text textExp = new Text(10, 50, "");
		textExp.setText("This is an application meant for studying, quizes,...You will have to fill in your questions and answers. When I ask you the question, and you answer correctly, you get to go to the next question. However, if not answered correctly, you'll have to keep answering until you get it right.\nSkipping is allowed.");
		textExp.setWrappingWidth(490);
		textExp.setStyle("-fx-font-size: 15;");
		
		buttonNext.setOnAction(e -> Questions.FillIn(window, buttonNext, buttonSave));
		
		BorderPane border = new BorderPane();

		VBox boxExpV = new VBox(5);
		boxExpV.setPadding(new Insets(10,10,10,10));
		boxExpV.getChildren().addAll(labelExp, textExp);
		
		border.setCenter(boxExpV);
		
		Pane space = new Pane();
		space.setMinWidth(400);
		
		HBox boxExpH = new HBox(5);
		boxExpH.setPadding(new Insets(10,10,10,10));
		boxExpH.getChildren().addAll(space, buttonNext);
		
		border.setBottom(boxExpH);
		
		sceneExplanation = new Scene(border, 500, 300);
		
		window.setScene(sceneExplanation);
	}

}
