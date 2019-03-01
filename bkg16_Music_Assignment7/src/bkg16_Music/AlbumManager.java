package bkg16_Music;

import java.util.UUID;

import javax.persistence.*;

public class AlbumManager {

	public void createAlbum(String title, String releaseDate, String coverImagePath, String recordingCompany, int numberOfTracks, String pmrcRating, int length) {
		EntityManagerFactory emFactory =
				Persistence.createEntityManagerFactory("bkg16_Music");
		
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

		//Add album object to ORM object grid
		em.persist(a);
		
		//Commit transaction
		em.getTransaction().commit();
		
		//Close connection to persistence manager
		em.close();
		emFactory.close();
		
	}

	public void updateAlbum(String albumID, String title, String releaseDate, String coverImagePath, String recordingCompany, int numberOfTracks, String pmrcRating, int length) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Album a = em.find(Album.class, albumID);
		
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

		em.persist(a);
		em.getTransaction().commit();
		
		em.close();
		emFactory.close();
	}
	
	public void deleteAlbum(String albumID) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Album a = em.find(Album.class, albumID);
		
		em.remove(a);
		em.getTransaction().commit();
		
		em.close();
		emFactory.close();
	}
	
	public Album findAlbum(String albumID) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Album a = em.find(Album.class, albumID);

		em.close();
		emFactory.close();
		
		return a;
		
	}
	
}
