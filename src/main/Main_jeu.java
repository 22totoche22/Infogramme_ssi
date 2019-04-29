package main;
import Vue.Vue;
import javafx.application.Application;
import javafx.stage.Stage;

public abstract class Main_jeu{
	
	public static void lancerJeu() {
		Application.launch(Vue.class, (String) null);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		lancerJeu();
	}
}
