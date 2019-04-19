package bkg16_Music;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AlbumListWS
 */
@WebServlet("/AlbumListWS")
public class AlbumListWS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlbumListWS() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		AlbumManager am = new AlbumManager();
		String albumTitle = "";
		String searchType = "";
		// Below IDs allow for searching album list by song or artist. 
		String artistID = "";
		String songID = "";
		
		// First check whether an artist or song ID was passed and, if so, get associated albums. 
		if (request.getParameter("artistID") != null) {
			artistID = request.getParameter("artistID");
			response.getWriter().print(am.getAlbumListByArtistID(artistID));
		} else if (request.getParameter("songID") != null) {
			songID = request.getParameter("songID");
			response.getWriter().print(am.getAlbumListBySongID(songID));
		} else { // If no artist or song provided, can assume typical album search.
			if(request.getParameter("albumTitle") != null && request.getParameter("searchType") != null) {
				albumTitle = request.getParameter("albumTitle");
				searchType = request.getParameter("searchType");
			}
			//If title and searchType are blank, then return everything.
			response.getWriter().print(am.getAlbumList(albumTitle, searchType));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
