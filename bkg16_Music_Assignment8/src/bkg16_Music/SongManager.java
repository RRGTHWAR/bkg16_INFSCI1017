package bkg16_Music;

import java.util.UUID;
import javax.persistence.*;

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
	 */
	public void createSong(String title, int length, String filePath, String releaseDate, String recordDate) {
		//Create and activate persistence manager connection.
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
		
		//Add Song object to ORM object grid.
		em.persist(s);
		
		//Commit transaction.
		em.getTransaction().commit();
		
		//Close connection to persistence manager.
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
	 */
	public void updateSong(String songID, String title, int length, String filePath, String releaseDate, String recordDate) {
		//Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Song s = em.find(Song.class, songID);
		
		//Checking whether fields are empty (or greater than 0 for ints) before updating.
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
		
		//Add Song object to ORM grid.
		em.persist(s);
		
		//Commit transaction.
		em.getTransaction().commit();
		
		//Close connection to persistence manager.
		em.close();
		emFactory.close();
	}
	
	/**
	 * Method deleteSong removes a Song record from the ORM object grid. 
	 * @param songID is the identifying UUID of the target Song.
	 */
	public void deleteSong(String songID) {
		//Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Song s = em.find(Song.class, songID);
		
		//Remove Song object from ORM grid.
		em.remove(s);
		
		//Commit transaction.
		em.getTransaction().commit();
		
		//Close connection to persistence manager.
		em.close();
		emFactory.close();
	}
	
	/**
	 * Method findSong locates a Song record in the ORM object grid using its songID.
	 * @param songID is the identifying UUID of the target Song.
	 * @return is the Song object.
	 */
	public Song findSong(String songID) {
		//Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Song s = em.find(Song.class, songID);
		
		//Close connection to persistence manager.
		em.close();
		emFactory.close();
		
		return s;
		
	}
	
}