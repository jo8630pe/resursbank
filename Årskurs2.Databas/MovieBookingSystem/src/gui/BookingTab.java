package gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.List;

import datamodel.CurrentUser;
import datamodel.Database;
import datamodel.Show;

import java.sql.SQLException;
import java.util.ArrayList;


public class BookingTab {
	// top context message
	@FXML private Text topContext;
	// bottom message
	@FXML private Text bookMsg;

	// table references
	@FXML private ListView<String> moviesList;
	@FXML private ListView<String> datesList;

	// show info references
	@FXML private Label showTitle;
	@FXML private Label showDate;
	@FXML private Label showVenue;
	@FXML private Label showFreeSeats;

	// booking button
	@FXML private Button bookTicket;

	private Database db;
	private Show crtShow = new Show();

	public void initialize() throws SQLException {
		System.out.println("Initializing BookingTab");

		fillNamesList();
		fillDatesList(null);
		fillShow(null,null);

		// set up listeners for the movie list selection
		moviesList.getSelectionModel().selectedItemProperty().addListener(
				(obs, oldV, newV) -> {
					// need to update the date list according to the selected movie
					// update also the details on the right panel
					String movie = newV;
					try {
						fillDatesList(newV);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						fillShow(movie,null);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});

		// set up listeners for the date list selection
		datesList.getSelectionModel().selectedItemProperty().addListener(
				(obs, oldV, newV) -> {
					// need to update the details according to the selected date
					String movie = moviesList.getSelectionModel().getSelectedItem();
					String date = newV;
				    try {
						fillShow(movie, date);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});

		// set up booking button listener
		// one can either use this method (setup a handler in initialize)
		// or directly give a handler name in the fxml, as in the LoginTab class
		bookTicket.setOnAction(
				(event) -> {

					String movie = moviesList.getSelectionModel().getSelectedItem();
					String date = datesList.getSelectionModel().getSelectedItem();
					CurrentUser c = CurrentUser.instance();
					String userName = c.getCurrentUserId();

					/* --- TODO: should attempt to book a ticket via the database --- */
					/* --- do not forget to report booking number! --- */
					/* --- update the displayed details (free seats) --- */
					try {
						if(db.ticketBooked(movie,date,userName)){
							try {
								fillShow(movie,date);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							report("Done, " + "booking id:" + db.getBookingNbr(movie,date,userName));

						}
						else{
							report("Failed to book a ticket for "+movie+" on "+date);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				});

		report("Ready.");
	}

	// helpers
	// updates user display
	private void fillStatus(String usr) {
		if(usr.isEmpty()) topContext.setText("You must log in as a known user!");
		else topContext.setText("Currently logged in as " + usr);
	}

	private void report(String msg) {
		bookMsg.setText(msg);
	}

	public void setDatabase(Database db) {
		this.db = db;
	}

	private void fillNamesList() throws SQLException {
		List<String> allmovies = new ArrayList<String>();
		if(db != null){
			allmovies = db.movies();
		}

		moviesList.setItems(FXCollections.observableList(allmovies));
		// remove any selection
		moviesList.getSelectionModel().clearSelection();
	}

	private void fillDatesList(String m) throws SQLException {
		List<String> alldates = new ArrayList<String>();
		if(m!=null && db != null) {
			alldates = db.movieDates(m);
		}
		datesList.setItems(FXCollections.observableList(alldates));
		// remove any selection
		datesList.getSelectionModel().clearSelection();
	}

	private void fillShow(String movie, String date) throws SQLException {
		if(movie==null) // no movie selected
			crtShow = new Show();
		else if(date==null) // no date selected yet
			crtShow = new Show(movie);
		else // query the database via db
			crtShow = db.getShowData(movie, date);

		showTitle.setText(crtShow.getTitle());
		showDate.setText(crtShow.getDate());
		showVenue.setText(crtShow.getVenue());
		if(crtShow.getSeats() >= 0) showFreeSeats.setText(crtShow.getSeats().toString());
		else showFreeSeats.setText("-");
	}

	// called in case the user logged in changed
	public void userChanged() throws SQLException {
		fillStatus(CurrentUser.instance().getCurrentUserId());
		fillNamesList();
		fillDatesList(null);
		fillShow(null,null);
	}

}
