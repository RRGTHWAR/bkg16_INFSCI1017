package bkg16_Music;

import java.util.UUID;

public class MusicTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/* SONG TESTING
		Song test = new Song("Freshmen", 3, "1995-11-08", "1995-06-05");
		System.out.println(test.getSongID());
		System.out.println(test.getTitle());
		System.out.println(test.getLength());
		System.out.println(test.getReleaseDate());
		System.out.println(test.getRecordDate());
		
		Song test2 = new Song("85c9c5da-867c-4443-8056-8dfee31c7021");
		
		test2.setTitle("Freshmen");
		System.out.println(test2.getTitle());
		System.out.println(test2.getLength());
		System.out.println(test2.getFilePath());
		System.out.println(test2.getReleaseDate());
		System.out.println(test2.getRecordDate());

		test2.deleteSong(test2.getSongID());
		test2.getTitle();
		
		System.out.println(test2.getLength());
		test2.setLength(3);
		System.out.println(test2.getLength());
		
		System.out.println(test2.getReleaseDate());
		test2.setReleaseDate("1995-01-01");
		System.out.println(test2.getReleaseDate());
		
		System.out.println(test2.getRecordDate());
		test2.setRecordDate("1995-01-01");
		System.out.println(test2.getRecordDate());
		
		System.out.println(test2.getFilePath());
		test2.setFilePath("test/test/Freshmen.song");
		System.out.println(test2.getFilePath());
		*/
		
		
		//ARTIST TESTING
		/*
		Artist testArtist = new Artist("Brian", "Vander Ark", "The Verve Pipe");
		System.out.println(testArtist.getArtistID());
		System.out.println(testArtist.getFirstName());
		System.out.println(testArtist.getLastName());
		System.out.println(testArtist.getBandName());
		System.out.println(testArtist.getBio());

		Artist testArtist2 = new Artist("85d3f93a-5fde-479a-a4e0-7009a0a016ed");
		
		System.out.println(testArtist2.getFirstName());
		System.out.println(testArtist2.getLastName());
		System.out.println(testArtist2.getBandName());
		System.out.println(testArtist2.getBio());
		
		testArtist2.deleteArtist(testArtist2.getArtistID());
		testArtist2.getFirstName();
		
		System.out.println(testArtist2.getFirstName());
		testArtist2.setFirstName("Brian");
		System.out.println(testArtist2.getFirstName());

		System.out.println(testArtist2.getLastName());
		testArtist2.setLastName("Vander Ark");
		System.out.println(testArtist2.getLastName());
		
		System.out.println(testArtist2.getBandName());
		testArtist2.setBandName("The Verve Pipe");
		System.out.println(testArtist2.getBandName());
		
		System.out.println(testArtist2.getBio());
		testArtist2.setBio("Band with one good song");
		System.out.println(testArtist2.getBio());
		*/
		
		
		//ALBUM TESTING
		
		//Album testAlbum = new Album("Villains", "1995-01-01", "Columbia", 12, "Meh", 43);
		
		/*
		System.out.println(testAlbum.getAlbumID());
		System.out.println(testAlbum.getTitle());
		System.out.println(testAlbum.getReleaseDate());
		System.out.println(testAlbum.getRecordingCompany());
		System.out.println(testAlbum.getNumberOfTracks());
		System.out.println(testAlbum.getPmrcRating());
		System.out.println(testAlbum.getLength());
		*/
		
		Album testAlbum2 = new Album("5f3a074d-6c74-43c5-b4ce-758e857e00c0");
		
		/*
		System.out.println(testAlbum2.getTitle());
		System.out.println(testAlbum2.getReleaseDate());
		System.out.println(testAlbum2.getCoverImagePath());
		System.out.println(testAlbum2.getRecordingCompany());
		System.out.println(testAlbum2.getNumberOfTracks());
		System.out.println(testAlbum2.getPmrcRating());
		System.out.println(testAlbum2.getLength());
		
		testAlbum2.deleteAlbum(testAlbum2.getAlbumID());
		testAlbum2.getTitle();
		testAlbum2.getAlbumID();
		testAlbum2.getReleaseDate();
		*/
		
		System.out.println(testAlbum2.getTitle());
		testAlbum2.setTitle("Villains");
		System.out.println(testAlbum2.getTitle());

	}

}
