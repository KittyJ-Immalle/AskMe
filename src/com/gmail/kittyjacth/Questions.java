package com.gmail.kittyjacth;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javafx.collections.ObservableList;
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
	
	public static void fillInQuestions(Stage window, Button buttonNext) {
		GridPane centerPane = new GridPane();
		BorderPane finalPane = new BorderPane();
		ScrollPane scrollPane = new ScrollPane(centerPane);
		Label fillIn = new Label("Please fill in.");
		Label rows = new Label("Rows:");
		Label max = new Label("(Max: " + 50 + ")");
		VBox centerBoxV = new VBox(5);
		HBox centerBoxH = new HBox(5);
		HBox lowerBox = new HBox(5);
		Pane space = new Pane();
		
		TextField rowCount = new TextField("3");
		rowCount.textProperty().addListener((observable) -> {
			centerPane.getChildren().clear();
		    generateRows(centerPane, rowCount);
		});
		
		// Style
		centerPane.setHgap(10);
		centerPane.setVgap(10);
		centerPane.setPadding(new Insets(10,10,10,10));
		fillIn.setStyle("-fx-font-size: 20;");
		rows.setStyle("-fx-font-size: 15;");
		max.setTextFill(Color.RED);
		centerBoxV.setPadding(new Insets(10,10,10,10));
		centerBoxH.getChildren().addAll(rows, max);
		centerBoxV.getChildren().addAll(fillIn, centerBoxH, rowCount);
		lowerBox.setPadding(new Insets(10,10,10,10));
		space.setMinWidth(400);
		
		// Events
		buttonNext.setOnAction(e -> {
			insertQuestionsIntoQuestionsHash(centerPane);
			askQuestions(window, buttonNext);
		});
		window.setOnCloseRequest(e -> {
			e.consume();
			AppWindow.closeProgram();
		});
		
		// Layout
		lowerBox.getChildren().addAll(space, buttonNext);
		finalPane.setCenter(scrollPane);
		finalPane.setBottom(lowerBox);
		finalPane.setTop(centerBoxV);
		scrollPane.setFitToHeight(true);
		
		generateRows(centerPane, rowCount);
		
		sceneFillIn = new Scene(finalPane, 500, 500);
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
			}		
		} catch (NumberFormatException e) {
			
		}
	}
	
	private static void insertQuestionsIntoQuestionsHash(GridPane pane){
		ObservableList<Node> nodeList = pane.getChildren();
		
		for (int i = 0; i < nodeList.size(); i++){
			
			Node n = nodeList.get(i);

			if (n instanceof TextField){
				
				TextField q = (TextField) n;
				TextField a = (TextField) nodeList.get(i+1);
				
				System.out.println("<q: " + q.getText() + ", " + "a: " + a.getText() + ">");
				
				if ( !q.getText().equals("") && !a.getText().equals("")){
					questionsHash.put(q, a);
				}
				
				i = i + 1; // jump two cells in the list cause we used two to put in the map
			}
		}
		
	}
	
	private static void askQuestions(Stage window, Button buttonNext) {
		Label ask = new Label();
		TextField resp = new TextField();
		Button quit = new Button("Quit");
		Button check = new Button("Answer");
		Pane space = new Pane();
		Pane space2 = new Pane();
		HBox topBox = new HBox(5);
		VBox centerBox = new VBox(5);
		HBox lowerBox = new HBox(5);
		BorderPane finalPane = new BorderPane();
		
		// Style
		ask.setStyle("-fx-font-size:20;");
		quit.setStyle("-fx-font-size:20;");
		check.setStyle("-fx-font-size:20;");
		space.setMinWidth(190);
		space2.setMinWidth(110);
		topBox.setPadding(new Insets(10,10,10,10));
		centerBox.setPadding(new Insets(10,10,10,10));
		centerBox.setAlignment(Pos.CENTER);
		lowerBox.setPadding(new Insets(10,10,10,10));
		
		// Events
		buttonNext.setOnAction(e -> {
			nextQuestion(ask);
		});
		quit.setOnAction(e -> {
			AppWindow.closeProgram();
		});
		window.setOnCloseRequest(e -> {
			e.consume();
			AppWindow.closeProgram();
		});
		// check.setOnAction(); !!!
		
		// Layout
		topBox.getChildren().add(quit);
		centerBox.getChildren().addAll(ask, resp);
		lowerBox.getChildren().addAll(space, check, space2, buttonNext);
		finalPane.setTop(topBox);
		finalPane.setCenter(centerBox);
		finalPane.setBottom(lowerBox);
		
		nextQuestion(ask);

		sceneQuestions = new Scene(finalPane, 500, 500);
		
		window.setScene(sceneQuestions);
	}
	
	private static void nextQuestion(Label ask) {
		
		Iterator<Entry<TextField, TextField>> it = questionsHash.entrySet().iterator();

		
		while (it.hasNext()) {
			Map.Entry<TextField, TextField> pair = (Map.Entry<TextField, TextField>) it.next();
			
			TextField qu = pair.getKey();
			TextField an = pair.getValue();
			
			if(!qu.getText().equals("") || !an.getText().equals("")) {
				ask.setText(qu.getText());
				TextField as = questionsHash.remove(qu);
				System.out.println("as.getText() : " + as.getText());
				break;
			}
		
			System.out.println();
		}
	}
	
}
