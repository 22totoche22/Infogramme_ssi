package Vue;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import main.Jeu_java;

public class Vue extends Application {
	
	private Stage stage;
	
	private Jeu_java jeu;
	
	private StackPane arrowIcon;
	
	private ProgressBar progress_time = new ProgressBar();
	
	private VBox ameliorations = new VBox();
	private VBox ameliorations_employes = new VBox();
	private VBox ameliorations_infra = new VBox();
	private VBox ameliorations_politiques = new VBox();
	private VBox ameliorations_partenaires = new VBox();
	private VBox ameliorations_donnees = new VBox();
	
	private VBox layout_formation = new VBox();
	private VBox layout_upgrade = new VBox();
	
	private VBox decision_cellule = new VBox();
	private VBox decisions = new VBox();
	private VBox decision_comm = new VBox();
	private VBox decision_e = new VBox();
	private VBox decision_reprise = new VBox();
	
	private StackPane ANSSI;
	
	private boolean focus = false;
	private boolean presence_ANSSI = false;
	private boolean attaque = false;
	private boolean action = false;
	
	
	private int temps_courant = 0;
	private int temps_total = 20;
	
	private Text helpText;
	
	private DoubleProperty formation_pourcent = new SimpleDoubleProperty(0);
	private DoubleProperty upgrade_pourcent = new SimpleDoubleProperty(0);
	private DoubleProperty time_pourcent = new SimpleDoubleProperty(0);
	
