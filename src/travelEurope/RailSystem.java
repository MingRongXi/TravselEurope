package travelEurope;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ����ʵ�����ҳ�ָ����㵽���г��е����·�ѵ�·��
 * @author dell
 *
 */
public class RailSystem {
	private EuropeGraph europeGraph = new EuropeGraph();
	//from_cityName�Ǵ����������·��ʱ��δ���������ľ��������̵Ķ��㣬����ʼ��λ���
	private String from_cityName;
	//�յ�
	private String destination;
	//���
	private String store_IntialFromCityName;
	
	
	public RailSystem() throws FileNotFoundException {
		europeGraph.loadService();
		get_From_To();
		recover_route();		
	}
	

	//���������յ�
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
	
	//ͨ���������ҳ��ó���
	private City find_city(String cityName){
		for(City find_city : europeGraph.getCityList()){
			if(cityName.equals(find_city.getCity_name())){
				return find_city;
			}
		}
		
		return null;
	}
	
	//ͨ���ڵ����ҳ�һ���ڵ�
	private Node find_node(String begin_cityName){
		for(Node findNode: europeGraph.getVertice()){
			if(findNode.getCity_Name().equals(begin_cityName)){
				return findNode;
			}
		}
		
		return null;
	}
	
	//�����·��
	private void recover_route(){
		Node beginNode = find_node(from_cityName);
		City from_city = find_city(beginNode.getCity_Name());
		from_city.setTotal_price(0);
		//�Ը�����ÿһ�����ӽ��г�ʼ��
		for(Service service : beginNode.getEdge()){
			double fee = service.getFee();
			String edgeName = service.getCity();
			City city = find_city(edgeName);
			city.setTotal_price(fee);
			city.setForm_city(from_cityName);
			city.setTotal_distance(service.getDistance());
		}
				
		//�����г��ж�����һ��
		int times = 0;
		while(times < europeGraph.getCityList().size()) {
			//�ҵ�·�����ٵĶ���
			City minPriceCity = findMinPriceCity();
			from_cityName = minPriceCity.getCity_name();
			beginNode = find_node(from_cityName);
			
			for (Service service : beginNode.getEdge()) {
				double fee = service.getFee();
				String edgeName = service.getCity();
				City city = find_city(edgeName);
				//�����·�����ٵĶ���Ϊa����ǰ�ڵ�Ϊb�������a������·��+a��b��·�ѣ�< b������·�ѣ����޸�b������·��
				if ((fee + minPriceCity.getTotal_price()) < city.getTotal_price() && !city.isVisit()) {
					city.setTotal_price(fee + minPriceCity.getTotal_price());
					city.setForm_city(from_cityName);
					//�޸ľ��룬ע�����벻һ������̾���
					city.setTotal_distance(service.getDistance() + minPriceCity.getTotal_distance());
				}
			}
			
			times++;
		}
	}
	
	private City findMinPriceCity(){
		//�ҵ����������˵Ķ���
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
	 * ������·��
	 */
	public void output() {
		ArrayList<City> pathList = new ArrayList<>();
		City pathCity = find_city(destination);
		String path = "";
		//ͨ���յ��from_city�����ҵ�����ǰ�����㣬��Щ���㼴Ϊ·������·������˳����У�Ȼ�������
		while (pathCity != null) {
			pathList.add(pathCity);
			String from_city = pathCity.getForm_city();
			if(from_city != null) {
				pathCity = find_city(from_city);
			}
			//��fromCityΪnullʱ�����������
			else {
				pathCity = null;
			}
		}
		
		//��·�������ַ�����
		for(int i = pathList.size() - 1; i >= 0; i--) {
			path = path  + " " + pathList.get(i).getCity_name() + " to";
		}
		
		path = "From " + path.substring(1, path.length() -3) + ".";
		System.out.println("The cheapest route from " + store_IntialFromCityName + " to " + destination  + "\n" +
						"costs " + find_city(destination).getTotal_price() + " euros and spans " + find_city(destination).getTotal_distance() + " kilometers.");
		System.out.println(path);
	}
}
