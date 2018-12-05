package travelEurope;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;

public class RailSystem {
	private EuropeGraph europeGraph = new EuropeGraph();
	private ArrayList<Double> minFee = new ArrayList<>();
	
	public double claculateFee(){
		return 0;
	}
	
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
		double totalPrice = 0;
		Node beginNode = find_node(from_cityName);
		find_city(beginNode.getCity_Name()).setVisit(true);
		ArrayList<City> cityList = new ArrayList<>();
		//对该起始定点的每一个连接进行初始化
		for(Service service : beginNode.getEdge()){
			double fee = service.getFee();
			String edgeName = service.getCity();
			City city = find_city(edgeName);
			city.setTotal_price(fee);
			city.setForm_city(from_cityName);
			cityList.add(city);
		}
		
		//在该节点的可到达城市中选出距离最短的城市
		City minPriceCity = cityList.get(0);
		double minPrice = minPriceCity.getTotal_price();
		totalPrice += minPrice;
		minPriceCity.setVisit(true);
		for(int i = 1; i < cityList.size(); i++) {
			if(cityList.get(i).getTotal_price() < minPrice) {
				minPriceCity = cityList.get(i);
			}
		}
		
		int times = 0;
		while(times < europeGraph.getCityList().size() - 1) {
			boolean status = true;	//判断CityList是否被修改过
			beginNode = find_node(minPriceCity.getCity_name());
			from_cityName = minPriceCity.getCity_name();

			for(Service service : beginNode.getEdge()){
				double fee = service.getFee();
				String edgeName = service.getCity();
				City city = find_city(edgeName);
				if((fee + minPriceCity.getTotal_price()) < city.getTotal_price() && !city.isVisit()) {
					if(status == true) {
						cityList.clear();
						status = false;
					}
					city.setTotal_price(fee + minPriceCity.getTotal_price());
					city.setForm_city(from_cityName);
					cityList.add(city);
				}
			}
			
			minPriceCity = cityList.get(0);
			minPrice = minPriceCity.getTotal_price();
			from_cityName = minPriceCity.getCity_name();

			
			for(int i = 1; i < cityList.size(); i++) {
				if(cityList.get(i).getTotal_price() < minPrice && !minPriceCity.isVisit()) {
					minPriceCity = cityList.get(i);
				}
			}
			
			status = true;
			times++;
		}
		System.out.println(totalPrice);
	}
	
	
}
