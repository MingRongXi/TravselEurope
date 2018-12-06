package travelEurope;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * ���ཫ��������ļ������������һ��ͼ
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
	
	//��ͼ����һ��vertices��˳����ÿһ��vertices��һ��Node,Node��һ��һ��������������ó��еĳ���������һ��������������ó����ܵ���ĳ�������
	private ArrayList<Node> vertices = new ArrayList<>();
	//cityList���������г���
	private ArrayList<City> cityList = new ArrayList<>();
	
	public EuropeGraph(){
		
	}
	
	/**
	 * �÷���������ȡ�ļ��е����ݣ��������һ��ͼ��
	 * @throws FileNotFoundException
	 */
	public void loadService() throws FileNotFoundException{
		//���ļ�����
		File file = new File("services.txt");
		Scanner input = new Scanner(file);
		
		//���ļ��е����ݴ���ͼ�е�ʱ����е�һ�����ݺ��������ݻ��в����Ϊ�ļ��е��������п��ܶ���һ�����е���Ϣ
		//���������������ļ��еĵ�һ�м���ͼ����Ϊ��һ���ڵ㣬����һ�п�ʼ���ж�����ӽڵ㻹����ӱ�
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
			//����ýڵ��Ѵ���ͼ�У�����ӱߣ�������ӽڵ�
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
