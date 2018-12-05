package travelEurope;

public class City {
	private String city_name;
	private String form_city;
	private double total_price;
	private boolean visit;
	


	public City(String city_name, String form_city, double total_price, boolean visit) {
		super();
		this.city_name = city_name;
		this.form_city = form_city;
		this.total_price = total_price;
		this.visit = visit;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getForm_city() {
		return form_city;
	}

	public void setForm_city(String form_city) {
		this.form_city = form_city;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	public boolean isVisit() {
		return visit;
	}

	public void setVisit(boolean visit) {
		this.visit = visit;
	}
	
}
