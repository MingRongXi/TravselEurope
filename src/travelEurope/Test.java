package travelEurope;

import java.io.FileNotFoundException;

public class Test {
	public static void main(String[] args) throws FileNotFoundException{
		EuropeGraph graph = new EuropeGraph();
		graph.loadService();
//		graph.test();
		//graph.test2();
		RailSystem rail = new RailSystem();
		rail.recover_route("Rome");
	}
}
