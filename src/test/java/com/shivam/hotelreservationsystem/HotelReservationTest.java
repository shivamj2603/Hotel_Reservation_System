package com.shivam.hotelreservationsystem;

import java.util.Scanner;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HotelReservationTest {
	/**
	 * TestCase 1
	 * Test for adding the hotels to the system
	 */
	@Test
	public void whenHotelAdded_toReservationSystem_shouldReturnSizeThree() {
		Scanner input = new Scanner(System.in);
		HotelReservation reservation = new HotelReservation();
		reservation.addHotel(input);
		assertEquals(3, reservation.hotelList.size());
	}
	/**
	 * TestCase 2
	 * Test for finding cheapest Hotels for variable rates
	 * 
	 */
	@Test
	public void whenCalled_findCheapestHotelFunction_shouldReturnSizeAs2() {
		Scanner input = new Scanner(System.in);
		HotelReservation reservation = new HotelReservation();
		reservation.addHotel(input);
		Hotel hotel1 = new Hotel("LakeWood", 110, 90, 4, "Regular");
		Hotel hotel2 = new Hotel("BridgeWood", 150, 50, 3, "Regular");
		ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
		hotelList.add(hotel1);
		hotelList.add(hotel2);
		assertEquals(hotelList.size(), reservation.findCheapestHotels().size());
	}
	/**
	 * TestCase 3
	 * 
	 */
	@Test
	public void whenHotelAdded_toReservationSystem_withWeekdayAndWeekendRates_shouldReturnSizeAs3() {
		Scanner input = new Scanner(System.in);
		HotelReservation reservation = new HotelReservation();
		reservation.addHotel(input);
		assertEquals(3, reservation.hotelList.size());
	}
	/**
	 * TestCase 5
	 */
	@Test
	public void whenHotelAdded_toReservationSystem_withRatings_shouldReturnSizeAs3() {
		Scanner input = new Scanner(System.in);
		HotelReservation reservation = new HotelReservation();
		reservation.addHotel(input);
		assertEquals(3,reservation.hotelList.size());
	}
	/**
	 * TestCase 6
	 */
	@Test	
	public void whenCalled_findCheapestHotelFunction_withGivenRating_shouldReturnCheapestHotel() {
		Scanner input = new Scanner(System.in);
		HotelReservation reservation = new HotelReservation();
		reservation.addHotel(input);
		Hotel hotel = new Hotel("LakeWood", 110, 90, 4, "Regular");
		ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
		hotelList.add(hotel);
		assertEquals(hotelList.get(0).getHotelName(), reservation.findCheapestHotels().get(0).getHotelName());
	}
	/**
	 * Testcase 7
	 */
	@Test 
	public void whenCalled_findBestRatedHotelFunction_withGivenRating_shouldReturnBestRatedHotel() {
		Scanner input = new Scanner(System.in);
		HotelReservation reservation = new HotelReservation();
		reservation.addHotel(input);
		Hotel hotel = new Hotel("RidgeWood", 220, 150, 5, "Regular");
		ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
		hotelList.add(hotel);
		assertEquals(hotelList.get(0).getHotelName(), reservation.findBestRatedHotels(reservation.hotelList).get(0).getHotelName());
	}
	@Test
	public void whenCalled_findCheapestBestRatedHotel_shouldReturnLakeWood() {
		Scanner input = new Scanner(System.in);
		HotelReservation reservation = new HotelReservation();
		reservation.addHotel(input);
		Hotel hotel = new Hotel("LakeWood", 110, 90, 4, "Regular");
		assertEquals(hotel.getHotelName(), reservation.findCheapestBestRatedHotels().get(0).getHotelName());
	}
	/**
	 * Testcase 11
	 */
	@Test
	public void whenCalled_findCheapestBestRatedHotel_forRewardCustomer_shouldReturnRidgeWood() {
		Scanner input = new Scanner(System.in);
		HotelReservation reservation = new HotelReservation();
		reservation.addHotel(input);
		assertEquals("RidgeWood", reservation.findCheapestBestRatedHotels().get(0).getHotelName());
	}
	/**
	 * TestCase 12
	 */
	@Test
	public void whenCalled_findCheapestBestRatedHotel_forRegularCustomer_shouldReturnLakeWood() {
		Scanner input = new Scanner(System.in);
		HotelReservation reservation = new HotelReservation();
		reservation.addHotel(input);
		assertEquals("LakeWood", reservation.findCheapestBestRatedHotels().get(0).getHotelName());
	}
}
