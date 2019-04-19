package bkg16_Music;

import java.util.Set;

import javax.persistence.*;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class Song defines the Song object.
 * @author Ben Gundy
 */
@Entity
@Table (name="song")
public class Song {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "song_id")
	private String songID;
	
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
	
	// Inverse join to create a list of albums for this song.
	@ManyToMany(mappedBy = "albumSongs", fetch = FetchType.EAGER)
	private Set<Album> songAlbums;
	
	// Inverse join to create a list of artists for this song.
	@ManyToMany(mappedBy = "artistSongs", fetch = FetchType.EAGER)
	private Set<Artist> songArtists;
	
	// Joining Song to Genre via the song_genre table to build a Set of genres for this song.
	// Source: https://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development/Mapping/Relationship_Mappings/Collection_Mappings/ManyToMany	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "song_genre",
			joinColumns = @JoinColumn(name = "fk_song_id", referencedColumnName="song_id"),
			inverseJoinColumns = @JoinColumn(name = "fk_genre_id", referencedColumnName="genre_id")
			)
	private Set<Genre> songGenres;

	// Getters and Setters
	
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
	
	public Set<Album> getSongAlbums() {
		return songAlbums;
	}
	
	public void setSongAlbums(Set<Album> songAlbums) {
		this.songAlbums = songAlbums;
	}
	
	public Set<Artist> getSongArtists() {
		return songArtists;
	}
	
	public void setSongArtists(Set<Artist> songArtists) {
		this.songArtists = songArtists;
	}
	
	public Set<Genre> getSongGenres() {
		return songGenres;
	}
	
	public void setSongGenres(Set<Genre> songGenres) {
		this.songGenres = songGenres;
	}
	
	/**
	 * This method converts the Song object to JSON.
	 * @return is the Song as a JSON object.
	 */
	public JSONObject toJSON(){
		JSONObject songJson = new JSONObject();
		try {
			songJson.put("song_id", this.songID);
			songJson.put("title", this.title);
			songJson.put("length", this.length);
			songJson.put("file_path", this.filePath);
			songJson.put("release_date", this.releaseDate);
			songJson.put("record_date", this.recordDate);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return songJson;
	}

}
