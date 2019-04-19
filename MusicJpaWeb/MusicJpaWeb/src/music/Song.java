package music;

import javax.persistence.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;
import java.sql.*;

//annotation structure based on MusicJPA code example
@Entity
@Table (name="song")
public class Song {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name="song_id")
	private String songID;
	
	@Column(name="title")
	private String title;
	
	@Column(name="length")
	private int length;
	
	@Column(name="file_path")
	private String filePath;
	
	@Column(name="release_date")
	private String releaseDate;
	
	@Column(name="record_date")
	private String recordDate;
	
	public Song() {
		UUID uSongID = UUID.randomUUID();
		songID = uSongID.toString();
	}

	
	public String getSongID() {
		return songID;
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
	
	public String getFilePath() {
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
	
	public JSONObject toJSON(){
		JSONObject songJson = new JSONObject();
		try {
			songJson.put("song_id", this.songID);
			songJson.put("title", this.title);
			songJson.put("song_length", this.length);
			songJson.put("file_path", this.filePath);
			songJson.put("release_date", this.releaseDate);
			songJson.put("record_date", this.recordDate);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return songJson;
		
	}
	
}