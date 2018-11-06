package datamodel;

import java.sql.*;
import java.util.ArrayList;
/**
 * Database is a class that specifies the interface to the
 * movie database. Uses JDBC and the MySQL Connector/J driver.
 */
import java.util.List;
public class Database {
	/**
	 * The database connection.
	 */
	private Connection conn;

	/**
	 * Create the database interface object. Connection to the database
	 * is performed later.
	 */
	public Database() {
		conn = null;
	}

	/**
	 * Open a connection to the database, using the specified user name
	 * and password.
	 *
	 * @param userName The user name.
	 * @param password The user's password.
	 * @return true if the connection succeeded, false if the supplied
	 * user name and password were not recognized. Returns false also
	 * if the JDBC driver isn't found.
	 */
	public boolean openConnection(String userName, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection
					("jdbc:mysql://puccini.cs.lth.se/" + userName,
							userName, password);
		}
		catch (SQLException e) {
			System.err.println(e);
			e.printStackTrace();
			return false;
		}
		catch (ClassNotFoundException e) {
			System.err.println(e);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Close the connection to the database.
	 */
	public void closeConnection() {
		try {
			if (conn != null)
				conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		conn = null;

		System.err.println("Database connection closed.");
	}

	/**
	 * Check if the connection to the database has been established
	 *
	 * @return true if the connection has been established
	 */
	public boolean isConnected() {
		return conn != null;
	}

	public Show getShowData(String mTitle, String mDate) throws SQLException {
		Integer mFreeSeats;
		String mVenue;
		String sql = "select * from Shows where movie = ? and movieDate = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, mTitle);
		ps.setString(2, mDate);
		ResultSet rs = ps.executeQuery();

		rs.next();

		mFreeSeats = rs.getInt("freeSeats");
		mVenue = rs.getString("theatreName");


		ps.close();
		return new Show(mTitle, mDate, mVenue, mFreeSeats);
	}

	public boolean login(String uName) throws SQLException{
		String sql = "select * from Users";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			if(uName.equals(rs.getString("userName"))){
				ps.close();
				return true;
			}
		}
		ps.close();
		return false;
	}

	public List<String> movies() throws SQLException{
		List<String> allmovies = new ArrayList<String>();
		String sql = "select distinct movie from Shows";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			allmovies.add(rs.getString("movie"));
		}
		ps.close();
		return allmovies;
	}

	public List<String> movieDates(String m) throws SQLException{
		List<String> alldates = new ArrayList<String>();
		String sql = "select movieDate from Shows where movie = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, m);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			alldates.add(rs.getString("movieDate"));
		}
		ps.close();
		return alldates;

	}

	public boolean ticketBooked(String movie, String date, String userName) throws SQLException {
		int freeSeats;

		conn.setAutoCommit(false);

		//uppdatera freeseats
		String sql2 = "update Shows set freeSeats = (freeSeats - 1) where movie = ? and movieDate = ?";
		PreparedStatement ps2 = conn.prepareStatement(sql2);
		ps2.setString(1, movie);
		ps2.setString(2, date);
		ps2.execute();

		//lägg till bookning
		String sql3 = "insert into Bookings(userName, movie, movieDate)"
				+ "values(?, ?, ?)";
		PreparedStatement ps3 = conn.prepareStatement(sql3);
		ps3.setString(1, userName);
		ps3.setString(2, movie);
		ps3.setString(3, date);
		ps3.execute();


		//kolla hur många freeseats
		String sql = "select freeSeats from Shows where movie = ? and movieDate = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, movie);
		ps.setString(2, date);
		ResultSet rs = ps.executeQuery();

		rs.next();
		freeSeats = rs.getInt("freeSeats");

		if(freeSeats >= 0){
			conn.commit();
			conn.setAutoCommit(true);
			ps.close();
			ps2.close();
			ps3.close();
			return true;
		}
		else{
			conn.rollback();
			conn.setAutoCommit(true);
			ps.close();
			ps2.close();
			ps3.close();
			return false;
		}

	}

	public String getBookingNbr(String movie, String date, String userName) throws SQLException {
		String sql = "select nbr from Bookings where movie = ? and movieDate = ? and userName = ? order by nbr desc";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, movie);
		ps.setString(2, date);
		ps.setString(3, userName);
		ResultSet rs = ps.executeQuery();
		rs.next();
		String nbr = rs.getString("nbr");
		return nbr;
	}
}
