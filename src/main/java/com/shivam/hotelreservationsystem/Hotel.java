package com.shivam.hotelreservationsystem;

public class Hotel {
	private String hotelName;
	public int rate;
	public String customerType;
	public Hotel(String hotelName, int rate, String customerType) {
		this.hotelName = hotelName;
		this.rate = rate;
		this.customerType = customerType;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public int getRate() {
		return rate;
	}	
	public void setRate(int rate) {
		this.rate = rate;
	}
	@Override
	public String toString() {
		String hotel = "Hotel : " + this.getHotelName() + "\n" + "Customer Type : " +this.getCustomerType() + "\n" + "Rate : " +this.getRate() + "$\n";
		return hotel;
	}
}
