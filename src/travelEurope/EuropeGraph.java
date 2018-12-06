package travelEurope;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * 该类将数据项从文件里读出来生成一个图
 * @author dell
 *
 */
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
	
	//将图存在一个vertices的顺序表里，每一个vertices是一个Node,Node里一的一个数据项用来存该城市的城市名，另一个数据项用来存该城市能到达的城市链表
	private ArrayList<Node> vertices = new ArrayList<>();
	//cityList用来存所有城市
	private ArrayList<City> cityList = new ArrayList<>();
	
	public EuropeGraph(){
		
	}
	
	/**
	 * 该方法用来读取文件中的内容，将其存在一个图里
	 * @throws FileNotFoundException
	 */
	public void loadService() throws FileNotFoundException{
		//读文件操作
		File file = new File("services.txt");
		Scanner input = new Scanner(file);
		
		//将文件中的数据存入图中的时候会有第一个数据和其他数据会有差别，因为文件中的连续几行可能都是一个城市的信息
		//所以先无条件将文件中的第一行加入图中作为第一个节点，从下一行开始就判断是添加节点还是添加边
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
			vertices.add(node);
		}
		
		while(input.hasNextLine()){
			String[] information = input.nextLine().split(" ");
			String city_name = information[0];
			String to_city = information[1];
			double fee = Double.parseDouble(information[2]);
			double distance = Double.parseDouble(information[3]);
			
			Node lastNode = vertices.get(vertices.size() - 1);
			String stored_city = lastNode.getCity_Name();
			//如果该节点已存入图中，则添加边，否则添加节点
			if(city_name.equals(stored_city)){
				Service service = new Service(to_city, distance, fee);
				lastNode.getEdge().add(service);
			}
			else{
				City city = new City(city_name, null, 10000, false);
				cityList.add(city);
				
				Node node = new Node();
				Service service = new Service(to_city, distance, fee);
				node.setCity_Name(city_name);
				node.getEdge().add(service);
				vertices.add(node);
			}
		}
	}

	public ArrayList<Node> getVertice() {
		return vertices;
	}

	public ArrayList<City> getCityList() {
		return cityList;
	}
}
