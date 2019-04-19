package bkg16_Music;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;

import org.json.JSONArray;

/**
 * Class SongManager is responsible for creating, updating and deleting Song records. 
 * @author bengundy
 *
 */
public class SongManager {

	/**
	 * Method createSong generates a new Song record in the ORM object grid.
	 * @param title is the song's title.
	 * @param length is the length of the song in minutes.
	 * @param filePath is the location of the corresponding song file.
	 * @param releaseDate is the date the song was released.
	 * @param recordDate is the date the song was recorded.
	 * @param songAlbums is the albums associated with the song.
	 * @param songArtists is the artists associated with the song.
	 * @param songGenres is the genres associated with the song.
	 */
	public void createSong(String title, int length, String filePath, String releaseDate, String recordDate, Set<Album> songAlbums, Set<Artist> songArtists, Set<Genre> songGenres) {
		// Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Song s = new Song();
		
		s.setSongID(UUID.randomUUID().toString());
		s.setTitle(title);
		s.setLength(length);
		s.setFilePath(filePath);
		s.setReleaseDate(releaseDate);
		s.setRecordDate(recordDate);
		s.setSongAlbums(songAlbums);
		s.setSongArtists(songArtists);
		s.setSongGenres(songGenres);
		
		// Add Song object to ORM object grid.
		em.persist(s);
		
		// Commit transaction.
		em.getTransaction().commit();
		
		// Close connection to persistence manager.
		em.close();
		emFactory.close();
		
	}

	/**
	 * Method updateSong updates a Song record in the ORM object grid.
	 * @param songID is the identifying UUID of the target Song.
	 * @param title is the song's title.
	 * @param length is the length of the song in minutes.
	 * @param filePath is the location of the corresponding song file.
	 * @param releaseDate is the date the song was released.
	 * @param recordDate is the date the song was recorded.
	 * @param songAlbums is the albums associated with the song.
	 * @param songArtists is the artists associated with the song.
	 * @param songGenres is the genres associated with the song.
	 */
	public void updateSong(String songID, String title, int length, String filePath, String releaseDate, String recordDate, Set<Album> songAlbums, Set<Artist> songArtists, Set<Genre> songGenres) {
		// Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Song s = em.find(Song.class, songID);
		
		// Making sure fields are not empty before updating them.
		if(!title.equals("")) {
			s.setTitle(title);
		}
		
		if(length > 0) {
			s.setLength(length);
		}

		if(!filePath.equals("")) {
			s.setFilePath(filePath);
		}
		
		if(!releaseDate.equals("")) {
			s.setReleaseDate(releaseDate);
		}
		
		if(!recordDate.equals("")) {
			s.setRecordDate(recordDate);
		}
		
		if(songAlbums.size() > 0) {
			s.setSongAlbums(songAlbums);
		}
		
		if(songArtists.size() > 0) {
			s.setSongArtists(songArtists);
		}
		
		if(songGenres.size() > 0) {
			s.setSongGenres(songGenres);
		}
		
		// Add Song object to ORM grid.
		em.persist(s);
		
		// Commit transaction.
		em.getTransaction().commit();
		
		// Close connection to persistence manager.
		em.close();
		emFactory.close();
	}
	
	/**
	 * Method deleteSong removes a Song record from the ORM object grid. 
	 * @param songID is the identifying UUID of the target Song.
	 */
	public void deleteSong(String songID) {
		// Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Song s = em.find(Song.class, songID);
		
		// Remove Song object from ORM grid.
		em.remove(s);
		
		// Commit transaction.
		em.getTransaction().commit();
		
		// Close connection to persistence manager.
		em.close();
		emFactory.close();
	}
	
	/**
	 * Method getSong locates a Song record in the ORM object grid using its songID.
	 * @param songID is the identifying UUID of the target Song.
	 * @return is the Song object.
	 */
	public Song getSong(String songID) {
		// Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Song s = em.find(Song.class, songID);
		
		// Close connection to persistence manager.
		em.close();
		emFactory.close();
		
		return s;
		
	}
	
