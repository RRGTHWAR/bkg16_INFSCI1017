import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.*;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class Tester {

	public static void main(String[] args) {
		GenreManager gm = new GenreManager();
		Genre g = gm.getGenre("e125e302-8bc5-454c-b0d9-a2628826258b");
		System.out.println(g.toJSON().toString());
		//System.out.println(g.getDescription());
		/*
		JSONObject gj = new JSONObject();
		try {
			gj.append("genre_id", "e125e302-8bc5-454c-b0d9-a2628826258b");
			gj.append("genre_name", "Americana");
			gj.append("description", "Music your dad likes");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {
			System.out.println(gj.get("genre_name"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	}

}
