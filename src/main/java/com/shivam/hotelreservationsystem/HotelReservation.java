package com.shivam.hotelreservationsystem;
import java.time.LocalDate;
import java.time.temporal.*;
import java.time.format.DateTimeFormatter;
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
		String hotelName = "";
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
	public Hotel findCheapestHotel(Scanner input) {
		LocalDate[] localDate = new LocalDate[2];
		String inputDate = "";
		inputDate = input.nextLine();
		String[] dates = inputDate.split(",");
		for(int iteration = 0; iteration<=1 ; iteration++) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy", Locale.ENGLISH);
			LocalDate date = LocalDate.parse(dates[iteration], formatter);
			localDate[iteration] = date;
		}	
		long totalDays = ChronoUnit.DAYS.between(localDate[0], localDate[1]);
		totalDays = totalDays + 1;
		Hotel cheapestHotel = this.findCheapest();
		int totalRate = cheapestHotel.getRate() * (int)totalDays;
		System.out.println(cheapestHotel.getHotelName()+ ", Total Rates : $"+totalRate);
		return cheapestHotel;
	}
	public Hotel findCheapest() {
		int minimum = hotelList.get(0).getRate();
		for(Hotel hotel : hotelList) {
			if(hotel.getRate()<minimum) {
				minimum = hotel.getRate();
			}
		}
		for(Hotel hotel : hotelList) {
			if(minimum == hotel.getRate()) {
				return hotel;
			}
		}
		return null;
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Hotel Reservation Program");
		HotelReservation reservation = new HotelReservation();
		reservation.findCheapestHotel(input);
	}
}
