package bkg16_Music;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author bengundy
 *
 */
public class Song {
	private String songID;
	private String title;
	private int length;
	private String filePath;
	private String releaseDate;
	private String recordDate;
	private Map<String, Artist> songArtists = new HashMap<String, Artist>(); //Initializing here for simplicity later

	private DbUtilities db;
	
	/**
	 * @param title
	 * @param length
	 * @param releaseDate
	 * @param recordDate
	 */
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
	
	/**
	 * @param songID
	 */
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
		
		//Populates list of artists by grabbing keys from song_artist table and adding artists to songArtists Map.
		String sql2 = "SELECT fk_artist_id FROM song_artist WHERE fk_song_id = '" + this.songID + "';";
		//System.out.println(sql2);
		try {
			ResultSet rs2 = db.getResultSet(sql2);
			//Using while instead of if, since the list will likely have more than one entry.
			while(rs2.next()) {
				this.songArtists.put(rs2.getString("fk_artist_id"), new Artist(rs2.getString("fk_artist_id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param songID
	 */
	public void deleteSong(String songID) {
		this.songID = songID;
		
		db = new DbUtilities();
		
		//Clearing out foreign key references in sql-sql4 before deleting song in sql5.
		String sql = "DELETE FROM album_song WHERE fk_song_id = '" + this.songID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);
		
		String sql2 = "DELETE FROM playlist_song WHERE fk_song_id = '" + this.songID + "';";
		//System.out.println(sql2);
		db.executeQuery(sql2);
				
		String sql3 = "DELETE FROM song_artist WHERE fk_song_id = '" + this.songID + "';";
		//System.out.println(sql3);
		db.executeQuery(sql3);
		
		String sql4 = "DELETE FROM song_genre WHERE fk_song_id = '" + this.songID + "';";
		//System.out.println(sql4);
		db.executeQuery(sql4);
		
		String sql5 = "DELETE FROM song WHERE song_id = '" + this.songID + "';";
		//System.out.println(sql5);
		db.executeQuery(sql5);
		
		//Setting pointers to null to destroy object.
		this.title = null;
		this.length = 0;
		this.filePath = null;
		this.releaseDate = null;
		this.recordDate = null;
		this.songArtists = null;
		this.songID = null;
	}
	
	/**
	 * @param artist
	 */
	public void addArtist(Artist artist) {
		this.songArtists.put(artist.getArtistID(), artist);
		db = new DbUtilities();
		//Using INSERT IGNORE here to avoid error on inserting duplicate values.
		String sql = "INSERT IGNORE INTO song_artist (fk_song_id, fk_artist_id) VALUES ('" + this.songID + "', '" + artist.getArtistID() + "');";
		//System.out.println(sql);
		db.executeQuery(sql);	
	}
	
	/**
	 * @param artistID
	 */
	public void deleteArtist(String artistID) {
		this.songArtists.remove(artistID);
		db = new DbUtilities();
		//Only deleting from song_artist, not artist, since we're only removing the reference.
		String sql = "DELETE FROM song_artist WHERE fk_song_id = '" + this.songID + "' AND fk_artist_id = '" + artistID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);			
	}
	
	/**
	 * @param artist
	 */
	public void deleteArtist(Artist artist) {
		this.songArtists.remove(artist.getArtistID());
		db = new DbUtilities();
		//Only deleting from song_artist, not artist, since we're only removing the reference.
		String sql = "DELETE FROM song_artist WHERE fk_song_id = '" + this.songID + "' AND fk_artist_id = '" + artist.getArtistID() + "';";
		//System.out.println(sql);
		db.executeQuery(sql);	
	}
	
//Getters and Setters
	/**
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
		db = new DbUtilities();
		String sql = "UPDATE song SET title = '" + this.title + "' WHERE song_id = '" + this.songID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);
	}

	/**
	 * @return
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length
	 */
	public void setLength(int length) {
		this.length = length;
		db = new DbUtilities();
		String sql = "UPDATE song SET length = " + this.length + " WHERE song_id = '" + this.songID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);
	}

	/**
	 * @return
	 */
	public String getReleaseDate() {
		return releaseDate;
	}

	/**
	 * @param releaseDate
	 */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
		String sql = "UPDATE song SET release_date = '" + this.releaseDate + "' WHERE song_id = '" + this.songID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);
	}

	/**
	 * @return
	 */
	public String getRecordDate() {
		return recordDate;
	}

	/**
	 * @param recordDate
	 */
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
		String sql = "UPDATE song SET record_date = '" + this.recordDate + "' WHERE song_id = '" + this.songID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);
	}
	
	//Skipping setter for songArtists, since they will be added via addArtist method above.
	/**
	 * @return
	 */
	public Map<String, Artist> getSongArtists() {
		return songArtists;
	}

	/**
	 * @return
	 */
	public String getSongID() {
		return songID;
	}

	/**
	 * @return
	 */
	public String getFilePath() {
		return filePath;
	}
	
	/**
	 * @param filePath
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
		String sql = "UPDATE song SET file_path = '" + this.filePath + "' WHERE song_id = '" + this.songID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);
	}
	
}
