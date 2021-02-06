package com.example.demo.queries;

public class TripQueries {
    public final static String CREATE_SQL = "INSERT INTO trips(id, fromAddress, toAddress, driverId) values (?, ?, ?, ?)";
    public final static String GETALLTRIPSFORDRIVER_SQL = "SELECT t.id, t.fromAddress, t.toAddress, t.driverId\n" +
            "FROM model.trips t join model.drivers d\n" +
            "\ton t.driverId = d.id\n" +
            "WHERE t.driverId = ?;";
}
