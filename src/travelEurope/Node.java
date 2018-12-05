package travelEurope;

import java.util.LinkedList;

public class Node {
	private String city_Name;
	private LinkedList<Service> edge = new LinkedList<Service>();
	
//	public Node(String city_Name, LinkedList<Service> edge) {
//		super();
//		this.city_Name = city_Name;
//		this.edge = edge;
//	}
	public Node(){
		
	}

	public String getCity_Name() {
		return city_Name;
	}

	public void setCity_Name(String city_Name) {
		this.city_Name = city_Name;
	}

	public LinkedList<Service> getEdge() {
		return edge;
	}

	public void setEdge(LinkedList<Service> edge) {
		this.edge = edge;
	}
		
}
