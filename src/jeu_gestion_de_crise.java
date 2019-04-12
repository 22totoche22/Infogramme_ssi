import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class jeu_gestion_de_crise extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	public void start(Stage stage) {
		StackPane root = new StackPane();
        Scene scene = new Scene(root);
        
        VBox layout= new VBox();
        Text debut = new Text("Bienvenue sur le jeu de gestion de crise ssi");
        debut.setTextAlignment(TextAlignment.JUSTIFY);
        
        layout.getChildren().add(debut);
        
        Button bouton = new Button("start");
        EventHandler<ActionEvent> quitHandler = quitEvent -> {
            System.exit(0);
        };
        bouton.setOnAction(quitHandler);
        layout.getChildren().add(bouton);
        
        
        root.getChildren().add(layout);

        
        stage.setTitle("Jeu de Gestion de crise ssi");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
	}
}
