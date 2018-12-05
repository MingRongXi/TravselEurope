package travelEurope;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class EuropeGraph {
//	private static class Node{
//		private String city_Name;
//		private LinkedList<Service> edge = new LinkedList<Service>();
//		
////		public Node(String city_Name, LinkedList<Service> edge) {
////			super();
////			this.city_Name = city_Name;
////			this.edge = edge;
////		}
//		public Node(){
//			
//		}
		
		
	//}
	private ArrayList<Node> vertice = new ArrayList<>();
	private ArrayList<City> cityList = new ArrayList<>();
	
	public EuropeGraph(){
		
	}
	
	public void loadService() throws FileNotFoundException{
		File file = new File("services.txt");
		Scanner input = new Scanner(file);
		
		if(input.hasNextLine()){
			String[] information = input.nextLine().split(" ");
			String city_name = information[0];
			String to_city = information[1];
			double fee = Double.parseDouble(information[2]);
			double distance = Double.parseDouble(information[3]);
			
			City city = new City(city_name, null, 10000, false);
			cityList.add(city);
			
			Node node = new Node();
			Service service = new Service(to_city, fee, distance);
			//node.city_Name = city_name;
			//node.edge.add(service);
			node.setCity_Name(city_name);
			node.getEdge().add(service);
			vertice.add(node);
			
			
		}
		while(input.hasNextLine()){
			String[] information = input.nextLine().split(" ");
			String city_name = information[0];
			String to_city = information[1];
			double fee = Double.parseDouble(information[2]);
			double distance = Double.parseDouble(information[3]);
			
			Node lastNode = vertice.get(vertice.size() - 1);
		//	String stored_city = lastNode.city_Name;
			String stored_city = lastNode.getCity_Name();
			if(city_name.equals(stored_city)){
				Service service = new Service(to_city, distance, fee);
				lastNode.getEdge().add(service);
			}
			else{
				City city = new City(city_name, null, 10000, false);
				cityList.add(city);
				
				Node node = new Node();
				Service service = new Service(to_city, distance, fee);
//				node.city_Name = city_name;
//				node.edge.add(service);
				node.setCity_Name(city_name);
				node.getEdge().add(service);
				vertice.add(node);
			}
		}
	}

	public ArrayList<Node> getVertice() {
		return vertice;
	}

	public ArrayList<City> getCityList() {
		return cityList;
	}
	
	
	
//	public void test1(){
//		for(Node node: vertice){
//			System.out.print(node.city_Name + "  ");
//			for(Service service: node.edge){
//				System.out.print(service.getCity() + "  " + service.getFee() + "  " + service.getDistance() + " | ");
//			}
//			System.out.println();
//		}
//	}
//	
//	public void test2(){
//		for(City city: cityList){
//			System.out.println(city.getCity_name() + " " + city.getForm_city() + " " + city.getTotal_price());
//		}
//	}
}
