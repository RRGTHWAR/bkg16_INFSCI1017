package bkg16_Music;

import java.util.UUID;

import javax.persistence.*;

public class SongManager {

	public void createSong(String title, int length, String filePath, String releaseDate, String recordDate) {
		EntityManagerFactory emFactory =
				Persistence.createEntityManagerFactory("bkg16_Music");
		
		EntityManager em = emFactory.createEntityManager();
		
		em.getTransaction().begin();
		Song s = new Song();
		
		s.setSongID(UUID.randomUUID().toString());
		s.setTitle(title);
		s.setLength(length);
		s.setFilePath(filePath);
		s.setReleaseDate(releaseDate);
		s.setRecordDate(recordDate);
		
		//Add song object to ORM object grid
		em.persist(s);
		
		//Commit transaction
		em.getTransaction().commit();
		
		//Close connection to persistence manager
		em.close();
		emFactory.close();
		
	}


	public void updateSong(String songID, String title, int length, String filePath, String releaseDate, String recordDate) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Song s = em.find(Song.class, songID);
		
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

		em.persist(s);
		em.getTransaction().commit();
		
		em.close();
		emFactory.close();
	}
	
	public void deleteSong(String songID) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Song s = em.find(Song.class, songID);
		
		em.remove(s);
		em.getTransaction().commit();
		
		em.close();
		emFactory.close();
	}
	
	public Song findSong(String songID) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkg16_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Song s = em.find(Song.class, songID);

		em.close();
		emFactory.close();
		
		return s;
		
	}
	
}