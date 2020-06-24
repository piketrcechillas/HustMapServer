package processor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import java.util.*;

public class PathFinder {
	public static ArrayList<String> findPath(String s, String e) {

        //Processing.loadData();
	    String start = Input.getName(s);
	    String end = Input.getName(e);
		ArrayList<String> result = Processing.FindPath(start, end);
		//Processing.showResult(result);
        return result;
	}
}
