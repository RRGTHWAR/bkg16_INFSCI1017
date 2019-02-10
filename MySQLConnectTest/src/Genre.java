import java.sql.ResultSet;
import java.sql.SQLException;

public class Genre {
	private int genreID;
	private String genreName;
	private String description;
	private DbUtilities db;
	
	
	public Genre(int genreID) {
		this.genreID = genreID;
		db = new DbUtilities();
		String sql = "SELECT genre_name, description FROM genre WHERE genre_id = " + this.genreID + ";";
		try {
			ResultSet rs = db.getResultSet(sql);
			if(rs.next()) {
				this.genreName = rs.getString("genre_name");
				this.description = rs.getString("description");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Genre(String genreName, String description) {
		db = new DbUtilities();
		this.genreName = genreName;
		this.description = description;
		
		String sql = "INSERT INTO genre (genre_name, description) VALUES ('" + genreName + "', '" + description + "');";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		String sql = "UPDATE genre SET genre_name = '" + genreName + "' WHERE genre_id = " + this.genreID + ";";
		this.genreName = genreName;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		String sql = "UPDATE genre SET description = '" + description + "' WHERE genre_id = " + this.genreID + ";";
		this.description = description;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public int getGenreID() {
		return genreID;
	}
	
	

}
