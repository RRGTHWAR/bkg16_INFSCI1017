package bkg16_Music;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;

import org.json.JSONArray;

/**
 * Class ArtistManager is responsible for creating, updating, retrieving and deleting Artist records. 
 * @author bengundy
 *
 */
public class ArtistManager {
	
	/**
	 * Method createArtist generates a new Artist record in the ORM object grid.
	 * @param firstName is the artist's first name.
	 * @param lastName is the artist's last name.
	 * @param bandName is the name of the artist's band.
	 * @param bio is biographical information for the artist and/or band.
	 * @param artistSongs is the songs associated with the artist.
	 */
	public void createArtist(String firstName, String lastName, String bandName, String bio, Set<Song> artistSongs) {
		// Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Artist a = new Artist();
		
		a.setArtistID(UUID.randomUUID().toString());
		a.setFirstName(firstName);
		a.setLastName(lastName);
		a.setBandName(bandName);
		a.setBio(bio);
		a.setArtistSongs(artistSongs);
		
		// Add Artist object to ORM object grid.
		em.persist(a);
		
		// Commit transaction.
		em.getTransaction().commit();
		
		// Close connection to persistence manager.
		em.close();
		emFactory.close();
		
	}

	/**
	 * Method updateArtist updates an Artist record in the ORM object grid.
	 * @param artistID is the identifying UUID of the target Artist.
	 * @param firstName is the artist's first name.
	 * @param lastName is the artist's last name.
	 * @param bandName is the name of the artist's band.
	 * @param bio is biographical information for the artist and/or band.
	 * @param artistSongs is the songs associated with the artist.
	 */
	public void updateArtist(String artistID, String firstName, String lastName, String bandName, String bio, Set<Song> artistSongs) {
		// Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Artist a = em.find(Artist.class, artistID);
		
		// Making sure fields are not empty before updating them.
		if(!firstName.equals("")) {
			a.setFirstName(firstName);
		}

		if(!lastName.equals("")) {
			a.setLastName(lastName);
		}
		
		if(!bandName.equals("")) {
			a.setBandName(bandName);
		}
		
		if(!bio.equals("")) {
			a.setBio(bio);
		}
		
		if(artistSongs.size() > 0) {
			a.setArtistSongs(artistSongs);
		}

		// Add Artist object to ORM object grid.
		em.persist(a);
		
		// Commit transaction.
		em.getTransaction().commit();
		
		// Close connection to persistence manager.
		em.close();
		emFactory.close();
	}
	
	/**
	 * Method deleteArtist removes an Artist record from the ORM object grid.
	 * @param artistID is the identifying UUID of the target Artist.
	 */
	public void deleteArtist(String artistID) {
		// Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Artist a = em.find(Artist.class, artistID);
		
		// Remove Artist object from ORM object grid.
		em.remove(a);
		
		// Commit transaction.
		em.getTransaction().commit();
		
		// Close connection to persistence manager.
		em.close();
		emFactory.close();
	}
	
	/**
	 * Method getArtist locates an Artist record in the ORM object grid using its artistID.
	 * @param artistID is the identifying UUID of the target Artist.
	 * @return is the Artist object.
	 */
	public Artist getArtist(String artistID) {
		// Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Artist a = em.find(Artist.class, artistID);

		// Close connection to persistence manager.
		em.close();
		emFactory.close();
		
		return a;	
	}
	
	/**
	 * Method getArtistList returns a set of artists that fit the incoming search criteria.
	 * @param searchTerm is the string target of the search.
	 * @param searchType is the portion of the artist name that must match the searchTerm.
	 * @return is a JSON array containing a list of artists that fit the incoming search criteria. 
	 */
	public JSONArray getArtistList(String searchTerm, String searchType){
		// Create persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		
		// Using prepared statement to prevent SQL injections.
		// Using "LIKE" instead of "=" in the query reduces the number of characters needed in the set parameters for each search type.
		// Because any of the three identifying fields could be blank, use an OR with first and last name joined to ensure appropriate results.
		Query q = em.createQuery("SELECT a.artistID FROM Artist a WHERE a.firstName + a.lastName LIKE ?1 OR a.bandName LIKE ?1");
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
		List<String> artistIDs = q.getResultList();
		JSONArray artistListJSON = new JSONArray();
		for(String artistID : artistIDs){
			Artist a = em.find(Artist.class, artistID);
			artistListJSON.put(a.toJSON());
		}

		// Close connection to persistence manager.
		em.close();
		emFactory.close();
		
		return artistListJSON;
	}
	
	/**
	 * Method getArtistListBySongID returns a set of artists that are linked to the incoming songID.
	 * @param songID is the ID of the song to be used in the search.
	 * @return is a JSON array containing a list of artists that are linked to the songID. 
	 */
	public JSONArray getArtistListBySongID(String songID){
		// Create persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		
		if (songID != null) {
			Song s = em.find(Song.class, songID);
			// Loop through results and add to list.
			Set<Artist> artists = s.getSongArtists();
			JSONArray artistListJSON = new JSONArray();
			for(Artist artist : artists){
				artistListJSON.put(artist.toJSON());
			}
			
			// Close connection to persistence manager.
			em.close();
			emFactory.close();
			
			return artistListJSON;
		}
		
		// Close connection to persistence manager.
		em.close();
		emFactory.close();
		
		return null;
	}

	/**
	 * Method getArtistListByAlbumID returns a set of artists that are linked to the incoming albumID.
	 * @param albumID is the ID of the album to be used in the search.
	 * @return is a JSON array containing a list of artists that are linked to the albumID. 
	 */
	public JSONArray getArtistListByAlbumID(String albumID){
		// Create persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		
		if(!albumID.equals("")) {
			AlbumManager am = new AlbumManager();
			Album album = am.getAlbum(albumID);
			Set<Song> songs = album.getAlbumSongs();
			JSONArray artistListJSON = new JSONArray();
			if(songs != null) {
				// Since there is no table directly linking albums to artists, pass from album to song to artist.
				// Loop through each artist associated with each song, adding the artists to the list.
				for (Song song : songs) {
					for (Artist artist : song.getSongArtists()) {
						artistListJSON.put(artist.toJSON());
					}
				}
			}

			// Close connection to persistence manager.
			em.close();
			emFactory.close();
			
			return artistListJSON;
			
		} else {
			
			// Close connection to persistence manager.
			em.close();
			emFactory.close();
			
			return null;
		}
	}


}
