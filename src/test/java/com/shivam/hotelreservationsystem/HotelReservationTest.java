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
	public void whenHotelAdded_toReservationSystem_shouldReturnSizeOne() {
		Scanner input = new Scanner(System.in);
		HotelReservation reservation = new HotelReservation();
		reservation.addHotel(input);
		assertEquals(1, reservation.hotelList.size());
	}
	/**
	 * TestCase 4
	 * Test for finding cheapest Hotels for variable rates
	 * 
	 */
	@Test
	public void whenCalled_findCheapestHotelFunction_shouldReturnCheapestHotel() {
		Scanner input = new Scanner(System.in);
		HotelReservation reservation = new HotelReservation();
		reservation.addHotel(input);
		reservation.addHotel(input);
		reservation.addHotel(input);
		Hotel hotel = new Hotel("LakeWood", 110, 90, 4, "Regular");
		ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
		hotelList.add(hotel);
		assertEquals(hotelList, reservation.findCheapestHotel(input));
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
		reservation.addHotel(input);
		reservation.addHotel(input);
		assertEquals(3, reservation.hotelList.size());
	}
	/**
	 * TestCase 5
	 */
	@Test
	public void whenHotelAdded_toReservationSystem_withRatings_shouldReturnSizeAs1() {
		Scanner input = new Scanner(System.in);
		HotelReservation reservation = new HotelReservation();
		reservation.addHotel(input);
		assertEquals(1,reservation.hotelList.size());
	}
	/**
	 * TestCase 6
	 */
	@Test	
	public void whenCalled_findCheapestHotelFunction_withGivenRating_shouldReturnCheapestHotel() {
		Scanner input = new Scanner(System.in);
		HotelReservation reservation = new HotelReservation();
		reservation.addHotel(input);
		reservation.addHotel(input);
		reservation.addHotel(input);
		Hotel hotel = new Hotel("LakeWood", 110, 90, 4, "Regular");
		ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
		hotelList.add(hotel);
		assertEquals(hotelList, reservation.findCheapestHotel(input));
	}
	/**
	 * Testcase 7
	 */
	@Test 
	public void whenCalled_findBestRatedHotelFunction_withGivenRating_shouldReturnBestRatedHotel() { 
		Scanner input = new Scanner(System.in); 
		HotelReservation reservation =new HotelReservation(); 
		reservation.addHotel(input);
		reservation.addHotel(input); 
		reservation.addHotel(input);
		Hotel hotel = new Hotel("RidgeWood", 220, 150, 5, "Regular"); 
		ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
		hotelList.add(hotel);
		assertTrue(reservation.findBestRatedHotels(input).equals(hotelList)); 
		}
	@Test
	public void whenthreeHotelAdded_toReservationSystem_shouldReturnSizeAs3() {
		Scanner input = new Scanner(System.in);
		HotelReservation reservation = new HotelReservation();
		reservation.addHotel(input);
		assertEquals(3, reservation.hotelList.size());
	}
}
