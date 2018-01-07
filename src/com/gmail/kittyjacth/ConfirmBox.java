package com.gmail.kittyjacth;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ConfirmBox {
	
	static boolean answer;
	
	public static boolean display(String title, String message) {
		Stage window = new Stage();
		VBox layout = new VBox(10);
		Label label = new Label();
		Scene scene = new Scene(layout);
		
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        label.setText(message);

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });
        
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        
        window.setScene(scene);
        window.showAndWait();

        return answer;
	}
	
}
