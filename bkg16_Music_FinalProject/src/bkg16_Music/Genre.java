package bkg16_Music;

import java.util.Set;

import javax.persistence.*;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class Genre defines the Genre object.
 * @author Ben Gundy
 */
@Entity
@Table (name="genre")
public class Genre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "genre_id")
	private String genreID;

	@Column(name = "genre_name")
	private String genreName;

	@Column(name = "description")
	private String description;

	// Inverse join to create a list of songs for this genre.
	@ManyToMany(mappedBy = "songGenres", fetch = FetchType.EAGER)
	private Set<Song> genreSongs;

	// Getters and Setters
	public String getGenreID() {
		return genreID;
	}
	
	public void setGenreID(String genreID){
		this.genreID = genreID;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;

	}
	
	public Set<Song> getGenreSongs() {
		return genreSongs;
	}
	
	public void setGenreSongs(Set<Song> genreSongs) {
		this.genreSongs = genreSongs;
	}
	
	/**
	 * This method converts the Genre object to JSON.
	 * @return is the Genre as a JSON object.
	 */
	public JSONObject toJSON(){
		JSONObject genreJson = new JSONObject();
		try {
			genreJson.put("genre_id", this.genreID);
			genreJson.put("genre_name", this.genreName);
			genreJson.put("description", this.description);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return genreJson;
	}



}
