package test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
		ArrayList<float[][]> result = LineRegistry.drawLine(query);
		
		return Response.status(Status.OK).entity(result).build();
	}
	
	@GET
	@Path("/queryPolygon")
	@Produces(MediaType.APPLICATION_JSON)
	public Response requestPolygon(@QueryParam("q") String query) throws NumberFormatException, SQLException, ClassNotFoundException  {
		if(query==null) {
			query = "";
		}
		ArrayList<float[][]> result = PolygonRegistry.drawPolygon(query);
		
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
	
}


