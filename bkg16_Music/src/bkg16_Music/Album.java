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
public class Album {
	private String albumID;
	private String title;
	private String releaseDate;
	private String coverImagePath;
	private String recordingCompany;
	private int numberOfTracks;
	private String pmrcRating;
	private int length;
	private Map<String, Song> albumSongs = new HashMap<String, Song>();
	
	private DbUtilities db;
	
	/**
	 * @param title
	 * @param releaseDate
	 * @param recordingCompany
	 * @param numberOfTracks
	 * @param pmrcRating
	 * @param length
	 */
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
	
	/**
	 * @param albumID
	 */
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
		
		//Populates list of songs by grabbing keys from album_song table and adding songs to albumSongs Map. 
		String sql2 = "SELECT fk_song_id FROM album_song WHERE fk_album_id = '" + this.albumID + "';";
		//System.out.println(sql2);
		try {
			ResultSet rs2 = db.getResultSet(sql2);
			//Using while instead of if, since the list will likely have more than one entry.
			while(rs2.next()) {
				this.albumSongs.put(rs2.getString("fk_song_id"), new Song(rs2.getString("fk_song_id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param albumID
	 */
	public void deleteAlbum(String albumID) {
		this.albumID = albumID;
		
		db = new DbUtilities();
		
		//Clearing out foreign key reference in sql before deleting album in sql2.
		String sql = "DELETE FROM album_song WHERE fk_album_id = '" + this.albumID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);
		
		String sql2 = "DELETE FROM album WHERE album_id = '" + this.albumID + "';";
		//System.out.println(sql2);
		db.executeQuery(sql2);
		
		//Setting pointers to null to destroy object.
		this.title = null;
		this.releaseDate = null;
		this.coverImagePath = null;
		this.recordingCompany = null;
		this.numberOfTracks = 0;
		this.pmrcRating = null;
		this.length = 0;
		this.albumSongs = null;
		this.albumID = null;
	}
	
	/**
	 * @param song
	 */
	public void addSong(Song song) {
		this.albumSongs.put(song.getSongID(), song);
		db = new DbUtilities();
		//Using INSERT IGNORE here to avoid error on inserting duplicate values.
		String sql = "INSERT IGNORE INTO album_song (fk_album_id, fk_song_id) VALUES ('" + this.albumID + "', '" + song.getSongID() + "');";
		//System.out.println(sql);
		db.executeQuery(sql);	
	}
	
	/**
	 * @param songID
	 */
	public void deleteSong(String songID) {
		this.albumSongs.remove(songID);
		db = new DbUtilities();
		//Only deleting from album_song, not song, since we're only removing the reference.
		String sql = "DELETE FROM album_song WHERE fk_album_id = '" + this.albumID + "' AND fk_song_id = '" + songID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);					
	}
	
	/**
	 * @param song
	 */
	public void deleteSong(Song song) {
		this.albumSongs.remove(song.getSongID());
		db = new DbUtilities();
		//Only deleting from album_song, not song, since we're only removing the reference.
		String sql = "DELETE FROM album_song WHERE fk_album_id = '" + this.albumID + "' AND fk_song_id = '" + song.getSongID() + "';";
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
		String sql = "UPDATE album SET title = '" + this.title + "' WHERE album_id = '" + this.albumID + "';";
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
		String sql = "UPDATE album SET release_date = '" + this.releaseDate + "' WHERE album_id = '" + this.albumID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);
	}

	/**
	 * @return
	 */
	public String getCoverImagePath() {
		return coverImagePath;
	}

	/**
	 * @param coverImagePath
	 */
	public void setCoverImagePath(String coverImagePath) {
		this.coverImagePath = coverImagePath;
		String sql = "UPDATE album SET cover_image_path = '" + this.coverImagePath + "' WHERE album_id = '" + this.albumID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);
	}

	/**
	 * @return
	 */
	public String getRecordingCompany() {
		return recordingCompany;
	}

	/**
	 * @param recordingCompany
	 */
	public void setRecordingCompany(String recordingCompany) {
		this.recordingCompany = recordingCompany;
		String sql = "UPDATE album SET recording_company_name = '" + this.recordingCompany + "' WHERE album_id = '" + this.albumID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);
	}

	/**
	 * @return
	 */
	public int getNumberOfTracks() {
		return numberOfTracks;
	}

	/**
	 * @param numberOfTracks
	 */
	public void setNumberOfTracks(int numberOfTracks) {
		this.numberOfTracks = numberOfTracks;
		String sql = "UPDATE album SET number_of_tracks = " + this.numberOfTracks + " WHERE album_id = '" + this.albumID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);
	}

	/**
	 * @return
	 */
	public String getPmrcRating() {
		return pmrcRating;
	}

	/**
	 * @param pmrcRating
	 */
	public void setPmrcRating(String pmrcRating) {
		this.pmrcRating = pmrcRating;
		String sql = "UPDATE album SET PMRC_rating = '" + this.pmrcRating + "' WHERE album_id = '" + this.albumID + "';";
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
		String sql = "UPDATE album SET length = " + this.length + " WHERE album_id = '" + this.albumID + "';";
		//System.out.println(sql);
		db.executeQuery(sql);
	}
	
	//Skipping setter for albumSongs, since they will be added via addSong method above.
	/**
	 * @return
	 */
	public Map<String, Song> getAlbumSongs() {
		return albumSongs;
	}

	/**
	 * @return
	 */
	public String getAlbumID() {
		return albumID;
	}

}
