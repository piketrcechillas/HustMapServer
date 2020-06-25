package processor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import java.util.*;

public class Processing {
	public static HashMap<String, ArrayList<Line>> lineList = new HashMap<String, ArrayList<Line>>();
	public static HashMap<String, Polygon> polygonList = new HashMap<String, Polygon>();
	
	public static void loadData() {
		lineList.clear();
		polygonList.clear();
		try (Connection connection = DriverManager.getConnection(
        		"jdbc:postgresql://hustmap.postgres.database.azure.com:5432/bkmap", "hustmap@hustmap", "Admin123")) {
			Statement statement = connection.createStatement();
			
			// Load line
			String query =  "select id, geom, oneway, no_car, no_motorbike, st_length(geom), " +
					"st_startpoint(geom), st_endpoint(geom), " +
					"st_astext(st_startpoint(geom)), st_astext(st_endpoint(geom)), st_astext(geom)\r\n" +
					"from subline\r\n";
			ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            	Line temp = new Line();
            	String startpoint = resultSet.getString(7);
            	String endpoint = resultSet.getString(8);
            	Boolean oneway = resultSet.getBoolean(3);
            	temp.setId(resultSet.getInt(1));
            	temp.setGeom(resultSet.getString(2));
            	temp.setOneWay(oneway);
            	temp.setNoCar(resultSet.getBoolean(4));
            	temp.setNoMotor(resultSet.getBoolean(5));
            	temp.setLength(resultSet.getFloat(6));
            	temp.setStartpoint(startpoint);
            	temp.setEndpoint(endpoint);
            	temp.setStartXY(resultSet.getString(9));
            	temp.setEndXY(resultSet.getString(10));
            	temp.setTextGeom(resultSet.getString(11));
            	if (lineList.get(startpoint) == null) {
            		ArrayList<Line> value = new ArrayList<Line>();
            		lineList.put(startpoint, value);
            	}
            	lineList.get(startpoint).add(temp);
            	if(!oneway) {
            		Line temp2 = new Line();
            		temp2.setId(resultSet.getInt(1));
                	temp2.setGeom(resultSet.getString(2));
                	temp2.setOneWay(oneway);
                	temp2.setNoCar(resultSet.getBoolean(4));
                	temp2.setNoMotor(resultSet.getBoolean(5));
                	temp2.setLength(resultSet.getFloat(6));
                	temp2.setStartpoint(endpoint);
                	temp2.setEndpoint(startpoint);
                	temp2.setEndXY(resultSet.getString(9));
                	temp2.setStartXY(resultSet.getString(10));
                	temp2.setTextGeom(resultSet.getString(11));
                	if (lineList.get(endpoint) == null) {
                		ArrayList<Line> value2 = new ArrayList<Line>();
                		lineList.put(endpoint, value2);
                	}
                	lineList.get(endpoint).add(temp2);
            	}
            }
            
            // Load polygon
            query = "select id, name, type, geom, gate, st_astext(gate)\r\n" + 
            		"from polygon";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            	Polygon temp = new Polygon();
            	temp.setId(resultSet.getString(1));
            	temp.setName(resultSet.getString(2));
            	temp.setType(resultSet.getString(3));
            	temp.setGeom(resultSet.getString(4));
            	temp.setGate(resultSet.getString(5));
            	temp.setGateTxt(resultSet.getString(6));
            	polygonList.put(temp.getName(), temp);
            }
            
		} catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
	}
	public static int partition(ArrayList<Line> arr, int low, int high, ArrayList<Integer> arrId) { 
        double pivot = arr.get(high).getDistance();
        int i = (low-1);
        for (int j=low; j<high; j++) { 
            if (arr.get(j).getDistance() < pivot) { 
                i++; 
                Line temp = arr.get(i); 
                arr.set(i, arr.get(j));
                arr.set(j, temp);
                int tempId = arrId.get(i);
                arrId.set(i, arrId.get(j));
                arrId.set(j, tempId);
            } 
        } 
  
        Line temp = arr.get(i+1); 
        arr.set(i+1, arr.get(high));
        arr.set(high, temp);
        int tempId = arrId.get(i+1);
        arrId.set(i+1, arrId.get(high));
        arrId.set(high, tempId);
  
        return i+1; 
    } 
  
    public static void sort(ArrayList<Line> arr, int low, int high, ArrayList<Integer> arrId) { 
        if (low < high) { 
            int pi = partition(arr, low, high, arrId);
            sort(arr, low, pi-1, arrId); 
            sort(arr, pi+1, high, arrId); 
        } 
    } 
    
	public static ArrayList<String> FindPath(String start, String end) {
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<Line> closeSet = new ArrayList<Line>();
		ArrayList<Line> openSet = new ArrayList<Line>();
		ArrayList<Integer> closeId = new ArrayList<Integer>();
		ArrayList<Integer> openId = new ArrayList<Integer>();
		Line startLine = new Line();
		Line endLine = new Line();
		double endX = 0, endY = 0, startX = 0, startY = 0;
		try (Connection connection = DriverManager.getConnection(
        		"jdbc:postgresql://hustmap.postgres.database.azure.com:5432/bkmap", "hustmap@hustmap", "Admin123")) {
        	Statement statement = connection.createStatement();
        	String query =  "select id, geom, oneway, no_car, no_motorbike, st_length(geom), " + 
        					"st_astext(st_endpoint(st_ShortestLine('"+ end +"', geom))), " +
        					"st_astext(geom)\r\n" +
        					"from subline\r\n" + 
        					"order by st_length(st_ShortestLine('"+ end +"', geom))\r\n" + 
        					"limit 1";
        	ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            	endLine.setId(resultSet.getInt(1));
            	endLine.setGeom(resultSet.getString(2));
            	endLine.setOneWay(resultSet.getBoolean(3));
            	endLine.setNoCar(resultSet.getBoolean(4));
            	endLine.setNoMotor(resultSet.getBoolean(5));
            	endLine.setLength(resultSet.getDouble(6));
            	endLine.setTextGeom(resultSet.getString(8));
            	String s = resultSet.getString(7);
            	String[] arr;
        		s = s.substring(6, s.length()-1);
        		arr = s.split(" ");
        		endX = Double.parseDouble(arr[0]);
        		endY = Double.parseDouble(arr[1]);
            }
            query =  "select id, geom, oneway, no_car, no_motorbike, st_length(geom), " + 
            		"st_startpoint(geom), st_endpoint(geom), " +
            		"st_astext(st_endpoint(st_ShortestLine('"+ start +"', geom))), " +
            		"st_astext(geom)\r\n" +
					"from subline\r\n" + 
					"order by st_length(st_ShortestLine('"+ start +"', geom))\r\n" + 
					"limit 1";
        	resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            	startLine.setId(resultSet.getInt(1));
            	startLine.setGeom(resultSet.getString(2));
            	startLine.setOneWay(resultSet.getBoolean(3));
            	startLine.setNoCar(resultSet.getBoolean(4));
            	startLine.setNoMotor(resultSet.getBoolean(5));
            	startLine.setLength(resultSet.getFloat(6));
            	startLine.setStartpoint(resultSet.getString(7));
            	startLine.setEndpoint(resultSet.getString(8));
            	startLine.setTextGeom(resultSet.getString(10));
            	String s = resultSet.getString(9);
            	String[] arr;
        		s = s.substring(6, s.length()-1);
        		arr = s.split(" ");
        		startX = Double.parseDouble(arr[0]);
        		startY = Double.parseDouble(arr[1]);
            }
            
            double h = Math.sqrt(Math.pow(startX-endX, 2) + Math.pow(startY-endY, 2));
            startLine.setDistance(h);
            
            openSet.add(startLine);
            openId.add(startLine.getId());
            while(!openId.isEmpty()) {
            	Processing.sort(openSet, 0, openId.size()-1, openId);
            	Line tmp = openSet.get(0);
            	openSet.remove(0);
            	openId.remove(0);
            	closeSet.add(tmp);
                closeId.add(tmp.getId());
            	if (tmp.getId() == endLine.getId()) {
            		String epoint = "POINT(" + endX + " " + endY + ")";
            		String spoint = "POINT(" + startX + " " + startY + ")";
            		result.add(epoint);
            		result.add(tmp.getTextGeom());
            		ArrayList<String> listP = new ArrayList<String>();
            		while (tmp.getParent() != null) {
            			tmp = tmp.getParent();
            			if (tmp.getParent() != null) {
	            			String tmpP = tmp.getStartX() + " " + tmp.getStartY() + "," +
	            							tmp.getEndX() + " " + tmp.getEndY();
	            			listP.add(tmpP);
            			}
            			result.add(tmp.getTextGeom());
            		}
            		result.add(spoint);
            		String tempS = listP.get(0);
            		String[] arr;
            		arr = tempS.split(",");
            		String eline = "LINESTRING(" + arr[1] + "," + endX + " " + endY + ")";
            		tempS = listP.get(listP.size() - 1);
            		arr = tempS.split(",");
            		String sline = "LINESTRING(" + startX + " " + startY + "," + arr[0] + ")";
            		result.set(1, eline);
            		result.set(result.size() - 2, sline);
            		System.out.println(result.size());
            		return result;
            	}
            	ArrayList<Line> childList = lineList.get(tmp.getEndpoint());
            	if (childList != null) {
	            	for (int i=0; i<childList.size(); i++) {
	            		Line tmpChild = childList.get(i);
	            		if (closeId.contains(tmpChild.getId()))
	                		continue;
	                	if (openId.contains(tmpChild.getId()))
	                		continue;
	                    h = Math.sqrt(Math.pow(tmpChild.getEndX() - endX, 2) + Math.pow(tmpChild.getEndY() - endY, 2));
	                    tmpChild.setCurCost(tmp.getCurCost() + tmpChild.getLength());
	                    tmpChild.setDistance(tmpChild.getCurCost() + h);
	                    tmpChild.setParent(tmp);
	                    openSet.add(tmpChild);
	                    openId.add(tmpChild.getId());
	            	}
            	}
            	if (tmp.getId() == startLine.getId()) {
            		childList = lineList.get(tmp.getStartpoint());
            		if (childList != null) {
	                	for (int i=0; i<childList.size(); i++) {
	                		Line tmpChild = childList.get(i);
	                		if (closeId.contains(tmpChild.getId()))
	                    		continue;
	                    	if (openId.contains(tmpChild.getId()))
	                    		continue;
	                		query = "select st_distance('"+tmpChild.getGeom()+"', '"+endLine.getGeom()+"')";
	                    	resultSet = statement.executeQuery(query);
	                    	h = Math.sqrt(Math.pow(tmpChild.getEndX() - endX, 2) + Math.pow(tmpChild.getEndY() - endY, 2));
		                    tmpChild.setCurCost(tmp.getCurCost() + tmpChild.getLength());
		                    tmpChild.setDistance(tmpChild.getCurCost() + h);
		                    tmpChild.setParent(tmp);
		                    openSet.add(tmpChild);
		                    openId.add(tmpChild.getId());
	                	}
            		}
            	}
                
            }
            System.out.println("Không tìm thấy đường!");
            
        }  catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
		return result;
	}
	
	public static void showResult(ArrayList<String> result	) {
		try (Connection connection = DriverManager.getConnection(
        		"jdbc:postgresql://hustmap.postgres.database.azure.com:5432/bkmap", "hustmap@hustmap", "Admin123")) {
            Statement statement = connection.createStatement();
			statement.execute("DELETE from result");
	        int i;
	        String query = "INSERT INTO result (id, geom)" + "\n" + "VALUES ";
	        for (i = 2; i <= result.size() - 1; i++) {
	        	String line = result.get(i-1);
	        	if (i == result.size() - 1) {
	        		query += "('"+i+"', st_geomfromtext('"+line+"', 4326));";
	        	} else {
	        		query += "('"+i+"', st_geomfromtext('"+line+"', 4326)),\n";
	        	}
	        }
	        statement.executeUpdate(query);
	        System.out.println("Done!");	            	                       
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
	}
	
}


