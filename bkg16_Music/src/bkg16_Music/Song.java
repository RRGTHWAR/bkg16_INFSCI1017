package bkg16_Music;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

public class Song {
	private String songID;
	private String title;
	private int length;
	private String filePath;
	private String releaseDate;
	private String recordDate;
	private Map<String, Artist> songArtists;
	private DbUtilities db;
	
	public Song(String title, int length, String releaseDate, String recordDate) {
		this.songID = UUID.randomUUID().toString();
		this.title = title;
		this.length = length;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate;
		
		db = new DbUtilities();
		String sql = "INSERT INTO song (song_id, title, length, release_date, record_date) VALUES ('" + this.songID + "', '" + this.title + "', " + this.length + ", '" + this.releaseDate + "', '" + this.recordDate +"');";
		//System.out.println(sql);
		db.executeQuery(sql);
	}
	
	public Song(String songID) {
		this.songID = songID;
		
		db = new DbUtilities();
		String sql = "SELECT title, length, file_path, release_date, record_date FROM song WHERE song_id = '" + this.songID + "';";
		//System.out.println(sql);
		try {
			ResultSet rs = db.getResultSet(sql);
			if(rs.next()) {
				this.title = rs.getString("title");
				this.length = rs.getInt("length");
				this.filePath = rs.getString("file_path");
				this.releaseDate = rs.getString("release_date");
				this.recordDate = rs.getString("record_date");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteSong(String songID) {
		this.songID = songID;
		
		db = new DbUtilities();
		String sql = "DELETE FROM song WHERE song_id = '" + this.songID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);		
		this.songID = null;
		
		//Also remove references in other tables
	}
	
	public void addArtist(Artist artist) {
		//COME BACK TO THIS
		db = new DbUtilities();
		//String sql = "SELECT artist_id FROM artist WHERE ";
		
	}
	
	public void deleteArtist(String artistID) {
		
	}
	
	public void deleteArtist(Artist artist) {
		
	}
	
//Getters and Setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		db = new DbUtilities();
		String sql = "UPDATE song SET title = '" + title + "' WHERE song_id = '" + this.songID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
		db = new DbUtilities();
		String sql = "UPDATE song SET length = " + length + " WHERE song_id = '" + this.songID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
		String sql = "UPDATE song SET release_date = '" + releaseDate + "' WHERE song_id = '" + this.songID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
		String sql = "UPDATE song SET record_date = '" + recordDate + "' WHERE song_id = '" + this.songID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);
	}

	public Map<String, Artist> getSongArtists() {
		return songArtists;
	}

	public void setSongArtists(Map<String, Artist> songArtists) {
		this.songArtists = songArtists;
	}

	public String getSongID() {
		return songID;
	}

	public String getFilePath() {
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
		String sql = "UPDATE song SET file_path = '" + filePath + "' WHERE song_id = '" + this.songID + "';";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	
}
