package travelEurope;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 该类实现了找出指定起点到所有城市的最短路费的路径
 * @author dell
 *
 */
public class RailSystem {
	private EuropeGraph europeGraph = new EuropeGraph();
	//from_cityName是代表在找最短路径时，未被遍历过的距离起点最短的顶点，被初始化位起点
	private String from_cityName;
	//终点
	private String destination;
	//起点
	private String store_IntialFromCityName;
	
	
	public RailSystem() throws FileNotFoundException {
		europeGraph.loadService();
		get_From_To();
		recover_route();		
	}
	

	//输入起点和终点
	private void get_From_To() {
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter from_city:  ");
		String from = input.next();
		System.out.print("Please enter destination:  ");
		String to = input.next();
		this.from_cityName = from;
		this.destination = to;
		this.store_IntialFromCityName = from;
	}
	
	//通过城市名找出该城市
	private City find_city(String cityName){
		for(City find_city : europeGraph.getCityList()){
			if(cityName.equals(find_city.getCity_name())){
				return find_city;
			}
		}
		
		return null;
	}
	
	//通过节点名找出一个节点
	private Node find_node(String begin_cityName){
		for(Node findNode: europeGraph.getVertice()){
			if(findNode.getCity_Name().equals(begin_cityName)){
				return findNode;
			}
		}
		
		return null;
	}
	
	//找最短路径
	private void recover_route(){
		Node beginNode = find_node(from_cityName);
		City from_city = find_city(beginNode.getCity_Name());
		from_city.setTotal_price(0);
		//对该起点的每一个连接进行初始化
		for(Service service : beginNode.getEdge()){
			double fee = service.getFee();
			String edgeName = service.getCity();
			City city = find_city(edgeName);
			city.setTotal_price(fee);
			city.setForm_city(from_cityName);
			city.setTotal_distance(service.getDistance());
		}
				
		//将所有城市都遍历一遍
		int times = 0;
		while(times < europeGraph.getCityList().size()) {
			//找到路费最少的顶点
			City minPriceCity = findMinPriceCity();
			from_cityName = minPriceCity.getCity_name();
			beginNode = find_node(from_cityName);
			
			for (Service service : beginNode.getEdge()) {
				double fee = service.getFee();
				String edgeName = service.getCity();
				City city = find_city(edgeName);
				//到起点路费最少的顶点为a，当前节点为b，如果（a到起点的路费+a到b的路费）< b到起点的路费，则修改b到起点的路费
				if ((fee + minPriceCity.getTotal_price()) < city.getTotal_price() && !city.isVisit()) {
					city.setTotal_price(fee + minPriceCity.getTotal_price());
					city.setForm_city(from_cityName);
					//修改距离，注：距离不一定是最短距离
					city.setTotal_distance(service.getDistance() + minPriceCity.getTotal_distance());
				}
			}
			
			times++;
		}
	}
	
	private City findMinPriceCity(){
		//找到到起点最便宜的顶点
		City minPriceCity = new City(Double.MAX_VALUE);
		double minPrice = minPriceCity.getTotal_price();
		for(int i = 0; i < europeGraph.getCityList().size(); i++) {
			City currentCity = europeGraph.getCityList().get(i);
			if(currentCity.getTotal_price() < minPrice && currentCity.isVisit() == false) {
				minPriceCity = europeGraph.getCityList().get(i);
				minPrice = minPriceCity.getTotal_price();
			}
		}
		
		minPriceCity.setVisit(true);
		return minPriceCity;
	}
	
	/*
	 * 输出最短路径
	 */
	public void output() {
		ArrayList<City> pathList = new ArrayList<>();
		City pathCity = find_city(destination);
		String path = "";
		//通过终点的from_city属性找到它的前驱顶点，这些顶点即为路径，将路径存入顺序表中，然后再输出
		while (pathCity != null) {
			pathList.add(pathCity);
			String from_city = pathCity.getForm_city();
			if(from_city != null) {
				pathCity = find_city(from_city);
			}
			//当fromCity为null时，它就是起点
			else {
				pathCity = null;
			}
		}
		
		//将路径存入字符串里
		for(int i = pathList.size() - 1; i >= 0; i--) {
			path = path  + " " + pathList.get(i).getCity_name() + " to";
		}
		
		path = "From " + path.substring(1, path.length() -3) + ".";
		System.out.println("The cheapest route from " + store_IntialFromCityName + " to " + destination  + "\n" +
						"costs " + find_city(destination).getTotal_price() + " euros and spans " + find_city(destination).getTotal_distance() + " kilometers.");
		System.out.println(path);
	}
}
