package com.shivam.hotelreservationsystem;
import java.time.LocalDate;
import java.time.temporal.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.time.DateTimeException;

public class HotelReservation {
	static long totalDays,totalWeekDays,totalWeekEndDays;
	static String customerType;
	public ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
	/**
	 * Usecase 1
	 * Function adds Hotel to the Hotel Reservation System
	 * @param input
	 * @return
	 */
	public void addHotel(Scanner input) {
		Hotel hotel1 = null;
		Hotel hotel2 = null;
		Hotel hotel3 = null;
		try {
			getInput(input);
			if(customerType.equals("Regular")) {
				hotel1 = new Hotel("LakeWood", 110, 90, 4, "Regular");
				hotel2 = new Hotel("BridgeWood", 150, 50, 3, "Regular");
				hotel3 = new Hotel("RidgeWood" , 220, 150, 5, "Regular");
			}
			else if(customerType.equals("Reward")){
				hotel1 = new Hotel("LakeWood", 80, 80, 4, "Reward");
				hotel2 = new Hotel("BridgeWood", 110, 50, 3, "Reward");
				hotel3 = new Hotel("RidgeWood" , 100, 40, 5, "Reward");
			}
			else {
				throw new InvalidEntryException("Invalid Customer Type");
			}
		}
		catch(InvalidEntryException exception) {
			System.out.println(exception);
		}
		hotelList.add(hotel1);
		hotelList.add(hotel2);
		hotelList.add(hotel3);
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
		String[] dates = inputDate.split(":|,");
		customerType = dates[0];
		for(int iteration = 1; iteration<=2 ; iteration++) {
			//Convert dates to standard format
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy", Locale.ENGLISH);
				LocalDate date = LocalDate.parse(dates[iteration], formatter);
				localDate[iteration-1] = date;
			}
			catch(DateTimeException exception) {
				System.out.println("Invalid Date Entry");
				getInput(input);
			}
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
	public ArrayList<Hotel> findCheapestHotels() {
		HashMap<Hotel,Integer> hotelMap = (HashMap<Hotel, Integer>)hotelList.stream().collect(Collectors.toMap(hotel -> hotel, hotel -> (int)(hotel.getWeekDayRate() * totalWeekDays + hotel.getWeekEndRate() * totalWeekEndDays)));
		int minimumRate = hotelMap.values().stream().min(Integer :: compare).get() ;
		ArrayList<Hotel> cheapestHotel = (ArrayList<Hotel>)hotelMap.entrySet().stream().filter(data -> data.getValue() == minimumRate).map(data -> data.getKey()).collect(Collectors.toList());
		return cheapestHotel;
	}
	/**
	 * Finds Best Rated Hotels
	 * @param hotels
	 * @return
	 */
	public ArrayList<Hotel> findBestRatedHotels(ArrayList<Hotel> hotels) {
		HashMap<Hotel,Integer> ratingMap = (HashMap<Hotel, Integer>)hotels.stream().collect(Collectors.toMap(hotel -> hotel, hotel -> hotel.getHotelRatings()));
		int maximumRate = ratingMap.values().stream().max(Integer :: compare).get() ;
		ArrayList<Hotel> bestRated = (ArrayList<Hotel>)ratingMap.entrySet().stream().filter(data -> data.getValue() == maximumRate).map(data -> data.getKey()).collect(Collectors.toList());
		return bestRated;
	}
	/**
	 * Find Cheapest Best Rated Hotels
	 * @return
	 */
	public ArrayList<Hotel> findCheapestBestRatedHotels(){
		ArrayList<Hotel> cheapestHotel = findCheapestHotels();
		ArrayList<Hotel> bestRatedHotel = findBestRatedHotels(cheapestHotel);
		return bestRatedHotel;
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Hotel Reservation Program");
		HotelReservation hotel = new HotelReservation();
		hotel.addHotel(input);
		ArrayList<Hotel> cheapest = hotel.findCheapestHotels();
		cheapest.forEach(System.out :: println);
		ArrayList<Hotel> bestRated = hotel.findBestRatedHotels(hotel.hotelList);
		bestRated.forEach(System.out :: println);
		ArrayList<Hotel> cheapestBestRated = hotel.findCheapestBestRatedHotels();
		cheapestBestRated.forEach(System.out :: println);
		input.close();
	}
}
