package com.shivam.hotelreservationsystem;
import java.util.Scanner;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HotelReservationTest {
	/**
	 * TestCase 1
	 */
	@Test
	public void whenHotelAdded_toReservationSystem_shouldReturnTrue() {
		Scanner input = new Scanner(System.in);
		HotelReservation reservation = new HotelReservation();
		boolean result = reservation.addHotel(input);
		assertTrue(result);
	}
	/**
	 * TestCase 4
	 * TestCase 2 modified 
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
	public void whenHotelAdded_toReservationSystem_withRatings_shouldReturnTrue() {
		Scanner input = new Scanner(System.in);
		HotelReservation reservation = new HotelReservation();
		boolean result = reservation.addHotel(input);
		assertTrue(result);
	}	
}
