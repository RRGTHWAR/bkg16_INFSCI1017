package bkg16_Music;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.*;

/**
 * Class Song defines the Song object and includes methods for deleting the song and
 * adding to and removing from the song's artist list.
 * @author Ben Gundy
 */
@Entity
@Table (name="song")
public class Song {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "song_id")
	private String songID; //SOMEHOW LINK THIS TO THE @ID ABOVE
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "length")
	private int length;
	
	@Column(name = "file_path")
	private String filePath;
	
	@Column(name = "release_date")
	private String releaseDate;
	
	@Column(name = "record_date")
	private String recordDate;
	
	@Transient
	private Map<String, Artist> SongArtists;	
	
//Getters and Setters
	
	public String getSongID() {
		return songID;
	}

	public void setSongID(String songID) {
		this.songID = songID;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getFilePath() {
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
