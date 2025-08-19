package com.thetestingacademy.modules;

import com.google.gson.Gson;
import com.thetestingacademy.pojos.*;

public class PayloadManager {
    Gson gson;

    public String createPayloadBookingAsString() {
        Booking booking = new Booking();
        booking.setFirstname("Suraj");
        booking.setLastname("Dubey");
        booking.setTotalprice(200);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2018-02-02");
        bookingdates.setCheckout("2020-02-02");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("lunch");
        System.out.println(booking);// this is a object


        gson = new Gson();
        String jsonStringpayload = gson.toJson(booking);
        System.out.println(jsonStringpayload);
        return jsonStringpayload;
    }

    public BookingResponse bookingResponseJava(String responseString) {
        gson = new Gson();
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }
    public Booking getResponseFromJSON(String getResponse){
        Booking booking = gson.fromJson(getResponse, Booking.class);
        return booking;
    }
    public String setAuthPayload(){
        Auth auth  = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        gson = new Gson();
        String jsonPayloadString = gson.toJson(auth);
        System.out.println("Payload set to the -> " + jsonPayloadString);
        return jsonPayloadString;
    }

    public String getTokenFromJSON(String tokenResponse){
        gson = new Gson();
        TokenResponse tokenResponse1 = gson.fromJson(tokenResponse, TokenResponse.class);
        return tokenResponse1.getToken();
    }

    public String fullUpdatePayloadString(){
        Booking booking = new Booking();
        booking.setFirstname("nidhi");
        booking.setLastname("ghorpade");
        booking.setTotalprice(240);
        booking.setDepositpaid(false);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2021-09-09");
        bookingdates.setCheckout("2023-09-09");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);
        gson = new Gson();
        String jsonStringpayload = gson.toJson(booking);
        System.out.println(jsonStringpayload);
        return gson.toJson(booking);
    }
}

