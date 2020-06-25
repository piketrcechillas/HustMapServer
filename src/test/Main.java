package test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import processor.Processing;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("/connect")
public class Main {

	@GET
	@Path("/queryPoint")
	@Produces(MediaType.APPLICATION_JSON)
	public Response requestPoint(@QueryParam("q") String query) throws NumberFormatException, SQLException, ClassNotFoundException  {
		if(query==null) {
			query = "";
		}
		ArrayList<float[]> result = PointRegistry.drawPoint(query);
		
		return Response.status(Status.OK).entity(result).build();
	}
	
	@GET
	@Path("/queryLine")
	@Produces(MediaType.APPLICATION_JSON)
	public Response requestLine(@QueryParam("q") String query) throws NumberFormatException, SQLException, ClassNotFoundException  {
		if(query==null) {
			query = "";
		}
		ArrayList<double[][]> result = LineRegistry.drawLine("", "");
		
		return Response.status(Status.OK).entity(result).build();
	}
	
	@GET
	@Path("/queryPolygon")
	@Produces(MediaType.APPLICATION_JSON)
	public Response requestPolygon() throws NumberFormatException, SQLException, ClassNotFoundException  {

		ArrayList<float[][]> result = PolygonRegistry.drawPolygon();
		
		return Response.status(Status.OK).entity(result).build();
	}
	
	@GET
	@Path("/queryPolygonPoint")
	@Produces(MediaType.APPLICATION_JSON)
	public Response requestPolygonPoint(@QueryParam("q") String query) throws NumberFormatException, SQLException, ClassNotFoundException  {
		if(query==null) {
			query = "";
		}
		ArrayList<float[]> result = PolygonToPoint.transformPolygon(query);
		
		return Response.status(Status.OK).entity(result).build();
	}
	
	@GET
	@Path("/queryPath")
	@Produces(MediaType.APPLICATION_JSON)
	public Response requestPath(@QueryParam("start") String start, @QueryParam("end") String end) throws NumberFormatException, SQLException, ClassNotFoundException  {

		ArrayList<double[][]> result = LineRegistry.drawLine(start, end);
		System.out.println("Done!");
		return Response.status(Status.OK).entity(result).build();
	}
	
	@GET
	@Path("/queryFromGPS")
	@Produces(MediaType.APPLICATION_JSON)
	public Response requestPathFromGPS(@QueryParam("start") String start, @QueryParam("end") String end) throws NumberFormatException, SQLException, ClassNotFoundException  {

		ArrayList<double[][]> result = LineRegistry.drawLineFromGPS(start, end);
		System.out.println("Done!");
		return Response.status(Status.OK).entity(result).build();
	}
	
	@GET
	@Path("/queryToGPS")
	@Produces(MediaType.APPLICATION_JSON)
	public Response requestPathToGPS(@QueryParam("start") String start, @QueryParam("end") String end) throws NumberFormatException, SQLException, ClassNotFoundException  {

		ArrayList<double[][]> result = LineRegistry.drawLineToGPS(start, end);
		System.out.println("Done!");
		return Response.status(Status.OK).entity(result).build();
	}
	
	@GET
	@Path("/getLabel")
	@Produces(MediaType.APPLICATION_JSON)
	public Response requestLabel() throws NumberFormatException, SQLException, ClassNotFoundException  {

		ArrayList<String> label = Processing.getLabelList();
		System.out.println("Done!");
		return Response.status(Status.OK).entity(label).build();
	}
}


