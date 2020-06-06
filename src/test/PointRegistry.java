package test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PointRegistry {
	public static ArrayList<float[]> drawPoint(String query) throws NumberFormatException, SQLException, ClassNotFoundException {
		
		PostgreConnection connect = new PostgreConnection();
		ResultSet rs;
		ArrayList<float[]> rsList = new ArrayList<>();
		if(query == "") {
			
			rs = connect.runSql("select st_astext(geom) from public.point;");
		}
		else {
			rs = connect.runSql("select st_astext(geom) from public.point WHERE type='" + query + "';");
		}
	        while(rs.next()) {
			   		 String result = rs.getString(1).substring(6, rs.getString(1).length()-1);
			   		 String[] coordinate = result.split(" ");
			   		 float longitude = Float.parseFloat(coordinate[0]);
			   		 float latitude = Float.parseFloat(coordinate[1]);
			   		 float[] arr = {longitude, latitude};
			   		 rsList.add(arr);
	        }
	        
	        return rsList;
	}
}
