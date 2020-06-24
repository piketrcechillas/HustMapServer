package test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import processor.PathFinder;

public class LineRegistry {
	public static ArrayList<float[][]> drawLine(String start, String end) throws NumberFormatException, SQLException, ClassNotFoundException {
		
		PostgreConnection connect = new PostgreConnection();
		ArrayList<String> rs = PathFinder.findPath(start, end);
		ArrayList<float[][]> rsList = new ArrayList<>();
		
	   for(int j = 0; j < rs.size(); j++) {
			   		 String result = rs.get(j).substring(11, rs.get(j).length()-1);
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
