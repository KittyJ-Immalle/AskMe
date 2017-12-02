package com.gmail.kittyjacth;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;

public class Questions {
	
	static Scene sceneFillIn;
	static Scene sceneQuestions;
	static HashMap<TextField, TextField> questionsHash = new HashMap<TextField, TextField>();
	
	public static void FillIn(Stage window, Button buttonNext, Button buttonSave) {
		GridPane pane = new GridPane();
		BorderPane paneB = new BorderPane();
		ScrollPane paneScr = new ScrollPane(pane);
		Label fillIn = new Label("Please fill in.");
		fillIn.setStyle("-fx-font-size: 20;");
		Label rows = new Label("Rows:");
		rows.setStyle("-fx-font-size: 15;");
		Label max = new Label("(Max: " + 50 + ")");
		max.setTextFill(Color.RED);
		
		TextField rowCount = new TextField("3");
		rowCount.textProperty().addListener((observable) -> {
			pane.getChildren().clear();
		    generateRows(pane, rowCount);
		});
		
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(10,10,10,10));
		
		//VBox
		VBox boxV = new VBox(5);
		boxV.setPadding(new Insets(10,10,10,10));
		
		HBox boxH2 = new HBox(5);
		boxH2.getChildren().addAll(rows, max);
		
		boxV.getChildren().addAll(fillIn, boxH2, rowCount);
		
		//HBox
		HBox boxSave = new HBox(5);
		boxSave.setPadding(new Insets(10,10,10,10));
		
		HBox boxNext = new HBox(5);
		boxNext.setPadding(new Insets(10,10,10,10));
		
		Pane space = new Pane();
		space.setMinWidth(100);
		
		buttonNext.setOnAction(e -> QuestionsAsk(window, buttonNext, pane, rowCount));
		buttonSave.setOnAction(e -> insertQuestionsIntoQuestionsHash(pane));
		boxNext.getChildren().addAll(space, buttonNext, buttonSave);
		
		paneB.setCenter(paneScr);
		paneB.setBottom(boxNext);
		paneB.setTop(boxV);
		

		paneScr.setFitToHeight(true);
		
		generateRows(pane, rowCount);
		
		sceneFillIn = new Scene(paneB, 500, 500);
		window.setScene(sceneFillIn);
	}
	
	private static void generateRows(GridPane pane, TextField textField) {
		try {
			if (Integer.parseInt(textField.getText()) > 50) {
				textField.setText("50");
			}
			TextField a;
			TextField b;
			for (int i = 0; i < Integer.parseInt(textField.getText()) * 2; i += 2) {
				pane.add(new Label("Q"), 0, i);
				pane.add(new Label("A"), 1, i);
				a = new TextField("");
				b = new TextField("");
				pane.add(a, 0, i + 1);
				pane.add(b, 1, i + 1);
				
				System.out.println("Q.a.getText() : " + a.getText());
				System.out.println("A.a.getText() : " + b.getText());
			}		
		} catch (NumberFormatException e) {
			
		}
	}
	
	private static void insertQuestionsIntoQuestionsHash(GridPane pane){
		ObservableList<Node> nodeList = pane.getChildren();
		
		for (int i = 0; i < nodeList.size(); i++){
			
			Node n = nodeList.get(i);
			System.out.println(n instanceof TextField ? ((TextField)n).getText() : "Not a TextField object");
			
			if (n instanceof TextField){
				
				TextField q = (TextField) n;
				TextField a = (TextField)nodeList.get(i+1);
				
				System.out.println("!!! " + a.getText());
				System.out.println("!!! " + q.getText());
				if ( !q.getText().equals("") && !a.getText().equals("")){
					questionsHash.put(q, a);
				}
				
				i = i + 1; // jump two cells in the list cause we used two to put in the map
			}
		}
		
		for (Map.Entry<TextField, TextField> t : questionsHash.entrySet()){
			System.out.println("t.key: " + t.getKey().getText() +  " || t.value: " + t.getValue().getText() );
		}
	}
	
	private static void QuestionsAsk(Stage window, Button buttonNext, GridPane gridPane, TextField textField) {
		generateRows(gridPane, textField);
		Label ask = new Label();
		ask.setStyle("-fx-font-size:20;");
		TextField resp = new TextField();
		Button quit = new Button("Quit");
		quit.setStyle("-fx-font-size:20;");
		Button check = new Button("Check");
		check.setStyle("-fx-font-size:20;");
		Pane space = new Pane();
		space.setMinWidth(200);
		
		VBox localBoxV = new VBox(5);
		localBoxV.setPadding(new Insets(10,10,10,10));
		localBoxV.getChildren().addAll(ask, resp);
		localBoxV.setAlignment(Pos.CENTER);
		
		HBox localBoxH = new HBox(5);
		localBoxH.setPadding(new Insets(10,10,10,10));
		localBoxH.getChildren().addAll(space, check, buttonNext);
		
		BorderPane localPane = new BorderPane();
		localPane.setTop(quit);
		localPane.setCenter(localBoxV);
		localPane.setBottom(localBoxH);
		
		Iterator<Entry<TextField, TextField>> it = questionsHash.entrySet().iterator();
		nextQuestion(ask, textField, it);
		
		//for(Map.Entry<TextField, TextField> entry:questionsHash.entrySet()) {
			
		//}
		sceneQuestions = new Scene(localPane, 500, 500);
		
		window.setScene(sceneQuestions);
	}
	
	private static void nextQuestion(Label ask, TextField textField, Iterator<Entry<TextField, TextField>> it) {
			while (it.hasNext()) {
				Map.Entry<TextField, TextField> pair = (Map.Entry<TextField, TextField>)it.next();
				TextField qu = pair.getKey();
				TextField an = pair.getValue();
				
				System.out.println("qu.getText() : " + qu.getText());
				System.out.println("an.getText() : " + an.getText());
				
				if(qu.getText().equals("") || an.getText().equals("")) {
					ask.setText(qu.getText());
				} else {
					nextQuestion(ask, textField, it);
			}
			
			System.out.println();
		}
	}
	
}
