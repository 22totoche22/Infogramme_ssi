import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
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
    		StackPane root2 = new StackPane();
            Scene newscene = new Scene(root2);
            
            VBox layout2= new VBox();
            Text debut2 = new Text("Vous �tes Olivier Chandessu, (age, situation pro, poste) au sein de la compagnie a�rienne AirEnac.\n"
            		+ "Cette compagnie se situe dans le milieu de l�aviation d�affaire et compte beaucoup d�entreprises telles que X, Y et Z comme clients.\r\n" + 
            		"Alors que les attaques informatiques se multiplient, vous d�cidez d�investir dans divers moyens afin de prot�ger votre entreprise.\nVous avez pour cela un budget de XXX euros.\r\n" + 
            		"Toutes les actions requi�rent du temps pour �tre mises en place, et certaines actions ne peuvent pas �tre men�es en m�me temps,\nchoisissez donc judicieusement.\r\n" + 
            		"");
            debut2.setTextAlignment(TextAlignment.JUSTIFY);
            
            layout2.getChildren().add(debut2);       
            root2.getChildren().add(layout2);
            Button suite = new Button("Suivant");
            layout2.getChildren().add(suite);
            //suite.setAlignment(Pos.CENTER);
            
            Stage newStage = new Stage();
            newStage.setScene(newscene);
            newStage.setResizable(false);
            newStage.show(); 
            stage.close();
        	
           // System.exit(0);
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
