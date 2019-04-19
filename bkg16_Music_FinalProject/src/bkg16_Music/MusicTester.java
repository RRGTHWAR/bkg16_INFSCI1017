package bkg16_Music;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;

/**
 * Class MusicTester is the testing class for other classes.
 * String literals used here for testing only.
 * @author bengundy
 *
 */
public class MusicTester {

	/**
	 * Main testing method.
	 * @param args is the arguments for the public static void main method.
	 */
	public static void main(String[] args) {
		
		/*
		AlbumManager am = new AlbumManager();
		Album a = am.getAlbum("10ea48a7-c541-4f5f-a174-e82bc9c4607e");
		Set<Song> abSongs = a.getAlbumSongs();
		System.out.println(abSongs);
		*/
		
		SongManager sm = new SongManager();
		
		Song s = sm.getSong("a18c5a25-327a-4910-8d6d-8bcff5741743");
		
		GenreManager gm = new GenreManager();
		Genre g = gm.getGenre("e125e302-8bc5-454c-b0d9-a2628826258b");
		Set<Genre> gs = new HashSet<Genre>();
		gs.add(g);
		
		Set<Album> empty1 = new HashSet<Album>();
		Set<Artist> empty2 = new HashSet<Artist>();
		
		sm.updateSong("a18c5a25-327a-4910-8d6d-8bcff5741743", "Ripple", 3, "music/songs/Ripple", "1969-05-08 00:00:00", "1968-01-08 00:00:00", empty1, empty2, gs);
		
		
		//GenreManager gm = new GenreManager();
		//System.out.println(gm.getGenreList("Blues", "contains"));
		
		//ArtistManager am = new ArtistManager();
		//System.out.println(am.getArtistListBySongID("5cb2675f-4367-4920-a28f-4876499c8696"));
		//System.out.println(am.getArtistList("The Beatles", "equals"));
		
		//SongManager sm = new SongManager();
		
		//sm.createSong("Dear Prudence", 4, "some/where/or/other", "1968-11-22", "1968-08-28");
		//sm.updateSong("f018340e-0afd-40f2-934f-64b69332bde8", "", 3, "", "", "");
		//sm.deleteSong("5d4d1942-8829-4219-8a3e-5d612bb5b206");
		
		
		//Song testSong = sm.getSong("'a54ba89d-ac33-4ffb-abd5-d600b7abb764");
		//System.out.println(sm.getArtistCount("a54ba89d-ac33-4ffb-abd5-d600b7abb764"));
		/*
		System.out.println(testSong.getTitle());
		System.out.println(testSong.getLength());
		System.out.println(testSong.getFilePath());
		System.out.println(testSong.getReleaseDate());
		System.out.println(testSong.getRecordDate());
		*/
		
		//AlbumManager am = new AlbumManager();
		//SongManager sm = new SongManager();
		
		//am.getAlbumListByArtistID("05225045-fbd3-4621-95b1-b2d29d121b16");
		
		//am.createAlbum("The Beatles", "1968-11-22", "image/path/location", "Apple Records", 30, "Meh", 94);
		//am.updateAlbum("5d56411a-7827-4ed8-a07e-6e1db9a80261", "Sgt. Pepper's Lonely Hearts Club Band", "1967-05-26", "", "Apple", 0, "Meh plus", 40);
		//am.deleteAlbum("5d56411a-7827-4ed8-a07e-6e1db9a80261");
		
		/*
		Album testAlbum = am.getAlbum("10ea48a7-c541-4f5f-a174-e82bc9c4607e");
		System.out.println(testAlbum.getTitle());
		System.out.println(testAlbum.getReleaseDate());
		System.out.println(testAlbum.getCoverImagePath());
		System.out.println(testAlbum.getRecordingCompany());
		System.out.println(testAlbum.getNumberOfTracks());
		System.out.println(testAlbum.getPmrcRating());
		System.out.println(testAlbum.getLength());
		*/
		
		//System.out.println(testAlbum.getAlbumSongs());
		//System.out.println(sm.getSongList("Dear Prudence", "equals"));
		
		
		//ArtistManager am2 = new ArtistManager();
		
		//am2.createArtist("George", "Harrison", "The Beatles", "Lead guitar player in some band you might have heard of");
		//am2.updateArtist("3ca9ace7-0c3d-4d74-86da-c385b6ed33f4", "Ringo", "Starr", "", "By far the least talented Beatle");
		//am2.deleteArtist("3ca9ace7-0c3d-4d74-86da-c385b6ed33f4");
		
		/*
		Artist testArtist = am2.findArtist("3ca9ace7-0c3d-4d74-86da-c385b6ed33f4");
		System.out.println(testArtist.getFirstName());
		System.out.println(testArtist.getLastName());
		System.out.println(testArtist.getBandName());
		System.out.println(testArtist.getBio());
		*/
	}

}