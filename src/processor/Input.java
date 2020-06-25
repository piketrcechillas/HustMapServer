package processor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import java.util.*;

public class Input {
	public static String getName(String name) {
		String geom = "";
		if (Processing.polygonList.containsKey(name)) {
			geom = Processing.polygonList.get(name).getGate();
		}
		else {
			System.out.println("Empty!");
		}
		return geom;
	}
}
