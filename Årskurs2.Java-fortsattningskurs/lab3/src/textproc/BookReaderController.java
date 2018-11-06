package textproc;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BookReaderController extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		//Model
		
//		Inför satser i start som skapar ett GeneralWordCounter-objekt och använder det för att
//		räkna ord i Nils Holgerssons underbara resa, så som gjordes i laboration 1. (Det går bra att
//		kopiera satserna för inläsningen från programmet Holgersson i den laborationen.)
		
		Scanner s = new Scanner(new File("../lab1/nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		
		Scanner stopwords = new Scanner(new File("undantagsord.txt"));
		
		GeneralWordCounter gwc = new GeneralWordCounter(stopwords);
		
		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			gwc.process(word);
		}

		s.close();
		
		
//		Läs ut en mängd (Set) med alla ord och deras antal. Använd metoden getWords som
//		du just lagt till i GeneralWordCounter.
		Set<Entry<String, Integer>> set = new HashSet<Entry<String, Integer>>();
		set = gwc.getWords();
		
		//TEST
//		Iterator<Entry<String, Integer>> itr = set.iterator();
//		
//		while(itr.hasNext()) {
//			System.out.println(itr.next().getKey());
//		}
		
//		Skapa en ObservableList med orden. Inför en lokal variabel för listan, eftersom du
//		kommer att behöva den till fler saker senare. Det finns en bekväm metod i JavaFX
//		för att skapa en ObservableList utifrån en mängd, som du strax ska få se.
		
		ObservableList<Entry<String, Integer>> words = FXCollections.observableArrayList(gwc.getWords());
		
//		Skapa ett ListView-objekt för att visa listan i fönstret.
		ListView<Entry<String, Integer>> listView = new ListView<Entry<String, Integer>>(words);
		

		//View
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root);
		primaryStage.setTitle("BookReader");
		primaryStage.setScene(scene);
		primaryStage.show();
		
//		Denna rad låter vår ListView fylla ut hela vår BorderPane		
		root.setCenter(listView);
		
		HBox hbox = new HBox();

		Button alphabeticButton = new Button("Alphabetic");
		Button frequencyButton = new Button("Frequency");
		Button searchButton = new Button("Search");
		
//		Gör så att knappen ”Find” aktiveras (trycks) automatiskt när man trycker Return.
//		Det finns en användbar metod i klassen Button för detta.
		searchButton.setDefaultButton(true);
		
		
		TextField tf = new TextField();
	
		
		hbox.getChildren().addAll(alphabeticButton, frequencyButton, tf, searchButton);
		
		root.setBottom(hbox);
		
		
		//Control
		
//		Nu ska vi få något att hända när man trycker på knapparna. Lägg till en rad i ditt program
//		så att en enkel textsträng skrivs ut när man trycker på en av knapparna. Använd ett
//		lambdauttryck med en System.out.println-sats.
//		Kör programmet och kontrollera att du får det förväntade resultatet när du trycker på
//		knappen.
//		Tips: Lambdauttrycket ersätter en metod. Därför måste det ha lika många parametrar
//		som metoden ifråga, även om parametrarna inte används i uttrycket.
		
		//aButton.setOnAction(event -> System.out.println("test"));
		
		
//		Ändra ditt program så att ordlistan sorteras alfabetiskt när man trycker på ”Alphabetic”,
//		samt på antal förekomster när man trycker på ”Frequency”.
//		Vår lista är en ObservableList, och därför kan förändringarna förmedlas automatiskt
//		till ListView. Kör programmet och verifiera att listans ordning förändras på rätt sätt när
//		du trycker på knapparna.
		
		

		alphabeticButton.setOnAction(event -> 
		{
			words.sort((a,b) -> 
				{
				return a.getKey().compareTo(b.getKey());
				});
		});
		
		frequencyButton.setOnAction(event -> words.sort((a,b) -> b.getValue() - a.getValue()));
		
//		Lägg nu till två element i vyn, ett textfält och en knapp, för att göra det möjligt att söka
//		upp ett ord i listan. Om det inskrivna ordet finns i listan scrollas listan så att ordet blir
//		synligt, annars görs ingenting.
		searchButton.setOnAction(event -> 
		{
			String input = tf.getText();
			Iterator<Entry<String, Integer>> itr = words.iterator();
			boolean wordNotFound = true;
			
			while(itr.hasNext()) {
				Entry<String, Integer> temp = itr.next();
				String word = temp.getKey();
				
				if(word.equalsIgnoreCase(input)) {
					listView.scrollTo(temp);
					wordNotFound = false;
				}
			}
			
			if(wordNotFound) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("BookReader Information");
				alert.setContentText("The word '" + input + "'" + " could not be found in BookReader list");
				alert.showAndWait();
			}
			
		});
		
		
	}
	
//	class AlphaSort implements Comparator<Entry<String, Integer>>{
//
//		@Override
//		public int compare(Entry<String, Integer> a, Entry<String, Integer> b) {
//			return a.getKey().compareTo(b.getKey());
//		}
//		
//	}
//	
//	class FreqSort implements Comparator<Entry<String, Integer>>{
//
//		@Override
//		public int compare(Entry<String, Integer> a, Entry<String, Integer> b) {
//			return b.getValue() - a.getValue();
//		}
//		
//	}
	

	public static void main(String[] args) {
		Application.launch(args);
	}

}
