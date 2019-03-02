package bkg16_Music;

import java.util.UUID;
import javax.persistence.*;

/**
 * Class ArtistManager is responsible for creating, updating and deleting Artist records. 
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
	 */
	public void createArtist(String firstName, String lastName, String bandName, String bio) {
		//Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Artist a = new Artist();
		
		a.setArtistID(UUID.randomUUID().toString());
		a.setFirstName(firstName);
		a.setLastName(lastName);
		a.setBandName(bandName);
		a.setBio(bio);
		
		//Add Artist object to ORM object grid.
		em.persist(a);
		
		//Commit transaction.
		em.getTransaction().commit();
		
		//Close connection to persistence manager.
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
	 */
	public void updateArtist(String artistID, String firstName, String lastName, String bandName, String bio) {
		//Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Artist a = em.find(Artist.class, artistID);
		
		//Making sure fields are not empty before updating them.
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

		//Add Artist object to ORM object grid.
		em.persist(a);
		
		//Commit transaction.
		em.getTransaction().commit();
		
		//Close connection to persistence manager.
		em.close();
		emFactory.close();
	}
	
	/**
	 * Method deleteArtist removes an Artist record from the ORM object grid.
	 * @param artistID is the identifying UUID of the target Artist.
	 */
	public void deleteArtist(String artistID) {
		//Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Artist a = em.find(Artist.class, artistID);
		
		//Remove Artist object from ORM object grid.
		em.remove(a);
		
		//Commit transaction.
		em.getTransaction().commit();
		
		//Close connection to persistence manager.
		em.close();
		emFactory.close();
	}
	
	/**
	 * Method findSong locates an Artist record in the ORM object grid using its artistID.
	 * @param artistID is the identifying UUID of the target Artist.
	 * @return is the Artist object.
	 */
	public Artist findArtist(String artistID) {
		//Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Artist a = em.find(Artist.class, artistID);

		//Close connection to persistence manager.
		em.close();
		emFactory.close();
		
		return a;
		
	}

}