	/**
	 * Method getSongList returns a set of songs that fit the incoming search criteria.
	 * @param searchTerm is the string target of the search.
	 * @param searchType is the portion of the song title that must match the searchTerm.
	 * @return is a JSON array containing a list of songs that fit the incoming search criteria. 
	 */
	public JSONArray getSongList(String searchTerm, String searchType){
		// Create persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		
		// Using prepared statement to prevent SQL injections.
		// Using "LIKE" instead of "=" in the query reduces the number of characters needed in the set parameters for each search type.
		Query q = em.createQuery("SELECT s.songID FROM Song s WHERE s.title LIKE ?1");
		if(!searchTerm.equals("")) {
			if(searchType.equalsIgnoreCase("equals")) {
				q.setParameter(1, searchTerm);
			} else if(searchType.equalsIgnoreCase("begins")) {
				q.setParameter(1, searchTerm + "%");				
			} else if(searchType.equalsIgnoreCase("ends")) {
				q.setParameter(1, "%" + searchTerm);					
			} else if (searchType.equalsIgnoreCase("contains")){
				q.setParameter(1, "%" + searchTerm + "%");									
			} else {
				q.setParameter(1, "%");
			}
		} else {
			q.setParameter(1, "%");
		}

		// Loop through results and add to list.
		List<String> songIDs = q.getResultList();
		JSONArray songListJSON = new JSONArray();
		for(String songID : songIDs){
			Song s = em.find(Song.class, songID);
			songListJSON.put(s.toJSON());
		}
		
		// Close connection to persistence manager.
		em.close();
		emFactory.close();
		
		return songListJSON;
	}

	/**
	 * Method getSongListByAlbumID returns a set of songs that are linked to the incoming albumID.
	 * @param albumID is the ID of the album to be used in the search.
	 * @return is a JSON array containing a list of songs that are linked to the albumID. 
	 */
	public JSONArray getSongListByAlbumID(String albumID){
		// Create persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		
		Album a = em.find(Album.class, albumID);
		// Loop through results and add to list.
		Set<Song> songs = a.getAlbumSongs();
		JSONArray songListJSON = new JSONArray();
		for(Song song : songs){
			songListJSON.put(song.toJSON());
		}
		
		// Close connection to persistence manager.
		em.close();
		emFactory.close();
		
		return songListJSON;
	}
	
	/**
	 * Method getSongListByArtistID returns a set of songs that are linked to the incoming artistID.
	 * @param artistID is the ID of the artist to be used in the search.
	 * @return is a JSON array containing a list of songs that are linked to the artistID. 
	 */
	public JSONArray getSongListByArtistID(String artistID){
		// Create persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		
		if (artistID != null) {
			Artist a = em.find(Artist.class, artistID);
			// Loop through results and add to list.
			Set<Song> songs = a.getArtistSongs();
			JSONArray songListJSON = new JSONArray();
			for(Song song : songs){
				songListJSON.put(song.toJSON());
			}
			
			// Close connection to persistence manager.
			em.close();
			emFactory.close();
			
			return songListJSON;
		}
		
		// Close connection to persistence manager.
		em.close();
		emFactory.close();
		
		return null;
	}
	
	/**
	 * Method getSongListByGenreID returns a set of songs that are linked to the incoming genreID.
	 * @param genreID is the ID of the genre to be used in the search.
	 * @return is a JSON array containing a list of songs that are linked to the genreID. 
	 */
	public JSONArray getSongListByGenreID(String genreID){
		// Create persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		
		if (genreID != null) {
			Genre g = em.find(Genre.class, genreID);
			// Loop through results and add to list.
			Set<Song> songs = g.getGenreSongs();
			JSONArray songListJSON = new JSONArray();
			for(Song song : songs){
				songListJSON.put(song.toJSON());
			}
			
			// Close connection to persistence manager.
			em.close();
			emFactory.close();
			
			return songListJSON;
		}
		
		// Close connection to persistence manager.
		em.close();
		emFactory.close();
		
		return null;
	}
	
}