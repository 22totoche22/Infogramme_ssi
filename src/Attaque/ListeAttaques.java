package Attaque;

public class ListeAttaques {
	
	public static Attaque[] listeAttaques() {
		
		Scenario d1L = new Scenario("Une inondation a eu lieu dans vos locaux et a touché l’alimentation électrique : \n il n’y a plus d’électricité dans tout le bâtiment ! \n Vos ordinateurs s’éteignent et deviennent inutilisables. \n Vos données sont perdues et vous devez racheter de nouveaux hardwares.",6,6,6,6,6,6);
		Scenario d1R = new Scenario("Une inondation a eu lieu dans vos locaux et a touché l’alimentation électrique : \n il n’y a plus d’électricité dans tout le bâtiment ! \n Vos ordinateurs s’éteignent et deviennent inutilisables. \n Vos données sont conservées sur le système de sauvegarde de secours mais vous devez racheter de nouveaux hardwares.",6,6,6,6,6,6);
		ScenariiTree c1R = new ScenariiTree(d1L,11,null,null);
		ScenariiTree c1L = new ScenariiTree(d1R,11,null,null);
		ScenariiTree c1 = new ScenariiTree(null,11,c1L,c1R);
		Attaque b1 = new Attaque(0.005,c1);
		
		Scenario d2L = new Scenario("Un tremblement de terre a eu lieu dans la région et vos locaux ont été touchés. \n Malheureusement pour vous, bien que les bâtiments aient été épargnés, tous les postes de travail ont été renversé : \n Vous avez perdu de nombreux pc et documents.Vous allez mettre du temps à tout ranger. Vous devez racheter du matériel et prendre du temps pour tenter de récupérer des données, peut-être corrompues.",6,6,6,6,6,6);
		Scenario d2R = new Scenario("Un tremblement de terre a eu lieu dans la région et vos locaux ont été touchés. \n Malheureusement pour vous, bien que les bâtiments aient été épargnés, tous les postes de travail ont été renversé : \n Vous avez perdu de nombreux pc et documents.Après un certain temps à ranger et à installer de nouvelles machines, \n vous pouvez vous servir de vos backups pour récupérer vos données.",6,6,6,6,6,6);
		ScenariiTree c2R = new ScenariiTree(d2R,11,null,null);
		ScenariiTree c2L = new ScenariiTree(d2L,11,null,null);
		ScenariiTree c2 = new ScenariiTree(null,11,c2L,c2R);
		Attaque b2 = new Attaque(0.005,c2);
		
		Scenario d3L = new Scenario("Un incendie s’est déclenché au deuxième étage du bâtiment ! \n Malgré la réactivité de votre personnel, une partie du matériel est brûlée et devient inutilisable. \n Vos données sont perdues et vous devez racheter de nouveaux hardwares.",6,6,6,6,6,6);
		Scenario d3R = new Scenario("Un incendie s’est déclenché au deuxième étage du bâtiment ! \n Malgré la réactivité de votre personnel, une partie du matériel est brûlée et devient inutilisable. \n Vos données sont conservées sur le système de sauvegarde de secours mais vous devez racheter de nouveaux hardwares.",6,6,6,6,6,6);
		ScenariiTree c3R = new ScenariiTree(d3R,11,null,null);
		ScenariiTree c3L = new ScenariiTree(d3L,11,null,null);
		ScenariiTree c3 = new ScenariiTree(null,11,c3L,c3R);
		Attaque b3 = new Attaque(0.005,c3);
		
		Scenario d4R = new Scenario("Un attaquant extérieur à l’entreprise a tenté de s’introduire dans le réseau en  subtilisant les mots de passes d’un de vos employés ! \n L’attaquant n’arrive pas à pénétrer profondément dans le réseau et à élever ses droits, il ne subtilise aucune donnée critique.",6,6,6,6,6,6);
		Scenario d4LL = new Scenario("Un attaquant extérieur à l’entreprise a tenté de s’introduire dans le réseau en  subtilisant les mots de passes d’un de vos employés ! \n L’attaquant arrive à pivoter sur des machines critiques du système et à subtiliser des données critiques en grande quantité.",6,6,6,6,6,6);
		Scenario d4LR = new Scenario("Un attaquant extérieur à l’entreprise a tenté de s’introduire dans le réseau en  subtilisant les mots de passes d’un de vos employés ! \n L’attaquant arrive à pivoter sur des machines critiques du système mais ne subtilise qu’une petite quantité \n de données grâce au firewall qui bloque la majorité des requêtes de l’attaquant.",6,6,6,6,6,6);
		ScenariiTree c4LR = new ScenariiTree(d4LR,5,null,null);
		ScenariiTree c4LL = new ScenariiTree(d4LL,5,null,null);
		ScenariiTree c4L = new ScenariiTree(null,5,c4LL,c4LR);
		ScenariiTree c4R = new ScenariiTree(d4R,0,null,null);
		ScenariiTree c4 = new ScenariiTree(null,0,c4L,c4R);
		Attaque b4 = new Attaque(0.005,c4);
		
		Scenario d5RR = new Scenario("Un employé a inséré une clé usb sur un ordinateur. \n Cette action, qui peut sembler sans importance, a conduit à l’introduction d’un virus sur vos serveurs.\n Le virus est détecté rapidement et seule une partie de vos données ont été corrompues.Vous avez pu récupérer vos données à partir de vos sauvegardes. \n Votre entreprise continue à tourner sans problème.",6,6,6,6,6,6);
		Scenario d5RL = new Scenario("Un employé a inséré une clé usb sur un ordinateur. \n Cette action, qui peut sembler sans importance, a conduit à l’introduction d’un virus sur vos serveurs.\n Le virus est détecté rapidement et seule une partie de vos données ont été corrompues.Pas de sauvegarde de vos données… \n Vous avez perdu des données concernant plusieurs de vos vols : ces vols ne peuvent plus décoller !",6,6,6,6,6,6);
		Scenario d5LR = new Scenario("Un employé a inséré une clé usb sur un ordinateur. \n Cette action, qui peut sembler sans importance, a conduit à l’introduction d’un virus sur vos serveurs.Le virus n’a pas été détecté : \n il s’est répandu et a corrompu vos données. Les serveurs sont inutilisables. \n Vous avez pu récupérer vos données à partir de vos sauvegardes.",6,6,6,6,6,6);
		Scenario d5LL = new Scenario("Un employé a inséré une clé usb sur un ordinateur. \n Cette action, qui peut sembler sans importance, a conduit à l’introduction d’un virus sur vos serveurs.Le virus n’a pas été détecté : \n il s’est répandu et a corrompu vos données. Les serveurs sont inutilisables. \n Pas de sauvegarde de vos données… Vous avez perdu des données concernant vos vols et vos clients :\n aucun vol ne peut décoller !",6,6,6,6,6,6);
		ScenariiTree c5RR = new ScenariiTree(d5RR,11,null,null);
		ScenariiTree c5RL = new ScenariiTree(d5RL,11,null,null);
		ScenariiTree c5LR = new ScenariiTree(d5LR,11,null,null);
		ScenariiTree c5LL = new ScenariiTree(d5LL,11,null,null);
		ScenariiTree c5L = new ScenariiTree(null,11,c5LL,c5LR);
		ScenariiTree c5R = new ScenariiTree(null,11,c5RL,c5RR);
		ScenariiTree c5 = new ScenariiTree(null,6,c5L,c5R);
		Attaque b5 = new Attaque(0.005,c5);
		
		Scenario d6R = new Scenario("Un de vos fournisseurs a été victime d’une attaque SSI ! \n Heureusement, les défenses du prestataire sont suffisamment hautes et les données fournies suffisamment \n mineures pour que vous n’ayez pas de perte de données critiques.",6,6,6,6,6,6);
		Scenario d6L = new Scenario(" Un de vos fournisseurs a été victime d’une attaque SSI ! \n Les défenses du prestataire sont trop faibles et les données critiques que vous lui avez fournies sont subtilisées.",6,6,6,6,6,6);
		ScenariiTree c6R = new ScenariiTree(d6R,13,null,null);
		ScenariiTree c6L = new ScenariiTree(d6L,13,null,null);
		ScenariiTree c6 = new ScenariiTree(null,13,c6L,c6R);
		Attaque b6 = new Attaque(0.005,c6);
		
		Scenario d7L = new Scenario("Une personne appelle la direction en se faisant passer pour le directeur \n et demande de l’argent sur un compte inconnu pour un nouvel investissement urgent.\n La direction décide de prendre la décision d’accepter cet investissement et envoie l’argent.",6,6,6,6,6,6);
		Scenario d7R = new Scenario("Une personne appelle la direction en se faisant passer pour le directeur \n et demande de l’argent sur un compte inconnu pour un nouvel investissement urgent.\n La direction décide d’ignorer cet appel suspect.",6,6,6,6,6,6);
		ScenariiTree c7R = new ScenariiTree(d7R,4,null,null);
		ScenariiTree c7L = new ScenariiTree(d7L,4,null,null);
		ScenariiTree c7 = new ScenariiTree(null,4,c7L,c7R);
		Attaque b7 = new Attaque(0.005,c7);	
		
		Scenario d8L = new Scenario("Le responsable des finances reçoit un mail des finances publiques demandant les mots de passe des comptes bancaires de l’entreprise pour une inspection. \n Le responsable accepte cette requête et envoie les codes des comptes.",6,6,6,6,6,6);
		Scenario d8R = new Scenario("Le responsable des finances reçoit un mail des finances publiques demandant les mots de passe des comptes bancaires de l’entreprise pour une inspection. \n Le responsable ignore ce mail suspect et le signale à la direction.",6,6,6,6,6,6);
		ScenariiTree c8R = new ScenariiTree(d8R,3,null,null);
		ScenariiTree c8L = new ScenariiTree(d8L,3,null,null);
		ScenariiTree c8 = new ScenariiTree(null,3,c8L,c8R);
		Attaque b8 = new Attaque(0.005,c8);
		
		Scenario d9RR = new Scenario("Un hackeur mal intentionné décide de vous attaquer par DDOS en saturant vos serveurs. \n Le Responsable de la Sécurité parvient à bannir l’adresse IP de l’attaquant dans le firewall, \n ce qui vous sauvez du déni de service.",6,6,6,6,6,6);
		Scenario d9L = new Scenario("Un hackeur mal intentionné décide de vous attaquer par DDOS en saturant vos serveurs. \n Vos serveurs tombent en panne pour une durée indéterminée. \n Vous ne pouvez plus assurer le service de vente de tickets !",6,6,6,6,6,6);
		ScenariiTree c9RR = new ScenariiTree(d9RR,5,null,null);
		ScenariiTree c9RL = new ScenariiTree(d9L,5,null,null);
		ScenariiTree c9R = new ScenariiTree(d9L,5,c9RL,c9RR);
		ScenariiTree c9L = new ScenariiTree(d9L,0,null,null);
		ScenariiTree c9 = new ScenariiTree(null,0,c9L,c9R);
		Attaque b9 = new Attaque(0.005,c9);
		
		Scenario d10R = new Scenario("Les machines qui maintiennent le serveur actif tombent en panne. \n L’équipe de maintenance arrive sur les lieux rapidement après la panne et résolvent le problème très rapidement, \n la suspension des activités n’était que très temporaire.",6,6,6,6,6,6);
		Scenario d10L = new Scenario("Un hackeur mal intentionné décide de vous attaquer par DDOS en saturant vos serveurs. \n Ces ordinateurs sont vieux et mal maintenus, il se passe beaucoup de temps avant que vous n'arriviez à les remplacer, ce qui cause l’ire de vos clients. !",6,6,6,6,6,6);
		ScenariiTree c10R = new ScenariiTree(d10R,7,null,null);
		ScenariiTree c10L = new ScenariiTree(d10L,7,null,null);
		ScenariiTree c10 = new ScenariiTree(null,7,c10L,c10R);
		Attaque b10 = new Attaque(0.005,c10);
		
		Scenario d11R = new Scenario("Un de vos employé déçu de ne pas recevoir d’augmentation, a mis le feu au compteur électrique. \n Votre compteur électrique n’étant pas accessible et sous vidéo-surveillance, \n votre agent de sécurité a pu prendre sur le fait cet employé.",6,6,6,6,6,6);
		Scenario d11LR = new Scenario("Un de vos employé déçu de ne pas recevoir d’augmentation, a mis le feu au compteur électrique.\n Le compteur a pris feu et provoquer un blackout pendant 12h le temps qu’un technicien intervienne. \n Malheureusement, vos serveurs ont souffert et vous avez perdu de nombreuses données.\n Vous avez pu récupérer vos données à partir de vos sauvegardes. Grâce à cela vos vols ont pu continuer",6,6,6,6,6,6);
		Scenario d11LL = new Scenario("Un de vos employé déçu de ne pas recevoir d’augmentation, a mis le feu au compteur électrique.\n Le compteur a pris feu et provoquer un blackout pendant 12h le temps qu’un technicien intervienne. \n Malheureusement, vos serveurs ont souffert et vous avez perdu de nombreuses données.\n Pas de sauvegarde de vos données… Vous avez perdu des données concernant vos vols et vos clients : \n aucun vol ne peut décoller et à cause de cela vous perdez des clients !",6,6,6,6,6,6);
		ScenariiTree c11LR = new ScenariiTree(d11LR,11,null,null);
		ScenariiTree c11LL = new ScenariiTree(d11LL,11,null,null);
		ScenariiTree c11R = new ScenariiTree(d11R,8,null,null);
		ScenariiTree c11L = new ScenariiTree(d10L,11,c11LL,c11LR);
		ScenariiTree c11 = new ScenariiTree(null,8,c11L,c11R);
		Attaque b11 = new Attaque(0.005,c11);

		
		Attaque a[] = {b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11};
		return a;
	}
}
