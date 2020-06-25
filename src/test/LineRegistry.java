package test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import processor.PathFinder;

public class LineRegistry {
	public static ArrayList<double[][]> drawLine(String start, String end) throws NumberFormatException, SQLException, ClassNotFoundException {
		
	//	PostgreConnection connect = new PostgreConnection();
		ArrayList<String> rs = PathFinder.findPath(start, end);
		ArrayList<double[][]> rsList = new ArrayList<>();
		
	   for(int j = 1; j < rs.size()-1; j++) {
			   		 String result = rs.get(j).substring(11, rs.get(j).length()-1);
			   		 String[] coordinates = result.split(",");
			   		 double[][] arr = new double[coordinates.length][2];
			   		 for(int i = 0; i < coordinates.length; i++) {
			   			 String[] coor = coordinates[i].split(" ");
			   			 double longitude = Double.parseDouble(coor[0]);
				   		 double latitude = Double.parseDouble(coor[1]);
				   		 
				   		 
				   		 arr[i][0] = longitude;
				   		 arr[i][1] = latitude;
				   		 rsList.add(arr);
			   		 }
	        }
	   
	   String endPoint = rs.get(0).substring(6, rs.get(0).length()-1);
	   String[] coordinate = endPoint.split(" ");
	   double longitude = Double.parseDouble(coordinate[0]);
 	   double latitude = Double.parseDouble(coordinate[1]);
 	   double[][] arr = new double[1][2];
 	   arr[0][0] = longitude;
 	   arr[0][1] = latitude;
 	   rsList.add(arr);
 	   
 	   String startPoint = rs.get(rs.size()-1).substring(6, rs.get(rs.size()-1).length()-1);
	   String[] coordinate2 = startPoint.split(" ");
	   double longitude2 = Double.parseDouble(coordinate2[0]);
 	   double latitude2 = Double.parseDouble(coordinate2[1]);
 	   double[][] arr2 = new double[1][2];
 	   arr2[0][0] = longitude2;
 	   arr2[0][1] = latitude2;
	   rsList.add(arr2);
	        
	   System.out.println(arr[0][0] + " " + arr[0][1] + " " + arr2[0][0] + " " + arr2[0][1]);
 	   
 	   return rsList;
	}
	
	public static ArrayList<double[][]> drawLineFromGPS(String start, String end) throws NumberFormatException, SQLException, ClassNotFoundException {
		
	//	PostgreConnection connect = new PostgreConnection();
		ArrayList<String> rs = PathFinder.findPathFromGPS(start, end);
		ArrayList<double[][]> rsList = new ArrayList<>();
		
	   for(int j = 1; j < rs.size()-1; j++) {
			   		 String result = rs.get(j).substring(11, rs.get(j).length()-1);
			   		 String[] coordinates = result.split(",");
			   		 double[][] arr = new double[coordinates.length][2];
			   		 for(int i = 0; i < coordinates.length; i++) {
			   			 String[] coor = coordinates[i].split(" ");
			   			 double longitude = Double.parseDouble(coor[0]);
				   		 double latitude = Double.parseDouble(coor[1]);
				   		 arr[i][0] = longitude;
				   		 arr[i][1] = latitude;
				   		 rsList.add(arr);
			   		 }
	        }
	   
	   String endPoint = rs.get(0).substring(6, rs.get(0).length()-1);
	   String[] coordinate = endPoint.split(" ");
	   double longitude = Double.parseDouble(coordinate[0]);
 	   double latitude = Double.parseDouble(coordinate[1]);
 	   double[][] arr = new double[1][2];
 	   arr[0][0] = longitude;
 	   arr[0][1] = latitude;
 	   rsList.add(arr);
 	   
 	   String startPoint = rs.get(rs.size()-1).substring(6, rs.get(rs.size()-1).length()-1);
	   String[] coordinate2 = startPoint.split(" ");
	   double longitude2 = Double.parseDouble(coordinate2[0]);
 	   double latitude2 = Double.parseDouble(coordinate2[1]);
 	   double[][] arr2 = new double[1][2];
 	   arr2[0][0] = longitude2;
 	   arr2[0][1] = latitude2;
	   rsList.add(arr2);
	        
	   System.out.println(arr[0][0] + " " + arr[0][1] + " " + arr2[0][0] + " " + arr2[0][1]);
 	   
 	   return rsList;
	}
	
	public static ArrayList<double[][]> drawLineToGPS(String start, String end) throws NumberFormatException, SQLException, ClassNotFoundException {
		
	//	PostgreConnection connect = new PostgreConnection();
		ArrayList<String> rs = PathFinder.findPathToGPS(start, end);
		ArrayList<double[][]> rsList = new ArrayList<>();
		
	   for(int j = 1; j < rs.size()-1; j++) {
			   		 String result = rs.get(j).substring(11, rs.get(j).length()-1);
			   		 String[] coordinates = result.split(",");
			   		 double[][] arr = new double[coordinates.length][2];
			   		 for(int i = 0; i < coordinates.length; i++) {
			   			 String[] coor = coordinates[i].split(" ");
			   			 double longitude = Double.parseDouble(coor[0]);
				   		 double latitude = Double.parseDouble(coor[1]);
				   		 arr[i][0] = longitude;
				   		 arr[i][1] = latitude;
				   		 rsList.add(arr);
			   		 }
	        }
	   
	   String endPoint = rs.get(0).substring(6, rs.get(0).length()-1);
	   String[] coordinate = endPoint.split(" ");
	   double longitude = Double.parseDouble(coordinate[0]);
 	   double latitude = Double.parseDouble(coordinate[1]);
 	   double[][] arr = new double[1][2];
 	   arr[0][0] = longitude;
 	   arr[0][1] = latitude;
 	   rsList.add(arr);
 	   
 	   String startPoint = rs.get(rs.size()-1).substring(6, rs.get(rs.size()-1).length()-1);
	   String[] coordinate2 = startPoint.split(" ");
	   double longitude2 = Double.parseDouble(coordinate2[0]);
 	   double latitude2 = Double.parseDouble(coordinate2[1]);
 	   double[][] arr2 = new double[1][2];
 	   arr2[0][0] = longitude;
 	   arr2[0][1] = latitude;
	   rsList.add(arr2);
	        
	   System.out.println(arr[0][0] + " " + arr[0][1] + " " + arr2[0][0] + " " + arr2[0][1]);
 	   
 	   return rsList;
	}
	
	
}
