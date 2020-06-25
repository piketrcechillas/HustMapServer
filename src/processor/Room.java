package processor;

public class Room {
	private String id;
	private String name;
	private String type;
	private String floor;
	private String room;
	private String polygon_id;
	
	public Room() {
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getPolygon_id() {
		return polygon_id;
	}
	public void setPolygon_id(String polygon_id) {
		this.polygon_id = polygon_id;
	}
}
