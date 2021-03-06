package bkg16_Music;

import java.util.UUID;
import javax.persistence.*;

/**
 * Class AlbumManager is responsible for creating, updating and deleting Album records. 
 * @author bengundy
 *
 */
public class AlbumManager {

	/**
	 * Method createAlbum generates a new Album record in the ORM object grid.
	 * @param title is the title of the album.
	 * @param releaseDate is the date on which the album was released.
	 * @param coverImagePath is the location of the cover image for the album.
 	 * @param recordingCompany is the company that recorded and released the album.
	 * @param numberOfTracks is the number of tracks on the album.
	 * @param pmrcRating is the PMRC's rating for the album.
	 * @param length is the length of the album in minutes.
	 */
	public void createAlbum(String title, String releaseDate, String coverImagePath, String recordingCompany, int numberOfTracks, String pmrcRating, int length) {
		//Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Album a = new Album();
		
		a.setAlbumID(UUID.randomUUID().toString());
		a.setTitle(title);
		a.setReleaseDate(releaseDate);
		a.setCoverImagePath(coverImagePath);
		a.setRecordingCompany(recordingCompany);
		a.setNumberOfTracks(numberOfTracks);
		a.setPmrcRating(pmrcRating);
		a.setLength(length);

		//Add Album object to ORM object grid.
		em.persist(a);
		
		//Commit transaction.
		em.getTransaction().commit();
		
		//Close connection to persistence manager.
		em.close();
		emFactory.close();
		
	}
	
	/**
	 * Method updateAlbum updates an Album record in the ORM object grid.
	 * @param albumID is the identifying UUID of the target Album.
	 * @param title is the title of the album.
	 * @param releaseDate is the date on which the album was released.
	 * @param coverImagePath is the location of the cover image for the album.
 	 * @param recordingCompany is the company that recorded and released the album.
	 * @param numberOfTracks is the number of tracks on the album.
	 * @param pmrcRating is the PMRC's rating for the album.
	 * @param length is the length of the album in minutes.
	 */
	public void updateAlbum(String albumID, String title, String releaseDate, String coverImagePath, String recordingCompany, int numberOfTracks, String pmrcRating, int length) {
		//Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Album a = em.find(Album.class, albumID);
		
		//Checking whether all fields are empty (or greater than 0 for ints) before updating them.
		if(!title.equals("")) {
			a.setTitle(title);
		}
		
		if(!releaseDate.equals("")) {
			a.setReleaseDate(releaseDate);
		}
		
		if(!coverImagePath.equals("")) {
			a.setCoverImagePath(coverImagePath);
		}
		
		if(!recordingCompany.equals("")) {
			a.setRecordingCompany(recordingCompany);
		}
		
		if(numberOfTracks > 0) {
			a.setNumberOfTracks(numberOfTracks);
		}
		
		if(!pmrcRating.equals("")) {
			a.setPmrcRating(pmrcRating);
		}
		
		if(length > 0) {
			a.setLength(length);
		}

		//Add Album object to ORM object grid.
		em.persist(a);
		
		//Commit transaction.
		em.getTransaction().commit();
		
		//Close connection to persistence manager.
		em.close();
		emFactory.close();
	}
	
	/**
	 * Method deleteAlbum removes an Album record from the ORM object grid.
	 * @param albumID is the identifying UUID of the target Album.
	 */
	public void deleteAlbum(String albumID) {
		//Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Album a = em.find(Album.class, albumID);
		
		//Remove Album object from ORM object grid.
		em.remove(a);
		
		//Commit transaction.
		em.getTransaction().commit();
		
		//Close connection to persistence manager.
		em.close();
		emFactory.close();
	}
	
	/**
	 * Method findAlbum locates an Album record in the ORM object grid using its albumID.
	 * @param albumID is the identifying UUID of the target Album.
	 * @return is the Album object.
	 */
	public Album findAlbum(String albumID) {
		//Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Album a = em.find(Album.class, albumID);

		//Close connection to persistence manager.
		em.close();
		emFactory.close();
		
		return a;
		
	}
	
}
