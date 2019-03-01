package bkg16_Music;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Class Artist defines the Artist object and includes a method for deleting the artist.
 * @author Ben Gundy
 */
public class Artist {
	private String artistID;
	private String firstName;
	private String lastName;
	private String bandName;
	private String bio;
	
	private DbUtilities db;
	
	/**
	 * The main constructor takes the artist's first and last name and the band's name and builds
	 * the Artist object.
	 * Note that any of these fields can be entered as nulls.
	 * @param firstName is the artist's first name.
	 * @param lastName is the artist's last name.
	 * @param bandName is the name of the artist's band.
	 */
	public Artist(String firstName, String lastName, String bandName) {
		this.artistID = UUID.randomUUID().toString();
		this.firstName = firstName;
		this.lastName = lastName;
		this.bandName = bandName;
		
		db = new DbUtilities();
		String sql = "INSERT INTO artist (artist_id, first_name, last_name, band_name) VALUES (?, ?, ?, ?);";
		//System.out.println(sql);
		//db.executeQuery(sql);
		
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.artistID);
			stmt.setString(2, this.firstName);
			stmt.setString(3, this.lastName);
			stmt.setString(4, this.bandName);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This secondary constructor uses an artistID to build an object by referencing an artist in the database.
	 * @param artistID is the artist's identifying UUID.
	 */
	public Artist(String artistID) {
		this.artistID = artistID;
		
		db = new DbUtilities();
		String sql = "SELECT first_name, last_name, band_name, bio FROM artist WHERE artist_id = ?;";
		//System.out.println(sql);
		try {
			//ResultSet rs = db.getResultSet(sql);
			
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.artistID);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				this.firstName = rs.getString("first_name");
				this.lastName = rs.getString("last_name");
				this.bandName = rs.getString("band_name");
				this.bio = rs.getString("bio");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method deletes an artist from the database and destroys the Java object.
	 * @param artistID is the artist's identifying UUID.
	 */
	public void deleteArtist(String artistID) {
		this.artistID = artistID;
		
		db = new DbUtilities();

		//Clearing out foreign key reference in sql before deleting artist in sql2.
		String sql = "DELETE FROM song_artist WHERE fk_artist_id = ?;";
		//System.out.println(sql);
		//db.executeQuery(sql);
		
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.artistID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql2 = "DELETE FROM artist WHERE artist_id = ?;";
		//System.out.println(sql2);
		//db.executeQuery(sql2);
		
		try {
			PreparedStatement stmt2 = db.getConn().prepareStatement(sql2);
			stmt2.setString(1, this.artistID);
			stmt2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Setting fields to null to destroy object.
		this.firstName = null;
		this.lastName = null;
		this.bandName = null;
		this.bio = null;
		this.artistID = null;
	}
	
//Getters and Setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		String sql = "UPDATE artist SET first_name = ? WHERE artist_id = ?;";
		//System.out.println(sql);
		//db.executeQuery(sql);
		
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, firstName);
			stmt.setString(2, this.artistID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		String sql = "UPDATE artist SET last_name = ? WHERE artist_id = ?;";
		//System.out.println(sql);
		//db.executeQuery(sql);
		
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, lastName);
			stmt.setString(2, this.artistID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
		String sql = "UPDATE artist SET band_name = ? WHERE artist_id = ?;";
		//System.out.println(sql);
		//db.executeQuery(sql);
		
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, bandName);
			stmt.setString(2, this.artistID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
		String sql = "UPDATE artist SET bio = ? WHERE artist_id = ?;";
		//System.out.println(sql);
		//db.executeQuery(sql);
		
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, bio);
			stmt.setString(2, this.artistID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getArtistID() {
		return artistID;
	}

}
