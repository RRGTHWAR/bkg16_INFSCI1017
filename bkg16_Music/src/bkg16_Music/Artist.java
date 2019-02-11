package bkg16_Music;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @author bengundy
 *
 */
public class Artist {
	private String artistID;
	private String firstName;
	private String lastName;
	private String bandName;
	private String bio;
	
	private DbUtilities db;
	
	/**
	 * @param firstName
	 * @param lastName
	 * @param bandName
	 */
	public Artist(String firstName, String lastName, String bandName) {
		this.artistID = UUID.randomUUID().toString();
		this.firstName = firstName;
		this.lastName = lastName;
		this.bandName = bandName;
		
		db = new DbUtilities();
		String sql = "INSERT INTO artist (artist_id, first_name, last_name, band_name) VALUES ('" + this.artistID + "', '" + this.firstName + "', '"+ this.lastName + "', '" + this.bandName + "');";
		//System.out.println(sql);
		db.executeQuery(sql);
	}
	
	/**
	 * @param artistID
	 */
	public Artist(String artistID) {
		this.artistID = artistID;
		
		db = new DbUtilities();
		String sql = "SELECT first_name, last_name, band_name, bio FROM artist WHERE artist_id = '" + this.artistID + "';";
		//System.out.println(sql);
		try {
			ResultSet rs = db.getResultSet(sql);
			if(rs.next()) {
				this.firstName = rs.getString("first_name");
				this.lastName = rs.getString("last_name");
				this.bandName = rs.getString("band_name");
				this.bio = rs.getString("bio");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param artistID
	 */
	public void deleteArtist(String artistID) {
		this.artistID = artistID;
		
		db = new DbUtilities();

		//Clearing out foreign key reference in sql before deleting artist in sql2.
		String sql = "DELETE FROM song_artist WHERE fk_artist_id = '" + this.artistID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);		
		
		String sql2 = "DELETE FROM artist WHERE artist_id = '" + this.artistID + "';";
		//System.out.println(sql2);
		db.executeQuery(sql2);		
		
		//Setting pointers to null to destroy object.
		this.firstName = null;
		this.lastName = null;
		this.bandName = null;
		this.bio = null;
		this.artistID = null;
	}
	
//Getters and Setters
	/**
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
		String sql = "UPDATE artist SET first_name = '" + this.firstName + "' WHERE artist_id = '" + this.artistID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);
	}

	/**
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
		String sql = "UPDATE artist SET last_name = '" + this.lastName + "' WHERE artist_id = '" + this.artistID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);
	}

	/**
	 * @return
	 */
	public String getBandName() {
		return bandName;
	}

	/**
	 * @param bandName
	 */
	public void setBandName(String bandName) {
		this.bandName = bandName;
		String sql = "UPDATE artist SET band_name = '" + this.bandName + "' WHERE artist_id = '" + this.artistID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);
	}

	/**
	 * @return
	 */
	public String getBio() {
		return bio;
	}

	/**
	 * @param bio
	 */
	public void setBio(String bio) {
		this.bio = bio;
		String sql = "UPDATE artist SET bio = '" + this.bio + "' WHERE artist_id = '" + this.artistID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);

	}

	/**
	 * @return
	 */
	public String getArtistID() {
		return artistID;
	}

}
