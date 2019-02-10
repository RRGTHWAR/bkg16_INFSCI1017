package bkg16_Music;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

public class Album {
	private String albumID;
	private String title;
	private String releaseDate;
	private String coverImagePath;
	private String recordingCompany;
	private int numberOfTracks;
	private String pmrcRating;
	private int length;
	private Map<String, Song> albumSongs;
	private DbUtilities db;
	
	public Album(String title, String releaseDate, String recordingCompany, int numberOfTracks, String pmrcRating, int length) {
		this.albumID = UUID.randomUUID().toString();
		this.title = title;
		this.releaseDate = releaseDate;
		this.recordingCompany = recordingCompany;
		this.numberOfTracks = numberOfTracks;
		this.pmrcRating = pmrcRating;
		this.length = length;
		
		db = new DbUtilities();
		String sql = "INSERT INTO album (album_id, title, release_date, recording_company_name, number_of_tracks, PMRC_rating, length) VALUES ('" + this.albumID + "', '" + this.title + "', '" + this.releaseDate + "', '" + this.recordingCompany + "', " + this.numberOfTracks + ", '" + this.pmrcRating + "', " + this.length + ");";
		//System.out.println(sql);
		db.executeQuery(sql);
	}
	
	public Album(String albumID) {
		this.albumID = albumID;
		
		db = new DbUtilities();
		String sql = "SELECT title, release_date, cover_image_path, recording_company_name, number_of_tracks, PMRC_rating, length FROM album WHERE album_id = '" + this.albumID + "';";
		//System.out.println(sql);
		try {
			ResultSet rs = db.getResultSet(sql);
			if(rs.next()) {
				this.title = rs.getString("title");
				this.releaseDate = rs.getString("release_date");
				this.coverImagePath = rs.getString("cover_image_path");
				this.recordingCompany = rs.getString("recording_company_name");
				this.numberOfTracks = rs.getInt("number_of_tracks");
				this.pmrcRating = rs.getString("PMRC_rating");
				this.length = rs.getInt("length");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteAlbum(String albumID) {
		this.albumID = albumID;
		
		db = new DbUtilities();
		String sql = "DELETE FROM album WHERE album_id = '" + this.albumID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);		
		this.albumID = null;
		
		//Also remove references in other tables
	}
	
	public void addSong(Song song) {
		
	}
	
	public void deleteSong(String songID) {
		
	}
	
	public void deleteSong(Song song) {
		
	}

//Getters and Setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		db = new DbUtilities();
		String sql = "UPDATE album SET title = '" + title + "' WHERE album_id = '" + this.albumID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);
	}
//HERE
	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getCoverImagePath() {
		return coverImagePath;
	}

	public void setCoverImagePath(String coverImagePath) {
		this.coverImagePath = coverImagePath;
	}

	public String getRecordingCompany() {
		return recordingCompany;
	}

	public void setRecordingCompany(String recordingCompany) {
		this.recordingCompany = recordingCompany;
	}

	public int getNumberOfTracks() {
		return numberOfTracks;
	}

	public void setNumberOfTracks(int numberOfTracks) {
		this.numberOfTracks = numberOfTracks;
	}

	public String getPmrcRating() {
		return pmrcRating;
	}

	public void setPmrcRating(String pmrcRating) {
		this.pmrcRating = pmrcRating;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Map<String, Song> getAlbumSongs() {
		return albumSongs;
	}

	public void setAlbumSongs(Map<String, Song> albumSongs) {
		this.albumSongs = albumSongs;
	}

	public String getAlbumID() {
		return albumID;
	}

}