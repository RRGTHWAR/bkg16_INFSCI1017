package bkg16_Music;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ArtistListWS
 */
@WebServlet("/ArtistListWS")
public class ArtistListWS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArtistListWS() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		ArtistManager am = new ArtistManager();
		String artistName = "";
		String searchType = "";
		// Below IDs allow for searching artist list by song or album. 
		String songID = "";
		String albumID = "";
		
		// First check whether an album or song ID was passed and, if so, get associated artists. 
		if(request.getParameter("songID") != null) {
			songID = request.getParameter("songID");
			response.getWriter().print(am.getArtistListBySongID(songID));
		} else if (request.getParameter("albumID") != null) {
			albumID = request.getParameter("albumID");
			response.getWriter().print(am.getArtistListByAlbumID(albumID));
		} else { // If no album or song provided, can assume typical artist search.
			if(request.getParameter("artistName") != null && request.getParameter("searchType") != null) {
				artistName = request.getParameter("artistName");
				searchType = request.getParameter("searchType");
			}
			// If artistName and searchType are blank, then return everything.
				response.getWriter().print(am.getArtistList(artistName, searchType));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
