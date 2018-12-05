package travelEurope;

import java.util.ArrayList;
import java.util.LinkedList;

public class RailSystem {
	private EuropeGraph europeGraph = new EuropeGraph();
	private ArrayList<Double> minFee = new ArrayList<>();
	
	
	public double claculateFee(){
		return 0;
	}
	
	public City find_city(String city_name){
		for(City find_city : europeGraph.getCityList()){
			if(city_name.equals(find_city.getCity_name())){
				return find_city;
			}
		}
		
		return null;
	}
	
	public Node find_node(String city_name){
		for(Node findNode: europeGraph.getVertice()){
			if(findNode.getCity_Name().equals(city_name)){
				return findNode;
			}
		}
		
		return null;
	}
	
	public void recover_route(String city_name){
		Node beginNode = find_node(city_name);
		for(Node node: europeGraph.getVertice()){
			for(Service service : node.getEdge()){
				double fee = service.getFee();
				City city = find_city(city_name);
				double current_minFee = city.getTotal_price();
				calculate()
			}
		}
	}
}
