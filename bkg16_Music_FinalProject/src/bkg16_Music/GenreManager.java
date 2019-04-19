package bkg16_Music;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.json.JSONArray;

/**
 * Class GenreManager is responsible for creating, updating, retrieving and deleting Genre records.
 * @author bengundy
 *
 */
public class GenreManager {
	
	
	/**
	 * Method createGenre generates a new Genre record in the ORM object grid.
	 * @param genreName is the name of the genre.
	 * @param descr is a description of the genre.
	 * @param genreSongs is the songs associated with the genre.
	 */
	public void createGenre(String genreName, String descr, Set<Song> genreSongs) {
		// Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Genre g = new Genre();
		
		g.setGenreName(genreName);
		g.setDescription(descr);
		g.setGenreSongs(genreSongs);
		
		// Add Genre object to ORM object grid
		em.persist(g);
		
		// Commit transaction
		em.getTransaction().commit();
		
		// Close connection to persistence manager
		em.close();
		emFactory.close();
		
	}
	
	/**
	 * Method updateGenre updates a Genre record in the ORM object grid.
	 * @param genreID is the identifying UUID of the target Genre.
	 * @param genreName is the name of the genre.
	 * @param descr is a description of the genre.
	 * @param genreSongs is the songs associated with the genre.
	 */
	public void updateGenre(String genreID, String genreName, String descr, Set<Song> genreSongs){
		// Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Genre g = em.find(Genre.class, genreID);
		
		// Making sure fields are not empty before updating them.
		if(!genreName.equals("")){
			g.setGenreName(genreName);
		}
		
		if(!descr.equals("")){
			g.setDescription(descr);
		}
		
		if(genreSongs.size() > 0) {
			g.setGenreSongs(genreSongs);
		}
		
		// Add Genre object to ORM object grid.
		em.persist(g);
		
		// Commit transaction.
		em.getTransaction().commit();

		// Close connection to persistence manager.
		em.close();
		emFactory.close();
	}
	
	/**
	 * Method deleteGenre removes a Genre record from the ORM object grid.
	 * @param genreID is the identifying UUID of the target Genre.
	 */
	public void deleteGenre(int genreID){
		// Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Genre g = em.find(Genre.class, genreID);
		
		// Remove Genre object from ORM object grid.
		em.remove(g);
		
		// Commit transaction.
		em.getTransaction().commit();
		
		// Close connection to persistence manager.
		em.close();
		emFactory.close();
	}
	

	/**
	 * Method getGenre locates a Genre record in the ORM object grid using its genreID.
	 * @param genreID is the identifying UUID of the target Genre.
	 * @return is the Genre object.
	 */
	public Genre getGenre(String genreID){
		// Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Genre g = em.find(Genre.class, genreID);
		
		// Close connection to persistence manager.
		em.close();
		emFactory.close();
		
		return g;
	}
	
	/**
	 * Method getGenreList returns a set of genres that fit the incoming search criteria.
	 * @param searchTerm is the string target of the search.
	 * @param searchType is the portion of the genre name that must match the searchTerm.
	 * @return is a JSON array containing a list of genres that fit the incoming search criteria. 
	 */
	public JSONArray getGenreList(String searchTerm, String searchType){
		// Create persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		
		// Using prepared statement to prevent SQL injections.
		// Using "LIKE" instead of "=" in the query reduces the number of characters needed in the set parameters for each search type.
		Query q = em.createQuery("SELECT g.genreID FROM Genre g WHERE g.genreName LIKE ?1");
		if(!searchTerm.equals("")){
			if(searchType.equalsIgnoreCase("equals")){
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
		List<String> genreIDs = q.getResultList();
		JSONArray genreListJSON = new JSONArray();
		for(String genreID : genreIDs){
			Genre g = em.find(Genre.class, genreID);
			genreListJSON.put(g.toJSON());
		}
		
		// Close connection to persistence manager.
		em.close();
		emFactory.close();
		
		return genreListJSON;
	}
	
	/**
	 * Method getGenreListBySongID returns a set of genres that are linked to the incoming songID.
	 * @param songID is the ID of the song to be used in the search.
	 * @return is a JSON array containing a list of genres that are linked to the songID. 
	 */
	public JSONArray getGenreListBySongID(String songID){
		// Create persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		
		if (songID != null) {
			Song s = em.find(Song.class, songID);
			// Loop through results and add to list.
			Set<Genre> genres = s.getSongGenres();
			JSONArray genreListJSON = new JSONArray();
			for(Genre genre : genres){
				genreListJSON.put(genre.toJSON());
			}

			// Close connection to persistence manager.
			em.close();
			emFactory.close();
			
			return genreListJSON;
		}
		
		// Close connection to persistence manager.
		em.close();
		emFactory.close();
		
		return null;
	}
}
