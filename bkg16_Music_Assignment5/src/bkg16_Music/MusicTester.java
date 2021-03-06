package bkg16_Music;

public class MusicTester {

	/**
	 * Testing class for other classes. String literals used here for testing only.
	 */
	public static void main(String[] args) {
		
		//SONG TESTING
		
		//Song test = new Song("Peaches", 2, "1995-10-08", "1995-06-25");
		//System.out.println(test.getSongID());
		/*
		System.out.println(test.getTitle());
		System.out.println(test.getLength());
		System.out.println(test.getReleaseDate());
		System.out.println(test.getRecordDate());
		*/
		
		//Song test2 = new Song("dddd18c7-8dc8-46f9-8cbd-8925e7b79f24");
		//System.out.println(test2.getSongArtists());
		
		/*
		test2.setTitle("Freshmen1");
		
		System.out.println(test2.getTitle());
		System.out.println(test2.getLength());
		System.out.println(test2.getFilePath());
		System.out.println(test2.getReleaseDate());
		System.out.println(test2.getRecordDate());
		
		
		test2.deleteSong(test2.getSongID());
		test2.getTitle();
		*/
		
		/*
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
		Artist testArtist = new Artist("Jim", "James", null);
		
		System.out.println(testArtist.getArtistID());
		System.out.println(testArtist.getFirstName());
		System.out.println(testArtist.getLastName());
		System.out.println(testArtist.getBandName());
		System.out.println(testArtist.getBio());
		*/

		//Artist testArtist2 = new Artist("d7345af2-a5dd-4470-a3bb-5407cfde9916");
		
		/*
		System.out.println(testArtist2.getFirstName());
		System.out.println(testArtist2.getLastName());
		System.out.println(testArtist2.getBandName());
		System.out.println(testArtist2.getBio());
		*/
		
		//testArtist2.deleteArtist(testArtist2.getArtistID());
		//testArtist2.getFirstName();
		
		/*
		System.out.println(testArtist2.getFirstName());
		testArtist2.setFirstName("Jim");
		System.out.println(testArtist2.getFirstName());

		System.out.println(testArtist2.getLastName());
		testArtist2.setLastName("James");
		System.out.println(testArtist2.getLastName());
		
		System.out.println(testArtist2.getBandName());
		testArtist2.setBandName("My Morning Jacket");
		System.out.println(testArtist2.getBandName());

		System.out.println(testArtist2.getBio());
		testArtist2.setBio("Excellent band");
		System.out.println(testArtist2.getBio());
		*/
		
		/*
		test2.addArtist(testArtist2);
		test2.deleteArtist("85d3f93a-5fde-479a-a4e0-7009a0a016ed");
		test2.deleteArtist(testArtist2);
		System.out.println(test2.getSongArtists());
		*/
		
		//ALBUM TESTING
		
		//Album testAlbum = new Album("Villains", "1995-01-01", "Columbia", 12, "Meh", 43);
		//System.out.println(testAlbum.getAlbumID());
		/*
		System.out.println(testAlbum.getTitle());
		System.out.println(testAlbum.getReleaseDate());
		System.out.println(testAlbum.getRecordingCompany());
		System.out.println(testAlbum.getNumberOfTracks());
		System.out.println(testAlbum.getPmrcRating());
		System.out.println(testAlbum.getLength());
		*/
		
		/*
		Album testAlbum2 = new Album("10ea48a7-c541-4f5f-a174-e82bc9c4607e");
		//Based the below line on this: https://stackoverflow.com/questions/36782231/printing-a-java-map-mapstring-object-how
		testAlbum2.getAlbumSongs().forEach((key, value) -> System.out.println(value.getTitle()));
		//testAlbum2.addSong(test2);
		System.out.println(testAlbum2.getAlbumSongs());
		
		System.out.println(testAlbum2.getTitle());
		System.out.println(testAlbum2.getReleaseDate());
		System.out.println(testAlbum2.getCoverImagePath());
		System.out.println(testAlbum2.getRecordingCompany());
		System.out.println(testAlbum2.getNumberOfTracks());
		System.out.println(testAlbum2.getPmrcRating());
		System.out.println(testAlbum2.getLength());
		*/
		
		/*
		testAlbum2.deleteAlbum(testAlbum2.getAlbumID());
		testAlbum2.getTitle();

		testAlbum2.getAlbumID();
		testAlbum2.getReleaseDate();
		
		System.out.println(testAlbum2.getTitle());
		testAlbum2.setTitle("American Beauty");
		System.out.println(testAlbum2.getTitle());
		
		System.out.println(testAlbum2.getReleaseDate());
		testAlbum2.setReleaseDate("1968-02-02");
		System.out.println(testAlbum2.getReleaseDate());

		
		System.out.println(testAlbum2.getCoverImagePath());
		testAlbum2.setCoverImagePath("some/where/or/other");
		System.out.println(testAlbum2.getCoverImagePath());
		
		System.out.println(testAlbum2.getRecordingCompany());
		testAlbum2.setRecordingCompany("Death Row");
		System.out.println(testAlbum2.getRecordingCompany());
		
		System.out.println(testAlbum2.getNumberOfTracks());
		testAlbum2.setNumberOfTracks(11);
		System.out.println(testAlbum2.getNumberOfTracks());
		
		System.out.println(testAlbum2.getPmrcRating());
		testAlbum2.setPmrcRating("Double Meh");
		System.out.println(testAlbum2.getPmrcRating());
		
		System.out.println(testAlbum2.getLength());
		testAlbum2.setLength(47);
		System.out.println(testAlbum2.getLength());
		*/
		
		//testAlbum2.addSong(test2);
		//testAlbum2.deleteSong("31226d05-001c-4de1-bcb0-ade29dc95201");
		//testAlbum2.deleteSong(test2);
		//System.out.println(testAlbum2.getAlbumSongs());
		
	}

}