package travelEurope;

public class Service {
	private String city;
	private double distance;
	private double fee;
	
	public Service(String city, double distance, double fee) {
		super();
		this.city = city;
		this.distance = distance;
		this.fee = fee;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}
	
	
	
}
