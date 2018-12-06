package travelEurope;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;

public class RailSystem {
	private EuropeGraph europeGraph = new EuropeGraph();

	
	public RailSystem() throws FileNotFoundException {
		europeGraph.loadService();
	}
	
	
	public City find_city(String from_cityName){
		for(City find_city : europeGraph.getCityList()){
			if(from_cityName.equals(find_city.getCity_name())){
				return find_city;
			}
		}
		
		return null;
	}
	
	public Node find_node(String from_cityName){
		for(Node findNode: europeGraph.getVertice()){
			if(findNode.getCity_Name().equals(from_cityName)){
				return findNode;
			}
		}
		
		return null;
	}
	
	public void recover_route(String from_cityName){
		Node beginNode = find_node(from_cityName);
		find_city(beginNode.getCity_Name()).setVisit(true);
		//对该起始定点的每一个连接进行初始化
		for(Service service : beginNode.getEdge()){
			double fee = service.getFee();
			String edgeName = service.getCity();
			City city = find_city(edgeName);
			city.setTotal_price(fee);
			city.setForm_city(from_cityName);
		}
		
//		City minPriceCity = findMinPriceCity();
		
		int times = 0;
		while(times < europeGraph.getCityList().size() - 1) {
			City minPriceCity = findMinPriceCity();
			beginNode = find_node(minPriceCity.getCity_name());
			from_cityName = minPriceCity.getCity_name();

			for(Service service : beginNode.getEdge()){
				double fee = service.getFee();
				String edgeName = service.getCity();
				City city = find_city(edgeName);
				if((fee + minPriceCity.getTotal_price()) < city.getTotal_price() && !city.isVisit()) {

					city.setTotal_price(fee + minPriceCity.getTotal_price());
					city.setForm_city(from_cityName);
				}
			}
			
			//minPriceCity = findMinPriceCity();
			times++;
		}
	}
	
	public City findMinPriceCity(){
		//找到与起点最便宜的顶点
		City minPriceCity = new City(10000);
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
}
