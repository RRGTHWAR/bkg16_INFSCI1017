package bkg16_Music;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Artist {
	private String artistID;
	private String firstName;
	private String lastName;
	private String bandName;
	private String bio;
	private DbUtilities db;
	
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

	public void deleteArtist(String artistID) {
		this.artistID = artistID;
		
		db = new DbUtilities();
		String sql = "DELETE FROM artist WHERE artist_id = '" + this.artistID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);		
		this.artistID = null;
		
		//Also remove references in other tables
		
	}
	
//Getters and Setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		String sql = "UPDATE artist SET first_name = '" + firstName + "' WHERE artist_id = '" + this.artistID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		String sql = "UPDATE artist SET last_name = '" + lastName + "' WHERE artist_id = '" + this.artistID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
		String sql = "UPDATE artist SET band_name = '" + bandName + "' WHERE artist_id = '" + this.artistID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
		String sql = "UPDATE artist SET bio = '" + bio + "' WHERE artist_id = '" + this.artistID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);

	}

	public String getArtistID() {
		return artistID;
	}

}