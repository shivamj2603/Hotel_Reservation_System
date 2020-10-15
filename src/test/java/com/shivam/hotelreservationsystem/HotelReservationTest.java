package com.shivam.hotelreservationsystem;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HotelReservationTest {
//	@Test
//	public void whenHotelAdded_toReservationSystem_shouldReturnTrue() {
//		Scanner input = new Scanner(System.in);
//		HotelReservation reservation = new HotelReservation();
//		boolean result = reservation.addHotel(input);
//		assertTrue(result);
//	}
	@Test
	public void whenCalled_findCheapestHotelFunction_shouldReturnCheapestHotel() {
		Scanner input = new Scanner(System.in);
		HotelReservation reservation = new HotelReservation();
		reservation.addHotel(input);
		reservation.addHotel(input);
		Hotel hotel = new Hotel("LakeWood", 100 ,"Regular");
		boolean result = hotel.getRate() == reservation.findCheapestHotel(input).getRate() ? true : false;
		assertTrue(result);
		
	}
}
