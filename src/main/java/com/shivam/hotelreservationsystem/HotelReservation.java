package com.shivam.hotelreservationsystem;
import java.util.*;
import java.util.Scanner;

public class HotelReservation {
	public ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
	/**
	 * Usecase 1
	 * Function adds Hotel to the Hotel Reservation System
	 * @param input
	 * @return
	 */
	public boolean addHotel(Scanner input) {
		String hotelName ="";
		int rate;
		System.out.println("Enter the Hotel Name:");
		hotelName = input.nextLine();
		System.out.println("Enter the Rates For Regular Customers(in $)");
		rate = input.nextInt();
		input.nextLine();
		Hotel hotel = new Hotel(hotelName, rate, "Regular");
		boolean added = hotelList.add(hotel);
		return added;
	}
	/**
	 * Get the list of hotels
	 * @return
	 */
	public ArrayList<Hotel> getHotelList() {
		return hotelList;
	}
	public void setHotelList(ArrayList<Hotel> hotelList) {
		this.hotelList = hotelList;
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Hotel Reservation Program");
		HotelReservation reservation = new HotelReservation();
		reservation.addHotel(input);
		System.out.println(reservation.getHotelList());
	}
}
