package processor;

public class Point {
	private String id;
	private String name;
	private String type;
	private String geom;
	private String txtGeom;
	
	public Point() {
		
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
	public String getGeom() {
		return geom;
	}
	public void setGeom(String geom) {
		this.geom = geom;
	}
	public String getTxtGeom() {
		return txtGeom;
	}
	public void setTxtGeom(String txtGeom) {
		this.txtGeom = txtGeom;
	}
}
