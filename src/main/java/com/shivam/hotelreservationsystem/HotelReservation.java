package com.shivam.hotelreservationsystem;
import java.time.LocalDate;
import java.time.temporal.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Map.Entry;
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
		int weekDayRate, weekEndRate;
		System.out.println("Enter the Hotel Name:");
		hotelName = input.nextLine();
		System.out.println("Enter the WeekDay Rates For Regular Customers(in $)");
		weekDayRate = input.nextInt();
		input.nextLine();
		System.out.println("Enter the Weekend Rates For Regular Customers(in $)");
		weekEndRate = input.nextInt();
		input.nextLine();
		System.out.println("Enter the Hotel Ratings:");
		int ratings = input.nextInt();
		input.nextLine();
		Hotel hotel = new Hotel(hotelName, weekDayRate, weekEndRate, ratings,  "Regular");
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
	/**
	 * Usecase 4
	 * Modification of the function to take into account different rates for weekend and weekdays
	 * @param input
	 * @return
	 */
	public ArrayList<Hotel> findCheapestHotel(Scanner input) {
		LocalDate[] localDate = new LocalDate[2];
		String inputDate = "";
		inputDate = input.nextLine();
		//Split the string to get the dates
		String[] dates = inputDate.split(",");
		for(int iteration = 0; iteration<=1 ; iteration++) {
			//Convert dates to standard format
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy", Locale.ENGLISH);
			LocalDate date = LocalDate.parse(dates[iteration], formatter);
			localDate[iteration] = date;
		}	
		//start date
		LocalDate start = localDate[0];
		//end date
		LocalDate end = localDate[1];
		long totalDays = ChronoUnit.DAYS.between(start, end);
		totalDays = totalDays + 1;
		long totalWeekEndDays = getTotalWeekEndDays(start, end);
		long totalWeekDays = totalDays - totalWeekEndDays;
		ArrayList<Hotel> cheapestHotels = this.findCheapest(totalWeekDays, totalWeekEndDays);
		return cheapestHotels;
	}
	public ArrayList<Hotel> findCheapest(long totalWeekDays, long totalWeekEndDays) {
		int minimum = 0;
		ArrayList<Hotel> cheapestHotels = new ArrayList<Hotel>();
		HashMap<Hotel,Integer> hotelMap = new HashMap<Hotel,Integer>();
		for(Hotel hotel : hotelList) {
			int totalRate = hotel.getWeekDayRate() * (int)totalWeekDays + hotel.getWeekEndRate() * (int)totalWeekEndDays;
			hotelMap.put(hotel, totalRate);
		}
		minimum = Collections.min(hotelMap.values());
		for(Map.Entry<Hotel, Integer> entry : hotelMap.entrySet()) {
			if(entry.getValue() == minimum) {
				cheapestHotels.add(entry.getKey());
			}
		}
		for(Hotel hotel : cheapestHotels) {
			System.out.println(hotel.getHotelName() + " Total Rate : " + minimum);
		}
		return cheapestHotels;
	}
	public long getTotalWeekEndDays(LocalDate start, LocalDate end) {
		long totalWeekEndDays = 0;
		LocalDate next = start.minusDays(1);
		//iterate from start date to end date
		while ((next = next.plusDays(1)).isBefore(end.plusDays(1))) {
			if(next.getDayOfWeek().toString().equals("SATURDAY") || next.getDayOfWeek().toString().equals("SUNDAY")) {
				totalWeekEndDays++;
			}
		}
		return totalWeekEndDays;	
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Hotel Reservation Program");
		input.close();
	}
}
