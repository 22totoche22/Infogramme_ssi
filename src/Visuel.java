import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.application.*;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.scene.text.TextAlignment;

public class Visuel extends Application {
	
	public void start(Stage stage) {
		StackPane root = new StackPane();
        Scene newscene = new Scene(root);
        
        VBox layout= new VBox();
        Text debut = new Text("Vous êtes Olivier Chandessu, (age, situation pro, poste) au sein de la compagnie aérienne AirEnac.\n"
        		+ "Cette compagnie se situe dans le milieu de l’aviation d’affaire et compte beaucoup d’entreprises telles que X, Y et Z comme clients.\r\n" + 
        		"Alors que les attaques informatiques se multiplient, vous décidez d’investir dans divers moyens afin de protéger votre entreprise.\nVous avez pour cela un budget de XXX euros.\r\n" + 
        		"Toutes les actions requièrent du temps pour être mises en place, et certaines actions ne peuvent pas être menées en même temps,\nchoisissez donc judicieusement.\r\n" + 
        		"");
        debut.setTextAlignment(TextAlignment.JUSTIFY);
        
        layout.getChildren().add(debut);       
        root.getChildren().add(layout);
     
        stage.setTitle("Jeu de Gestion de crise ssi");
        stage.setScene(newscene);
        stage.setResizable(false);
        stage.show();
        
	}

	
}
