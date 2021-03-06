package bkg16_Music;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Class Song defines the Song object and includes methods for deleting the song and
 * adding to and removing from the song's artist list.
 * @author Ben Gundy
 */
public class Song {
	private String songID;
	private String title;
	private int length;
	private String filePath;
	private String releaseDate;
	private String recordDate;
	private Map<String, Artist> songArtists;

	private DbUtilities db;
	
	/**
	 * The main constructor takes a title, length, release date and record date and builds the song,
	 * as well as an empty list of artists.
	 * @param title is the song's title.
	 * @param length is the length of the song in minutes.
	 * @param releaseDate is the date the song was released.
	 * @param recordDate is the date the song was recorded.
	 */
	public Song(String title, int length, String releaseDate, String recordDate) {
		this.songID = UUID.randomUUID().toString();
		this.title = title;
		this.length = length;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate;
		this.songArtists = new HashMap<String, Artist>();
		
		db = new DbUtilities();
		String sql = "INSERT INTO song (song_id, title, length, release_date, record_date) VALUES (?, ?, ?, ?, ?);";
		//System.out.println(sql);
		//db.executeQuery(sql);
		
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.songID);
			stmt.setString(2, this.title);
			stmt.setInt(3, this.length);
			stmt.setString(4, this.releaseDate);
			stmt.setString(5, this.recordDate);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This secondary constructor uses a songID to build an object by referencing a song in the database.
	 * @param songID is the song's identifying UUID.
	 */
	public Song(String songID) {
		this.songID = songID;
		this.songArtists = new HashMap<String, Artist>();
		
		db = new DbUtilities();
		String sql = "SELECT title, length, file_path, release_date, record_date FROM song WHERE song_id = ?;";
		//System.out.println(sql);
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.songID);
			//ResultSet rs = db.getResultSet(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				this.title = rs.getString("title");
				this.length = rs.getInt("length");
				this.filePath = rs.getString("file_path");
				this.releaseDate = rs.getString("release_date");
				this.recordDate = rs.getString("record_date");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Populates list of artists by grabbing keys from song_artist table and adding artists to songArtists Map.
		String sql2 = "SELECT fk_artist_id FROM song_artist WHERE fk_song_id = ?;";
		//System.out.println(sql2);
		try {
			PreparedStatement stmt2 = db.getConn().prepareStatement(sql2);
			stmt2.setString(1, this.songID);
			//ResultSet rs2 = db.getResultSet(sql2);
			ResultSet rs2 = stmt2.executeQuery();
			//Using while instead of if, since the list will likely have more than one entry.
			while(rs2.next()) {
				this.songArtists.put(rs2.getString("fk_artist_id"), new Artist(rs2.getString("fk_artist_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method deletes a song from the database and destroys the Java object.
	 * @param songID is the song's identifying UUID.
	 */
	public void deleteSong(String songID) {
		this.songID = songID;
		
		db = new DbUtilities();
		
		//Clearing out foreign key references in sql-sql4 before deleting song in sql5.
		String sql = "DELETE FROM album_song WHERE fk_song_id = '?;";
		//System.out.println(sql);
		//db.executeQuery(sql);
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.songID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql2 = "DELETE FROM playlist_song WHERE fk_song_id = ?;";
		//System.out.println(sql2);
		//db.executeQuery(sql2);
		try {
			PreparedStatement stmt2 = db.getConn().prepareStatement(sql2);
			stmt2.setString(1, this.songID);
			stmt2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		String sql3 = "DELETE FROM song_artist WHERE fk_song_id = ?;";
		//System.out.println(sql3);
		//db.executeQuery(sql3);
		try {
			PreparedStatement stmt3 = db.getConn().prepareStatement(sql3);
			stmt3.setString(1, this.songID);
			stmt3.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql4 = "DELETE FROM song_genre WHERE fk_song_id = ?;";
		//System.out.println(sql4);
		//db.executeQuery(sql4);
		try {
			PreparedStatement stmt4 = db.getConn().prepareStatement(sql4);
			stmt4.setString(1, this.songID);
			stmt4.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String sql5 = "DELETE FROM song WHERE song_id = ?;";
		//System.out.println(sql5);
		//db.executeQuery(sql5);
		try {
			PreparedStatement stmt5 = db.getConn().prepareStatement(sql5);
			stmt5.setString(1, this.songID);
			stmt5.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Setting fields to null or 0 to destroy object.
		this.title = null;
		this.length = 0;
		this.filePath = null;
		this.releaseDate = null;
		this.recordDate = null;
		this.songArtists = null;
		this.songID = null;
	}
	
	/**
	 * This method adds an artist to the list of artists associated with the song.
	 * @param artist is the object of the artist to be added.
	 */
	public void addArtist(Artist artist) {
		this.songArtists.put(artist.getArtistID(), artist);
		db = new DbUtilities();
		//Using INSERT IGNORE here to avoid error on inserting duplicate values.
		String sql = "INSERT IGNORE INTO song_artist (fk_song_id, fk_artist_id) VALUES (?, ?);";
		//System.out.println(sql);
		//db.executeQuery(sql);	
		
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.songID);
			stmt.setString(2, artist.getArtistID());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method removes an artist from the list of artists associated with the song.
	 * The method does not remove the artist from the database.
	 * @param artistID is the identifying UUID of the artist to be removed.
	 */
	public void deleteArtist(String artistID) {
		this.songArtists.remove(artistID);
		db = new DbUtilities();
		//Only deleting from song_artist, not artist, since we're only removing the reference.
		String sql = "DELETE FROM song_artist WHERE fk_song_id = ? AND fk_artist_id = ?;";
		//System.out.println(sql);
		//db.executeQuery(sql);

		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.songID);
			stmt.setString(2, artistID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method also removes an artist from the list of artists associated with the song.
	 * The method does not remove the artist from the database.
	 * @param artist is the object of the artist to be removed.
	 */
	public void deleteArtist(Artist artist) {
		this.songArtists.remove(artist.getArtistID());
		db = new DbUtilities();
		//Only deleting from song_artist, not artist, since we're only removing the reference.
		String sql = "DELETE FROM song_artist WHERE fk_song_id = ? AND fk_artist_id = ?;";
		//System.out.println(sql);
		//db.executeQuery(sql);
		
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.songID);
			stmt.setString(2, artist.getArtistID());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//Getters and Setters

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		db = new DbUtilities();
		String sql = "UPDATE song SET title = ? WHERE song_id = ?;";
		//System.out.println(sql);
		//db.executeQuery(sql);
		
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, this.songID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
		db = new DbUtilities();
		String sql = "UPDATE song SET length = ? WHERE song_id = ?;";
		//System.out.println(sql);
		//db.executeQuery(sql);
		
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setInt(1, length);
			stmt.setString(2, this.songID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
		String sql = "UPDATE song SET release_date = ? WHERE song_id = ?;";
		//System.out.println(sql);
		//db.executeQuery(sql);
		
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, releaseDate);
			stmt.setString(2, this.songID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
		String sql = "UPDATE song SET record_date = ? WHERE song_id = ?;";
		//System.out.println(sql);
		//db.executeQuery(sql);
		
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, recordDate);
			stmt.setString(2, this.songID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Skipping setter for songArtists, since they will be added and removed via the methods above.
	public Map<String, Artist> getSongArtists() {
		return songArtists;
	}

	public String getSongID() {
		return songID;
	}

	public String getFilePath() {
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
		String sql = "UPDATE song SET file_path = ? WHERE song_id = ?;";
		//System.out.println(sql);
		//db.executeQuery(sql);
		
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, filePath);
			stmt.setString(2, this.songID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
