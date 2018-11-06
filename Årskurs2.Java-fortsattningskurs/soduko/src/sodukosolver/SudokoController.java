package sodukosolver;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;


public class SudokoController extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {	

		//Model
		int[][] sudoko = new int[9][9];
		SudokoSolver sol = new SudokoSolver(sudoko);
		TextField[][] tfM = new TextFieldLimited[9][9];
		
		
		
		
		//View
		BorderPane root = new BorderPane();
		root.setPrefSize(350,385);

		Scene scene = new Scene(root);

		primaryStage.setTitle("Soduko solver");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);

		TilePane tile = new TilePane();
		root.setCenter(tile);


		HBox hbox = new HBox();

		Button solveButton = new Button("Solve");
		Button clearButton = new Button("Clear");

		root.setBottom(hbox);
		solveButton.setDefaultButton(true);


		
		tile.setPrefColumns(9);
		tile.setPrefRows(9);
		final int SIZE = 40;
		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++) {
				TextField tf = new TextFieldLimited();
				tf.setPrefSize(SIZE, SIZE);
				//Tillåt endast sifforna 1-9
				((TextFieldLimited) tf).setRestrict("[1-9]");

				if (i<3 && (k<6 && k>2) || (i>2 && i<6) && k<3 || (i>2 && i<6) && k>5 || i>5 && (k<6 && k>2)) {
					tf.setStyle("-fx-background-color: #ffffff;"
							+ "-fx-font-size: 18; -fx-label-padding: 10;"
							+ "-fx-border-color: black;");
				}
				else {
					tf.setStyle("-fx-background-color: #7FDEFF;"
							+ "-fx-font-size: 18; -fx-label-padding: 10;"
							+ "-fx-border-color: black;");
				}

				tfM[i][k] = tf;
				tile.getChildren().add(tfM[i][k]);
			}
		}

		hbox.getChildren().addAll(solveButton, clearButton);

		
		
		//Control
		clearButton.setOnAction(event -> 		{
			
			for (int i = 0; i < 9; i++) {
				for (int k = 0; k < 9; k++) {
					tfM[i][k].clear();
				}
			}});
		
		solveButton.setOnAction(event -> 		{
			
			for (int i = 0; i < 9; i++) {
				for (int k = 0; k < 9; k++) {
					if(tfM[i][k].getText() == null || tfM[i][k].getText().trim().isEmpty()) {
						sudoko[i][k] = 0;
					}
					
					else {
						int temp = Integer.valueOf(tfM[i][k].getText().toString());
						sudoko[i][k] = temp;
					}

				}
			}
			
			if(sol.solve(0,0)) {

				
				for (int i = 0; i < 9; i++) {
					for (int k = 0; k < 9; k++) {
						tfM[i][k].setText(String.valueOf(sudoko[i][k]));
					}
				}
			}
			
			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sudoko solver");
				alert.setContentText("This sudoko is unfortunately unsolvable");
				alert.showAndWait();
			}
			
		});

	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}

