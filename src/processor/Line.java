package processor;

public class Line {
	private int id;
	private String geom;
	private String textGeom;
	private Boolean oneway;
	private Boolean no_car;
	private Boolean no_motorbike;
	private Line parent;
	private double distance;
	private double curCost;
	private double length;
	private String startpoint;
	private String endpoint;
	private double endX;
	private double endY;
	private double startX;
	private double startY;
	
	public Line() {
		this.distance = 0;
		this.curCost = 0;
		this.parent = null;
	}
	
	public Line(int id, String geom, Boolean oneway, Boolean no_car, Boolean no_motorbike) {
		this.id = id;
		this.geom  = geom;
		this.oneway = oneway;
		this.no_car = no_car;
		this.no_motorbike = no_motorbike;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}
	public void setGeom(String geom) {
		this.geom = geom;
	}
	public String getGeom() {
		return this.geom;
	}
	public void setTextGeom(String geom) {
		this.textGeom = geom;
	}
	public String getTextGeom() {
		return this.textGeom;
	}
	public void setOneWay(Boolean b) {
		this.oneway = b;
	}
	public Boolean getOneWay() {
		return this.oneway;
	}
	public void setNoCar(Boolean b) {
		this.no_car = b;
	}
	public Boolean getNoCar() {
		return this.no_car;
	}
	public void setNoMotor(Boolean b) {
		this.no_motorbike = b;
	}
	public Boolean getNoMotor() {
		return this.no_motorbike;
	}
	public void setParent(Line p) {
		this.parent = p;
	}
	public Line getParent() {
		return this.parent;
	}
	public void setDistance(double d) {
		this.distance = d;
	}
	public double getDistance() {
		return this.distance;
	}
	public void setCurCost(double d) {
		this.curCost = d;
	}
	public double getCurCost() {
		return this.curCost;
	}
	public void setLength(double d) {
		this.length = d;
	}
	public double getLength() {
		return this.length;
	}
	public void setStartpoint(String s) {
		this.startpoint = s;
	}
	public String getStartpoint() {
		return this.startpoint;
	}
	public void setEndpoint(String s) {
		this.endpoint = s;
	}
	public String getEndpoint() {
		return this.endpoint;
	}
	public void setEndXY(String s) {
		String[] arr;
		s = s.substring(6, s.length()-1);
		arr = s.split(" ");
		this.endX = Double.parseDouble(arr[0]);
		this.endY = Double.parseDouble(arr[1]);
	}
	public double getEndX() {
		return this.endX;
	}
	public double getEndY() {
		return this.endY;
	}
	public void setStartXY(String s) {
		String[] arr;
		s = s.substring(6, s.length()-1);
		arr = s.split(" ");
		this.startX = Double.parseDouble(arr[0]);
		this.startY = Double.parseDouble(arr[1]);
	}
	public double getStartX() {
		return this.startX;
	}
	public double getStartY() {
		return this.startY;
	}
}