	private EventHandler<ActionEvent> quitting = quitEvent -> {
		Stage stageQuit = new Stage();
		StackPane root = new StackPane();
		Scene scene = new Scene(root);
		stageQuit.setTitle("Quitter");
		VBox layout = new VBox();
		layout.setPadding(new Insets(50));
		Text text = new Text("Etes vous sûr ?");
		text.setFont(new Font(20));
		layout.getChildren().add(text);
		VBox.setMargin(text, new Insets(0,0,0,8));
		
		HBox menu = new HBox();
		menu.setSpacing(15);
		
		Button stopping = new Button("Oui");
		stopping.setFont(new Font(20));
		stopping.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				stage.close();
				stageQuit.close();
			}
		});
		Button continuing = new Button("Non");
		continuing.setFont(new Font(20));
		continuing.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				stageQuit.close();
			}
		});
		
		menu.getChildren().addAll(stopping,continuing);
		layout.getChildren().add(menu);
		VBox.setMargin(menu, new Insets(5,0,0,8));
		root.getChildren().add(layout);
		stageQuit.setScene(scene);
		stageQuit.show();
	};
	
	
	private EventHandler<MouseEvent> clickArrow = new EventHandler<MouseEvent>(){
    	@Override
    	public void handle(MouseEvent e) {
    		arrowIcon.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
    		ameliorations.setVisible(true);
    		ameliorations.setManaged(true);
    		
    		stage.sizeToScene();
    		helpText.setText("←");
    		arrowIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
    			@Override
            	public void handle(MouseEvent e) {
    				arrowIcon.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
    				ameliorations.setVisible(false);
    				ameliorations.setManaged(false);
    				ameliorations_employes.setVisible(false);
    				ameliorations_employes.setManaged(false);
    				ameliorations_infra.setVisible(false);
    				ameliorations_infra.setManaged(false);
    				ameliorations_donnees.setVisible(false);
    				ameliorations_donnees.setManaged(false);
    				ameliorations_partenaires.setVisible(false);
    				ameliorations_partenaires.setManaged(false);
    				ameliorations_politiques.setVisible(false);
    				ameliorations_politiques.setManaged(false);
    				stage.sizeToScene();
        			helpText.setText("→");
        			arrowIcon.addEventHandler(MouseEvent.MOUSE_CLICKED,clickArrow);
    			}
    		});
    	}
    };
	
    private EventHandler<MouseEvent> manage_menus(VBox menu_a_afficher, StackPane source) {
    	EventHandler<MouseEvent> function = new EventHandler<MouseEvent>(){
    		@Override
        	public void handle(MouseEvent e) {
    			if(!focus) {
    				focus = true;
    				menu_a_afficher.setVisible(true);
    				menu_a_afficher.setManaged(true);
    				stage.sizeToScene();
    				source.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
    				source.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
    					@Override
    					public void handle(MouseEvent e) {
    						focus = false;
    						menu_a_afficher.setVisible(false);
    	    				menu_a_afficher.setManaged(false);
    	    				stage.sizeToScene();
    	    				source.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
    	    				source.addEventHandler(MouseEvent.MOUSE_CLICKED, manage_menus(menu_a_afficher,source));
    					}
    				});
    			}
    		}
    	};
    	return function;
    }
    
    private EventHandler<MouseEvent> manage_formations(double argent, int i, int temps) {
    	EventHandler<MouseEvent> function = new EventHandler<MouseEvent>(){
    		@Override
        	public void handle(MouseEvent e) {
    			jeu.getEntreprise().debut_formation(argent, i, temps);
    			layout_formation.setVisible(true);
    			layout_formation.setManaged(true);
    		}
    	};
    	return function;
    }
    
    private EventHandler<MouseEvent> manage_upgrades(double argent, int i, int temps) {
    	EventHandler<MouseEvent> function = new EventHandler<MouseEvent>(){
    		@Override
        	public void handle(MouseEvent e) {
    			jeu.getEntreprise().debut_formation(argent, i, temps);
    			layout_upgrade.setVisible(true);
    			layout_upgrade.setManaged(true);
    		}
    	};
    	return function;
    }
    
	private EventHandler<ActionEvent> quitHandlerBegin2 = quitEvent -> {
		stage.close();
    	StackPane root = new StackPane();
    	Scene scene = new Scene(root);
    	stage.setTitle("Jeu de Gestion de crise ssi");
    	VBox layout = new VBox();
    	HBox menu = new HBox();
    	
    	menu.setPadding(new Insets(5, 2, 5, 2));
        menu.setSpacing(10);
        menu.setStyle("-fx-background-color: #336699;");

    	Button buttonCurrent = new Button("Quitter");
    	buttonCurrent.setOnAction(quitting);
    	menu.getChildren().add(buttonCurrent);
    	layout.getChildren().add(menu);
    	
    	HBox gameplay = new HBox();
    	
    	//Gestion des panneaux des améliorations et de tous les autres
    	//ameliorations = new VBox();
    	
    	StackPane employes = new StackPane();
    	employes.getChildren().addAll(new Rectangle(100,40,Color.rgb(255, 255, 255)), new Text("Employes"));
    	employes.setPadding(new Insets(10));
    	employes.addEventHandler(MouseEvent.MOUSE_CLICKED,manage_menus(ameliorations_employes,employes));
    	
    	StackPane infrastructures = new StackPane();
    	infrastructures.getChildren().addAll(new Rectangle(100,40,Color.rgb(255, 255, 255)), new Text("Infrastructures"));
    	infrastructures.setPadding(new Insets(10));
    	infrastructures.addEventHandler(MouseEvent.MOUSE_CLICKED,manage_menus(ameliorations_infra,infrastructures));
    	
    	StackPane politiques = new StackPane();
    	politiques.getChildren().addAll(new Rectangle(100,40,Color.rgb(255, 255, 255)), new Text("Politiques"));
    	politiques.setPadding(new Insets(10));
    	politiques.addEventHandler(MouseEvent.MOUSE_CLICKED,manage_menus(ameliorations_politiques,politiques));
    	
    	StackPane partenaires = new StackPane();
    	partenaires.getChildren().addAll(new Rectangle(100,40,Color.rgb(255, 255, 255)), new Text("Partenaires"));
    	partenaires.setPadding(new Insets(10));
    	partenaires.addEventHandler(MouseEvent.MOUSE_CLICKED,manage_menus(ameliorations_partenaires,partenaires));
    	
    	StackPane donnees = new StackPane();
    	donnees.getChildren().addAll(new Rectangle(100,40,Color.rgb(255, 255, 255)), new Text("Données"));
    	donnees.setPadding(new Insets(10));
    	donnees.addEventHandler(MouseEvent.MOUSE_CLICKED,manage_menus(ameliorations_donnees,donnees));
    	
    	ameliorations.getChildren().addAll(employes, infrastructures,politiques,partenaires,donnees);
    	ameliorations.setVisible(false);
    	ameliorations.setManaged(false);
    	
    	//amelioration_employes
    	
    	StackPane RSSI = new StackPane();
    	RSSI.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)), new Text("RSSI"));
    	RSSI.setPadding(new Insets(5));
    	RSSI.addEventHandler(MouseEvent.MOUSE_CLICKED,manage_formations(1000,0,temps_courant));
    	
    	StackPane finance = new StackPane();
    	finance.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Finance"));
    	finance.setPadding(new Insets(5));
    	finance.addEventHandler(MouseEvent.MOUSE_CLICKED,manage_formations(1000,1,temps_courant));
    	
    	StackPane comms = new StackPane();
    	comms.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Communications"));
    	comms.setPadding(new Insets(5));
    	comms.addEventHandler(MouseEvent.MOUSE_CLICKED,manage_formations(1000,2,temps_courant));
    	
    	StackPane juridique = new StackPane();
    	juridique.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Juridique"));
    	juridique.setPadding(new Insets(5));
    	juridique.addEventHandler(MouseEvent.MOUSE_CLICKED,manage_formations(1000,3,temps_courant));
    	
    	StackPane director = new StackPane();
    	director.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)), new Text("Direction"));
    	director.setPadding(new Insets(5));
    	director.addEventHandler(MouseEvent.MOUSE_CLICKED,manage_formations(1000,4,temps_courant));
    	
    	ameliorations_employes.getChildren().addAll(RSSI,finance,comms,juridique,director);
    	ameliorations_employes.setVisible(false);
    	ameliorations_employes.setManaged(false);
    	
    	
    	//amelioration_infra
    	
    	StackPane firewall = new StackPane();
    	firewall.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Firewall"));
    	firewall.setPadding(new Insets(5));
    	firewall.addEventHandler(MouseEvent.MOUSE_CLICKED,manage_upgrades(1000,5,temps_courant));
    	
    	StackPane antivirus = new StackPane();
    	antivirus.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Antivirus"));
    	antivirus.setPadding(new Insets(5));
    	antivirus.addEventHandler(MouseEvent.MOUSE_CLICKED,manage_upgrades(1000,6,temps_courant));
    	
    	StackPane maintenance = new StackPane();
    	maintenance.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Maintenance"));
    	maintenance.setPadding(new Insets(5));
    	maintenance.addEventHandler(MouseEvent.MOUSE_CLICKED,manage_upgrades(1000,7,temps_courant));
    	
    	StackPane protection = new StackPane();
    	protection.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Protection physique"));
    	protection.setPadding(new Insets(5));
    	protection.addEventHandler(MouseEvent.MOUSE_CLICKED,manage_upgrades(1000,8,temps_courant));
    	
    	StackPane logiciels = new StackPane();
    	logiciels.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Logiciels à jour"));
    	logiciels.setPadding(new Insets(5));
    	logiciels.addEventHandler(MouseEvent.MOUSE_CLICKED,manage_upgrades(1000,9,temps_courant));
    	
    	ameliorations_infra.getChildren().addAll(firewall,antivirus,maintenance,protection,logiciels);
    	ameliorations_infra.setVisible(false);
    	ameliorations_infra.setManaged(false);
    	
    	
    	//ameliorations_politiques = new VBox();
    	
    	StackPane risques = new StackPane();
    	risques.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Analyses des risques"));
    	risques.setPadding(new Insets(5));
    	risques.addEventHandler(MouseEvent.MOUSE_CLICKED,manage_upgrades(1000,12,temps_courant));
    	
    	StackPane plan_reprise = new StackPane();
    	plan_reprise.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Plan de reprise"));
    	plan_reprise.setPadding(new Insets(5));
    	plan_reprise.addEventHandler(MouseEvent.MOUSE_CLICKED,manage_upgrades(1000,10,temps_courant));
    	
    	StackPane backup = new StackPane();
    	backup.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Backup des sauvegardes"));
    	backup.setPadding(new Insets(5));
    	backup.addEventHandler(MouseEvent.MOUSE_CLICKED,manage_upgrades(1000,11,temps_courant));
    	
    	ameliorations_politiques.getChildren().addAll(risques,plan_reprise,backup);
    	ameliorations_politiques.setVisible(false);
    	ameliorations_politiques.setManaged(false);
    	
    	
    	//ameliorations_partenaires = new VBox();
    	
    	StackPane part1 = new StackPane();
    	part1.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Premier partenaire"));
    	part1.setPadding(new Insets(5));
    	part1.addEventHandler(MouseEvent.MOUSE_CLICKED,manage_upgrades(1000,13,temps_courant));
    	
    	StackPane part2 = new StackPane();
    	part2.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Second partenaire"));
    	part2.setPadding(new Insets(5));
    	part2.addEventHandler(MouseEvent.MOUSE_CLICKED,manage_upgrades(1000,14,temps_courant));
    	
    	ameliorations_partenaires.getChildren().addAll(part1,part2);
    	ameliorations_partenaires.setVisible(false);
    	ameliorations_partenaires.setManaged(false);
    	
    	
    	//ameliorations_donnees = new VBox();
    	
    	StackPane protection_donnees = new StackPane();
    	protection_donnees.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Protection des données"));
    	protection_donnees.setPadding(new Insets(5));
    	protection_donnees.addEventHandler(MouseEvent.MOUSE_CLICKED,manage_upgrades(1000,16,temps_courant));
    	
    	StackPane cryptage = new StackPane();
    	cryptage.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Cryptage des données"));
    	cryptage.setPadding(new Insets(5));
    	cryptage.addEventHandler(MouseEvent.MOUSE_CLICKED,manage_upgrades(1000,15,temps_courant));
    	
    	ameliorations_donnees.getChildren().addAll(protection_donnees,cryptage);
    	ameliorations_donnees.setVisible(false);
    	ameliorations_donnees.setManaged(false);
    	
    	//Grille contenant les éléments de jeu du centre de l'écran
    	VBox mainScreen = new VBox();
    	mainScreen.setPadding(new Insets(0, 10, 0, 10));
    	
    	HBox optionsList = new HBox();
    	
    	VBox leftOptions = new VBox();
    	
    	VBox middleOptions = new VBox();
    	
    	VBox rightOptions = new VBox();
    	
    	optionsList.getChildren().addAll(leftOptions,middleOptions,rightOptions);
    	
    	mainScreen.getChildren().add(optionsList);
    	
    	//Icone permettant d'afficher les améliorations
    	arrowIcon = new StackPane();
        Rectangle helpIcon = new Rectangle(20.0, 25.0);
        helpIcon.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
            new Stop[]{
            new Stop(0,Color.web("#4977A3")),
            new Stop(0.5, Color.web("#B0C6DA")),
            new Stop(1,Color.web("#9CB6CF")),}));
        helpIcon.setStroke(Color.web("#D0E6FA"));
        helpIcon.setArcHeight(3.5);
        helpIcon.setArcWidth(3.5);

        helpText = new Text("→");
        helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        helpText.setFill(Color.WHITE);
        helpText.setStroke(Color.web("#7080A0")); 

        arrowIcon.getChildren().addAll(helpIcon, helpText);
        arrowIcon.setAlignment(Pos.CENTER_LEFT);     // Right-justify nodes in stack
        StackPane.setMargin(helpText, new Insets(0, 25, 0, 2)); // Center "?"
    	
        
        arrowIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, clickArrow);
    	
    	leftOptions.getChildren().add(arrowIcon);
    	
    	
    	//Layout de la barre de chargement
    	//VBox layout_formation = new VBox();
    	layout_formation.setPadding(new Insets(20,0,20,0));
    	ProgressBar progress_formation = new ProgressBar();
    	progress_formation.progressProperty().bind(formation_pourcent);
    	
    	layout_formation.getChildren().add(progress_formation);
    	layout_formation.getChildren().add(new Text("Formation"));
    	
    	layout_formation.setVisible(false);
    	layout_formation.setManaged(false);
    	
    	leftOptions.getChildren().add(layout_formation);
    	
    	//VBox layout_upgrade = new VBox();
    	ProgressBar progress_upgrade = new ProgressBar();
    	progress_upgrade.progressProperty().bind(upgrade_pourcent);
    	
    	layout_upgrade.getChildren().add(progress_upgrade);
    	layout_upgrade.getChildren().add(new Text("Infrastructures"));
    	
    	layout_upgrade.setVisible(false);
    	layout_upgrade.setManaged(false);
    	
    	leftOptions.getChildren().add(layout_upgrade);
    	
    	//Layout du centre
    	
    	middleOptions.setMinWidth(570);
    	middleOptions.setMinHeight(300);
    	
    	
    	//Layout de droite (informations de l'entreprise)
    	
    	StackPane argent_display = new StackPane();
    	double argent_double = jeu.getEntreprise().getArgent();
    	argent_display.getChildren().add(new Text("Argent : " + Double.toString(argent_double)));
    	argent_display.setPadding(new Insets(10));
    	argent_display.setAlignment(Pos.TOP_LEFT);
    	
    	StackPane rep_display = new StackPane();
    	rep_display.getChildren().add(new Text("Réputation : " + Double.toString((jeu.getEntreprise().getReputationClient() + jeu.getEntreprise().getReputationPublic())/2)));
    	rep_display.setPadding(new Insets(10));
    	rep_display.setAlignment(Pos.TOP_LEFT);
    	
    	rightOptions.getChildren().addAll(argent_display,rep_display);
    	rightOptions.setAlignment(Pos.TOP_LEFT);
    	
    	VBox layout_time = new VBox();
    	layout_time.setPadding(new Insets(50,0,0,0));
    	progress_time = new ProgressBar();
    	progress_time.setMinWidth(900);
    	progress_time.progressProperty().bind(time_pourcent);
    	
    	layout_time.getChildren().add(progress_time);
    	
    	mainScreen.getChildren().add(layout_time);
    	
    	//Menus de la partie de gestion de crise
    	
    	//VBox decision_cellule = new VBox();
    	
    	StackPane declencher = new StackPane();
    	declencher.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Déclencher la cellule de crise"));
    	declencher.setPadding(new Insets(10));
    	
    	decision_cellule.getChildren().add(declencher);
    	decision_cellule.setVisible(false);
    	decision_cellule.setManaged(false);
    	decision_cellule.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
    		@Override
    		public void handle(MouseEvent e) {
    			decision_cellule.setVisible(false);
    	    	decision_cellule.setManaged(false);
    	    	decisions.setVisible(true);
    	    	decisions.setManaged(true);
    		}
    	});
    	
    	//decision_comm = new VBox();
    	
    	StackPane interieur = new StackPane();
    	interieur.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Aux employés"));
    	interieur.setPadding(new Insets(5));
    	
    	StackPane clients = new StackPane();
    	clients.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Aux clients"));
    	clients.setPadding(new Insets(5));
    	
    	StackPane exterieur = new StackPane();
    	exterieur.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Au public"));
    	exterieur.setPadding(new Insets(5));
    	
    	StackPane ANSSI = new StackPane();
    	ANSSI.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Contacter l'ANSSI"));
    	ANSSI.setPadding(new Insets(5));
    	
    	decision_comm.getChildren().addAll(exterieur,clients,interieur,ANSSI);
    	decision_comm.setVisible(false);
    	decision_comm.setManaged(false);
    	
    	
    	//decision_e = new VBox();
    	
    	
    	StackPane SSI = new StackPane();
    	SSI.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Améliorer la SSI"));
    	SSI.setPadding(new Insets(5));
    	
    	StackPane failles = new StackPane();
    	failles.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Réparer les failles"));
    	failles.setPadding(new Insets(5));
    	
    	StackPane anssi = new StackPane();
    	anssi.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Demander l'aide de l'ANSSI"));
    	anssi.setPadding(new Insets(5));
    	anssi.setVisible(false);
    	
    	decision_e.getChildren().addAll(SSI,failles,anssi);
    	decision_e.setVisible(false);
    	decision_e.setManaged(false);
    	
    	
    	//decision_reprise = new VBox();
    	
    	StackPane rachat = new StackPane();
    	rachat.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Racheter du matériel"));
    	rachat.setPadding(new Insets(5));
    	
    	StackPane PCA = new StackPane();
    	PCA.getChildren().addAll(new Rectangle(80,30,Color.rgb(255, 255, 255)),new Text("Lancer le plan de reprise"));
    	PCA.setPadding(new Insets(5));
    	
    	decision_reprise.getChildren().addAll(rachat,PCA);
    	decision_reprise.setVisible(false);
    	decision_reprise.setManaged(false);
    	
    	//VBox decisions = new VBox();
    	
    	StackPane analyse = new StackPane();
    	analyse.getChildren().addAll(new Rectangle(100,40,Color.rgb(255, 255, 255)),new Text("Analyse de risque"));
    	analyse.setPadding(new Insets(10));
    	
    	StackPane comm = new StackPane();
    	comm.getChildren().addAll(new Rectangle(100,40,Color.rgb(255, 255, 255)),new Text("Communications"));
    	comm.setPadding(new Insets(10));
    	comm.addEventHandler(MouseEvent.MOUSE_CLICKED, manage_menus(decision_comm,comm));
    	
    	StackPane entrep = new StackPane();
    	entrep.getChildren().addAll(new Rectangle(100,40,Color.rgb(255, 255, 255)),new Text("Renforcement de l'entreprise"));
    	entrep.setPadding(new Insets(10));
    	entrep.addEventHandler(MouseEvent.MOUSE_CLICKED, manage_menus(decision_e,entrep));
    	
    	StackPane reprise = new StackPane();
    	reprise.getChildren().addAll(new Rectangle(100,40,Color.rgb(255, 255, 255)),new Text("Reprise de l'activité"));
    	reprise.setPadding(new Insets(10));
    	reprise.addEventHandler(MouseEvent.MOUSE_CLICKED, manage_menus(decision_reprise,reprise));
    	
    	StackPane stop_cellule = new StackPane();
    	stop_cellule.getChildren().addAll(new Rectangle(100,40,Color.rgb(255, 255, 255)),new Text("Arrêter la cellule"));
    	stop_cellule.setPadding(new Insets(10));
    	
    	decisions.getChildren().addAll(analyse,comm,entrep,reprise,stop_cellule);
    	decisions.setVisible(false);
    	decisions.setManaged(false);
    	
    	gameplay.getChildren().addAll(ameliorations,ameliorations_employes,ameliorations_infra,ameliorations_donnees,ameliorations_partenaires,ameliorations_politiques,mainScreen,decision_comm,decision_e,decision_reprise,decisions,decision_cellule);
    	
    	layout.getChildren().add(gameplay);
    	
    	root.getChildren().add(layout);
    	stage.setScene(scene);
    	stage.show();
    	main_loop();
    };
	
    private EventHandler<ActionEvent> quitHandlerBegin = quitEvent -> {
    	stage.close();
		StackPane root = new StackPane();
        Scene scene = new Scene(root);
        stage.setTitle("Jeu de Gestion de crise ssi");
        
        VBox layout= new VBox();
        layout.setPadding(new Insets(10));
        Text debut = new Text("Vous �tes Olivier Chandessu, (age, situation pro, poste) au sein de la compagnie a�rienne AirEnac.\n"
        		+ "Cette compagnie se situe dans le milieu de l�aviation d�affaire et compte beaucoup d�entreprises telles que X, Y et Z comme clients.\r\n" + 
        		"Alors que les attaques informatiques se multiplient, vous d�cidez d�investir dans divers moyens afin de prot�ger votre entreprise.\nVous avez pour cela un budget de XXX euros.\r\n" + 
        		"Toutes les actions requi�rent du temps pour �tre mises en place, et certaines actions ne peuvent pas �tre men�es en m�me temps,\nchoisissez donc judicieusement.\r\n" + 
        		"");
        debut.setTextAlignment(TextAlignment.JUSTIFY);
        
        layout.getChildren().add(debut);       
        root.getChildren().add(layout);
        Button suite = new Button("Suivant");
        suite.setOnAction(quitHandlerBegin2);
        layout.getChildren().add(suite);
        //suite.setAlignment(Pos.CENTER);
        VBox.setMargin(suite, new Insets(0,0,0,410));
        
        stage = new Stage();
        stage.setScene(scene);
        stage.show(); 
    	
       // System.exit(0);
    };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	public void start(Stage stage) {
		jeu = new Jeu_java();
		this.stage = stage;
		StackPane root = new StackPane();
        Scene scene = new Scene(root);
        
        VBox layout= new VBox();
        layout.setPadding(new Insets(10));
        Text debut = new Text("Bienvenue sur le jeu de gestion de crise ssi");
        debut.setTextAlignment(TextAlignment.JUSTIFY);
        
        layout.getChildren().add(debut);
        
        Button bouton = new Button("start");
        VBox.setMargin(bouton, new Insets(10,0,0,120));
        
        
        
        bouton.setOnAction(quitHandlerBegin);
        layout.getChildren().add(bouton);
        
        
        root.getChildren().add(layout);

        
        stage.setTitle("Jeu de Gestion de crise ssi");
        stage.setScene(scene);
        stage.show();
	}
	
	/*public void main_loop() {
		System.out.println("Ok");
		while(System.currentTimeMillis() - temps_debut < temps_total) {
			if(!attaque) {
				if(jeu.loop()) {
					
				}
				else {
					
					time_pourcent.set((System.currentTimeMillis() - temps_debut)/temps_total);

					if(jeu.getEntreprise().fin_formation()) {
						formation_pourcent.set(0);
					}
					else {
						formation_pourcent.set(jeu.getEntreprise().pourcent_formation());
					}
					
					if(jeu.getEntreprise().fin_upgrade()) {
						upgrade_pourcent.set(0);
					}
					else {
						upgrade_pourcent.set(jeu.getEntreprise().pourcent_upgrade());
					}
				}
			}
			stage.show();
		}
		System.out.println("Temps_fin : " + System.currentTimeMillis());
		//stage.close();
	}*/
	
	public void main_loop() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				javafx.application.Platform.runLater(new Runnable() {
					@Override
					public void run(){
						
						if(!attaque) {
							if(jeu.loop(temps_courant)) {
								System.out.println("Attaque !");
								attaque = true;
								ameliorations.setVisible(false);
								ameliorations.setManaged(false);
								ameliorations_employes.setVisible(false);
								ameliorations_employes.setManaged(false);
								ameliorations_infra.setVisible(false);
								ameliorations_infra.setManaged(false);
								ameliorations_donnees.setVisible(false);
								ameliorations_donnees.setManaged(false);
								ameliorations_partenaires.setVisible(false);
								ameliorations_partenaires.setManaged(false);
								ameliorations_politiques.setVisible(false);
								ameliorations_politiques.setManaged(false);
								arrowIcon.setVisible(false);
								arrowIcon.setManaged(false);
								layout_formation.setVisible(false);
								layout_formation.setManaged(false);
								layout_upgrade.setVisible(false);
								layout_upgrade.setManaged(false);
								
								decision_cellule.setManaged(true);
								decision_cellule.setVisible(true);
								
								focus = false;
								action = false;
								
								stage.sizeToScene();
								
								Stage stageAttaque = new Stage();
								
								StackPane root = new StackPane();
						        Scene scene = new Scene(root);
						        
						        VBox layout= new VBox();
						        layout.setPadding(new Insets(10));
						        layout.getChildren().add(new Text("Vous avez été attaqué !"));
						        layout.getChildren().add(new Text(jeu.getTexteScenario()));
						        Button bouton = new Button("Résoudre la crise");
						        bouton.setOnAction(new EventHandler<ActionEvent>() {
						        	@Override
						        	public void handle(ActionEvent e) {
						        		stageAttaque.close();
						        	}
						        });
						        layout.getChildren().add(bouton);
						        
						        root.getChildren().add(layout);
						        
						        stageAttaque.setTitle("Vous avez été attaqué !");
						        stageAttaque.setScene(scene);
						        stageAttaque.show();
						        
							}
							else {
								
								temps_courant+=1;
								time_pourcent.set(temps_courant/temps_total);

								if(jeu.getEntreprise().fin_formation(temps_courant)) {
									formation_pourcent.set(0);
									layout_formation.setVisible(false);
									layout_formation.setManaged(false);
								}
								else {
									formation_pourcent.set(jeu.getEntreprise().pourcent_formation(temps_courant));
								}
								
								if(jeu.getEntreprise().fin_upgrade(temps_courant)) {
									upgrade_pourcent.set(0);
									layout_upgrade.setVisible(false);
									layout_upgrade.setManaged(false);
								}
								else {
									upgrade_pourcent.set(jeu.getEntreprise().pourcent_upgrade(temps_courant));
								}
							}
						}
						else {
							if(action) {
								jeu.Evolution_entreprise();
								jeu.evolution_gravite();
							}
						}
					}
				});
			}
		},0,1000);
	}
    
	
}