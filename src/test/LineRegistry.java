package test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import processor.PathFinder;

public class LineRegistry {
	public static ArrayList<float[][]> drawLine(String start, String end) throws NumberFormatException, SQLException, ClassNotFoundException {
		
	//	PostgreConnection connect = new PostgreConnection();
		ArrayList<String> rs = PathFinder.findPath(start, end);
		ArrayList<float[][]> rsList = new ArrayList<>();
		
	   for(int j = 1; j < rs.size()-1; j++) {
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
	   
	   String endPoint = rs.get(0).substring(6, rs.get(0).length()-1);
	   String[] coordinate = endPoint.split(" ");
 	   float longitude = Float.parseFloat(coordinate[0]);
 	   float latitude = Float.parseFloat(coordinate[1]);
 	   float[][] arr = new float[1][2];
 	   arr[0][0] = longitude;
 	   arr[0][1] = latitude;
 	   rsList.add(arr);
 	   
 	   String startPoint = rs.get(rs.size()-1).substring(6, rs.get(rs.size()-1).length()-1);
	   String[] coordinate2 = startPoint.split(" ");
	   float longitude2 = Float.parseFloat(coordinate2[0]);
	   float latitude2  = Float.parseFloat(coordinate2[1]);
	   float[][] arr2 = new float[1][2];
	   arr2[0][0] = longitude2;
	   arr2[0][1] = latitude2;
	   rsList.add(arr2);
	        
	   System.out.println(arr[0][0] + " " + arr[0][1] + " " + arr2[0][0] + " " + arr2[0][1]);
 	   
 	   return rsList;
	}
}
