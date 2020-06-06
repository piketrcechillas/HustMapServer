package test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PolygonRegistry {
	public static ArrayList<float[][]> drawPolygon(String query) throws NumberFormatException, SQLException, ClassNotFoundException {
		
		PostgreConnection connect = new PostgreConnection();
		ResultSet rs;
		ArrayList<float[][]> rsList = new ArrayList<>();
		if(query == "") {
			
			rs = connect.runSql("select st_astext(geom) from public.polygon;");
		}
		else {
			rs = connect.runSql("select st_astext(geom) from public.polygon;");
		}
	        while(rs.next()) {
			   		 String result = rs.getString(1).substring(9, rs.getString(1).length()-2);
			   		 String[] coordinates = result.split(",");
			   		 float[][] arr = new float[coordinates.length][2];
			   		 for(int i = 0; i < coordinates.length; i++) {
			   			 String[] coor = coordinates[i].split(" ");
			   			 float longitude = Float.parseFloat(coor[0]);
				   		 float latitude = Float.parseFloat(coor[1]);
				   		 arr[i][0] = longitude;
				   		 arr[i][1] = latitude;
				   		 rsList.add(arr);
			   		 }
	        }
	        
	        return rsList;
	}
}
