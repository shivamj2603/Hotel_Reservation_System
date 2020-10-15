package com.shivam.hotelreservationsystem;
import java.time.LocalDate;
import java.time.temporal.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Map.Entry;
import java.util.Scanner;

public class HotelReservation {
	static long totalDays,totalWeekDays,totalWeekEndDays;
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
	public void getInput(Scanner input) {
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
		LocalDate start = localDate[0];
		LocalDate end = localDate[1];
		totalDays = ChronoUnit.DAYS.between(start, end);
		totalDays = totalDays + 1;
		totalWeekEndDays = getTotalWeekEndDays(start, end); 
		totalWeekDays = totalDays - totalWeekEndDays;
	}
	public int getTotalWeekEndDays(LocalDate start, LocalDate end) {
		long weekEndDays = 0;
		LocalDate next = start.minusDays(1);
		//iterate from start date to end date
		while ((next = next.plusDays(1)).isBefore(end.plusDays(1))) {
			if(next.getDayOfWeek().toString().equals("SATURDAY") || next.getDayOfWeek().toString().equals("SUNDAY")) {
				totalWeekEndDays++;
			}
		}
		return (int)totalWeekEndDays;	
	}
	/**
	 * Usecase 4
	 * Modification of the function to take into account different rates for weekend and weekdays
	 * @param input
	 * @return
	 */
	public ArrayList<Hotel> findCheapestHotel(Scanner input) {
		ArrayList<Hotel> cheapestHotels = this.findCheapest();
		return cheapestHotels;
	}
	public ArrayList<Hotel> findCheapest() {
		int minimumRate = 0;
		ArrayList<Hotel> cheapestHotels = new ArrayList<Hotel>();
		HashMap<Hotel,Integer> hotelMap = new HashMap<Hotel,Integer>();
		HashMap<Hotel,Integer> ratingMap = new HashMap<Hotel,Integer>();
		for(Hotel hotel : hotelList) {
			int totalRate = hotel.getWeekDayRate() * (int)totalWeekDays + hotel.getWeekEndRate() * (int)totalWeekEndDays;
			hotelMap.put(hotel, totalRate);
			ratingMap.put(hotel, hotel.getHotelRatings());
		}
		minimumRate = Collections.min(hotelMap.values());
		
		for(Map.Entry<Hotel, Integer> entry : hotelMap.entrySet()) {
			if(entry.getValue() == minimumRate) {
				cheapestHotels.add(entry.getKey());
			}
		}
		int maximumRating = Collections.max(ratingMap.values());
		for(Hotel hotel : cheapestHotels) {
			if(hotel.getHotelRatings() != maximumRating) {
				cheapestHotels.remove(hotel);
				continue;
			}
			System.out.println(hotel.getHotelName() + "Ratings : " + hotel.getHotelRatings() +" Total Rate : " + minimumRate);
		}
		return cheapestHotels;
	}
	/**
	 *UseCase 7
	 *Function to find Best Rated Hotels
	 * @return
	 */
	public ArrayList<Hotel> findBestRatedHotels(Scanner input){
		getInput(input);
		ArrayList<Hotel> bestRatedHotels = new ArrayList<Hotel>();
		HashMap<Hotel,Integer> ratingMap = new HashMap<Hotel,Integer>();
		for(Hotel hotel : hotelList) {
			ratingMap.put(hotel, hotel.getHotelRatings());
		}
		int maximumRating = Collections.max(ratingMap.values());
		for(Map.Entry<Hotel, Integer> entry : ratingMap.entrySet()) {
			if(entry.getValue() == maximumRating) {
				bestRatedHotels.add(entry.getKey());
				System.out.println("Hotel Name : " +entry.getKey().getHotelName() + "Ratings : " + entry.getKey().getHotelRatings() +" Total Rate : " + ((int) totalWeekDays * entry.getKey().getWeekDayRate() + (int)totalWeekEndDays * entry.getKey().getWeekEndRate()));
			}
		}
		for(Hotel hotel : bestRatedHotels) {
			System.out.println(hotel);
		}
		return bestRatedHotels;	
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Hotel Reservation Program");
		input.close();
	}
}